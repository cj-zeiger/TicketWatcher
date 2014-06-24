package com.zygr.ticketWatcher;

import java.util.ArrayList;

public class TicketCountConsole {
	private TicketManager _tm;
	public static void main(String[] args) {
		new TicketCountConsole();
	}
	public TicketCountConsole(){
		_tm = new TicketManager();
		ArrayList<String> owners = _tm.getOwners();
		ArrayList<Ticket> tickets = _tm.getTickets();
		ArrayList<TicketOwner> counts = new ArrayList<TicketOwner>();
		
		for(String name: owners){
			TicketOwner newName = new TicketOwner(name);
			counts.add(newName);
		}
		
		for(TicketOwner towner :counts){
			String name = towner.getName();
			for(Ticket tk : tickets){
				if (name.equals(tk.getOwner())){
					towner.addCount();
				}
			}
		}
		print("Ticket Counts:");
		
		for(TicketOwner town: counts){
			print(town.getName() + " - " +  town.getCount() + " ticekts" );
		}
		
	}
	public void print(String print){
		System.out.println(print);
	}
	private class TicketOwner{
		private String mName;
		private int mCount;
		public TicketOwner(String name){
			mCount = 0;
			mName = name;
		}
		public int getCount(){
			return mCount;
		}
		public String getName(){
			return mName;
		}
		public void addCount(){
			mCount ++;
		}
	}
	

}
