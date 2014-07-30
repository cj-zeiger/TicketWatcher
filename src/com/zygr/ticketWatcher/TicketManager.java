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
	private ArrayList<Ticket> mTickets;
	private static final String URL_NETCARRIER_TICKET = "http://tickets/tickets/view.asp";
	private FilterResult mFilterResult;
	private ArrayList<TicketUpdateListener> ticketClients;
	/**
	 * TicketManager's Constructor automatically loads tickets into its
	 * holder data member on construction.
	 */
	public TicketManager(){
		mTickets = loadData();
		ticketClients = new ArrayList<TicketUpdateListener>();
	}
	/**
	 * Saves given FilterResult into TicketManager's data member.
	 * @param fr - FilterResult to save.
	 */
	public void setFilterResult(FilterResult fr){
		mFilterResult = fr;
	}
	/**
	 * Returns reference to the ArrayList of Tickets that TicketManager 
	 * stores in a data member.
	 * @return - ArrayList of Tickets stored.
	 */
	public ArrayList<Ticket> getTickets(){
		return mTickets;
	}
	/**
	 * Complete Synchronization of Tickets. Loads new Ticket from webpage, filters them if available,
	 * sets data member to the updated list. 
	 * Also notifies TicketUpdateListener clients of the refresh.
	 */
	public void refreshTickets(){
		mTickets = filterTickets(loadData());
		for(TicketUpdateListener client: ticketClients){
			client.ticketsUpdated(mTickets);
		}
	}
	/**
	 * Attempts to connect to the Netcarrier Ticket webpage and download
	 * HTML Data. It then passes the Element Object containing the HTML text
	 * to parse the data into an ArrayList of newly created Ticket Objects.
	 * 
	 * @return ArrayList of Tickets generated.
	 */
	private ArrayList<Ticket> loadData() {
		ArrayList<Ticket> ticketArray = new ArrayList<Ticket>();
		try {
			//Document webPage = Jsoup.connect(URL_NETCARRIER_TICKET).get();
			File input = new File("nctickets.html");
			Document webPage = Jsoup.parse(input, "UTF-8");
			Elements allRows = webPage.select("tr");
			ticketArray = findTicketsFromHTML(allRows);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ticketArray;
		
	}
	/**
	 * Takes an Element Object and attempts to find all valid tickets 
	 * by iterating over nested Elements and checking if all relevant fields
	 * are valid Strings. 
	 *
	 * This does not remove the buggy whitespace that the Strings on the webpage
	 * adds to the end of various entries in the ticket table.
	 * @param  allRows - Element Object containing HTML of entire Ticket page.
	 * @return - ArrayList of created Tickets.
	 */
	
	//TODO - Add better validation for correct tickets. Specifically remove the
	//Beginning and trailing whitespace on tickets here.
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
	/**
	 * Creates a new empty ArrayList of tickets to store the filtered tickets.
	 * Iterates over passed ticket list and add ones that pass the filter to the new
	 * ArrayList. Finally returns filtered list of tickets.
	 */
	private ArrayList<Ticket> filterTickets(ArrayList<Ticket> tickets){
		if(mFilterResult!=null){
			ArrayList<Ticket> filteredTickets = new ArrayList<Ticket>();
			if (mFilterResult != null){
				for (Ticket tk : tickets){
					boolean cleanTicket = true;
					
					String cleanGroup = Ticket.removeOutsideWhiteSpace(tk.getGroup());
					String cleanFilterGroup = Ticket.removeOutsideWhiteSpace(mFilterResult.group);
					if (cleanFilterGroup != null && !cleanFilterGroup.equals("")){
						if (!cleanGroup.equals(cleanFilterGroup))
							cleanTicket = false;
					}
					String cleanPriority = Ticket.removeOutsideWhiteSpace(tk.getPriority());
					if (mFilterResult.priority != null && !mFilterResult.priority.equals("")){
						if (!cleanPriority.equals(mFilterResult.priority))
							cleanTicket = false;
					}
					if (mFilterResult.owner != null && !mFilterResult.owner.equals("")){
						if (!tk.getOwner().equals(mFilterResult.owner))
							cleanTicket = false;
					}
					String cleanStatus = Ticket.removeOutsideWhiteSpace(tk.getStatus());
					if (mFilterResult.status != null && !mFilterResult.status.equals("")){
						if (!cleanStatus.equals(mFilterResult.status))
							cleanTicket = false;
					}
					if(cleanTicket)
						filteredTickets.add(tk);
				}
			}
			return filteredTickets;
		} else {
			return tickets;
		}
	}
	@SuppressWarnings("unused")
	/**
	 * Attempts to start an instance of Could.exe with the provided ticket.
	 * Pulls the Customer and Account ID from the provided Ticket.
	 * @param ticket - Ticket to pull Customer and Account ID from.
	 */
	public void openCloudAccount(Ticket ticket){
		try {
			String account = Ticket.removeAllWhiteSpace(ticket.getAccount()).substring(0, 5).replaceAll("[^\\d.]", "");
			String customer = Ticket.removeAllWhiteSpace(ticket.getCustomer()).substring(0, 5).replaceAll("[^\\d.]", "");
			Process openCloud = new ProcessBuilder("N:\\Cloud.exe","-cid", "" + customer,"-aid",account).start();
			
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
	}
	/**
	 * Returns an ArrayList that contains a Name of everyone that currently
	 * holds a Ticket.
	 * @return - List of names.
	 */
	public ArrayList<String> getOwners(){
		ArrayList<String> names = new ArrayList<String>();
		for (Ticket ticket: mTickets){
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
	/**
	 * Returns an ArrayList that contains a group name of 
	 * every group that currently holds a Ticket.
	 * @return - List of groups.
	 */
	public ArrayList<String> getGroups(){
		ArrayList<String> groups = new ArrayList<String>();
		for (Ticket ticket: mTickets){
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
	/**
	 * @return - The FilterResult currently in use.
	 */
	public FilterResult getFilterResult(){
		return mFilterResult;
	}
	public void registerTicketUpdateListener(TicketUpdateListener client) {
		ticketClients.add(client);
	}
	
}
