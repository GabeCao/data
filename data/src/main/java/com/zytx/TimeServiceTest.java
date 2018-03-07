package com.zytx;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zytx.service.TimeService;


public class TimeServiceTest extends BaseTest{

	@Autowired
	private TimeService timeService;
	
	@Test
	public void testServiceImpl() throws ParseException {
		String StartTime = "2018";
		String registNumber="181796";
		String deviceID="1000000100000004";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		Date startTime = simpleDateFormat.parse(StartTime);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startTime);
		calendar.add(Calendar.YEAR, 1);
		Date endTime = calendar.getTime();

		Map map1 = timeService.getAbnormalNumbersDataByMonth(startTime, endTime, registNumber, deviceID);
		Map map2 = timeService.getRunNumbersDataByMonth(startTime, endTime, registNumber, deviceID);
		Map map3 = timeService.getRunTimeDataByMonth(startTime, endTime, registNumber, deviceID);
		
	}
}
