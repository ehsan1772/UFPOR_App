package com.ufpor.app.client.view.project;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

public class HalfPopUpView extends Composite {
    private static HalfPopUpViewUiBinder uiBinder = GWT.create(HalfPopUpViewUiBinder.class);
    //LEFT ROW
    @UiField
    protected TextBox firstTextBoxL;
    @UiField
    protected TextBox secondTextBoxL;
    @UiField
    protected TextBox thirdTextBoxL;
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
    protected TextBox firstTextBoxR;
    @UiField
    protected Label firstTextBoxEndingR;
    @UiField
    protected Label secondTextBoxTitleR;
    @UiField
    protected TextBox secondTextBoxR;
    @UiField
    protected Label secondTextBoxEndingR;
    @UiField
    protected Label thirdTextBoxTitleR;
    @UiField
    protected TextBox thirdTextBoxR;
    @UiField
    protected Label thirdTextBoxEndingR;
    @UiField
    protected Label longTextBoxTitleR;
    @UiField
    protected TextArea longTextBoxR;

    public HalfPopUpView() {
        initWidget(uiBinder.createAndBindUi(this));
        firstTextBoxEndingL.setVisible(false);
        firstTextBoxL.setVisible(false);
        firstTextBoxTitleL.setVisible(false);

        secondTextBoxEndingL.setVisible(false);
        secondTextBoxTitleL.setVisible(false);
        secondTextBoxL.setVisible(false);

        thirdTextBoxEndingL.setVisible(false);
        thirdTextBoxL.setVisible(false);
        thirdTextBoxTitleL.setVisible(false);

        longTextBoxL.setVisible(false);
        longTextBoxTitleL.setVisible(false);

        firstTextBoxEndingR.setVisible(false);
        firstTextBoxR.setVisible(false);
        firstTextBoxTitleR.setVisible(false);

        secondTextBoxEndingR.setVisible(false);
        secondTextBoxTitleR.setVisible(false);
        secondTextBoxR.setVisible(false);

        thirdTextBoxEndingR.setVisible(false);
        thirdTextBoxR.setVisible(false);
        thirdTextBoxTitleR.setVisible(false);

        longTextBoxR.setVisible(false);
        longTextBoxTitleR.setVisible(false);
    }

    public void setFirstTextBoxTitleL(String title) {
        firstTextBoxTitleL.setVisible(true);
        firstTextBoxL.setVisible(true);
        firstTextBoxTitleL.setText(title);
    }

    public void setSecondTextBoxTitleL(String title) {
        secondTextBoxTitleL.setVisible(true);
        secondTextBoxL.setVisible(true);
        secondTextBoxTitleL.setText(title);
    }

    public void setThirdTextBoxTitleL(String title) {
        thirdTextBoxTitleL.setVisible(true);
        thirdTextBoxL.setVisible(true);
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

    public void setFirstTextBoxL(String title) {
        firstTextBoxL.setVisible(true);
        firstTextBoxL.setText(title);
    }

    public void setSecondTextBoxL(String title) {
        secondTextBoxL.setVisible(true);
        secondTextBoxL.setText(title);
    }

    public void setThirdTextBoxL(String title) {
        thirdTextBoxL.setVisible(true);
        thirdTextBoxL.setText(title);
    }

    public void setLongTextBoxL(String title) {
        longTextBoxL.setVisible(true);
        longTextBoxTitleL.setVisible(true);
        longTextBoxTitleL.setText(title);
    }




    public void setFirstTextBoxTitleR(String title) {
        firstTextBoxTitleR.setVisible(true);
        firstTextBoxR.setVisible(true);
        firstTextBoxTitleR.setText(title);
    }

    public void setSecondTextBoxTitleR(String title) {
        secondTextBoxTitleR.setVisible(true);
        secondTextBoxR.setVisible(true);
        secondTextBoxTitleR.setText(title);
    }

    public void setThirdTextBoxTitleR(String title) {
        thirdTextBoxTitleR.setVisible(true);
        thirdTextBoxR.setVisible(true);
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

    public void setFirstTextBoxR(String title) {
        firstTextBoxR.setVisible(true);
        firstTextBoxR.setText(title);
    }

    public void setSecondTextBoxR(String title) {
        secondTextBoxR.setVisible(true);
        secondTextBoxR.setText(title);
    }

    public void setThirdTextBoxR(String title) {
        thirdTextBoxR.setVisible(true);
        thirdTextBoxR.setText(title);
    }

    public void setLongTextBoxR(String title) {
        longTextBoxR.setVisible(true);
        longTextBoxTitleR.setVisible(true);
        longTextBoxTitleR.setText(title);
    }

    public HasKeyUpHandlers getLongTextBoxR() {
        return longTextBoxR;
    }

    public HasKeyUpHandlers getThirdTextBoxR() {
        return thirdTextBoxR;
    }

    public HasKeyUpHandlers getSecondTextBoxR() {
        return secondTextBoxR;
    }

    public HasKeyUpHandlers getFirstTextBoxR() {
        return firstTextBoxR;
    }

    public HasKeyUpHandlers getFirstTextBoxL() {
        return firstTextBoxL;
    }

    public HasKeyUpHandlers getSecondTextBoxL() {
        return secondTextBoxL;
    }

    public HasKeyUpHandlers getThirdTextBoxL() {
        return thirdTextBoxL;
    }

    public HasKeyUpHandlers getLongTextBoxL() {
        return longTextBoxL;
    }

    public String getFirstTextBoxRText() {
        return firstTextBoxL.getText();
    }

    public String getSecondTextBoxRText() {
        return secondTextBoxL.getText();
    }

    public String getThirdTextBoxRText() {
        return thirdTextBoxL.getText();
    }

    public String getLongTextBoxRText() {
        return longTextBoxL.getText();
    }

    public String getFirstTextBoxLText() {
        return firstTextBoxL.getText();
    }

    public String getSecondTextBoxLText() {
        return secondTextBoxL.getText();
    }

    public String getThirdTextBoxLText() {
        return thirdTextBoxL.getText();
    }

    public String getLongTextBoxLText() {
        return longTextBoxL.getText();
    }


    public String getMaxArea() {
        return secondTextBoxL.getText();
    }

    interface HalfPopUpViewUiBinder extends UiBinder<Widget, HalfPopUpView> {
    }

}
