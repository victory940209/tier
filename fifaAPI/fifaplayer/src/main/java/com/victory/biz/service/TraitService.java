package com.victory.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victory.biz.model.TraitVo;
import com.victory.biz.repository.TraitMongoDBRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TraitService {

	@Autowired
	private TraitMongoDBRepository traitMongoDBRepository;

	public List<TraitVo> getTrait() {

		return traitMongoDBRepository.findAll();

	}

}
