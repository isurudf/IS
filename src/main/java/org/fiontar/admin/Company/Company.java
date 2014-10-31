package org.fiontar.admin.Company;

public class Company {
	private String name;
	private String description;
	private String shortName;
	private String logo;
	private String fields;
	private int id;
	private int capacity;
	private String building;
	private String tel;
	private String buildTel;
	private String undergrads;

	public Company() {
	}

	public Company(String name, String description, String shortName, String logo, String fields,
	               int id, int capacity, String building, String tel, String buildTel,
	               String undergrads) {
		this.name = name;
		this.description = description;
		this.shortName = shortName;
		this.logo = logo;
		this.fields = fields;
		this.id = id;
		this.capacity = capacity;
		this.building = building;
		this.tel = tel;
		this.buildTel = buildTel;
		this.undergrads = undergrads;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public String getFields() {
		return fields;
	}

	public String getLogo() {
		return logo;
	}

	public String getName() {
		return name;
	}

	public String getShortName() {
		return shortName;
	}

	public int getCapacity() {
		return capacity;
	}

	public String getBuildTel() {
		return buildTel;
	}

	public String getBuilding() {
		return building;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public void setBuildTel(String buildTel) {
		this.buildTel = buildTel;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getUndergrads() {
		return undergrads;
	}

}
