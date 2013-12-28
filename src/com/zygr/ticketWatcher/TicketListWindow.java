package com.zygr.ticketWatcher;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.browser.Browser;

public class TicketListWindow {

	protected Shell shlTicketwatcher;
	private TicketManager ticketmanage;
	private FilterResult fr = null;
	/**
	 * Open the window.
	 */
	public TicketListWindow(TicketManager t){
		ticketmanage = t;
		
	}
	public void open() {
		Display display = Display.getDefault();
		createContents();
		ticketmanage.setList(shlTicketwatcher);
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
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlTicketwatcher = new Shell();
		shlTicketwatcher.setSize(210, 720);
		shlTicketwatcher.setText("TicketWatcher");
		shlTicketwatcher.setLayout(null);
		shlTicketwatcher.setLocation(new Point(0, 0));
		
		Menu menu = new Menu(shlTicketwatcher, SWT.BAR);
		shlTicketwatcher.setMenuBar(menu);
		
		MenuItem filterItem = new MenuItem(menu, SWT.NONE);
		filterItem.setText("Filter");
		filterItem.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e){
				
				FilterDialog fd = new FilterDialog(shlTicketwatcher, SWT.NONE, ticketmanage.getOwners(), ticketmanage.getGroups());
				fr = (FilterResult) fd.open();
				updateList(fr);
				
			}
		});
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shlTicketwatcher, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 10, shlTicketwatcher.getClientArea().width - 15, shlTicketwatcher.getClientArea().height - 15);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Tree tree = new Tree(scrolledComposite, SWT.BORDER);
		scrolledComposite.setContent(tree);
		scrolledComposite.setMinSize(tree.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		ticketmanage.updateListUI(tree, fr);
		ticketmanage.addTreeSelection(tree);
		
		
		
	}
	private void updateList(FilterResult filterResult){
		
	}
}
