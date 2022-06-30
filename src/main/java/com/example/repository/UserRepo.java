package com.example.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Permission;
import com.example.entity.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	public List<User> findByPermissions(Permission permissions);
	
}
