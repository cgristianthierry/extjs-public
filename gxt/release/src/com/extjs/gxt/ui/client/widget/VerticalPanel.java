/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.extjs.gxt.ui.client.widget;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.Element;

/**
 * A {@link Container} that lays out its children in a single column using a
 * {@link TableLayout}. Each child widget can control its cell's properties
 * using an associated {@link TableData} instance.
 */
public class VerticalPanel extends Container {

  private String tableWidth;
  private String tableHeight;
  private int spacing = 0;
  private HorizontalAlignment horizontalAlign = HorizontalAlignment.LEFT;
  private VerticalAlignment verticalAlign = VerticalAlignment.TOP;
  private TableLayout layout;

  /**
   * Creates a new vertical panel.
   */
  public VerticalPanel() {
    setMonitorResize(false);
  }

  /**
   * Returns the horizontal alignment.
   * 
   * @return the horizontalAlign
   */
  public HorizontalAlignment getHorizontalAlign() {
    return horizontalAlign;
  }

  public int getSpacing() {
    return spacing;
  }

  /**
   * Returns the table's height.
   * 
   * @return the table height
   */
  public String getTableHeight() {
    return tableHeight;
  }

  /**
   * @return the tableWidth
   */
  public String getTableWidth() {
    return tableWidth;
  }

  /**
   * Returns the vertical alignment.
   * 
   * @return the vertical alignment
   */
  public VerticalAlignment getVerticalAlign() {
    return verticalAlign;
  }
  
  /**
   * Sets the horizontal cell alignment (defaults to LEFT).
   * 
   * @param horizontalAlign the horizontal alignment
   */
  public void setHorizontalAlign(HorizontalAlignment horizontalAlign) {
    this.horizontalAlign = horizontalAlign;
  }

  /**
   * Sets the panel's spacing.
   * 
   * @param spacing the spacing
   */
  public void setSpacing(int spacing) {
    this.spacing = spacing;
    if (layout != null) {
      layout.setCellSpacing(spacing);
    }
  }

  /**
   * Sets the table's height.
   * 
   * @param tableHeight the table height
   */
  public void setTableHeight(String tableHeight) {
    this.tableHeight = tableHeight;
  }

  /**
   * Sets the width of the internal table.
   * 
   * @param tableWidth the table width
   */
  public void setTableWidth(String tableWidth) {
    this.tableWidth = tableWidth;
  }

  /**
   * Sets the vertical cell alignment (defaults to TOP).
   */
  public void setVerticalAlign(VerticalAlignment verticalAlign) {
    this.verticalAlign = verticalAlign;
  }

  @Override
  protected void onRender(Element parent, int pos) {
    super.onRender(parent, pos);
    setStyleAttribute("overflow", "visible");
    layout = new TableLayout(1);
    layout.horizontalAlign = getHorizontalAlign();
    layout.verticalAlign = getVerticalAlign();
    layout.cellSpacing = getSpacing();
    layout.cellPadding = 0;
    layout.width = tableWidth;
    layout.height = tableHeight;
    setLayout(layout);
    layout();
  }
}
