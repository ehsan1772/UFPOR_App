package com.ufpor.app.client.view;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.ufpor.app.client.App;
import com.ufpor.app.client.eventbus.MenuEvent;

/**
 * Created by Ehsan Barekati on 11/8/14.
 */
public class MenuBuilder {

    public static MenuBar getMenu() {

        // Create a menu bar
        MenuBar menu = new MenuBar();
        menu.setAutoOpen(true);
        menu.setWidth("100px");
      //  menu.setAnimationEnabled(true);

        // Create the file menu
        MenuBar fileMenu = new MenuBar(true);
      //  fileMenu.setAnimationEnabled(true);

        fileMenu.addItem("New", new Command() {
            @Override
            public void execute() {
              //  showSelectedMenuItem("New");
                MenuEvent event = new MenuEvent();
             //  SimpleEventBus gg =  App.getInjector().getSimpleEventBus();
             App.getInjector().getSimpleEventBus().fireEvent(event);

            }
        });
        fileMenu.addItem("Open", new Command() {
            @Override
            public void execute() {
                showSelectedMenuItem("Open");
            }
        });
        fileMenu.addSeparator();
        fileMenu.addItem("Exit", new Command() {
            @Override
            public void execute() {
                showSelectedMenuItem("Exit");
            }
        });

        // Create the edit menu
        MenuBar editMenu = new MenuBar(true);
      //  editMenu.setAnimationEnabled(true);

        editMenu.addItem("Undo", new Command() {
            @Override
            public void execute() {
                showSelectedMenuItem("Undo");
            }
        });
        editMenu.addItem("Redo", new Command() {
            @Override
            public void execute() {
                showSelectedMenuItem("Redo");
            }
        });
        editMenu.addItem("Cut", new Command() {
            @Override
            public void execute() {
                showSelectedMenuItem("Cut");
            }
        });
        editMenu.addItem("Copy", new Command() {
            @Override
            public void execute() {
                showSelectedMenuItem("Copy");
            }
        });
        editMenu.addItem("Paste", new Command() {
            @Override
            public void execute() {
                showSelectedMenuItem("Paste");
            }
        });

        menu.addItem(new MenuItem("File", fileMenu));
        menu.addSeparator();
        menu.addItem(new MenuItem("Edit", editMenu));

        return menu;
    }

    private static void showSelectedMenuItem(String menuItemName){
        Window.alert("Menu item: " + menuItemName + " selected");
    }
}
