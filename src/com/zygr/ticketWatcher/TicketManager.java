package com.zygr.ticketWatcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TicketManager {
	private TicketDetailWindow tw;
	private Shell ticketListWindow;
	private ArrayList<Tickets> tickets;
	private static final String URL_NETCARRIER_TICKET = "http://tickets/tickets/view.asp";
	private Tree t;
	public TicketManager(){
		loadData();
	}
	
	public void setList(Shell tlw){
		if (tlw!=null)
			ticketListWindow = tlw;
	}
	public void setTree(Tree tree){
		t = tree;
	}
	private void loadData() 
	{
		tickets = new ArrayList<Tickets>();
		try {
			
			//Document tickets = Jsoup.connect(URL_NETCARRIER_TICKET).get();
			File input = new File("nctickets.html");
			Document webPage = Jsoup.parse(input, "UTF-8");
			Elements rows = webPage.select("tr");
			for (Element element : rows){
				Elements col = element.select("td");
				int colIndex = 0;
				Tickets holderTicket = new Tickets();
				for (Element collums : col){
					holderTicket.setAll(colIndex, collums.text());
					colIndex++;
				}
				boolean clean = true;
				for (int x = 0; x<11;x++){
					if (holderTicket.getAll(x)==null || holderTicket.getAll(x).isEmpty())
						clean = false;
				}
				if (clean)
					tickets.add(holderTicket);
			}
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		
	}
	public void updateListUI(){
		
		//Removed old tree items
		t.removeAll();
		//Adding Tickets as Tree Items
		for (Tickets ticket: tickets){
			TreeItem ticketItem = new TreeItem(t, SWT.DEFAULT);
			ticketItem.setText(ticket.getAll(Tickets.INDEX_TICKETNUMBER) + " - " + ticket.getAll(Tickets.INDEX_ESCALATIONTIME));
			ticketItem.setData("ticket", ticket);
			for (int x = 0; x < 11; x++){
				if (x!=6&&x!=7){
					TreeItem sub = new TreeItem(ticketItem, SWT.DEFAULT);
					sub.setText(ticket.info()[x]);
					sub.setData(Integer.toString(x), ticket.info()[x]);
					
				}
				
			}
		}
	}
	public void newTickets(FilterResult filterResult){
		ArrayList<Tickets> newTickets = new ArrayList<Tickets>();
		for (Tickets tk : tickets){
			boolean cleanTicket = true;
			//Checks Group, sets cleanTicket to false if the ticket does not match the FilterResult
			if (filterResult.group != null && !filterResult.group.equals("")){
				if (!tk.getGroup().equals(filterResult.group))
					cleanTicket = false;
			}
			//Checks Group, sets cleanTicket to false if the ticket does not match the FilterResult
			if (filterResult.priority != null && !filterResult.priority.equals("")){
				if (!tk.getPriority().equals(filterResult.priority))
					cleanTicket = false;
			}
			//Checks Group, sets cleanTicket to false if the ticket does not match the FilterResult
			if (filterResult.owner != null && !filterResult.owner.equals("")){
				if (!tk.getOwner().equals(filterResult.owner))
					cleanTicket = false;
			}
			//Checks Group, sets cleanTicket to false if the ticket does not match the FilterResult
			if (filterResult.status != null && !filterResult.status.equals("")){
				if (!tk.getStatus().equals(filterResult.status))
					cleanTicket = false;
			}
			if(cleanTicket)
				newTickets.add(tk);
			
		}
		tickets = newTickets;
		
	}
	public void addTreeSelection(){
		//Selection Listeners
		t.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e){
				TreeItem ti = (TreeItem) e.item;
				if (!e.widget.isDisposed()){
					if (ti.getData(Integer.toString(Tickets.INDEX_CUSTOMER))!= null){
						String customerNumber = (String) ti.getData(Integer.toString(Tickets.INDEX_CUSTOMER));
						customerNumber = customerNumber.replaceAll("[^\\d.]", "");
						int number = Integer.parseInt(customerNumber);
						openCloudAccount(number);
					}
					if(ti.getData("ticket")!=null){
						if (tw==null){
							tw = new TicketDetailWindow(ticketListWindow);
							tw.open();
						}
						if (tw!=null)
							tw.createNewTab((Tickets) ti.getData("ticket"));
					
					}
			}
			}
		});
	}
	private void openCloudAccount(int customerNumber){
		try {
			Process openCloud = new ProcessBuilder("\\\\ncdomain\\clouddfs\\Cloudapp", "-"+customerNumber).start();
			
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
	}
	public ArrayList<String> getOwners(){
		ArrayList<String> names = new ArrayList<String>();
		for (Tickets ticket: tickets){
			boolean unique = true;
			String name = ticket.getOwner();
			for (int x = 0; x < names.size(); x++){
				if (name.equalsIgnoreCase(names.get(x))){
					unique = false;
				}
			}
			if (unique)
				names.add(name);
		}
		return names;
	}
	public ArrayList<String> getGroups(){
		ArrayList<String> groups = new ArrayList<String>();
		for (Tickets ticket: tickets){
			boolean unique = true;
			String name = ticket.getGroup();
			for (int x = 0; x < groups.size(); x++){
				if (name.equalsIgnoreCase(groups.get(x))){
					unique = false;
				}
			}
			if (unique)
				groups.add(name);
		}
		return groups;
	}
	
}
