package com.example.demo.interfaces;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.oyuncuModel;
import com.example.demo.models.turModel;



@Repository
public interface turRepository extends JpaRepository<turModel,Long> {

	ArrayList<turModel> findTop10ByfilminturuIgnoreCaseContaining(String content);

}


		