package com.ufpor.app.client.view.project;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

public class SpinnerPopUpView extends Composite {
    private static SpinnerPopUpViewUiBinder uiBinder = GWT.create(SpinnerPopUpViewUiBinder.class);
    //LEFT ROW
    @UiField
    protected ListBox firstListBoxL;
    @UiField
    protected ListBox secondListBoxL;
    @UiField
    protected ListBox thirdListBoxL;
    @UiField
    protected TextArea longTextBoxL;
    @UiField
    protected Label firstTextBoxTitleL;
    @UiField
    protected Label firstTextBoxEndingL;
    @UiField
    protected Label secondTextBoxTitleL;
    @UiField
    protected Label secondTextBoxEndingL;
    @UiField
    protected Label thirdTextBoxTitleL;
    @UiField
    protected Label thirdTextBoxEndingL;
    @UiField
    protected Label longTextBoxTitleL;
    //RIGHT ROW
    @UiField
    protected Label firstTextBoxTitleR;
    @UiField
    protected ListBox firstListBoxR;
    @UiField
    protected Label firstTextBoxEndingR;
    @UiField
    protected Label secondTextBoxTitleR;
    @UiField
    protected ListBox secondListBoxR;
    @UiField
    protected Label secondTextBoxEndingR;
    @UiField
    protected Label thirdTextBoxTitleR;
    @UiField
    protected ListBox thirdListBoxR;
    @UiField
    protected Label thirdTextBoxEndingR;
    @UiField
    protected Label longTextBoxTitleR;
    @UiField
    protected TextArea longTextBoxR;

    public SpinnerPopUpView() {
        initWidget(uiBinder.createAndBindUi(this));
        firstTextBoxEndingL.setVisible(false);
        firstListBoxL.setVisible(false);
        firstTextBoxTitleL.setVisible(false);

        secondTextBoxEndingL.setVisible(false);
        secondTextBoxTitleL.setVisible(false);
        secondListBoxL.setVisible(false);

        thirdTextBoxEndingL.setVisible(false);
        thirdListBoxL.setVisible(false);
        thirdTextBoxTitleL.setVisible(false);

        longTextBoxL.setVisible(false);
        longTextBoxTitleL.setVisible(false);

        firstTextBoxEndingR.setVisible(false);
        firstListBoxR.setVisible(false);
        firstTextBoxTitleR.setVisible(false);

        secondTextBoxEndingR.setVisible(false);
        secondTextBoxTitleR.setVisible(false);
        secondListBoxR.setVisible(false);

        thirdTextBoxEndingR.setVisible(false);
        thirdListBoxR.setVisible(false);
        thirdTextBoxTitleR.setVisible(false);

        longTextBoxR.setVisible(false);
        longTextBoxTitleR.setVisible(false);
    }

    public void setFirstTextBoxTitleL(String title) {
        firstTextBoxTitleL.setVisible(true);
        firstListBoxL.setVisible(true);
        firstTextBoxTitleL.setText(title);
    }

    public void setSecondTextBoxTitleL(String title) {
        secondTextBoxTitleL.setVisible(true);
        secondListBoxL.setVisible(true);
        secondTextBoxTitleL.setText(title);
    }

    public void setThirdTextBoxTitleL(String title) {
        thirdTextBoxTitleL.setVisible(true);
        thirdListBoxL.setVisible(true);
        thirdTextBoxTitleL.setText(title);
    }

    public void setFirstTextBoxEndingL(String title) {
        firstTextBoxEndingL.setVisible(true);
        firstTextBoxEndingL.setText(title);
    }

    public void setSecondTextBoxEndingL(String title) {
        secondTextBoxEndingL.setVisible(true);
        secondTextBoxEndingL.setText(title);
    }

    public void setThirdTextBoxEndingL(String title) {
        thirdTextBoxEndingL.setVisible(true);
        thirdTextBoxEndingL.setText(title);
    }

    public void setFirstListBoxL(String... titles) {
        firstListBoxL.setVisible(true);
        for (String title : titles) {
            firstListBoxL.addItem(title);
        }
    }

