package com.example.converter;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.PermissionDTO;
import com.example.dto.UserDTO;
import com.example.entity.User;

@Component
public class UserConverter {

	@Autowired
	ModelMapper modelMapper;

	public User convertToEntity(UserDTO dto) {
		User entity = modelMapper.map(dto, User.class);

		return entity;
	}

	public UserDTO convertToDTO(User entity) {
		UserDTO dto = modelMapper.map(entity, UserDTO.class);
		Set<PermissionDTO> pers = entity.getPermissions().stream().map(x -> new PermissionDTO(x.getName())).collect(Collectors.toSet());
		return dto;
	}

}
