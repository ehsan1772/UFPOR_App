package com.ufpor.app.client.view.project;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;

public class FullPopUpView extends Composite {
    public HasKeyUpHandlers getTextBox(Position position) {
        switch (position) {
            case L1:
                return TextBox_L1;
            case L2:
                return TextBox_L2;
            case L3:
                return TextBox_L3;
            case L4:
                return TextBox_L4;
            case L5:
                return TextBox_L5;
            case L6:
                return TextBox_L6;
            case R1:
                return TextBox_R1;
            case R2:
                return TextBox_R2;
            case R3:
                return TextBox_R3;
            case R4:
                return TextBox_R4;
            case R5:
                return TextBox_R5;
            case R6:
                return TextBox_R6;
        }
        return null;
    }

    public void setTextBoxEnding(String ending, Position position) {
        switch (position) {
            case L1:
                TextBoxEnding_L1.setVisible(true);
                TextBoxEnding_L1.setText(ending);
                break;
            case L2:
                TextBoxEnding_L2.setVisible(true);
                TextBoxEnding_L2.setText(ending);
                break;
            case L3:
                TextBoxEnding_L3.setVisible(true);
                TextBoxEnding_L3.setText(ending);
                break;
            case L4:
                TextBoxEnding_L4.setVisible(true);
                TextBoxEnding_L4.setText(ending);
                break;
            case L5:
                TextBoxEnding_L5.setVisible(true);
                TextBoxEnding_L5.setText(ending);
                break;
            case L6:
                TextBoxEnding_L6.setVisible(true);
                TextBoxEnding_L6.setText(ending);
                break;
            case R1:
                TextBoxEnding_R1.setVisible(true);
                TextBoxEnding_R1.setText(ending);
                break;
            case R2:
                TextBoxEnding_R2.setVisible(true);
                TextBoxEnding_R2.setText(ending);
                break;
            case R3:
                TextBoxEnding_R3.setVisible(true);
                TextBoxEnding_R3.setText(ending);
                break;
            case R4:
                TextBoxEnding_R4.setVisible(true);
                TextBoxEnding_R4.setText(ending);
                break;
            case R5:
                TextBoxEnding_R5.setVisible(true);
                TextBoxEnding_R5.setText(ending);
                break;
            case R6:
                TextBoxEnding_R6.setVisible(true);
                TextBoxEnding_R6.setText(ending);
                break;
        }
    }

    public enum Position {L1, L2, L3, L4, L5, L6, R1, R2, R3, R4, R5, R6};

    private static FullPopUpViewUiBinder uiBinder = GWT.create(FullPopUpViewUiBinder.class);
    private final Presenter presenter;
    //LEFT ROW
    @UiField
    protected TextBox TextBox_L1;
    @UiField
    protected TextBox TextBox_L2;
    @UiField
    protected TextBox TextBox_L3;
    @UiField
    protected TextBox TextBox_L4;
    @UiField
    protected TextBox TextBox_L5;
    @UiField
    protected TextBox TextBox_L6;
    @UiField
    protected Label TextBoxTitle_L1;
    @UiField
    protected Label TextBoxEnding_L1;
    @UiField
    protected Label TextBoxTitle_L2;
    @UiField
    protected Label TextBoxEnding_L2;
    @UiField
    protected Label TextBoxTitle_L3;
    @UiField
    protected Label TextBoxEnding_L3;
    @UiField
    protected Label TextBoxTitle_L4;
    @UiField
    protected Label TextBoxEnding_L4;
    @UiField
    protected Label TextBoxTitle_L5;
    @UiField
    protected Label TextBoxEnding_L5;
    @UiField
    protected Label TextBoxTitle_L6;
    @UiField
    protected Label TextBoxEnding_L6;

    //RIGHT ROW
    @UiField
    protected Label TextBoxTitle_R1;
    @UiField
    protected TextBox TextBox_R1;
    @UiField
    protected Label TextBoxEnding_R1;
    @UiField
    protected Label TextBoxTitle_R2;
    @UiField
    protected TextBox TextBox_R2;
    @UiField
    protected Label TextBoxEnding_R2;
    @UiField
    protected Label TextBoxTitle_R3;
    @UiField
    protected TextBox TextBox_R3;
    @UiField
    protected Label TextBoxEnding_R3;
    @UiField
    protected Label TextBoxTitle_R4;
    @UiField
    protected TextBox TextBox_R4;
    @UiField
    protected Label TextBoxEnding_R4;
    @UiField
    protected Label TextBoxTitle_R5;
    @UiField
    protected TextBox TextBox_R5;
    @UiField
    protected Label TextBoxEnding_R5;
    @UiField
    protected Label TextBoxTitle_R6;
    @UiField
    protected TextBox TextBox_R6;
    @UiField
    protected Label TextBoxEnding_R6;

