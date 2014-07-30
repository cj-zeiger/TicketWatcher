package com.zygr.ticketWatcher;

public class Ticket extends Object{
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
		private static final String whitespace_chars =  ""       /* dummy empty string for homogeneity */
                + "\\u0009" // CHARACTER TABULATION
                + "\\u000A" // LINE FEED (LF)
                + "\\u000B" // LINE TABULATION
                + "\\u000C" // FORM FEED (FF)
                + "\\u000D" // CARRIAGE RETURN (CR)
                + "\\u0020" // SPACE
                + "\\u0085" // NEXT LINE (NEL) 
                + "\\u00A0" // NO-BREAK SPACE
                + "\\u1680" // OGHAM SPACE MARK
                + "\\u180E" // MONGOLIAN VOWEL SEPARATOR
                + "\\u2000" // EN QUAD 
                + "\\u2001" // EM QUAD 
                + "\\u2002" // EN SPACE
                + "\\u2003" // EM SPACE
                + "\\u2004" // THREE-PER-EM SPACE
                + "\\u2005" // FOUR-PER-EM SPACE
                + "\\u2006" // SIX-PER-EM SPACE
                + "\\u2007" // FIGURE SPACE
                + "\\u2008" // PUNCTUATION SPACE
                + "\\u2009" // THIN SPACE
                + "\\u200A" // HAIR SPACE
                + "\\u2028" // LINE SEPARATOR
                + "\\u2029" // PARAGRAPH SEPARATOR
                + "\\u202F" // NARROW NO-BREAK SPACE
                + "\\u205F" // MEDIUM MATHEMATICAL SPACE
                + "\\u3000" // IDEOGRAPHIC SPACE
                ;        
				private final static String     whitespace_charclass = "["  + 
					whitespace_chars + "]";
	/**
	 * Setter method for each individual Ticket's data members.
	 * @param index - The static Enumeration mapped to a specific
	 * Ticket data memeber.
	 * @param pString - String to set the data member to.
	 */	
	public void setAll(int index, String pString){
		switch (index){
		case INDEX_TICKETNUMBER: setTicketNumber(removeAllWhiteSpace(pString));
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
		}
	}
	/**
	 * Retreive a String data memeber based on the given index.
	 * @param  index - The static int tied to the requested data
	 * member.	
	 * @return The string held in this Ticket's data member.
	 */
	public String getAll(int index){
		switch (index){
		case 0: return getTicketNumber();
		case 1: return getStatus();
		case 2: return getPriority();
		case 3: return getCustomer();
		case 4: return getAccount();
		case 5: return getSubject();
		case 6: return getOwner();
		case 7: return getGroup();
		case 8: return getEscalationTime();
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
	/**
	 * Returns the http URL of this ticket.
	 * @return URL String.
	 */
	public String getUrl(){
		return "http://tickets/tickets/viewticket.asp?id=" + ticketNumber.replaceAll("[^\\d.]", "");
	}
	/**
	 * Returns a String Array of size 11 that holds each of this Ticket's
	 * Data memebers. Tickets are Immutable, so this array object will never
	 * be out of date.
	 * @return - The String Array.
	 */
	public String[] info(){
		String[] infoString = new String[11];
		for (int x =0; x<11;x++){
			if(x!=6&&x!=7)
				infoString[x] = getAll(x);
		}
		
		return infoString;
	}
	
	/**
	 * Returns a formated description block of this ticket as a String
	 * for debugging purposes.
	 * @return - The formatted, multiline String.
	 */
	public String formatText(){
		
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
	/**
	 * Static method that takes a string and removes any and all
	 * white space anywhere in the string. Accounts for weird and
	 * obscure whitespace charecters.
	 * @param  s - Input String.
	 * @return - New String containing no whtiespace.
	 */
	public static final String removeAllWhiteSpace(String s){
		s = s.replaceAll(whitespace_charclass+"+$","");
		s = s.replaceAll("^"+whitespace_charclass+"+","");
		return s;
	}
	/**
	 * Same as removeAllWhiteSpace() but only targest whitespace leading
	 * and/or trailing the given String.
	 * @param  s - String with whitespace.
	 * @return - The new String without leading or trailing whitesapce.
	 */
	public static final String removeOutsideWhiteSpace(String s){
		if (!s.equals("")){
			String first = s.substring(0, 1);
			String last = s.substring(s.length()-1,s.length());
			first = first.replaceAll(whitespace_charclass+"+$","");
			first = first.replaceAll("^"+whitespace_charclass+"+","");
			last = last.replaceAll(whitespace_charclass+"+$","");
			last = last.replaceAll("^"+whitespace_charclass+"+","");
			
			String middle = s.substring(1,s.length()-1);
			return first+middle+last;
		} else {
			return s;
		}
	}
	
}
