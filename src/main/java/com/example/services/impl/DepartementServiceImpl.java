package com.example.services.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.DepartementEntity;
import com.example.entities.UserEntity;
import com.example.repositories.DepartementRepository;
import com.example.repositories.UserRepository;
import com.example.services.DepartementService;
import com.example.sheared.Utils;
import com.example.sheared.dto.DepartementDto;
import com.example.sheared.dto.UserDto;


@Service
public class DepartementServiceImpl implements DepartementService {

	
	
	@Autowired
	DepartementRepository departementRepository;
	
	@Autowired
	
	UserRepository userRepository;
	
	@Autowired 
	Utils util ;
	
	
	@Override
	public List<DepartementDto> getAllDepartements(String email) {
		
		
		// TODO Auto-generated method stub
		
		UserEntity currentUser = userRepository.findByEmail(email);
		
		
		
		List<DepartementEntity> departements = currentUser.getAdmin() == true ? (List<DepartementEntity>) departementRepository.findAll(): departementRepository.findByUser(currentUser);
		
		Type listType = new TypeToken<List<DepartementDto>>() {}.getType();
		List<DepartementDto> departementsDto = new ModelMapper().map(departements, listType);
		return departementsDto;
	}
	
	
	
	
	@Override
	public DepartementDto createDepartement(DepartementDto departement, String email) {
		
		UserEntity currentUser = userRepository.findByEmail(email);
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(currentUser, UserDto.class);
		
		departement.setDepartementId(util.generateStringId(30));
		departement.setUser(userDto);
		
		DepartementEntity departementEntity = modelMapper.map(departement, DepartementEntity.class); 
		
		DepartementEntity newDepartement = departementRepository.save(departementEntity);
		
		DepartementDto departementDto = modelMapper.map(newDepartement, DepartementDto.class);
		
		return departementDto;
	}
	
	@Override
	public DepartementDto getDepartement(String departementId) {
		
		DepartementEntity departementEntity = departementRepository.findByDepartementId(departementId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		DepartementDto departementDto = modelMapper.map(departementEntity, DepartementDto.class);
		
		return departementDto;
	}

	@Override
	public void deleteDepartement(String departementId) {
		
		DepartementEntity departement = departementRepository.findByDepartementId(departementId);
		
		if(departement == null) throw new RuntimeException("Departement not found");
		
		departementRepository.delete(departement);
	
	}


}
