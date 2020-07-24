package com.example.sheared.dto;

public class DepartementDto {
	
	private long id;
	private String departementId;
	private String nomemp;
	private  String typeemp;
	private String remuneration;
	private String typedep;
	private UserDto user;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomemp() {
		return nomemp;
	}
	public String getDepartementId() {
		return departementId;
	}
	public void setDepartementId(String departementId) {
		this.departementId = departementId;
	}
	public void setNomemp(String nomemp) {
		this.nomemp = nomemp;
	}
	public String getTypeemp() {
		return typeemp;
	}
	public void setTypeemp(String typeemp) {
		this.typeemp = typeemp;
	}
	public String getRemuneration() {
		return remuneration;
	}
	public void setRemuneration(String remuneration) {
		this.remuneration = remuneration;
	}
	public String getTypedep() {
		return typedep;
	}
	public void setTypedep(String typedep) {
		this.typedep = typedep;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}

}
