package com.zygr.ticketWatcher;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.eclipse.swt.*;
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
		TicketListWindow gui = new TicketListWindow();
		gui.open();
	}
	private static void loadData() 
	{
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
					ticketHolder.add(holderTicket);
			}
			} catch (IOException e) {
				System.out.println(e.toString());
			}
	}
}
