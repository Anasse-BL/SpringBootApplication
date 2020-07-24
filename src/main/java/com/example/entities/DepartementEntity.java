package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;





@Entity(name="departements")
public class DepartementEntity implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -584354483891588860L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(length=20, nullable=false)
	private String departementId;
	
	@Column(length=20, nullable=false)
	private String nomemp;
	
	@Column(length=20, nullable=false)
	private  String typeemp;
	
	@Column(length=20, nullable=false)
	private String remuneration;
	
	@Column(length=20, nullable=false)
	private String typedep;
	
	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepartementId() {
		return departementId;
	}

	public void setDepartementId(String departementId) {
		this.departementId = departementId;
	}

	public String getNomemp() {
		return nomemp;
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}


}
