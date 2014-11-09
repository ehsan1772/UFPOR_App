package com.ufpor.app.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragLeaveHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

class DragDropLabel extends Button 
{
     private static DragDropLabel dragging = null;
     public static Widget dropping;
     final boolean droppable;
     public DragDropLabel(String text, boolean draggable, boolean droppable)
     {
         super(text);
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
         addDragStartHandler(new DragStartHandler()
         {
             @Override
             public void onDragStart(DragStartEvent event)
             {
                 // Remember what's being dragged
                 dragging = DragDropLabel.this;
                 // Must set for FireFox
                 event.setData("text", "hi there");

                // Copy the label image for the drag icon
                // 10,10 indicates the pointer offset, not the image size.
                event.getDataTransfer().setDragImage(getElement(), 10, 10);
             }
         });
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
                     Tree tree = (Tree)DragDropLabel.this.getParent();
 
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
                             if (w == DragDropLabel.this)
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
//                         while(test != null)
//                         {
//                             if (test == dragSource)
//                             {
//                                 return;
//                             }
//                             test = test.getParentItem();
//                         }
                         dragTarget.addItem(dragSource);
                         dragTarget.setState(true);
                     }
                     dragging = null;
                 }
             }
         }, DropEvent.getType());
     }
 }
