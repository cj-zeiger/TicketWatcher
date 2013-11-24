package com.zygr.ticketWatcher;

public class Tickets extends Object{
		private String ticketNumber;
		private String ticketStatus;
		private String ticketPriority;
		private String ticketCustomer;
		private String accountNumber;
		private String ticketSubject;
		private String ticketOwner;
		private String ticketGroup;
		private String ticketEscalationTime;
		
		public final static int INDEX_TICKETNUMBER = 0;
		public final static int INDEX_STATUS = 1;
		public final static int INDEX_PRIORITY = 2;
		public final static int INDEX_CUSTOMER = 3;
		public final static int INDEX_ACCOUNT = 4;
		public final static int INDEX_SUBJECT = 5;
		public final static int INDEX_OWNER = 8;
		public final static int INDEX_GROUP = 9;
		public final static int INDEX_ESCALATIONTIME = 10;
		
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
		case INDEX_PRIORITY: setPriority(pString);
		break;
		case INDEX_CUSTOMER: setCustomer(pString);
		break;
		case INDEX_ACCOUNT: setAccount(pString);
		break;
		case INDEX_SUBJECT: setSubject(pString);
		break;
		case INDEX_OWNER: setOwner(pString);
		break;
		case INDEX_GROUP: setGroup(pString);
		break;
		case INDEX_ESCALATIONTIME: setEscalationTime(pString);
		break;
		default:
			break;
		}
	}
	public String getAll(int index){
		switch (index){
		case INDEX_TICKETNUMBER: return getTicketNumber();
		case INDEX_STATUS: return getStatus();
		case INDEX_PRIORITY: return getPriority();
		case INDEX_CUSTOMER: return getCustomer();
		case INDEX_ACCOUNT: return getAccount();
		case INDEX_SUBJECT: return getSubject();
		case INDEX_OWNER: return getOwner();
		case INDEX_GROUP: return getGroup();
		case INDEX_ESCALATIONTIME: return getEscalationTime();
		default: return "blank string";
		}
	}
	public void setTicketNumber(String pticketNumber){
		
		ticketNumber = pticketNumber;
		}
	public void setStatus(String pticketStatus){
		
		ticketStatus = pticketStatus;
		}
	public void setPriority(String pticketPriority){
		
		ticketPriority = pticketPriority;
		}
	public void setCustomer(String pticketCustomer){
		
		ticketCustomer = pticketCustomer;
		}
	public void setAccount(String paccountNumber){
	
		accountNumber = paccountNumber;
		}
	public void setSubject(String pticketSubject){
	
		ticketSubject = pticketSubject;
		}
	public void setOwner(String pticketOwner){
	
		ticketOwner = pticketOwner;
	}
	public void setGroup(String pticketGroup){
		
		ticketGroup = pticketGroup;
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
	public String  getPriority(){
	
		return ticketPriority;
	}
	public String  getCustomer(){
		
		return ticketCustomer;
	}
	public String  getAccount(){

		return accountNumber;
	}
	public String  getSubject(){

		return ticketSubject;
	}
	public String  getOwner(){

		return ticketOwner;
	}
	public String  getGroup(){

		return ticketGroup;
	}
	public String  getEscalationTime(){
	
		return ticketEscalationTime;
	}
	public String[] info(){
		String[] infoString = new String[11];
		for (int x =0; x<11;x++){
			if(x!=6&&x!=7)
				infoString[x] = getAll(x);
		}
		
		return infoString;
	}
	public String  formatText(){
		
		String formatedInfo = ("Ticket Number: " + ticketNumber+
				"\nTicket Status: " + ticketStatus+
				"\nTicket Priority: " + ticketPriority +
				"\nCustomer: " + ticketCustomer +
				"\nAccount Number: " + accountNumber+
				"\nTickt Subject: " + ticketSubject+
				"\nTicket Owner: "+ ticketOwner +
				"\nTicket Group: " + ticketGroup +
				"\nEscilation Time: " + ticketEscalationTime+
				"\n\n\n");
		
		
		return formatedInfo;
	}
	
}
