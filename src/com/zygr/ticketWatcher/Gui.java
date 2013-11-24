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

public class Gui {

	protected Shell shell;
	public Tree tree;

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
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setMinimumSize(new Point(1280, 720));
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		mntmNewItem.setText("Filter");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(10, 10, 249, 642);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setBounds(0, 0, 249, 642);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		tree = new Tree(scrolledComposite, SWT.BORDER);
		scrolledComposite.setContent(tree);
		scrolledComposite.setMinSize(tree.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Browser browser = new Browser(shell, SWT.NONE);
		browser.setBounds(265, 10, 989, 642);
		browser.setUrl("https://www.c)
		for (Tickets ticket: Main.ticketHolder){
			TreeItem test = new TreeItem(tree, SWT.DEFAULT);
			test.setText(ticket.getAll(Tickets.INDEX_TICKETNUMBER));
			for (int x = 0; x < 11; x++){
				if (x!=6&&x!=7){
					TreeItem sub = new TreeItem(test, SWT.DEFAULT);
					sub.setText(ticket.info()[x]);
				}
			}
		}
	}
}
