package com.maingocdieu.SportShop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maingocdieu.SportShop.dto.UserDto;
import com.maingocdieu.SportShop.entity.User;

@Component
public class UserConverter {

	@Autowired
	private ModelMapper modelMapper;

	public UserDto convertToDto(User user) {
		UserDto result = modelMapper.map(user, UserDto.class);
		return result;
	}
	
	public User converToEntity(UserDto userDto) {
		User result = modelMapper.map(userDto, User.class);
		return result;
	}
}
