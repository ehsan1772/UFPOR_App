package com.ufpor.app.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class DoubleTable extends Composite {

	private static DoubleTableUiBinder uiBinder = GWT
			.create(DoubleTableUiBinder.class);
	@UiField Button addAll;
	@UiField Button remove;
	@UiField Button removeAll;
	@UiField Button add;
	@UiField ListBox sourceList;
	@UiField ListBox editList;

	interface DoubleTableUiBinder extends UiBinder<Widget, DoubleTable> {
	}

	public DoubleTable() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	public DoubleTable(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}


	@Override
	protected void initWidget(Widget widget) {
		super.initWidget(widget);
		
		add.addClickHandler(addClickHandler);
		addAll.addClickHandler(addAllClickHandler);
		remove.addClickHandler(removeClickHandler);
		removeAll.addClickHandler(removeAllClickHandler);
		
		for (int i = 0 ; i < 5 ; i++) {
			sourceList.addItem("Hello " + i, Direction.LTR);
		}
		
	}
	
	private ClickHandler addClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			int i = sourceList.getSelectedIndex();
			String text = sourceList.getItemText(i);
			editList.addItem(text);
			sourceList.removeItem(i);
		}
	};
	
	private ClickHandler addAllClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			int addableCount = sourceList.getItemCount();
			for (int i = 0; i < addableCount; i++) {
				String text = sourceList.getItemText(0);
				editList.addItem(text);
				sourceList.removeItem(0);
			}
			
		}
	};
	
	private ClickHandler removeClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			int i = editList.getSelectedIndex();
			String text = editList.getItemText(i);
			sourceList.addItem(text);
			editList.removeItem(i);
		}
	};
	
	private ClickHandler removeAllClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			int removableCount = editList.getItemCount();
			for (int i = 0; i < removableCount; i++) {
				String text = editList.getItemText(0);
				sourceList.addItem(text);
				editList.removeItem(0);
			}
			
		}
	};
	
	
	

}
