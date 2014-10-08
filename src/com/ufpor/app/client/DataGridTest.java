package com.ufpor.app.client;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;
import com.ufpor.app.client.RequirementDatabase.Assessor;
import com.ufpor.app.client.RequirementDatabase.RequirememtInfo;

import java.util.List;

public class DataGridTest extends Composite {

	private static DataGridTestUiBinder uiBinder = GWT
			.create(DataGridTestUiBinder.class);

	interface DataGridTestUiBinder extends UiBinder<Widget, DataGridTest> {
	}
	
	  /**
	   * The constants used in this Content Widget.
	   */
	  public static interface CwConstants extends Constants {
	    String cwDataGridColumnRequirement();

	    String cwDataGridColumnMinValue();

	    String cwDataGridColumnMaxValue();
	    
	    String cwDataGridColumnUnit();

	    String cwDataGridColumnAssessor();
	    
	    String cwDataGridEmpty();

	  }

	  /**
	   * The main DataGrid.
	   */
	  @UiField(provided = true)
	  DataGrid<RequirememtInfo> dataGrid;

	  /**
	   * An instance of the constants.
	   */
	  private final CwConstants constants = new CwConstants(){

		@Override
		public String cwDataGridColumnRequirement() {
			// TODO Auto-generated method stub
			return "Requirment";
		}

		@Override
		public String cwDataGridColumnMinValue() {
			// TODO Auto-generated method stub
			return "Min";
		}

		@Override
		public String cwDataGridColumnMaxValue() {
			// TODO Auto-generated method stub
			return "Max";
		}
		
		@Override
		public String cwDataGridColumnUnit() {
			// TODO Auto-generated method stub
			return "Unit";
		}

		@Override
		public String cwDataGridColumnAssessor() {
			// TODO Auto-generated method stub
			return "Assessor";
		}

		@Override
		public String cwDataGridEmpty() {
			// TODO Auto-generated method stub
			return "No Requirements To Show";
		}
	  };

