/*******************************************************************************
 * Copyright (c) 2008, 2009 Oakland Software Incorporated and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oakland Software Incorporated - initial API and implementation
 *.....IBM Corporation - fixed dead code warning
 *******************************************************************************/
package org.eclipse.ui.tests.navigator;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.internal.navigator.NavigatorPlugin;
import org.eclipse.ui.tests.navigator.extension.TestResourceContentProvider;

public class SorterTest extends NavigatorTestBase {

	public SorterTest() {
		_navigatorInstanceId = TEST_VIEWER;
	}

	private IStatus _status;
	private int _statusCount;

	// bug 262707 CommonViewerSorter gets NPE when misconfigured
	public void testSorterMissing() throws Exception {

		TestResourceContentProvider._returnBadObject = true;

		_contentService.bindExtensions(new String[] { TEST_SORTER_CONTENT },
				false);
		_contentService.getActivationService().activateExtensions(
				new String[] { TEST_SORTER_CONTENT }, true);

		refreshViewer();

		ILogListener ll = new ILogListener() {
			public void logging(IStatus status, String plugin) {
				_status = status;
				_statusCount++;
			}
		};

		NavigatorPlugin.getDefault().getLog().addLogListener(ll);

		// Gets an NPE because the sorter can't find the object
		_viewer.expandAll();

		NavigatorPlugin.getDefault().getLog().removeLogListener(ll);

		// Can vary depending on if the test is run standalone or with something
		assertTrue("Status Count: " + _statusCount, _statusCount == 6 || _statusCount == 9);
		assertTrue(_status.getMessage().indexOf("Cannot find nav") > -1);
		assertTrue(_status.getMessage().indexOf("P/Test") > -1);
	}

	// bug 231855 [CommonNavigator] CommonViewerSorter does not support isSorterProperty method of ViewerComparator 
	public void testSorterProperty() throws Exception {

		_contentService.bindExtensions(new String[] { TEST_SORTER_CONTENT },
				false);
		_contentService.getActivationService().activateExtensions(
				new String[] { TEST_SORTER_CONTENT }, true);

		refreshViewer();

		// FIXME - this test does not actually hit the sorter property call,
		// need to figure out how to do that.
		_viewer.expandAll();

	}


}