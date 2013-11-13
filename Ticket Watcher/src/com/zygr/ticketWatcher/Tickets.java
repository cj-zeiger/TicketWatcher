package com.zygr.ticketWatcher;

public class Tickets extends Object{
		private String ticketNumber;
		private String ticketStatus;
		private String ticketType;
		private String ticketDescriptor;
		private String ticketIssue;
		private String ticketOwner;
		private String ticketEscalationTime;
		
		public final int INDEX_TICKETNUMBER = 0;
		public final int INDEX_STATUS = 1;
		public final int INDEX_TYPE = 2;
		public final int INDEX_DESCRIPTOR = 4;
		public final int INDEX_ISSUE = 5;
		public final int INDEX_OWNER = 8;
		public final int INDEX_ESCALATIONTIME = 10;
		
		
		
	//Constructor
		
	public Tickets(){
		
	}
	//Sets
	public void setAll(int index, String pString){
		switch (index){
		
		case INDEX_TICKETNUMBER: setTicketNumber(pString);
		break;
		case INDEX_STATUS: setStatus(pString);
		break;
		case INDEX_TYPE: setType(pString);
		break;
		case INDEX_DESCRIPTOR: setDescriptor(pString);
		break;
		case INDEX_ISSUE: setIssue(pString);
		break;
		case INDEX_OWNER: setOwner(pString);
		break;
		case INDEX_ESCALATIONTIME: setEscalationTime(pString);
		break;
		default: break;
		
		}
		
	}
	public void setTicketNumber(String pticketNumber){
		
		ticketNumber = pticketNumber;
		}
	public void setStatus(String pticketStatus){
		
		ticketStatus = pticketStatus;
		}
	public void setType(String pticketType){
		
		ticketType = pticketType;
		}
	public void setDescriptor(String pticketDescriptor){
	
		ticketDescriptor = pticketDescriptor;
		}
	public void setIssue(String pticketIssue){
	
		ticketIssue = pticketIssue;
		}
	public void setOwner(String pticketOwner){
	
		ticketOwner = pticketOwner;
		}
	public void setEscalationTime(String pticketEscalationTime){
		
		ticketEscalationTime = pticketEscalationTime;
		}
	
	//Gets
	public String getTicketNumber(){
		
		return ticketNumber;
	}
	public String  getStatus(){
	
		return ticketStatus;
	}
	public String  getType(){
	
		return ticketType;
	}
	public String  getDescriptor(){

		return ticketDescriptor;
	}
	public String  getIssue(){

		return ticketIssue;
	}
	public String  getOwner(){

		return ticketOwner;
	}
	public String  getEscalationTime(){
	
		return ticketEscalationTime;
	}
	public String  formatText(){
		String formatedInfo = ("Ticket Number: " + ticketNumber+
				"\nTicket Type: " + ticketType +
				"\nTicket Status: " + ticketStatus+
				"\nTicket Owner: "+ ticketOwner +"\n\n\n");
		
		
		return formatedInfo;
	}
	
}
