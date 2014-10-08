package com.ufpor.app.server;

import com.google.appengine.api.users.User;
import com.ufpor.app.client.EnvironmentDM;

import javax.jdo.annotations.*;
import java.util.Date;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Environment {
	@PrimaryKey
	  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	  private Long id;
	  @Persistent
	  private User user;
	  @Persistent
	  private String name;
	  @Persistent
	  private String area;
	  @Persistent
	  private Date createDate;

	  public Environment() {
	    this.createDate = new Date();
	  }

	  public Environment(User user, String symbol, String area) {
	    this();
	    this.user = user;
	    this.name = symbol;
	    this.area = area;
	  }
	  
	  public Long getId() {
		    return this.id;
		  }

		  public User getUser() {
		    return this.user;
		  }

		  public String getName() {
		    return this.name;
		  }

		  public Date getCreateDate() {
		    return this.createDate;
		  }

		  public void setUser(User user) {
		    this.user = user;
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
		
		public EnvironmentDM getEnvDM() {
			EnvironmentDM result = new EnvironmentDM();
			
			result.setArea(getArea());
			result.setCreateDate(getCreateDate().getTime());
			result.setId(getId());
			result.setName(getName());
			result.setUser(getUser().getEmail());
			
			return result;
		}
	  
}
