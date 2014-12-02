package com.ufpor.app.client.view.project;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.ufpor.app.client.service.EnvironmentService;
import com.ufpor.app.client.view.SingleTable;

import java.util.List;

/**
 * Created by Ehsan Barekati on 11/30/14.
 */
public class OpenProjectPresenter {
    private SingleTable view;

    public OpenProjectPresenter() {
        view = new SingleTable();
        refresh();

    }

    public Widget getView() {
        return view;
    }

    public String getSelectedProjectName() {
        return view.getSelectedItem();
    }

    public void refresh() {
        view.removeAll();
        EnvironmentService.App.getInstance().getProjectsNames(new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<String> result) {
                view.addToTheList(result);
            }
        });
    }
}
