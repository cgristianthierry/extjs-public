/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.extjs.gxt.ui.client.aria;

import com.extjs.gxt.ui.client.event.PreviewEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class TabPanelHandler extends FocusHandler {

  @Override
  public boolean canHandleKeyPress(Component component, PreviewEvent pe) {
    // stop default handler
    if (component instanceof TabPanel || component instanceof TabItem) {
      return true;
    }
    return false;
  }

  @Override
  public void onTab(Component component, PreviewEvent pe) {
    if (component instanceof TabItem) {
      pe.preventDefault();

      if (pe.isShiftKey()) {
        focusPreviousWidget(component.getParent());
      } else {
        focusNextWidget(component.getParent());
      }
    } else if (component instanceof TabPanel) {
      pe.preventDefault();
      if (pe.isShiftKey()) {
        focusPreviousWidget(component);
      } else {
        focusNextWidget(component);
      }
    }
  }

  public void onEscape(Component component, PreviewEvent pe) {
    Widget p = component.getParent();
    if (p != null) {
      if (p instanceof TabItem) {
        stepOut(((TabItem) p).getTabPanel());
        ((TabItem) p).getTabPanel().focus();
      } else {
        stepOut(component);
      }
    }
  }

  @Override
  public void onEnter(Component component, PreviewEvent pe) {
    if (component instanceof TabPanel) {
      pe.preventDefault();
      TabPanel panel = (TabPanel) component;
      TabItem item = panel.getSelectedItem();
      if (item != null && item.getItemCount() > 0) {
        item.getItem(0).focus();
      }
    }
  }

}