    public void setSecondListBoxL(String... titles) {
        secondListBoxL.setVisible(true);
        for (String title : titles) {
            secondListBoxL.addItem(title);
        }
    }


    public void setThirdListBoxL(String... titles) {
        thirdListBoxL.setVisible(true);
        for (String title : titles) {
            thirdListBoxL.addItem(title);
        }
    }

    public void setLongTextBoxL(String title) {
        longTextBoxL.setVisible(true);
        longTextBoxTitleL.setVisible(true);
        longTextBoxTitleL.setText(title);
    }

    public void setFirstTextBoxTitleR(String title) {
        firstTextBoxTitleR.setVisible(true);
        firstListBoxR.setVisible(true);
        firstTextBoxTitleR.setText(title);
    }

    public void setSecondTextBoxTitleR(String title) {
        secondTextBoxTitleR.setVisible(true);
        secondListBoxR.setVisible(true);
        secondTextBoxTitleR.setText(title);
    }

    public void setThirdTextBoxTitleR(String title) {
        thirdTextBoxTitleR.setVisible(true);
        thirdListBoxR.setVisible(true);
        thirdTextBoxTitleR.setText(title);
    }

    public void setFirstTextBoxEndingR(String title) {
        firstTextBoxEndingR.setVisible(true);
        firstTextBoxEndingR.setText(title);
    }

    public void setSecondTextBoxEndingR(String title) {
        secondTextBoxEndingR.setVisible(true);
        secondTextBoxEndingR.setText(title);
    }

    public void setThirdTextBoxEndingR(String title) {
        thirdTextBoxEndingR.setVisible(true);
        thirdTextBoxEndingR.setText(title);
    }

    public void setFirstListBoxR(String... titles) {
        firstListBoxR.setVisible(true);
        for (String title : titles) {
            firstListBoxR.addItem(title);
        }
    }

    public void setSecondListBoxR(String... titles) {
        secondListBoxR.setVisible(true);
        for (String title : titles) {
            secondListBoxL.addItem(title);
        }
    }

    public void setThirdListBoxR(String... titles) {
        thirdListBoxR.setVisible(true);
        for (String title : titles) {
            thirdListBoxL.addItem(title);
        }
    }

    public void setLongTextBoxR(String title) {
        longTextBoxR.setVisible(true);
        longTextBoxTitleR.setVisible(true);
        longTextBoxTitleR.setText(title);
    }

    public HasKeyUpHandlers getLongTextBoxR() {
        return longTextBoxR;
    }

    public HasKeyUpHandlers getThirdListBoxR() {
        return thirdListBoxR;
    }

    public HasKeyUpHandlers getSecondListBoxR() {
        return secondListBoxR;
    }

    public HasKeyUpHandlers getFirstListBoxR() {
        return firstListBoxR;
    }

    public ListBox getFirstListBoxL() {
        return firstListBoxL;
    }

    public ListBox getSecondListBoxL() {
        return secondListBoxL;
    }

    public ListBox getThirdListBoxL() {
        return thirdListBoxL;
    }

    public HasKeyUpHandlers getLongTextBoxL() {
        return longTextBoxL;
    }

    public String getFirstListBoxLText() {
        return firstListBoxL.getItemText(firstListBoxL.getSelectedIndex());
    }

    public String getSecondListBoxLText() {
        return secondListBoxL.getItemText(secondListBoxL.getSelectedIndex());
    }

    public String getThirdListBoxLText() {
        return thirdListBoxL.getItemText(secondListBoxL.getSelectedIndex());
    }

    public String getFirstListBoxRText() {
        return firstListBoxR.getItemText(firstListBoxR.getSelectedIndex());
    }

    public String getSecondListBoxRText() {
        return secondListBoxR.getItemText(secondListBoxR.getSelectedIndex());
    }

    public String getThirdListBoxRText() {
        return thirdListBoxR.getItemText(secondListBoxR.getSelectedIndex());
    }

    public String getLongTextBoxRText() {
        return longTextBoxL.getText();
    }


    public String getLongTextBoxLText() {
        return longTextBoxL.getText();
    }


    interface SpinnerPopUpViewUiBinder extends UiBinder<Widget, SpinnerPopUpView> {
    }

}
