package com.ufpor.app.client;

import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;

import java.util.ArrayList;
import java.util.List;

public class RequirementDatabase {
	private static RequirementDatabase instance;
	private ArrayList<Unit> units;
	
	public static RequirementDatabase getInstance() {
		if (instance == null) {
			instance = new RequirementDatabase();
		}
		
		return instance;
	}
	
	private RequirementDatabase() {
		
		units = new ArrayList<Unit>();
		
		units.add(new Unit("Length"));
		units.add(new Unit("Area"));
		units.add(new Unit("Volume"));
		units.add(new Unit("Count"));
		units.add(new Unit("Cost"));
		
		generateRequirememts();
	}
	
	public ArrayList<String> getUnitNames() {
		ArrayList<String> results = new ArrayList<String>();
		for (Unit unit : units) {
			results.add(unit.displayName);
		}
		
		return results;
	}
	
	private void generateRequirememts() {
		List<RequirememtInfo> list = dataProvider.getList();
		
		list.add(createReq("One"));
		list.add(createReq("Two"));
		list.add(createReq("Three"));
		list.add(createReq("Four"));
		list.add(createReq("Five"));
		list.add(createReq("Six"));
		list.add(createReq("Seven"));
		list.add(createReq("Eight"));
		list.add(createReq("Nine"));
		list.add(createReq("Ten"));
		list.add(createReq("Eleven"));
		list.add(createReq("Twelve"));
		
		
	}
	
	public ListDataProvider<RequirememtInfo> getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(ListDataProvider<RequirememtInfo> dataProvider) {
		this.dataProvider = dataProvider;
	}

	private ListDataProvider<RequirememtInfo> dataProvider = new ListDataProvider<RequirememtInfo>();
	
	public void addRequirement(RequirememtInfo req) {
		List<RequirememtInfo> list = dataProvider.getList();
		list.remove(req);
		list.add(req);
		
	}
	
	  /**
	   * Add a display to the database. The current range of interest of the display
	   * will be populated with data.
	   * 
	   * @param display a {@Link HasData}.
	   */
	  public void addDataDisplay(HasData<RequirememtInfo> display) {
	    dataProvider.addDataDisplay(display);
	  }
	  
	  public RequirememtInfo createReq(String key) {
		  RequirememtInfo req = new RequirememtInfo();
		  req.setRequirment(key);
		  req.setMinValue(String.valueOf(key.getBytes().length));
		  req.setMaxValue(String.valueOf(key.getBytes().length * 2));
		  req.setAssessor(new Assessor(key));
		  req.setUnit(units.get(0));
		  
		  return req;
	  }
	
	public static class RequirememtInfo {
		private static int nextId = 0;
		private final int id;
		private String requirment;
		private String maxValue;
		private String minValue;
		private Assessor assessor;
		private Unit unit;
		
		public static final ProvidesKey<RequirememtInfo> KEY_PROVIDER = new ProvidesKey<RequirememtInfo>() {

			@Override
			public Object getKey(RequirememtInfo item) {
				// TODO Auto-generated method stub
				return item.getRequirment();
			}
			
		};
		
		public RequirememtInfo() {
			  id = nextId;
			  nextId++;
		}
		public String getMinValue() {
			return minValue;
		}
		public void setMinValue(String minValue) {
			this.minValue = minValue;
		}
		public String getRequirment() {
			return requirment;
		}
		public void setRequirment(String requirment) {
			this.requirment = requirment;
		}
		public Assessor getAssessor() {
			return assessor;
		}
		public void setAssessor(Assessor assessor) {
			this.assessor = assessor;
		}
		public String getMaxValue() {
			return maxValue;
		}
		public void setMaxValue(String maxValue) {
			this.maxValue = maxValue;
		}
		public int getId() {
			return id;
		}
		public Unit getUnit() {
			return unit;
		}
		public void setUnit(Unit unit) {
			this.unit = unit;
		}
	}
	
	public static class Assessor {
		private final String displayName;
		
		public Assessor(String name) {
			displayName = name;
		}

		public String getDisplayName() {
			return displayName;
		}
	}
	
	public static class Unit {
		private final String displayName;
		
		public Unit(String name) {
			displayName = name;
		}

		public String getDisplayName() {
			return displayName;
		}
	}

	  /**
	   * Refresh all displays.
	   */
	  public void refreshDisplays() {
	    dataProvider.refresh();
	  }

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}
}
