package com.example.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="User")
public class User {
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_name")
	private String name;

	@JsonManagedReference
	@ManyToMany(  cascade = {
	        CascadeType.PERSIST, 
	        CascadeType.MERGE, 
	        CascadeType.DETACH,
	        CascadeType.REFRESH
	    })
	@JoinTable(
	  name = "UserPermission", 
	  joinColumns = @JoinColumn(name = "UserId"), 
	  inverseJoinColumns = @JoinColumn(name = "PermissionId"))
	private Set<Permission> permissions = new HashSet<Permission>();
	
	
	
}
