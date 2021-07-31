package com.cityhelpz.cityhelpzapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 50, nullable = false)
	private String username;
	
	@Column(length = 100, nullable = false)
	private String email;
	
	@Column(length = 200, nullable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(length = 15, nullable = false)
	private String roles;
	
	@Column(length = 6)
	private String zipcode;
	
	@Column(length = 50)
	private String state;
	
	@Column(length = 100)
	private String lattitude;
	
	@Column(length = 100)
	private String longitude;
	
	@Column(name="is_active", nullable = false)
	private boolean isActive;
	
	@Column(name="created_at", nullable = false)
	private Date createdAt;
	
	@Column(name="updated_at", nullable = false)
	private Date updatedAt;
	
	@Column(name="deleted_at")
	private Date deletedAt;

	public User() {
		super();
	}

	public User(String name, String username, String email, String password, String roles, String zipcode, String state,
			String lattitude, String longitude, boolean isActive, Date createdAt, Date updatedAt, Date deletedAt) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.zipcode = zipcode;
		this.state = state;
		this.lattitude = lattitude;
		this.longitude = longitude;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLattitude() {
		return lattitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
	
	
	

}
