package com.zygr.ticketWatcher;

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

	/**
	 * Launch the application.
	 * @param args
	 */
	//public static void main(String[] args) {
	//	try {
	//		Gui window = new Gui();
	//		window.open();
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//	}
	//}

	/**
	 * Open the window.
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
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlTicketwatcher = new Shell();
		shlTicketwatcher.setSize(450, 700);
		shlTicketwatcher.setText("TicketWatcher");
		shlTicketwatcher.setLayout(null);
		
		Menu menu = new Menu(shlTicketwatcher, SWT.BAR);
		shlTicketwatcher.setMenuBar(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("Filter");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shlTicketwatcher, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 10, 430, 655);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Tree tree = new Tree(scrolledComposite, SWT.BORDER);
		scrolledComposite.setContent(tree);
		scrolledComposite.setMinSize(tree.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		TicketManager tm = new TicketManager(tree);
		
		
	}
}
