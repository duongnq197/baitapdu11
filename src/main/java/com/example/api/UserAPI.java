package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserDTO;
import com.example.entity.Permission;
import com.example.service.UserService;

@RestController
public class UserAPI {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
		return ResponseEntity.ok(userService.createUserDTO(dto));
	}
	@GetMapping("/getUser")
	public ResponseEntity<List<UserDTO>> getAllUserByPermissioin(@RequestHeader(value = "permis") String permis) {
		return ResponseEntity.ok(userService.findByPermisson(permis));
	}
	
	@PostMapping("/updateUser")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto) {
		return ResponseEntity.ok(userService.update(dto));
	} 
	
	@GetMapping("/deleteUser/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
		return ResponseEntity.ok(userService.delete(id));
	} 
}
