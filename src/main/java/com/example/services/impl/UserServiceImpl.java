package com.example.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import com.example.entities.UserEntity;
import com.example.repositories.UserRepository;

import com.example.services.UserService;
import com.example.sheared.Utils;

import com.example.sheared.dto.UserDto;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired

	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils util;

	@Override
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub

		UserEntity checkUser	=userRepository.findByEmail(user.getEmail());
		
		if(checkUser!= null) throw new RuntimeException("User Already exists !");

			UserEntity userEntity = new UserEntity();
			BeanUtils.copyProperties(user, userEntity);
			
			userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			
			userEntity.setUserId(util.generateStringId(32));
			
			UserEntity newUser = userRepository.save(userEntity);
			
			UserDto userDto = new UserDto();
			
			BeanUtils.copyProperties(newUser, userDto);
			
			return userDto;
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());

	}

	@Override
	public UserDto getUser(String email) {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByEmail(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException(userId);

	UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);

	return userDto;
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		UserEntity userUpdated = userRepository.save(userEntity);
		UserDto user = new UserDto();
		BeanUtils.copyProperties(userUpdated, user);
		return user;
	}
	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException(userId);

		userRepository.delete(userEntity);
	}
	@Override
	public List<UserDto> getUsers(int page, int limit, String search ,int status) {
	// TODO Auto-generated method stub
		if (page > 0)
			page -= 1;
		List<UserDto> usersDto = new ArrayList<>();

	Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<UserEntity> userPage;
		
	if(search.isEmpty()) {
			
			userPage = userRepository.findAllUsers(pageableRequest);
		}
		else {
			
			 userPage = userRepository.findAllUserByCriteria(pageableRequest, search, status);
			
		}
		
		List<UserEntity> users = userPage.getContent();

		for (UserEntity userEntity : users) {

			ModelMapper modelMapper = new ModelMapper();
			UserDto user = modelMapper.map(userEntity, UserDto.class);
			
			usersDto.add(user);
		}

		return usersDto;
	}
}