    //SPINNERS
    protected ListBox list_L1;
    protected ListBox list_L2;
    protected ListBox list_L3;
    protected ListBox list_L4;
    protected ListBox list_L5;
    protected ListBox list_L6;
    protected ListBox list_R1;
    protected ListBox list_R2;
    protected ListBox list_R3;
    protected ListBox list_R4;
    protected ListBox list_R5;
    protected ListBox list_R6;

    @UiField
    protected HTMLPanel root;

    @UiField
    FullPopUpStyle style;
    private EventListener eventlistener = new EventListener() {
        @Override
        public void onBrowserEvent(Event event) {
            if (Event.ONCHANGE == event.getTypeInt()) {
                //   if ((Element.as(event.getEventTarget()) == list_L3.getElement())) {
                presenter.listboxValueChanged(list_L3.getValue(list_L3.getSelectedIndex()), Element.as(event.getEventTarget()));
                //   }
            }

        }
    };

    public FullPopUpView(Presenter presenter) {
        this.presenter = presenter;
        initWidget(uiBinder.createAndBindUi(this));
//        TextBoxEnding_L1.setVisible(false);
        TextBoxTitle_L1.setText("Test");
        TextBox_L1.setVisible(false);
//        TextBoxTitle_L1.setVisible(false);

        TextBoxEnding_L2.setVisible(false);
        TextBoxTitle_L2.setVisible(false);
        //   TextBox_L2.setVisible(false);

        TextBoxEnding_L3.setVisible(false);
        TextBox_L3.setVisible(false);
        TextBoxTitle_L3.setVisible(false);

        TextBoxEnding_L4.setVisible(false);
        TextBox_L4.setVisible(false);
        TextBoxTitle_L4.setVisible(false);

        TextBoxEnding_L5.setVisible(false);
        TextBox_L5.setVisible(false);
        TextBoxTitle_L5.setVisible(false);

        TextBoxEnding_L6.setVisible(false);
        TextBox_L6.setVisible(false);
        TextBoxTitle_L6.setVisible(false);

        TextBoxEnding_R1.setVisible(false);
        TextBox_R1.setVisible(false);
        TextBoxTitle_R1.setVisible(false);

        TextBoxEnding_R2.setVisible(false);
        TextBoxTitle_R2.setVisible(false);
        TextBox_R2.setVisible(false);

        TextBoxEnding_R3.setVisible(false);
        TextBox_R3.setVisible(false);
        TextBoxTitle_R3.setVisible(false);

        TextBoxEnding_R4.setVisible(false);
        TextBox_R4.setVisible(false);
        TextBoxTitle_R4.setVisible(false);

        TextBoxEnding_R5.setVisible(false);
        TextBox_R5.setVisible(false);
        TextBoxTitle_R5.setVisible(false);

        TextBoxEnding_R6.setVisible(false);
        TextBox_R6.setVisible(false);
        TextBoxTitle_R6.setVisible(false);
    }

    public void setTextBoxTitle_L1(String title) {
        TextBoxTitle_L1.setVisible(true);
        TextBox_L1.setVisible(true);
        TextBoxTitle_L1.setText(title);
    }

    public void setTextBoxTitle(String title, FullPopUpView.Position position) {
        switch (position) {
            case L1:
                TextBoxTitle_L1.setVisible(true);
                TextBox_L1.setVisible(true);
                TextBoxTitle_L1.setText(title);
                break;
            case L2:
                TextBoxTitle_L2.setVisible(true);
                TextBox_L2.setVisible(true);
                TextBoxTitle_L2.setText(title);
                break;
            case L3:
                TextBoxTitle_L3.setVisible(true);
                TextBox_L3.setVisible(true);
                TextBoxTitle_L3.setText(title);
                break;
            case L4:
                TextBoxTitle_L4.setVisible(true);
                TextBox_L4.setVisible(true);
                TextBoxTitle_L4.setText(title);
                break;
            case L5:
                TextBoxTitle_L5.setVisible(true);
                TextBox_L5.setVisible(true);
                TextBoxTitle_L5.setText(title);
                break;
            case L6:
                TextBoxTitle_L6.setVisible(true);
                TextBox_L6.setVisible(true);
                TextBoxTitle_L6.setText(title);
                break;
            case R1:
                TextBoxTitle_R1.setVisible(true);
                TextBox_R1.setVisible(true);
                TextBoxTitle_R1.setText(title);
                break;
            case R2:
                TextBoxTitle_R2.setVisible(true);
                TextBox_R2.setVisible(true);
                TextBoxTitle_R2.setText(title);
                break;
            case R3:
                TextBoxTitle_R3.setVisible(true);
                TextBox_R3.setVisible(true);
                TextBoxTitle_R3.setText(title);
                break;
            case R4:
                TextBoxTitle_R4.setVisible(true);
                TextBox_R4.setVisible(true);
                TextBoxTitle_R4.setText(title);
                break;
            case R5:
                TextBoxTitle_R5.setVisible(true);
                TextBox_R5.setVisible(true);
                TextBoxTitle_R5.setText(title);
                break;
            case R6:
                TextBoxTitle_R6.setVisible(true);
                TextBox_R6.setVisible(true);
                TextBoxTitle_R6.setText(title);
                break;
        }
    }

