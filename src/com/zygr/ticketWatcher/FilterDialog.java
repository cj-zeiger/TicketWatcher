package com.zygr.ticketWatcher;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

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
		setText("Filter");
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
		shlFilter.setSize(400, 300);
		shlFilter.setText("Filter");
		
		Composite composite = new Composite(shlFilter, SWT.NONE);
		composite.setBounds(10, 10, 380, 250);
		
		Button btnOwner = new Button(composite, SWT.RADIO);
		btnOwner.setBounds(10, 33, 70, 24);
		btnOwner.setText("Owner");
		
		Button btnEscilation = new Button(composite, SWT.RADIO);
		btnEscilation.setBounds(86, 33, 91, 24);
		btnEscilation.setText("Escilation");
		
		Button btnStatus = new Button(composite, SWT.RADIO);
		btnStatus.setBounds(183, 33, 70, 24);
		btnStatus.setText("Status");
		
		Label lblFilterBy = new Label(composite, SWT.NONE);
		lblFilterBy.setBounds(10, 10, 70, 17);
		lblFilterBy.setText("Filter By:");

	}
}
