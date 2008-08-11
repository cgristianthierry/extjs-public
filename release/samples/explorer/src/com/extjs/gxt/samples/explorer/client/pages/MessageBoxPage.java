/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.extjs.gxt.samples.explorer.client.pages;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.ProgressBar;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;

public class MessageBoxPage extends LayoutContainer implements EntryPoint {

  public void onModuleLoad() {
    RootPanel.get().add(this);
  }

  @Override
  protected void onRender(Element parent, int pos) {
    super.onRender(parent, pos);
    final Listener l = new Listener<ComponentEvent>() {
      public void handleEvent(ComponentEvent ce) {
        Dialog dialog = (Dialog) ce.component;
        Button btn = dialog.getButtonPressed();
        Info.display("MessageBox", "The '{0}' button was pressed", btn.getText());
      }
    };

    ButtonBar buttonBar = new ButtonBar();

    buttonBar.add(new Button("Confirm", new SelectionListener<ComponentEvent>() {
      public void componentSelected(ComponentEvent ce) {
        MessageBox.confirm("Confirm", "Are you sure you want to do that?", l);
      }
    }));

    buttonBar.add(new Button("Prompt", new SelectionListener<ComponentEvent>() {
      public void componentSelected(ComponentEvent ce) {
        MessageBox box = MessageBox.prompt("Name", "Please enter your name:");
        box.addCallback(l);
      }
    }));

    buttonBar.add(new Button("Multiline Prompt", new SelectionListener<ComponentEvent>() {
      public void componentSelected(ComponentEvent ce) {
        MessageBox box = MessageBox.prompt("Address", "Please enter your address:", true);
        box.addCallback(l);
      }
    }));

    buttonBar.add(new Button("Yes/No/Cancel", new SelectionListener<ComponentEvent>() {
      public void componentSelected(ComponentEvent ce) {
        MessageBox box = new MessageBox();
        box.setButtons(MessageBox.YESNOCANCEL);
        box.setIcon(MessageBox.QUESTION);
        box.setTitle("Save Changes?");
        box.addCallback(l);
        box.setMessage("You are closing a tab that has unsaved changes. Would you like to save your changes?");
        box.show();
      }
    }));

    buttonBar.add(new Button("Progress", new SelectionListener<ComponentEvent>() {
      public void componentSelected(ComponentEvent ce) {
        final MessageBox box = MessageBox.progress("Please wait", "Loading items...",
            "Initializing...");
        final ProgressBar bar = box.getProgressBar();
        final Timer t = new Timer() {
          float i;

          @Override
          public void run() {
            bar.updateProgress(i / 100, (int) i + "% Complete");
            i += 5;
            if (i > 105) {
              cancel();
              box.hide();
              Info.display("Message", "Items were loaded", "");
            }
          }
        };
        t.scheduleRepeating(500);
      }
    }));

    buttonBar.add(new Button("Wait", new SelectionListener<ComponentEvent>() {
      public void componentSelected(ComponentEvent ce) {
        final MessageBox box = MessageBox.wait("Progress",
            "Saving your data, please wait...", "Saving...");
        Timer t = new Timer() {
          @Override
          public void run() {
            Info.display("Message", "Your fake data was saved", "");
            box.hide();
          }
        };
        t.schedule(5000);
      }
    }));

    buttonBar.add(new Button("Alert", new SelectionListener<ComponentEvent>() {
      public void componentSelected(ComponentEvent ce) {
        MessageBox.alert("Alert", "Access Denied", l);
      }
    }));
    setLayout(new FlowLayout(4));
    add(buttonBar);
  }

}
