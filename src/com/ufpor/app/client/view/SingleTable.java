package com.ufpor.app.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

public class SingleTable extends Composite {

	private static SingleTableUiBinder uiBinder = GWT
			.create(SingleTableUiBinder.class);

	@UiField ListBox sourceList;
    @UiField
    ParagraphElement emptyView;

    public void removeAll() {
        sourceList.clear();
    }


    interface SingleTableUiBinder extends UiBinder<Widget, SingleTable> {
	}

	public SingleTable() {
		initWidget(uiBinder.createAndBindUi(this));
        emptyView.getStyle().setVisibility(Style.Visibility.HIDDEN);
	}

    public ListBox getSourceList() {
        return sourceList;
    }

    @Override
	protected void initWidget(Widget widget) {
		super.initWidget(widget);

	}

    public void addToTheList(List<String> values) {
        if (values.isEmpty()) {
            emptyView.getStyle().setVisibility(Style.Visibility.VISIBLE);
        }
        for (String value : values) {
            sourceList.addItem(value);
        }
    }

    public String getSelectedItem() {
        return sourceList.getItemText(sourceList.getSelectedIndex());
    }

}
