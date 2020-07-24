package com.example.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity(name="groups")
public class GroupEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 734816981413182154L;
	
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="name", length=30)
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="groups_users", joinColumns = {@JoinColumn(name="groups_id")}, inverseJoinColumns = {@JoinColumn(name="users_id")})
	private Set<UserEntity> users = new HashSet<>();

}
