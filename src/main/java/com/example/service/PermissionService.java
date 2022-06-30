package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Permission;
import com.example.repository.PermisRepo;



@Service
public class PermissionService {
	
	@Autowired
	PermisRepo repo;
	
	public Permission findByName(String name) {
		return repo.findByName(name);
	}
	
	
}
