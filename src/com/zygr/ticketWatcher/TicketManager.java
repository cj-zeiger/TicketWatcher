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
	private ArrayList<Ticket> tickets;
	private static final String URL_NETCARRIER_TICKET = "http://tickets/tickets/view.asp";
	private FilterResult mFilterResult;
	public TicketManager(){
		tickets = loadData();
	}
	
	public void setFilterResult(FilterResult fr){
		mFilterResult = fr;
	}
	public ArrayList<Ticket> getTickets(){
		return tickets;
	}
	public ArrayList<Ticket> loadData() {
		ArrayList<Ticket> ticketArray = new ArrayList<Ticket>();
		try {
			Document webPage = Jsoup.connect(URL_NETCARRIER_TICKET).get();
			//File input = new File("nctickets.html");
			//Document webPage = Jsoup.parse(input, "UTF-8");
			Elements allRows = webPage.select("tr");
			ticketArray = findTicketsFromHTML(allRows);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ticketArray;
		
	}
	private ArrayList<Ticket> findTicketsFromHTML(Elements allRows){
		ArrayList<Ticket> ticketArray = new ArrayList<Ticket>();
		for (Element singleRow: allRows){
			Ticket holdingTicket = new Ticket();
			boolean cleanTicket = true;
			Elements singleCollum = singleRow.select("td");
			int index = 0;
			for (Element singleCell: singleCollum){
				String cellText = singleCell.text();
				holdingTicket.setAll(index, cellText);
				if (cellText.equals("")||cellText.isEmpty()||cellText==null){
					cleanTicket = false;
					break;
				}
				index++;
			}
			if (cleanTicket && index >=11){
				ticketArray.add(holdingTicket);
			}
		}
		return ticketArray;
	}
	
	//Loads new tickets from web source, checks if there are filter results available and filters, finally sets object tickst to the result.
	public void refreshFilteredTickets(){
		if(mFilterResult!=null){
			ArrayList<Ticket> allTickets = loadData();
			ArrayList<Ticket> filteredTickets = new ArrayList<Ticket>();
			if (mFilterResult != null){
				for (Ticket tk : allTickets){
					boolean cleanTicket = true;
					if (mFilterResult.group != null && !mFilterResult.group.equals("")){
						if (!tk.getGroup().equals(mFilterResult.group))
							cleanTicket = false;
					}
					if (mFilterResult.priority != null && !mFilterResult.priority.equals("")){
						if (!tk.getPriority().equals(mFilterResult.priority))
							cleanTicket = false;
					}
					if (mFilterResult.owner != null && !mFilterResult.owner.equals("")){
						if (!tk.getOwner().equals(mFilterResult.owner))
							cleanTicket = false;
					}
					if (mFilterResult.status != null && !mFilterResult.status.equals("")){
						if (!tk.getStatus().equals(mFilterResult.status))
							cleanTicket = false;
					}
					if(cleanTicket)
						filteredTickets.add(tk);
				}
			}
			tickets = filteredTickets;
		}
	}
	/**
	public void addTreeSelection(){
		//Selection Listeners
		t.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e){
				TreeItem ti = (TreeItem) e.item;
				if (!e.widget.isDisposed()){
					if (ti.getData(""+Ticket.INDEX_CUSTOMER) != null || ti.getData(""+Ticket.INDEX_ACCOUNT) != null){
						Object test1 = ti.getData();
						Object test2 = ti.getParent();
						Ticket ticket = (Ticket) ti.getData("ticket");
						String customerNumber = ticket.getCustomer();
						String accountNumber = ticket.getAccount();
						System.out.println("cust: " + customerNumber + " account: " + accountNumber);
						openCloudAccount(Integer.parseInt(customerNumber), Integer.parseInt(accountNumber));
					}
					if(ti.getData("ticket")!=null){
						if (tw==null || tw.isDisposed()){
							tw = new TicketDetailWindow(ticketListWindow);
							tw.open();
							
						}
						if (tw!=null && !tw.isDisposed())
							tw.createNewTab((Ticket) ti.getData("ticket"));
					
					}
			}
			}
		});
	}
	**/
	private void openCloudAccount(int customerNumber, int accountNumber){
		try {
			System.out.println(customerNumber);
			Process openCloud = new ProcessBuilder("N:\\Cloud.exe","-aid", "" + customerNumber).start();
			
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
	}
	public ArrayList<String> getOwners(){
		ArrayList<String> names = new ArrayList<String>();
		for (Ticket ticket: tickets){
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
		for (Ticket ticket: tickets){
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
	public void refresh(){
		tickets = loadData();
	}
	public FilterResult getFilterResult(){
		return mFilterResult;
	}
	
}
