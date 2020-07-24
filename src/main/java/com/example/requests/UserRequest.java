package com.example.requests;

import java.util.List;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class UserRequest {
	
	@NotNull(message="Ce champ ne doit pas etre null !")
	@Size(min=3,message = "Ce champ doit avoir au moin 3 caratère")
	private String firstName;
	
	@NotNull(message="Ce champ ne doit pas etre null !")
	@Size(min=3,message = "Ce champ doit avoir au moin 3 caratère")
	private String lastName;
	
	@NotNull(message="Ce champ ne doit pas etre null !")

	private String email;
	
	@NotNull(message="Ce champ ne doit pas etre null !")
	@Size(min=8, max=12, message="Mot de passe doit avoir entre 8 et 12 caractère !")
	@Pattern(regexp="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message="ce mot de passe doit avoir des lettres en Maj et Minsc et numero")
	private String password;
	
	
	private Boolean admin;
	
	
	private List<DepartementRequest> departements;
	private ContactRequest contact ;
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public List<DepartementRequest> getDepartements() {
		return departements;
	}
	public void setDepartements(List<DepartementRequest> departements) {
		this.departements = departements;
	}
	public ContactRequest getContact() {
		return contact;
	}
	public void setContact(ContactRequest contact) {
		this.contact = contact;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	
	
	
	
	
}
