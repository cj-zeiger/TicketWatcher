package com.zygr.ticketWatcher;

public class FilterResult {
	public String group;
	public String status;
	public String priority;
	public String owner;
	
	public FilterResult(String group, String status, String priority, String owner){
		this.group = group;
		this.status = status;
		this.priority = priority;
		this.owner = owner;
	}

}
