package com.example.services;

import java.util.List;

import com.example.sheared.dto.DepartementDto;

public interface DepartementService {

	
	List<DepartementDto> getAllDepartements(String email);
	
	
	DepartementDto createDepartement(DepartementDto departement, String email);
	
	DepartementDto getDepartement(String departementId);
	
	void deleteDepartement(String departementId );
	
	
}
