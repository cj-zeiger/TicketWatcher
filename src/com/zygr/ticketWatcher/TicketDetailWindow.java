package com.zygr.ticketWatcher;


import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.DeviceData;
import org.eclipse.swt.graphics.Point;

public class TicketDetailWindow {

	protected Shell shell;
	protected TabFolder tabFolder;
	private Shell listWindow;
	private Ticket iniTicket;
	private TabItem rightClickedTabItem;
	private TicketManager mTm;
	private int height;
	private int widht;
	private TicketListTable mListTable;
	
	/**
	 * Sets Data Members.
	 * @param  lw - Shell of the TicketListTable that created this detail
	 * window.
	 * @param  initial - Ticket assoicated with the event that created the
	 * need for the detial window.
	 * @param  tm - Global TicketManager associated with this instance of 
	 * TicketWatcher.
	 * @param  lt - TicketListTable Object itself.
	 * @return
	 */
	public TicketDetailWindow(Shell lw, Ticket initial, TicketManager tm,
			TicketListTable lt){
		listWindow = lw;
		iniTicket = initial;
		mTm = tm;
		mListTable = lt;
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		height = display.getClientArea().height;
		widht = display.getClientArea().width;
		createContents();
		shell.open();
		createNewTab(iniTicket);
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
		Shell parent = (Shell) shell.getParent();
		shell.setSize(parent.getSize().x,height - parent.getSize().y-30);
		shell.setText("Ticket Viewer");
		shell.setLocation(new Point(0,parent.getSize().y));
		
		
		tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 10, shell.getClientArea().width - 20, shell.getClientArea().height - 20);
		tabFolder.addMouseListener(new MouseListener(){
			public void mouseDown(MouseEvent e){
				if(e.button==3){
					rightClickedTabItem = tabFolder.getItem(new Point(e.x,e.y));
				}
				if(e.button==2){
					closeTab(tabFolder.getItem(new Point(e.x,e.y)));
				}
			}
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
			@Override
			public void mouseUp(MouseEvent e) {
			}
		});
		
		//Right Click Menu
		Menu rightMenu = new Menu(tabFolder);
		
		
		MenuItem close = new MenuItem(rightMenu, SWT.CASCADE);
		close.setText("Close");
		close.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				closeTab(rightClickedTabItem);
			}
		});
		
		MenuItem refresh = new MenuItem(rightMenu, SWT.CASCADE);
		refresh.setText("Refresh");
		refresh.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				Browser b = (Browser) rightClickedTabItem.getControl();
				b.refresh();
			}
		});
		
		MenuItem openCloud = new MenuItem(rightMenu,SWT.CASCADE);
		openCloud.setText("Open Cloud Account");
		openCloud.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				Ticket selectedTicket = (Ticket) rightClickedTabItem.getData();
				mTm.openCloudAccount(selectedTicket);
			}
		});
		tabFolder.setMenu(rightMenu);
	}
	/**
	 * First verifies the state of the this detail window and checks to
	 * make sure that a tab for thsi ticket is not already open.
	 * Then creates a new TabItem and sets up said Object.
	 * @param t - Ticket tab is created for.
	 */
	public void createNewTab(Ticket t){
		if (isInteractable() && newTicket(t)){
			TabItem ti = new TabItem(tabFolder, SWT.NONE);
			ti.setText(t.getTicketNumber());
			ti.setData(t);
			Browser browser = new Browser(tabFolder, SWT.NONE);
			ti.setControl(browser);
			browser.setUrl("http://tickets/tickets/viewticket.asp?id=" + t.getTicketNumber().replaceAll("[a-zA-Z]+", ""));
			ti.addDisposeListener(new DisposeListener(){
				@Override
				public void widgetDisposed(DisposeEvent e) {
					checkForLastTabClose();
				}
				
				
			});
			tabFolder.setSelection(ti);
		}
	}
	
	/**
	 * Called when TabItems are destroyed. If said destruction brings
	 * the tab count to 0, the entire detail window is disposed.
	 */
	private void checkForLastTabClose(){
		if(tabFolder.getItemCount()==1){
						shell.dispose();
					}
	}
	/**
	 * Checks to make sure a tab for the provided ticket doesn't already
	 * exist.
	 * @param  t  - Ticket to search for.
	 * @return - Result of find. True if it is actually a new Ticket.
	 */
	private boolean newTicket(Ticket t){
		String ticketNumber = t.getTicketNumber();
		TabItem[] tabItems = tabFolder.getItems();
		for (TabItem item: tabItems){
			if(item.getText().equals(ticketNumber)){
				tabFolder.setSelection(item);
				return false;
			}
		}
		return true;
	}
	/**
	 * Disposes of passed TabItem, refreshes the UI, and updates the 
	 * List of current Valid ticket from the the ticket webpage.
	 * @param t - Tab Item to close.
	 */
	private void closeTab(TabItem t){
		if (t!=null&&!t.isDisposed()){
			t.dispose();
			mTm.refresh();
			mTm.refreshFilteredTickets();
			mListTable.refreshUi();
		}
	}
	/**
	 * Check to make sure the detail window is interactable.
	 * @return - Result of the check.
	 */
	public boolean isInteractable(){
		if(shell!=null)
			if (!shell.isDisposed()&&shell.isVisible())
			return true;
		return false;
	}
}
