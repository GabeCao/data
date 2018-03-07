package com.zytx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zytx.dao.TwoCodeElevatorInfoDao;
import com.zytx.entity.TwoCodeElevatorInfo;
import com.zytx.service.TwoCodeElevatorInfoService;

@Transactional
@Service
public class TwoCodeElevatorInfoServiceImpl implements TwoCodeElevatorInfoService {

	@Autowired
	private TwoCodeElevatorInfoDao twoCodeElevatorInfoDao;
	
	public TwoCodeElevatorInfo getTwoCodeElevatorInfoByRegistNumber(String registNumber) {
		
		return twoCodeElevatorInfoDao.getTwoCodeElevatorInfoByRegistNumber(registNumber);
	}

}
