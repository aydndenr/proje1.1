package com.example.demo.interfaces;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.models.filmModel;



@Repository
public interface filmRepository extends JpaRepository<filmModel,Long> {

	
	ArrayList<filmModel> findTop10BynameIgnoreCaseContaining(String content);
	ArrayList<filmModel> findByOrderByYearAsc();

}



