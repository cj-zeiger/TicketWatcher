package com.zygr.ticketWatcher;
public class Main {
	public static void main(String[] args) {
		TicketManager tm = new TicketManager();
		TicketListWindow gui = new TicketListWindow(tm);
		gui.open();
	}
}
