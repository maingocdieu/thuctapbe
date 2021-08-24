package com.maingocdieu.SportShop.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Admin
 *
 */
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class User extends BaseEntity {

	@NotBlank
	@Size(max = 20)
	@Column(nullable = false)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	@Column(nullable = false)
	private String email;

	@NotBlank
	@Size(max = 120)
	@Column(nullable = false)
	private String password;

	@Column(name = "Adress", columnDefinition = "nvarchar(255)")
	private String address;

	@Column(name = "Phone")
	private String phone;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String fullName;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	

	public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password, Integer status, String address, String phone, String fullName,
			Set<Role> roles, Set<Order> orders) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		
		this.address = address;
		this.phone = phone;
		this.fullName = fullName;
		this.roles = roles;
		this.orders = orders;
	}

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<Order> orders;

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}