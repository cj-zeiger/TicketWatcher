package com.zygr.ticketWatcher;

import java.util.ArrayList;

public interface TicketUpdateListener {
	public void ticketsUpdated(ArrayList<Ticket> Tickets);
}
