package com.zygr.ticketWatcher;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;

public class TicketDetailWindow {

	protected Shell shell;
	protected TabFolder tabFolder;
	
	
	public TicketDetailWindow(){
		
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(1280, 720);
		shell.setText("Ticket Viewer");
		
		tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 10, 1244, 662);
		

	}
	public void createNewTab(Tickets t){
		TabItem ti = new TabItem(tabFolder, SWT.NONE);
		ti.setText(t.getTicketNumber());
		
		Browser browser = new Browser(tabFolder, SWT.NONE);
		ti.setControl(browser);
		browser.setUrl("https://www.google.com/#q=" + t.getTicketNumber() + "&safe=off");
	}
}
