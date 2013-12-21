package com.zygr.ticketWatcher;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;

public class FilterDialog extends Dialog {

	protected Object result;
	protected Shell shlFilter;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public FilterDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlFilter.open();
		shlFilter.layout();
		Display display = getParent().getDisplay();
		while (!shlFilter.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlFilter = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlFilter.setMinimumSize(new Point(300, 200));
		shlFilter.setSize(450, 300);
		shlFilter.setText("Filter");
		
		Composite composite = new Composite(shlFilter, SWT.NONE);
		composite.setBounds(10, 10, 428, 251);

	}
}
