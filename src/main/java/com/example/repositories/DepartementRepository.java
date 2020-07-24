package com.example.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.UserEntity;
import com.example.entities.DepartementEntity;

@Repository
public interface DepartementRepository extends CrudRepository <DepartementEntity, Long>{
	
	List<DepartementEntity> findByUser(UserEntity currentUser);

	DepartementEntity findByDepartementId(String departementId);
	
}
