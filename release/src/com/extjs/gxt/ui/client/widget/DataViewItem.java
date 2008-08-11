/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.extjs.gxt.ui.client.widget;

import java.util.Map;

import com.extjs.gxt.ui.client.event.ComponentEvent;

/**
 * A item in a <code>DataView</code>.
 */
public class DataViewItem extends Component {

  protected Map<String, Object> properties;
  
  public DataViewItem(Map<String, Object> properties) {
    this.properties = properties;
  }
  
  public Map<String, Object> getValues() {
    return properties;
  }

  @Override
  public void onComponentEvent(ComponentEvent ce) {
    super.onComponentEvent(ce);
    if (toolTip != null) {
      toolTip.handleEvent(ce);
    }
  }
  
}