	public DataGridTest() {
		
		// Create a DataGrid.

	    /*
	     * Set a key provider that provides a unique key for each contact. If key is
	     * used to identify contacts when fields (such as the name and address)
	     * change.
	     */
	    dataGrid = new DataGrid<RequirememtInfo>(RequirementDatabase.RequirememtInfo.KEY_PROVIDER);
	    dataGrid.setWidth("100%");

	    /*
	     * Do not refresh the headers every time the data is updated. The footer
	     * depends on the current data, so we do not disable auto refresh on the
	     * footer.
	     */
	    dataGrid.setAutoHeaderRefreshDisabled(true);

	    // Set the message to display when the table is empty.
	    dataGrid.setEmptyTableWidget(new Label(constants.cwDataGridEmpty()));


	    // Add a selection model so we can select cells.
	    final SelectionModel<RequirememtInfo> selectionModel =
	        new MultiSelectionModel<RequirememtInfo>(RequirementDatabase.RequirememtInfo.KEY_PROVIDER);
	    dataGrid.setSelectionModel(selectionModel, DefaultSelectionEventManager
	        .<RequirememtInfo> createCheckboxManager());

	    // Initialize the columns.
	    initTableColumns(selectionModel);

	    // Add the CellList to the adapter in the database.
	    RequirementDatabase.getInstance().addDataDisplay(dataGrid);
	    
	    
		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	   * Add the columns to the table.
	   */
	  private void initTableColumns(final SelectionModel<RequirememtInfo> selectionModel) {

	    // Requirment name.
	    Column<RequirememtInfo, String> requirementColumn =
	        new Column<RequirememtInfo, String>(new EditTextCell()) {
	          @Override
	          public String getValue(RequirememtInfo object) {
	            return object.getRequirment();
	          }
	        };
	        requirementColumn.setSortable(true);

	    dataGrid.addColumn(requirementColumn, constants.cwDataGridColumnRequirement());
	    requirementColumn.setFieldUpdater(new FieldUpdater<RequirememtInfo, String>() {
	      @Override
	      public void update(int index, RequirememtInfo object, String value) {
	        // Called when the user changes the value.
	        object.setRequirment(value);
	        RequirementDatabase.getInstance().refreshDisplays();
	      }
	    });
	    dataGrid.setColumnWidth(requirementColumn, 50, Unit.PCT);

	    // Max value.
	    Column<RequirememtInfo, String> maxValueColumn =
	        new Column<RequirememtInfo, String>(new EditTextCell()) {
	          @Override
	          public String getValue(RequirememtInfo object) {
	            return object.getMaxValue();
	          }
	        };
	    maxValueColumn.setSortable(true);

	    dataGrid.addColumn(maxValueColumn, constants.cwDataGridColumnMaxValue());
	    maxValueColumn.setFieldUpdater(new FieldUpdater<RequirememtInfo, String>() {
	      @Override
	      public void update(int index, RequirememtInfo object, String value) {
	        // Called when the user changes the value.
	        object.setMaxValue(value);
	        RequirementDatabase.getInstance().refreshDisplays();
	      }
	    });
	    dataGrid.setColumnWidth(maxValueColumn, 7, Unit.PCT);



	    // Min value.
	    Column<RequirememtInfo, String> minValueColumn =
	        new Column<RequirememtInfo, String>(new EditTextCell()) {
	          @Override
	          public String getValue(RequirememtInfo object) {
	            return object.getMinValue();
	          }
	        };
	    minValueColumn.setSortable(true);
	    dataGrid.addColumn(minValueColumn, constants.cwDataGridColumnMinValue());
	    minValueColumn.setFieldUpdater(new FieldUpdater<RequirememtInfo, String>() {
	      @Override
	      public void update(int index, RequirememtInfo object, String value) {
	        // Called when the user changes the value.
	        object.setMinValue(value);
	        RequirementDatabase.getInstance().refreshDisplays();
	      }
	    });
	    dataGrid.setColumnWidth(minValueColumn, 7, Unit.PCT);
	    
	    // Unit.

	    List<String> categoryNames = RequirementDatabase.getInstance().getUnitNames();
	    

	    SelectionCell categoryCell = new SelectionCell(categoryNames);
	    Column<RequirememtInfo, String> categoryColumn = new Column<RequirememtInfo, String>(categoryCell) {
	      @Override
	      public String getValue(RequirememtInfo object) {
	        return object.getUnit().getDisplayName();
	      }
	    };
	    dataGrid.addColumn(categoryColumn, constants.cwDataGridColumnUnit());
//	    categoryColumn.setFieldUpdater(new FieldUpdater<ContactInfo, String>() {
//	      @Override
//	      public void update(int index, ContactInfo object, String value) {
//	        for (Category category : categories) {
//	          if (category.getDisplayName().equals(value)) {
//	            object.setCategory(category);
//	          }
//	        }
//	        ContactDatabase.get().refreshDisplays();
//	      }
//	    });
	    dataGrid.setColumnWidth(categoryColumn, 20, Unit.PCT);

	    // Assessor.
	    Column<RequirememtInfo, String> assessorColumn =
	        new Column<RequirememtInfo, String>(new EditTextCell()) {
	          @Override
	          public String getValue(RequirememtInfo object) {
	        	  
	        	this.setCellStyleNames("reqCell");
	            return object.getAssessor().getDisplayName();
	            
	          }
	          
	          
	        };
	        assessorColumn.setSortable(true);
	        
	       // assessorColumn.setCellStyleNames("td.reqCell");
	       

	    dataGrid.addColumn(assessorColumn, constants.cwDataGridColumnAssessor());
	    assessorColumn.setFieldUpdater(new FieldUpdater<RequirememtInfo, String>() {
	      @Override
	      public void update(int index, RequirememtInfo object, String value) {
	        // Called when the user changes the value.
	        object.setAssessor(new Assessor(value));
	        RequirementDatabase.getInstance().refreshDisplays();
	      }
	    });
	    dataGrid.setColumnWidth(assessorColumn, 15, Unit.PCT);

	    
	  }

	  public void redraw() {
		  dataGrid.redraw();
	  }

}