    public void setTextBoxTitle_L2(String title) {
        TextBoxTitle_L2.setVisible(true);
        TextBox_L2.setVisible(true);
        TextBoxTitle_L2.setText(title);
    }

    public void setTextBoxTitle_L3(String title) {
        TextBoxTitle_L3.setVisible(true);
        TextBox_L3.setVisible(true);
        TextBoxTitle_L3.setText(title);
    }

    public void setTextBoxEnding_L1(String title) {
        TextBoxEnding_L1.setVisible(true);
        TextBoxEnding_L1.setText(title);
    }

    public void setTextBoxEnding_L2(String title) {
        TextBoxEnding_L2.setVisible(true);
        TextBoxEnding_L2.setText(title);
    }

    public void setTextBoxEnding_L3(String title) {
        TextBoxEnding_L3.setVisible(true);
        TextBoxEnding_L3.setText(title);
    }

    public void setTextBoxTitle_R1(String title) {
        TextBoxTitle_R1.setVisible(true);
        TextBox_R1.setVisible(true);
        TextBoxTitle_R1.setText(title);
    }

    public void setTextBoxTitle_R2(String title) {
        TextBoxTitle_R2.setVisible(true);
        TextBox_R2.setVisible(true);
        TextBoxTitle_R2.setText(title);
    }

    public void setTextBoxTitle_R3(String title) {
        TextBoxTitle_R3.setVisible(true);
        TextBox_R4.setVisible(true);
        TextBoxTitle_R3.setText(title);
    }

    public void setTextBoxEnding_R1(String title) {
        TextBoxEnding_R1.setVisible(true);
        TextBoxEnding_R1.setText(title);
    }

    public void setTextBoxEnding_R2(String title) {
        TextBoxEnding_R2.setVisible(true);
        TextBoxEnding_R2.setText(title);
    }

    public void setTextBoxEnding_R3(String title) {
        TextBoxEnding_R3.setVisible(true);
        TextBoxEnding_R3.setText(title);
    }

    private void setListBox(final ListBox list, final String container, final ArrayList<String> values) {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                root.getElementById(container).removeAllChildren();
                for (String value : values) {
                    list.addItem(value);
                }
                list.addStyleName(style.textBox());
                Element element = list.getElement();
                root.getElementById(container).appendChild(element);
                Event.sinkEvents(element, Event.ONCHANGE);

                Event.setEventListener(element, eventlistener);
            }
        });
    }

    public HasKeyUpHandlers getTextBox_R3() {
        return TextBox_R4;
    }

    public void setTextBox_R3(String title) {
        TextBox_R4.setVisible(true);
        TextBox_R4.setText(title);
    }

    public HasKeyUpHandlers getTextBox_R2() {
        return TextBox_R2;
    }

    public void setTextBox_R2(String title) {
        TextBox_R2.setVisible(true);
        TextBox_R2.setText(title);
    }

    public HasKeyUpHandlers getTextBox_R1() {
        return TextBox_R1;
    }

    public void setTextBox_R1(String title) {
        TextBox_R1.setVisible(true);
        TextBox_R1.setText(title);
    }

    public HasKeyUpHandlers getTextBox_L1() {
        return TextBox_L1;
    }

    public void setTextBox_L1(String title) {
        TextBox_L1.setVisible(true);
        TextBox_L1.setText(title);
    }

    public HasKeyUpHandlers getTextBox_L2() {
        return TextBox_L2;
    }

    public void setTextBox_L2(String title) {
        TextBox_L2.setVisible(true);
        TextBox_L2.setText(title);
    }

    public HasKeyUpHandlers getTextBox_L3() {
        return TextBox_L3;
    }

    public void setTextBox_L3(String title) {
        TextBox_L3.setVisible(true);
        TextBox_L3.setText(title);
    }

    public HasChangeHandlers getListBox_L1() {
        return list_L1;
    }

    public HasChangeHandlers getListBox_L2() {
        return list_L2;
    }

    public HasChangeHandlers getListBox_L3() {
        return list_L3;
    }

    public HasChangeHandlers getListBox_L4() {
        return list_L4;
    }

    public HasChangeHandlers getListBox_L5() {
        return list_L5;
    }

    public HasChangeHandlers getListBox_L6() {
        return list_L6;
    }

