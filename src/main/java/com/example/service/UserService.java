package com.example.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.converter.UserConverter;
import com.example.dto.UserDTO;
import com.example.entity.Permission;
import com.example.entity.User;
import com.example.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserConverter mapper;
	
	@Autowired
	PermissionService perservice;
	
	@Autowired
	UserRepo userRepo;
	
	@Transactional
	public UserDTO createUserDTO(UserDTO dto) {
		User user = mapper.convertToEntity(dto);
		Set<Permission> permissions = new HashSet<Permission>();

		user.getPermissions().forEach(x ->{
			Permission per = perservice.findByName(x.getName());
			if(null == per){
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found role: " + x.getName());
			} else {
				permissions.add(per);
				per.getUsers().add(user);
			}
		});
		user.setPermissions(permissions);

		return mapper.convertToDTO(userRepo.save(user));
	}
	
	@Transactional
	public List<UserDTO> findByPermisson( String permis){
		
		List<User> findByPermissions = userRepo.findByPermissions(perservice.findByName(permis));
		
		List<UserDTO> listDTO =  findByPermissions.stream().map(x -> mapper.convertToDTO(x)).collect(Collectors.toList());
		return listDTO;
		
	}

	@Transactional
	public UserDTO update(UserDTO dto) {
		Optional<User> user  = userRepo.findById(dto.getId());
		
		if(user.isPresent()) {
			User entity = user.get();
			entity.setName(dto.getName());
			User usSave = userRepo.save(entity);
			
			return  mapper.convertToDTO(usSave);
			
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found user");
		}
		
		
	}
	
	@Transactional
	public UserDTO delete(Long id) {
		
		Optional<User> user  = userRepo.findById(id);
		if(user.isPresent()) {
			User entity = user.get();
			userRepo.delete(user.get());
			return  mapper.convertToDTO(entity);
			
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found user");
		}
	}
	
	
}
