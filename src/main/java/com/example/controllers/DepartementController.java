package com.example.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.requests.DepartementRequest;
import com.example.responses.DepartementResponse;
import com.example.services.DepartementService;
import com.example.sheared.dto.DepartementDto;

@RestController
@RequestMapping("/departements")
public class DepartementController {

	@Autowired
	DepartementService departementService;
	
	@GetMapping
	public ResponseEntity<List<DepartementResponse>>getDepartements(Principal principal){
		
		
		
		List<DepartementDto> departements = departementService.getAllDepartements(principal.getName());
		
		Type listType = new TypeToken<List<DepartementResponse>>() {}.getType();
		List<DepartementResponse> departementsResponse = new ModelMapper().map(departements, listType);
		
		return new ResponseEntity<List<DepartementResponse>>(departementsResponse, HttpStatus.OK);
		
	}
	
	@PostMapping(
			consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, 
		    produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<DepartementResponse> StoreDepartement(@RequestBody DepartementRequest departementRequest, Principal principal) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		DepartementDto departementDto = modelMapper.map(departementRequest, DepartementDto.class);
		DepartementDto createDepartement = departementService.createDepartement(departementDto, principal.getName());
		
		DepartementResponse newDepartement = modelMapper.map(createDepartement, DepartementResponse.class);
		
		return new ResponseEntity<DepartementResponse>(newDepartement, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<DepartementResponse> getOneDepartement(@PathVariable(name="id") String departementId) {
		
		DepartementDto departementDto = departementService.getDepartement(departementId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		DepartementResponse departementResponse = modelMapper.map(departementDto, DepartementResponse.class);
		
		return new ResponseEntity<DepartementResponse>(departementResponse, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateDepartemente(@PathVariable(name="id") String departementId) {
		return new ResponseEntity<>("update departements", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDepartemente(@PathVariable(name="id") String departementId) {
		
		departementService.deleteDepartement(departementId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
}
