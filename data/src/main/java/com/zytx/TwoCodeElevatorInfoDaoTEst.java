package com.zytx;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zytx.dao.TwoCodeElevatorInfoDao;
import com.zytx.entity.TwoCodeElevatorInfo;

public class TwoCodeElevatorInfoDaoTEst extends BaseTest{
	
	@Autowired
	private TwoCodeElevatorInfoDao twoCodeElevatorInfoDao;
	
	@Test
	public void DaoTEst() {
		TwoCodeElevatorInfo twoCodeElevatorInfo = twoCodeElevatorInfoDao.getTwoCodeElevatorInfoByRegistNumber("181796");
		System.out.println(twoCodeElevatorInfo);
	}

}
