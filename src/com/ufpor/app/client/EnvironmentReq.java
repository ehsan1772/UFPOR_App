package com.ufpor.app.client;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class EnvironmentReq extends Composite {
	@UiField
	Label firstTitle;
	@UiField
	Label secondTitle;
	@UiField
	CellTable table;

	private static class Contact {
		private final String address;
		private final Date birthday;
		private final String name;

		public Contact(String name, Date birthday, String address) {
			this.name = name;
			this.birthday = birthday;
			this.address = address;
		}
	}

	/**
	 * The list of data to display.
	 */
	private static final List<Contact> CONTACTS = Arrays.asList(new Contact(
			"John", new Date(80, 4, 12), "123 Fourth Avenue"), new Contact(
			"Joe", new Date(85, 2, 22), "22 Lance Ln"), new Contact("George",
			new Date(46, 6, 6), "1600 Pennsylvania Avenue"));
	
	private ListDataProvider<Contact> dataProvider = new ListDataProvider<Contact>();

	private static EnvironmentGeneralUiBinder uiBinder = GWT.create(EnvironmentGeneralUiBinder.class);

	interface EnvironmentGeneralUiBinder extends
			UiBinder<Widget, EnvironmentReq> {
	}

	public EnvironmentReq() {
		table = new CellTable<Contact>();
		table.setWidth("100%");
		table.setHeight("100%");
	//	dataProvider.getList().addAll(CONTACTS);

		TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
			@Override
			public String getValue(Contact object) {
				return object.name;
			}
		};
		table.addColumn(nameColumn, "Name");

		// Add a date column to show the birthday.
		DateCell dateCell = new DateCell();
		Column<Contact, Date> dateColumn = new Column<Contact, Date>(dateCell) {
			@Override
			public Date getValue(Contact object) {
				return object.birthday;
			}
		};
		table.addColumn(dateColumn, "Birthday");

		// Add a text column to show the address.
		TextColumn<Contact> addressColumn = new TextColumn<Contact>() {
			@Override
			public String getValue(Contact object) {
				return object.address;
			}
		};
		table.addColumn(addressColumn, "Address");
		
	//	dataProvider.addDataDisplay(table);
		table.setRowData(0, CONTACTS);
		
		table.redraw();
		
		initWidget(uiBinder.createAndBindUi(this));
		
		
	}

	public EnvironmentReq(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setFirstTitle(String title) {
		firstTitle.setText(title);
	}

	public void setSecondTitle(String title) {
		secondTitle.setText(title);
	}

}
