package com.zygr.ticketWatcher;


import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TicketListTable implements TicketUpdateListener {
	
	protected Shell mShell;
	private Table table;
	private String[] columnNames = {"Ticket #","Status","Priority","Customer #","Account #", "Subject","Owner","Group","Escalation"};
	private TicketManager mTm;
	private FilterResult mFr;
	private boolean openDiag = false;
	private TicketDetailWindow detailWindow;
	private TicketListTable mListTable = this;
	
	/**
	 * Creates this UI element by accepting a already created TicketManager.
	 * @param  tm - TicketManager associated with this instance of TicketWatcher.
	 */
	public TicketListTable(TicketManager tm){
		mTm = tm;
		tm.registerTicketUpdateListener(this);
		
	}
	/**
	 * Shows the main UI window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		mShell.open();
		mShell.layout();
		//mShell.pack();
		populateTickets();
		while (!mShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
	/**
	 * Creates child objects used to build the UI of this window.
	 */
	private void createContents(){
		mShell = new Shell();
		mShell.setText("TicketWatcher");
		mShell.setLayout(new FillLayout());
		mShell.setLocation(new Point(0, 0));
		
		createMenuBar();
		
		table = new Table(mShell, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		
		for (int i =0;i<columnNames.length;i++){
			TableColumn tableCol = new TableColumn(table,SWT.NONE);
			tableCol.setText(columnNames[i]);
			tableCol.setWidth(150);
			tableCol.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e){
					//add sorting
				}
			});
			
		}
		mShell.setSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT).x,220);
		table.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				TableItem[] items = table.getSelection();
				if(items.length > 0){
					if(detailWindow == null || !detailWindow.isInteractable()){
						detailWindow = new TicketDetailWindow(mShell,(Ticket)items[0].getData(),mTm, mListTable);
						detailWindow.open();
					} else{
						detailWindow.createNewTab((Ticket)items[0].getData());
					}
				}
			}
		});
	}
	/**
	 * Creates the objects for the top menu bar.
	 */
	private void createMenuBar(){
		Menu menuBar = new Menu(mShell, SWT.BAR);
		mShell.setMenuBar(menuBar);
		
		//filter
		MenuItem filterItem = new MenuItem(menuBar,SWT.CASCADE);
		filterItem.setText("Filter");
		filterItem.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				mTm.refreshTickets();
				if(!openDiag){
					openDiag = true;
					FilterDialog fd = new FilterDialog(mShell, SWT.NONE, mTm.getOwners(), mTm.getGroups(),mTm.getFilterResult());
					mFr = (FilterResult) fd.open();
					if (mFr != null){
					mTm.setFilterResult(mFr);
					}
					openDiag = false;
				}
				mTm.refreshTickets();
			}
		});
		
		//refresh
		MenuItem refreshItem = new MenuItem(menuBar, SWT.CASCADE);
		refreshItem.setText("Refresh");
		refreshItem.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				mTm.refreshTickets();
			}
		});
		
		
	}
	/**
	 * Checks to make sure shell is not disposed before pull most
	 * current tickets and updating the UI accordingly.
	 */
	public void refreshUi(){
		if(!mShell.isDisposed()&&!table.isDisposed())
			populateTickets();
	}
	/**
	 * Removes all children from table UI object. Then creates new 
	 * TableItems to populate the UI table. Loads Ticket objects as Data for each
	 * TableItem.
	 */
	private void populateTickets(){
		if(!mShell.isDisposed()){
			ArrayList<Ticket> tickets = mTm.getTickets();
			table.removeAll();
			for(Ticket eachTicket: tickets){
				TableItem item = new TableItem(table,SWT.NONE);
				item.setData(eachTicket);
				String[] data = new String[9];
				for (int i = 0; i<9;i++){
					data[i] = eachTicket.getAll(i);
				}
				item.setText(data);
			
			}
		}
	}
	@Override
	/**
	 * Called by TicketManager whenever tickets are updated.
	 */
	public void ticketsUpdated(ArrayList<Ticket> Tickets) {
		populateTickets();
	}
}
