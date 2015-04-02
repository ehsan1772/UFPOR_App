package com.ufpor.app.client.presenter;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ufpor.app.client.view.DataGridTest;
import com.ufpor.app.client.view.EnvironmentActivity;
import com.ufpor.app.client.view.EnvironmentGrouping;
import com.ufpor.app.client.view.project.FullPopUpView;
import com.ufpor.app.shared.ifcclient.property.SpaceBaseQuantitiesBuilder;
import com.ufpor.app.shared.ifcclient.property.SpaceCommonPropertySetBuilder;
import com.ufpor.app.shared.ifcclient.type.IfcClientSpaceType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ehsan Barekati on 11/28/14.
 */
public class SpaceTypePresenter implements SpaceTypePresenterI, FullPopUpView.Presenter {
    public final static String TRUE = "Yes";
    public final static String FALSE = "No";
    private final KeyUpHandler nameChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            spaceType.setName(((TextBox) event.getSource()).getText());
        }
    };

    private final KeyUpHandler longNameChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            spaceType.setLongName(((TextBox) event.getSource()).getText());
        }
    };
    private final KeyUpHandler descriptionChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            spaceType.setDescription(((TextBox) event.getSource()).getText());
        }
    };
    private final KeyUpHandler refIdChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            pBuilder.setReferenceId(((TextBox) event.getSource()).getText());
        }
    };
    private final SpaceCommonPropertySetBuilder pBuilder;
    private final KeyUpHandler netAreaChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            pBuilder.setNetPlannedArea(Double.valueOf(((TextBox) event.getSource()).getText()));
        }
    };
    private final KeyUpHandler grossAreaChanged = new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            pBuilder.setGrossPlannedArea(Double.valueOf(((TextBox) event.getSource()).getText()));
        }
    };
    private SpaceBaseQuantitiesBuilder constraintBuilder;
    private Widget constraintsWidget;
    private ChangeHandler typeChanged = new ChangeHandler() {
        @Override
        public void onChange(ChangeEvent event) {
            ListBox lBox = (ListBox) event.getSource();
            IfcClientSpaceType.IfcSpaceTypeEnum value = IfcClientSpaceType.IfcSpaceTypeEnum.valueOf(lBox.getItemText(lBox.getSelectedIndex()));
            spaceType.setPredefinedType(value);
        }
    };

    public IfcClientSpaceType getSpaceType() {
        spaceType.addPropertySet(pBuilder.getProperties());
        spaceType.addPropertySet((constraintBuilder.getIfcClientElementQuantity()));
        return spaceType;
    }

    private IfcClientSpaceType spaceType;

    private HashMap<String, Widget> views;
    private Widget generalWidget;

    public SpaceTypePresenter(IfcClientSpaceType spaceType) {
        this.spaceType = spaceType;
        pBuilder = new SpaceCommonPropertySetBuilder();
        constraintBuilder = new SpaceBaseQuantitiesBuilder();

        views = new HashMap<String, Widget>();

        generalWidget = createGeneral();
        views.put("General", generalWidget);

        ArrayList<Widget> constraintsWidgets = createConstraint();
        views.put("Area Constraints", constraintsWidgets.get(0));
        views.put("Height Constraints", constraintsWidgets.get(1));


       // views.put("General", new EnvironmentGeneral());



        views.put("Grouping", new EnvironmentGrouping());


        EnvironmentGrouping adjacencies = new EnvironmentGrouping();
        adjacencies.setFirstTitle("Required Adjacencies");
        adjacencies.setSecondTitle("Avoid Adjacencies");
        views.put("Adjacencies", adjacencies);

        EnvironmentActivity activity = new EnvironmentActivity();
        activity.setFirstTitle("Activities");
        activity.setSecondTitle("People");
        views.put("Activities", activity);

        DataGridTest test = new DataGridTest();
        test.redraw();
        views.put("Requirements", test);
      //  views.put("General", constraintsWidget);
    }

    private ArrayList<Widget> createConstraint() {
        ArrayList<Widget> results = new ArrayList<Widget>();

        //AREA
        final FullPopUpView constraints1 = new FullPopUpView(this);

        constraints1.setTextBoxTitle("Max Gross Ceiling", FullPopUpView.Position.L1);
        constraints1.setTextBoxEnding("sqft", FullPopUpView.Position.L1);
        constraints1.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMaxGrossCeilingArea(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints1.setTextBoxTitle("Min Gross Ceiling", FullPopUpView.Position.L2);
        constraints1.setTextBoxEnding("sqft", FullPopUpView.Position.L2);
        constraints1.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMinGrossCeilingArea(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints1.setTextBoxTitle("Max Net Ceiling", FullPopUpView.Position.L3);
        constraints1.setTextBoxEnding("sqft", FullPopUpView.Position.L3);
        constraints1.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMaxNetCeilingArea(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints1.setTextBoxTitle("Min Net Ceiling", FullPopUpView.Position.L4);
        constraints1.setTextBoxEnding("sqft", FullPopUpView.Position.L4);
        constraints1.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMinNetCeilingArea(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints1.setTextBoxTitle("Max Gross Wall", FullPopUpView.Position.L5);
        constraints1.setTextBoxEnding("sqft", FullPopUpView.Position.L5);
        constraints1.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMaxGrossWallArea(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints1.setTextBoxTitle("Min Gross Wall", FullPopUpView.Position.L6);
        constraints1.setTextBoxEnding("sqft", FullPopUpView.Position.L6);
        constraints1.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMinGrossWallArea(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });



        constraints1.setTextBoxTitle("Max Gross Floor", FullPopUpView.Position.R1);
        constraints1.setTextBoxEnding("sqft", FullPopUpView.Position.R1);
        constraints1.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMaxGrossFloorArea(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints1.setTextBoxTitle("Min Gross Floor", FullPopUpView.Position.R2);
        constraints1.setTextBoxEnding("sqft", FullPopUpView.Position.R2);
        constraints1.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMinGrossFloorArea(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints1.setTextBoxTitle("Max Net Floor", FullPopUpView.Position.R3);
        constraints1.setTextBoxEnding("sqft", FullPopUpView.Position.R3);
        constraints1.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMaxNetFloorArea(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints1.setTextBoxTitle("Min Net Floor", FullPopUpView.Position.R4);
        constraints1.setTextBoxEnding("sqft", FullPopUpView.Position.R4);
        constraints1.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMinNetFloorArea(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints1.setTextBoxTitle("Max Net Wall", FullPopUpView.Position.R5);
        constraints1.setTextBoxEnding("sqft", FullPopUpView.Position.R5);
        constraints1.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMaxNetWallArea(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints1.setTextBoxTitle("Min Net Wall", FullPopUpView.Position.R6);
        constraints1.setTextBoxEnding("sqft", FullPopUpView.Position.R6);
        constraints1.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMinNetWallArea(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });


        //HEIGHT
        FullPopUpView constraints2 = new FullPopUpView(this);

        constraints2.setTextBoxTitle("Max Finished Ceiling", FullPopUpView.Position.L1);
        constraints2.setTextBoxEnding("ft", FullPopUpView.Position.L1);
        constraints2.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMaxFinishCeilingHeight(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints2.setTextBoxTitle("Min Finished Ceiling", FullPopUpView.Position.L2);
        constraints2.setTextBoxEnding("ft", FullPopUpView.Position.L2);
        constraints2.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMinFinishCeilingHeight(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints2.setTextBoxTitle("Max Finished Floor", FullPopUpView.Position.L3);
        constraints2.setTextBoxEnding("ft", FullPopUpView.Position.L3);
        constraints2.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMaxFinishFloorHeight(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints2.setTextBoxTitle("Min Finished Floor", FullPopUpView.Position.L4);
        constraints2.setTextBoxEnding("ft", FullPopUpView.Position.L4);
        constraints2.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMinFinishFloorHeight(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints2.setTextBoxTitle("Max Space Height", FullPopUpView.Position.L5);
        constraints2.setTextBoxEnding("ft", FullPopUpView.Position.L5);
        constraints2.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMaxHeight(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        constraints2.setTextBoxTitle("Min Space Height", FullPopUpView.Position.L6);
        constraints2.setTextBoxEnding("ft", FullPopUpView.Position.L6);
        constraints2.getTextBox_L1().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                constraintBuilder.setMinHeight(Double.valueOf(((TextBox) event.getSource()).getText()));
            }
        });

        results.add(constraints1);
        results.add(constraints2);

        return results;

    }

    private Widget createGeneral() {
        FullPopUpView general = new FullPopUpView(this);

        general.setTitle("New Space Type");

        //name
        general.setTextBoxTitle_L1("Name");
        general.getTextBox_L1().addKeyUpHandler(nameChanged);


        //longName
        general.setTextBoxTitle_L2("Long Name");
        general.getTextBox_L1().addKeyUpHandler(longNameChanged);

        //type
        ArrayList<String> typeValues = new ArrayList<String>();
        for (IfcClientSpaceType.IfcSpaceTypeEnum value : IfcClientSpaceType.IfcSpaceTypeEnum.values()) {
            typeValues.add(value.name());
        }
        general.setList_L3(typeValues);
        general.setTextBoxTitle_L3("Type");

        //description
        general.setTextBoxTitle("Description", FullPopUpView.Position.L4);
        general.getTextBox(FullPopUpView.Position.L4).addKeyUpHandler(descriptionChanged);

        //properties
        //id
        general.setTextBoxTitle("Reference ID", FullPopUpView.Position.L5);
        general.getTextBox(FullPopUpView.Position.L5).addKeyUpHandler(refIdChanged);

        //isExternal
        ArrayList<String> externalValues = new ArrayList<String>();
        externalValues.add(TRUE);
        externalValues.add(FALSE);
        general.setList_L6(externalValues);
        general.setTextBoxTitle("Is External?", FullPopUpView.Position.L6);

        //isPublic
        general.setList_R1(externalValues);
        general.setTextBoxTitle("Is public?", FullPopUpView.Position.R1);

        //isHandicapAccessible
        general.setList_R2(externalValues);
        general.setTextBoxTitle("Handicap Accessible?", FullPopUpView.Position.R2);

        //Net Planned Area
        general.setTextBoxTitle("Net Planned Area", FullPopUpView.Position.R3);
        general.getTextBox(FullPopUpView.Position.R3).addKeyUpHandler(netAreaChanged);
        general.setTextBoxEnding("sqft", FullPopUpView.Position.R3);

        //Gross Planned Area
        general.setTextBoxTitle("Gross Planned Area", FullPopUpView.Position.R4);
        general.getTextBox(FullPopUpView.Position.R4).addKeyUpHandler(grossAreaChanged);
        general.setTextBoxEnding("sqft", FullPopUpView.Position.R4);

        //isPublicly accessible
        general.setList_R5(externalValues);
        general.setTextBoxTitle("Publicly Accessible?", FullPopUpView.Position.R5);

        return general;
    }

    @Override
    public HashMap<String, Widget> getViews() {
        return views;
    }

    @Override
    public void listboxValueChanged(String value, Element element) {
        if (element == ((FullPopUpView)generalWidget).getList_L3().getElement()) {
            spaceType.setPredefinedType(IfcClientSpaceType.IfcSpaceTypeEnum.valueOf(value));
        }
    }
}
