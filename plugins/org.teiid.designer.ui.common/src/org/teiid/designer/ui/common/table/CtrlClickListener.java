/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.ui.common.table;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.teiid.designer.ui.common.eventsupport.SelectionUtilities;

/**
 * CtrlClickListener is a hack to allow single-selection TableViewers to clear their selection upon Ctrl-click. It currently has a
 * bug: if you Ctrl-click on an un-selected row, it will clear the active selection and swallow the new selection. That needs to
 * get fixed.
 *
 * @since 8.0
 */
public class CtrlClickListener extends MouseAdapter implements SelectionListener {

    TableViewer tableViewer;
    private boolean clearNextSelection = false;

    /**
     * Construct an instance of CtrlClickListener.
     * 
     * @param the TableViewer that this instance is supposed to monitor.
     */
    public CtrlClickListener( TableViewer tableViewer ) {
        tableViewer.getTable().addMouseListener(this);
        tableViewer.getTable().addSelectionListener(this);
        this.tableViewer = tableViewer;
    }

    /**
     * @see org.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events.MouseEvent)
     */
    @Override
    public void mouseUp( MouseEvent e ) {
        if ((e.stateMask & SWT.CTRL) != 0) {
            clearNextSelection = true;
            Display.getDefault().asyncExec(new Runnable() {
                @Override
				public void run() {
                    if (SelectionUtilities.getSelectedObject(tableViewer.getSelection()) != null) {
                        tableViewer.setSelection(new StructuredSelection());
                    }
                }
            });
        }
    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
	public void widgetDefaultSelected( SelectionEvent e ) {
    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
	public void widgetSelected( SelectionEvent e ) {

        if (clearNextSelection) {
            clearNextSelection = false;
            Display.getCurrent().asyncExec(new Runnable() {
                @Override
				public void run() {
                    if (SelectionUtilities.getSelectedObject(tableViewer.getSelection()) != null) {
                        tableViewer.setSelection(new StructuredSelection());
                    }
                }
            });
        }

    }

    /**
     * Call this method to unhook this listener from the table.
     */
    public void dispose() {
        tableViewer.getTable().removeMouseListener(this);
        tableViewer.getTable().removeSelectionListener(this);
    }
}
