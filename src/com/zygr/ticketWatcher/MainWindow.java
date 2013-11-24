package com.zygr.ticketWatcher;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.List;

public class MainWindow {

	protected Shell shlTicketwatcher;

	
	/**
	 * @wbp.parser.entryPoint
	 */

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlTicketwatcher.open();
		shlTicketwatcher.layout();
		while (!shlTicketwatcher.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlTicketwatcher = new Shell();
		shlTicketwatcher.setImage(null);
		shlTicketwatcher.setMinimumSize(new Point(1280, 720));
		shlTicketwatcher.setSize(450, 300);
		shlTicketwatcher.setText("TicketWatcher");
		shlTicketwatcher.setLayout(null);
		
		Menu menu = new Menu(shlTicketwatcher, SWT.BAR);
		shlTicketwatcher.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.NONE);
		mntmFile.setText("File");
		
		MenuItem mntmnd = new MenuItem(menu, SWT.NONE);
		mntmnd.setText("2nd");
		
		List list = new List(shlTicketwatcher, SWT.BORDER);
		list.setBounds(10, 10, 180, 642);
		for (Tickets ticket: Main.ticketHolder){
			if(ticket.getOwner()!=null)
				list.add(ticket.getOwner());
		}
		
	}
	
	
}
