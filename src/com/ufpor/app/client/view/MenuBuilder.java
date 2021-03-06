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
        UfporMenu menu = new UfporMenu();
        menu.setAutoOpen(true);
        menu.setWidth("100px");
        //  menu.setAnimationEnabled(true);

        // Create the file menu
        MenuBar fileMenu = new MenuBar(true);
        //  fileMenu.setAnimationEnabled(true);

        MenuItem newMenue = new MenuItem("New", new Command() {
            @Override
            public void execute() {
                MenuEvent event = new MenuEvent(MenuEvent.Event.NEW);
                App.getInjector().getSimpleEventBus().fireEvent(event);

            }
        });

        fileMenu.addItem(newMenue);
        menu.setNewProject(newMenue);
        fileMenu.addItem("Open", new Command() {
            @Override
            public void execute() {
                MenuEvent event = new MenuEvent(MenuEvent.Event.OPEN);
                App.getInjector().getSimpleEventBus().fireEvent(event);
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


        // Create the insert menu
        MenuBar insertMenu = new MenuBar(true);
        //  editMenu.setAnimationEnabled(true);

        insertMenu.addItem("Undo", new Command() {
            @Override
            public void execute() {
                showSelectedMenuItem("Undo");
            }
        });
        insertMenu.addItem("Redo", new Command() {
            @Override
            public void execute() {
                showSelectedMenuItem("Redo");
            }
        });
        insertMenu.addItem("Cut", new Command() {
            @Override
            public void execute() {
                showSelectedMenuItem("Cut");
            }
        });
        insertMenu.addItem("Copy", new Command() {
            @Override
            public void execute() {
                showSelectedMenuItem("Copy");
            }
        });
        insertMenu.addItem("Paste", new Command() {
            @Override
            public void execute() {
                showSelectedMenuItem("Paste");
            }
        });

        MenuItem file = new MenuItem("File", fileMenu);
        menu.addItem(file);

        menu.setFile(file);
        menu.addSeparator();
        menu.addItem(new MenuItem("Edit", editMenu));
        menu.addSeparator();
        menu.addItem(new MenuItem("Insert", insertMenu));
        menu.addSeparator();
        menu.addItem(new MenuItem("Format", editMenu));
        menu.addSeparator();
        menu.addItem(new MenuItem("Help", editMenu));

        return menu;
    }

    private static void showSelectedMenuItem(String menuItemName) {
        Window.alert("Menu item: " + menuItemName + " selected");
    }

    public static class UfporMenu extends MenuBar {
        private MenuItem file;
        private MenuItem newProject;
        private MenuItem edit;
        private MenuItem insert;
        private MenuItem mFormat;

        public MenuItem getNewProject() {
            return newProject;
        }

        public void setNewProject(MenuItem newProject) {
            this.newProject = newProject;
        }

        public MenuItem getmFormat() {
            return mFormat;
        }

        public void setmFormat(MenuItem mFormat) {
            this.mFormat = mFormat;
        }

        public MenuItem getInsert() {
            return insert;
        }

        public void setInsert(MenuItem insert) {
            this.insert = insert;
        }

        public MenuItem getEdit() {
            return edit;
        }

        public void setEdit(MenuItem edit) {
            this.edit = edit;
        }

        public MenuItem getFile() {
            return file;
        }

        public void setFile(MenuItem file) {
            this.file = file;
        }
    }
}
