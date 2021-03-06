package com.ufpor.app.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragLeaveHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.ufpor.app.client.App;
import com.ufpor.app.shared.ifcclient.type.IfcClientSpaceType;

public class EnvironmentTreeItem extends Composite {
    private static EnvironmentTreeItem dragging = null;
    public static Widget dropping;
    final boolean droppable;
    private final IfcClientSpaceType spaceType;

    @UiField
	protected Label name;
	
	@UiField
	protected Label area;
	
	@UiField
	protected FocusPanel panel;

	private static EnvironmentTreeItemUiBinder uiBinder = GWT
			.create(EnvironmentTreeItemUiBinder.class);

    public IfcClientSpaceType getSpaceType() {
        return spaceType;
    }

    interface EnvironmentTreeItemUiBinder extends
			UiBinder<Widget, EnvironmentTreeItem> {
	}

	public EnvironmentTreeItem(boolean draggable, boolean droppable, IfcClientSpaceType env) {
        spaceType = env;
		initWidget(uiBinder.createAndBindUi(this));
		
		if (draggable)
        {
            initDrag();
        }
        if (droppable)
        {
            initDrop();
        }
        this.droppable = droppable;
        if (droppable)
        {
            addStyleName("droppable");
        }
        else if (draggable)
        {
            addStyleName("draggable");
        }
		
	}
	
    private void initDrag()
    {
        getElement().setDraggable(Element.DRAGGABLE_TRUE);

        panel.addDragStartHandler(new DragStartHandler()
        {
            @Override
            public void onDragStart(DragStartEvent event)
            {
                // Remember what's being dragged
                dragging = EnvironmentTreeItem.this;
                // Must set for FireFox

                ArrayList<EnvironmentTreeItem> cacheList  = (ArrayList<EnvironmentTreeItem>) App.getCache(HomeView.SPACE_TYPE);

                GWT.log("CacheListSize = " + cacheList.size());
                GWT.log("index = " + String.valueOf(cacheList.indexOf(EnvironmentTreeItem.this)));

                event.setData("index", String.valueOf(cacheList.indexOf(EnvironmentTreeItem.this)));



               // Copy the label image for the drag icon
               // 10,10 indicates the pointer offset, not the image size.
               event.getDataTransfer().setDragImage(getElement(), 10, 10);
            }
        });
   }

	
	public void setName(String spaceName) {
		name.setText(spaceName);
	}
	
	public void setArea(String spaceArea) {
		area.setText(spaceArea);
	}
	
      
      private void initDrop()
      {

          addDomHandler(new DragOverHandler()
          {
              @Override
              public void onDragOver(DragOverEvent event)
              {
                  addStyleName("dropping");
              }
          }, DragOverEvent.getType());

          addDomHandler(new DragLeaveHandler()
          {
              @Override
              public void onDragLeave(DragLeaveEvent event)
              {
                  removeStyleName("dropping");
              }
          }, DragLeaveEvent.getType());

          addDomHandler(new DropHandler()
          {
              @Override
              public void onDrop(DropEvent event)
              {
             	// TreeItem t = DragDropLabel.this;
                  event.preventDefault();
                  if (dragging != null)
                  {
                      // Target treeitem is found via 'this';
                      // Dragged treeitem is found via 'dragging'.

                      TreeItem dragTarget = null;
                      TreeItem dragSource = null;
                      // The parent of 'this' is not the TreeItem, as that's not a Widget.
                      // The parent is the tree containing the treeitem.
                      Tree tree = (Tree)EnvironmentTreeItem.this.getParent();
  
                      // Visit the entire tree, searching for the drag and drop TreeItems
                      List<TreeItem> stack = new ArrayList<TreeItem>();
                      stack.add(tree.getItem(0));
                      while(!stack.isEmpty())
                      {
                          TreeItem item = stack.remove(0);
                          for(int i=0;i<item.getChildCount();i++)
                          {
                              stack.add(item.getChild(i));
                          }

                          Widget w = item.getWidget();
                          if (w != null)
                          {
                              if (w == dragging)
                              {
                                  dragSource = item;
                                  if (dragTarget != null)
                                  {
                                      break;
                                  }
                              }
                              if (w == EnvironmentTreeItem.this)
                              {
                                  dragTarget = item;
                                  w.removeStyleName("dropping");
                                  dragSource = new TreeItem(dragging);
                                  if (dragSource != null)
                                  {
                                      break;
                                  }
                              }
                          }
                      }
                      if (dragSource != null && dragTarget != null)
                      {
                          // Make sure that target is not a child of dragSource

                          TreeItem test = dragTarget;
//                          while(test != null)
//                          {
//                              if (test == dragSource)
//                              {
//                                  return;
//                              }
//                              test = test.getParentItem();
//                          }
                          dragTarget.addItem(dragSource);
                          dragTarget.setState(true);
                      }
                      dragging = null;

                  }
                  //TODO fix this! it doesn't need to refresh the space type, it just needs to prevent deleting items from the list aftre dropping them
                  App.getInjector().getHomeView().loadSpaceTypes(HomeView.projectName);
              }



          }, DropEvent.getType());
      }



}
