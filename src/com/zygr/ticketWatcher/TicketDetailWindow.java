package com.zygr.ticketWatcher;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.SWT;

public class TicketDetailWindow {

	protected Shell shell;
	private Tickets ticket;

	
	public TicketDetailWindow(Tickets t){
		ticket = t;
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
		shell.setText(ticket.getTicketNumber());
		
		Browser browser = new Browser(shell, SWT.NONE);
		browser.setBounds(10, 10, 1240, 685);
		browser.setUrl("google.com");

	}
}
