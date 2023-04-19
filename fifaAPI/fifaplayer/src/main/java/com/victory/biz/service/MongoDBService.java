package com.victory.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.victory.biz.model.DivisionVo;
import com.victory.biz.repository.UserMongoDBRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MongoDBService {
	@Autowired
	UserMongoDBRepository userMongoDBRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	public DivisionVo selectDivisionId(int id) {

		DivisionVo divisionVo = userMongoDBRepository.findByDivisionId(id);

		log.info("divisionVo : " + divisionVo);

		return divisionVo;
	}

	public DivisionVo selectDivisionNm(String Name) {



		DivisionVo divisionVo = userMongoDBRepository.findByDivisionName(Name);

		log.info("divisionVo : " + divisionVo);

		return divisionVo;
	}

	public void saveDivision(String name, int age) {

//    	DivisionVo mongoDBTestModel = new DivisionVo();
//        mongoDBTestModel.setName(name);
//        mongoDBTestModel.setAge(age);
//
//        if (userMongoDBRepository.findByName(name) != null) {
//            log.info("[Service][update] name is already exist!!");
//            mongoDBTestModel.setId(mongoDBTestRepository.findByName(name).getId());
//        } else {
//            log.info("[Service][insert] New name received!!");
//        }
//
//        userMongoDBRepository.save(mongoDBTestModel);
	}
}
