package com.zygr.ticketWatcher;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Point;

public class TicketDetailWindow {

	protected Shell shell;
	protected TabFolder tabFolder;
	private Shell listWindow;
	
	
	public TicketDetailWindow(Shell lw){
		listWindow = lw;
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
		shell = new Shell(listWindow);
		shell.setSize(1050, 720);
		shell.setText("Ticket Viewer");
		shell.setLocation(new Point(210,0));
		
		tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 10, shell.getClientArea().width - 20, shell.getClientArea().height - 20);
		

	}
	public void createNewTab(Tickets t){
		if (tabFolder != null){
		TabItem ti = new TabItem(tabFolder, SWT.NONE);
		ti.setText(t.getTicketNumber());
		//ti.addListener(SWT.);
		
		Browser browser = new Browser(tabFolder, SWT.NONE);
		ti.setControl(browser);
		browser.setUrl("https://www.google.com/#q=" + t.getTicketNumber() + "&safe=off");
		}
	}
}
