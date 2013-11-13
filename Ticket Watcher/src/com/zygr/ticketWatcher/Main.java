package com.zygr.ticketWatcher;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
	public final static String CJ_NAME = "carl.zeiger";	
	public static final String URL_NETCARRIER_TICKET = "http://tickets/tickets/view.asp";
	public static List<Tickets> ticketHolder = new ArrayList<Tickets>();
	
	public static void main(String[] args) {
		loadData();
	}
	private static void updateUi() {
		int numberOfTickets = ticketHolder.size();
		while (numberOfTickets > 0){
			System.out.println(ticketHolder.get(numberOfTickets-1).formatText());
			numberOfTickets--;
		}
	}
	@SuppressWarnings("null")
	private static void loadData() 
	{
		try {
			//Document tickets = Jsoup.connect(URL_NETCARRIER_TICKET).get();
			File input = new File("C:/nctickets.html");
			Document webPage = Jsoup.parse(input, "UTF-8");
			Elements rows = webPage.select("tr");
			for (Element element : rows){
				System.out.println(element.text() + "\n");
				Elements col = element.select("td");
				int colIndex = 0;
				Tickets holderTicket = new Tickets();
				for (Element collums : col){
					holderTicket.setAll(colIndex, collums.text());
					colIndex++;
				}
				ticketHolder.add(holderTicket);
			}
			} catch (IOException e) {
			}
		updateUi();
	}
	private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}
