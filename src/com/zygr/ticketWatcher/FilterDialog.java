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
	private ArrayList<String> owners;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public FilterDialog(Shell parent, int style, ArrayList<String> names) {
		super(parent, style);
		setText("Filter");
		owners = names;
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
		return result;
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
		btnGo.setBounds((int)(composite.getClientArea().width*.5)-45, composite.getClientArea().height-39, 90, 29);
		btnGo.setText("Go");
		
		Label lowner = new Label(composite, SWT.NONE);
		lowner.setBounds(10, 10, 57, 17);
		lowner.setText("Owner:");
		
		Combo cowner = new Combo(composite, SWT.NONE);
		cowner.setBounds(73, 10, 317, 29);
		
		Label lstatus = new Label(composite, SWT.NONE);
		lstatus.setBounds(10, 45, 57, 17);
		lstatus.setText("Status:");
		
		Combo cstatus = new Combo(composite, SWT.NONE);
		cstatus.setBounds(73, 45, 317, 29);
		
		Label lpriority = new Label(composite, SWT.NONE);
		lpriority.setBounds(10, 80, 57, 17);
		lpriority.setText("Priority:");
		
		Combo cpriority = new Combo(composite, SWT.NONE);
		cpriority.setBounds(73, 80, 317, 29);
		
		Label lgroup = new Label(composite, SWT.NONE);
		lgroup.setText("Group:");
		lgroup.setBounds(10, 115, 57, 17);
		
		Combo cgroup = new Combo(composite, SWT.NONE);
		cgroup.setBounds(73, 115, 317, 29);
		
		btnGo.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent t){
				//close and send data
				
				composite.dispose();
				shlFilter.dispose();
			}
		});
		
	}
}
