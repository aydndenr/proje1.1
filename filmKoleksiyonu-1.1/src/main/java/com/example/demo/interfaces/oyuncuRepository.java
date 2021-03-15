package com.example.demo.interfaces;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.filmModel;
import com.example.demo.models.oyuncuModel;




@Repository
public interface oyuncuRepository extends JpaRepository<oyuncuModel,Long> {

	ArrayList<oyuncuModel> findTop10BynameIgnoreCaseContaining(String content);

	
	
}




