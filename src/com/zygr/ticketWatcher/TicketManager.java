package com.zygr.ticketWatcher;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class TicketManager {
	private Tree masterTree;
	private TicketDetailWindow tw;
	
	public TicketManager(Tree t){
		masterTree = t;
		addTickets(masterTree);
		addSelection(masterTree);
	}
	private void addSelection(Tree t){
		t.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e){
				TreeItem ti = (TreeItem) e.item;
				if (ti.getData(Integer.toString(Tickets.INDEX_CUSTOMER))!= null){
					String customerNumber = (String) ti.getData(Integer.toString(Tickets.INDEX_CUSTOMER));
					customerNumber = customerNumber.replaceAll("[^\\d.]", "");
					int number = Integer.parseInt(customerNumber);
					openCloudAccount(number);
				}
				if(ti.getData("ticket")!=null){
					if (tw==null){
					tw = new TicketDetailWindow();
					tw.open();
					}
					tw.createNewTab((Tickets) ti.getData("ticket"));
					
				}
			}
		});
	}
	private void addTickets(Tree t){
		for (Tickets ticket: Main.ticketHolder){
			TreeItem ticketItem = new TreeItem(t, SWT.DEFAULT);
			ticketItem.setText(ticket.getAll(Tickets.INDEX_TICKETNUMBER));
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
	private void openCloudAccount(int customerNumber){
		try {
			Process openCloud = new ProcessBuilder("\\\\ncdomain\\clouddfs\\Cloudapp", "-"+customerNumber).start();
			
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
	}
}
