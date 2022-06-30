package com.example.dto;

import java.util.Set;

import com.example.entity.Permission;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	public Long id;
	public String name;
	
	public Set<PermissionDTO> permissions;
}
