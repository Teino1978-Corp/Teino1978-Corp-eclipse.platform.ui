package org.eclipse.ui.tests.dialogs;

import org.eclipse.jface.preference.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class EnableTestPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	private BooleanFieldEditor be;
	private ColorFieldEditor ce;
	private FontFieldEditor fe;
	private PathEditor pe;
	private RadioGroupFieldEditor rg;
	private StringFieldEditor se;
	
	private Composite beParent;
	private Composite ceParent;
	private Composite feParent;
	private Composite peParent;
	private Composite rgParent;
	private Composite seParent;

	private boolean enabledState = true;

	public EnableTestPreferencePage() {
		super(GRID);
	}

	public void flipState() {
		if (enabledState)
			enabledState = false;
		else
			enabledState = true;

		be.setEnabled(enabledState, beParent);
		ce.setEnabled(enabledState, ceParent);
		fe.setEnabled(enabledState, feParent);
		pe.setEnabled(enabledState, peParent);
		rg.setEnabled(enabledState, rgParent);
		se.setEnabled(enabledState, seParent);

	}

	/**
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	protected void createFieldEditors() {

		String[][] labelsAndValues =
			new String[][] { { "Label 1", "Value 1" }, {
				"Label 2", "Value 2" }
		};

		beParent = getFieldEditorParent();
		be =
			new BooleanFieldEditor(
				"BooleanValue",
				"BooleanTest",
				beParent);
		addField(be);
		
		ceParent = getFieldEditorParent();
		ce =
			new ColorFieldEditor(
				"ColorValue",
				"Color Test",
				ceParent);
		addField(ce);
		
		feParent = getFieldEditorParent();
		fe =
			new FontFieldEditor(
				"FontValue",
				"Font Test",
				feParent);
		addField(fe);
		
		peParent = getFieldEditorParent();
		pe =
			new PathEditor(
				"PathValue",
				"Path Test",
				"C:\temp",
				peParent);
		addField(pe);
		
		rgParent = getFieldEditorParent();
		rg =
			new RadioGroupFieldEditor(
				"Radio Value",
				"Radio Test",
				2,
				labelsAndValues,
				rgParent);
		addField(rg);
		
		seParent = getFieldEditorParent();
		se =
			new StringFieldEditor(
				"String Value",
				"String Editor",
				seParent);
		addField(se);

	}

	/* (non-Javadoc)
	* Method declared on PreferencePage.
	*/
	protected Control createContents(Composite parent) {
		Composite composite = (Composite) super.createContents(parent);
		Button enabledButton = new Button(parent, SWT.PUSH);
		enabledButton.setText("Switch Enabled State");
	
		enabledButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				flipState();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				flipState();
			}
		});
		return composite;
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}