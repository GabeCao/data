package com.zytx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zytx.dao.TimeDao;


public class TimeDaoTest extends BaseTest{

	@Autowired
	private TimeDao timeDao;
	
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
		
		for(int i =1;i<=12;i++) {
			Long res = timeDao.getRunnTimeByMonth(startTime, endTime, i, registNumber, deviceID);
			System.out.println(res);
		}
	}
}
