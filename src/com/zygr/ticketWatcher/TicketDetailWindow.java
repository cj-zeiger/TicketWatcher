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
	private TabItem rightClickedTableItem;
	private TicketManager mTm;
	private int height;
	private int widht;
	
	
	public TicketDetailWindow(Shell lw, Ticket initial, TicketManager tm){
		listWindow = lw;
		iniTicket = initial;
		mTm = tm;
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
					rightClickedTableItem = tabFolder.getItem(new Point(e.x,e.y));
				}
				if(e.button==2){
					Browser b = (Browser) tabFolder.getItem(new Point(e.x,e.y)).getControl();
					b.refresh();
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
				rightClickedTableItem.dispose();				
			}
		});
		
		MenuItem refresh = new MenuItem(rightMenu, SWT.CASCADE);
		refresh.setText("Refresh");
		refresh.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				Browser b = (Browser) rightClickedTableItem.getControl();
				b.refresh();
			}
		});
		
		MenuItem openCloud = new MenuItem(rightMenu,SWT.CASCADE);
		openCloud.setText("Open Cloud Account");
		openCloud.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				Ticket selectedTicket = (Ticket) rightClickedTableItem.getData();
				mTm.openCloudAccount(selectedTicket);
			}
		});
		
		tabFolder.setMenu(rightMenu);
		

	}
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
					if(tabFolder.getItemCount()==1){
						shell.dispose();
					}
				}
				
				
			});
		}
	}
	private boolean newTicket(Ticket t){
		String ticketNumber = t.getTicketNumber();
		TabItem[] tabItems = tabFolder.getItems();
		for (TabItem item: tabItems){
			if(item.getText() == ticketNumber){
				tabFolder.setSelection(item);
				return false;
			}
		}
		return true;
	}
	public boolean isInteractable(){
		if(shell!=null)
			if (!shell.isDisposed()&&shell.isVisible())
			return true;
		return false;
	}
}
