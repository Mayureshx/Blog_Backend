package org.blog.service;

import java.util.Optional;

import org.blog.exception.ResourceNotFoundException;
import org.blog.model.User;

public interface UserService {
	
	public void addUser(User user);
	public void updateUser(User user);
	public Optional<User> getUserByEmail(String email);
	public void deleteUserById(long userId)throws ResourceNotFoundException;

}
