package com.zytx;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zytx.entity.TwoCodeElevatorInfo;
import com.zytx.service.TwoCodeElevatorInfoService;

public class TwoCodeElevatorInfoServiceTEst2 extends BaseTest{
	
	@Autowired
	private TwoCodeElevatorInfoService twoCodeElevatorInfoService;
	
	@Test
	public void DaoTEst() {
		TwoCodeElevatorInfo twoCodeElevatorInfo = twoCodeElevatorInfoService.getTwoCodeElevatorInfoByRegistNumber("181796");
		System.out.println(twoCodeElevatorInfo);
	}

}