//    private ChangeHandler listL3Handler = new ChangeHandler() {
//        @Override
//        public void onChange(ChangeEvent event) {
//            presenter.listboxValueChanged(list_L3.getValue(list_L3.getSelectedIndex()), Element.as(event.getEventTarget()));
//        }
//    };

    public HasChangeHandlers getListBox_R1() {
        return list_R1;
    }

    public HasChangeHandlers getListBox_R2() {
        return list_R2;
    }

    public HasChangeHandlers getListBox_R3() {
        return list_R3;
    }

    public HasChangeHandlers getListBox_R4() {
        return list_R4;
    }

    public HasChangeHandlers getListBox_R5() {
        return list_R5;
    }

    public HasChangeHandlers getListBox_R6() {
        return list_R6;
    }

    public String getFirstTextBoxRText() {
        return TextBox_L1.getText();
    }

    public String getSecondTextBoxRText() {
        return TextBox_L2.getText();
    }

    public String getThirdTextBoxRText() {
        return TextBox_L3.getText();
    }

    public String getFirstTextBoxLText() {
        return TextBox_L1.getText();
    }

    public String getSecondTextBoxLText() {
        return TextBox_L2.getText();
    }

    public String getThirdTextBoxLText() {
        return TextBox_L3.getText();
    }

    public String getMaxArea() {
        return TextBox_L2.getText();
    }

    public ListBox getList_L1() {
        return list_L1;
    }

    public void setList_L1(final ArrayList<String> values) {
        setListBox(list_L1, "tBContainer_L1", values);
    }

    public ListBox getList_L2() {
        return list_L2;
    }

    public void setList_L2(final ArrayList<String> values) {
        setListBox(list_L2, "tBContainer_L2", values);
    }

    public ListBox getList_L3() {
        return list_L3;
    }

    public void setList_L3(final ArrayList<String> values) {
        list_L3 = new ListBox();
        setListBox(list_L3, "tBContainer_L3", values);
    }

    public ListBox getList_L4() {
        return list_L4;
    }

    public void setList_L4(final ArrayList<String> values) {
        list_L4 = new ListBox();
        setListBox(list_L4, "tBContainer_L4", values);
    }

    public ListBox getList_L5() {
        return list_L5;
    }

    public void setList_L5(final ArrayList<String> values) {
        list_L5 = new ListBox();
        setListBox(list_L5, "tBContainer_L5", values);
    }

    public ListBox getList_L6() {
        return list_L6;
    }

    public void setList_L6(final ArrayList<String> values) {
        list_L6 = new ListBox();
        setListBox(list_L6, "tBContainer_L6", values);
    }

    public ListBox getList_R1() {
        return list_R1;
    }

    public void setList_R1(final ArrayList<String> values) {
        list_R1 = new ListBox();
        setListBox(list_R1, "tBContainer_R1", values);
    }

    public ListBox getList_R2() {
        return list_R2;
    }

    public void setList_R2(final ArrayList<String> values) {
        list_R2 = new ListBox();
        setListBox(list_R2, "tBContainer_R2", values);
    }

    public ListBox getList_R3() {
        return list_R3;
    }

    public void setList_R3(final ArrayList<String> values) {
        list_R3 = new ListBox();
        setListBox(list_R3, "tBContainer_R3", values);
    }

    public ListBox getList_R4() {
        return list_R4;
    }

    public void setList_R4(final ArrayList<String> values) {
        list_R4 = new ListBox();
        setListBox(list_R4, "tBContainer_R4", values);
    }

    public ListBox getList_R5() {
        return list_R5;
    }

    public void setList_R5(final ArrayList<String> values) {
        list_R5 = new ListBox();
        setListBox(list_R5, "tBContainer_R5", values);
    }

    public ListBox getList_R6() {
        return list_R6;
    }

    public void setList_R6(final ArrayList<String> values) {
        list_R6 = new ListBox();
        setListBox(list_R6, "tBContainer_R6", values);
    }

    interface FullPopUpViewUiBinder extends UiBinder<Widget, FullPopUpView> {
    }

    interface FullPopUpStyle extends CssResource {


        String singleRow();

        String label();

        String mainOverlay();

        String suffix();

        String bigRow();

        String main();

        String textBoxContainer();

        String leftPanel();

        String rightPanel();

        String bigTextBox();

        String suffixContainer();

        String labelContainer();

        String textBox();
    }

    public static interface Presenter {
        void listboxValueChanged(String value, Element element);
    }
}
