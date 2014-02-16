package com.zygr.ticketWatcher;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

public class FilterDialog extends Dialog {

	protected Object result;
	protected Shell shlFilter;
	protected Composite composite;
	private FilterResult fr;
	private ArrayList<String> owners;
	private ArrayList<String> groups;
	private ArrayList<String> statuss;
	private ArrayList<String> prios;
	private String ownerFilter;
	private String groupFilter;
	private String statusFilter;
	private String priorityFilter;
	private FilterResult existingFilterResult;
	
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public FilterDialog(Shell parent, int style, ArrayList<String> owners,  ArrayList<String> groups,FilterResult existingResult) {
		super(parent, style);
		statuss = new ArrayList<String>();
		prios = new ArrayList<String>();
		setText("Filter");
		
		this.owners = owners;
		this.groups = groups;
		
		this.owners.add(0, "");
		this.groups.add(0, "");
		statuss.add("");
		prios.add("");
		
		statuss.add("Waiting");
		statuss.add("In Process");
		statuss.add("Monitoring");
		
		prios.add("Major");
		prios.add("Moderate");
		prios.add("Informational");
		
		existingFilterResult = existingResult;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlFilter.open();
		shlFilter.layout();
		Display display = getParent().getDisplay();
		while (!shlFilter.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return fr;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlFilter = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlFilter.setSize(400, 235);
		shlFilter.setText("Filter");
		
		composite = new Composite(shlFilter, SWT.BORDER_SOLID);
		composite.setBounds(shlFilter.getClientArea());
	
		
		Button btnGo = new Button(composite, SWT.NONE);
		btnGo.setBounds((int)(composite.getClientArea().width*.5)-90, composite.getClientArea().height-39, 90, 29);
		btnGo.setText("Go");
		
		Button btnReset = new Button(composite, SWT.NONE);
		btnReset.setBounds((int)(composite.getClientArea().width*.5), composite.getClientArea().height-39, 90, 29);
		btnReset.setText("Clear");
		
		Label lowner = new Label(composite, SWT.NONE);
		lowner.setBounds(10, 10, 57, 17);
		lowner.setText("Owner:");
		
		final Combo cowner = new Combo(composite, SWT.NONE);
		cowner.setBounds(73, 10, 317, 29);
		for (String s : owners){
			cowner.add(s);
		}
		
		Label lstatus = new Label(composite, SWT.NONE);
		lstatus.setBounds(10, 45, 57, 17);
		lstatus.setText("Status:");
		
		final Combo cstatus = new Combo(composite, SWT.NONE);
		cstatus.setBounds(73, 45, 317, 29);
		for (String s : statuss){
			cstatus.add(s);
		}
		
		Label lpriority = new Label(composite, SWT.NONE);
		lpriority.setBounds(10, 80, 57, 17);
		lpriority.setText("Priority:");
		
		final Combo cpriority = new Combo(composite, SWT.NONE);
		cpriority.setBounds(73, 80, 317, 29);
		for (String s : prios){
			cpriority.add(s);
		}
		
		Label lgroup = new Label(composite, SWT.NONE);
		lgroup.setText("Group:");
		lgroup.setBounds(10, 115, 57, 17);
		
		final Combo cgroup = new Combo(composite, SWT.NONE);
		cgroup.setBounds(73, 115, 317, 29);
		for (String s : groups){
			cgroup.add(s);
		}
		if(existingFilterResult!=null){
			cowner.setText(existingFilterResult.owner);
			cstatus.setText(existingFilterResult.status);
			cpriority.setText(existingFilterResult.priority);
			cgroup.setText(existingFilterResult.group);
		}
		
		btnGo.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent t){
				//close and send data
				ownerFilter = cowner.getText();
				statusFilter = cstatus.getText();
				priorityFilter = cpriority.getText();
				groupFilter = cgroup.getText();
				fr = new FilterResult(groupFilter, statusFilter, priorityFilter, ownerFilter);
				composite.dispose();
				shlFilter.dispose();
			}
		});
		btnReset.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent t){
				fr = new FilterResult("","","","");
				composite.dispose();
				shlFilter.dispose();
			}
		});
		
	}
}
