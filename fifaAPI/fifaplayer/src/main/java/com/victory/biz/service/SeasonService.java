package com.victory.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victory.biz.model.SeasonVo;
import com.victory.biz.repository.SeasonMongoDBRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SeasonService {

	@Autowired
	private SeasonMongoDBRepository seasonMongoDBRepository;

	public List<SeasonVo> getSeason() {

		return seasonMongoDBRepository.findAll();

	}

}
