package com.ufpor.app.client.view;

import java.io.Serializable;



public class EnvironmentDM implements Serializable {

	private static final long serialVersionUID = -53971233308699772L;

	private Long id;

	  private String user;

	  private String name;

	  private String area;

	  private Long createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
}
