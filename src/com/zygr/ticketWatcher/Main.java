package com.zygr.ticketWatcher;
public class Main {
	public static void main(String[] args) {
		TicketManager tm = new TicketManager();
		TicketListTable gui = new TicketListTable(tm);
		gui.open();
	}
}
