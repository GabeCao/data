package com.zytx.service.impl;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zytx.dao.TimeDao;
import com.zytx.service.TimeService;

@Service
@Transactional
public class TimeServiceImpl implements TimeService {

	@Autowired
	private TimeDao timeDao;

	
	
	//获得开始时间startTime~结束时间endTime ，这个时间段的相关数据：运行次数，运行时间，运行效率，异常次数
	//获得开始时间startTime~结束时间endTime中 每一天总共的运行次数
	public Map<String, List<String>> getRunNumbersDataByDay(Date startTime, Date endTime, String registNumber,
			String deviceID) {
		Map<String,List<String>> map = new HashedMap();
		List<String> xData = new ArrayList<String>();
		List<String> yData = new ArrayList<String>();
		
		Date tempTime;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startTime);
		//number 和 flag 用于判断 返回的所有值是否都为null,若是，则向上一层返回空的Map
		int number = 0;
		int flag = 0;
		
		while(startTime.getTime()<endTime.getTime()) {
			number++;
			Integer month = calendar.get(Calendar.MONTH)+1;
			Integer day = calendar.get(Calendar.DAY_OF_MONTH);
			xData.add(day+"");
			
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			tempTime = calendar.getTime();
			
			Integer runNumbers = timeDao.getRunNumbersByDay(startTime, endTime, day, 
					registNumber, deviceID);
			
			if(runNumbers == null) {
				runNumbers = 0;
				flag++;
			}
				
			yData.add(runNumbers+"");
			startTime = tempTime;
		}
		if(number == flag) {
			return null;
		}
		map.put("category", xData);
		map.put("data", yData);
		return map;
	}
	
	//获得开始时间startTime~结束时间endTime中 每一天总共的异常次数
	public Map<String, List<String>> getAbnormalNumbersDataByDay(Date startTime, Date endTime, String registNumber,
			String deviceID) {
		Map<String,List<String>> map = new HashedMap();
		List<String> xData = new ArrayList<String>();
		List<String> yData = new ArrayList<String>();
		
		Date tempTime;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startTime);
		
		//number 和 flag 用于判断 返回的所有值是否都为null,若是，则向上一层返回空的Map
		int number = 0;
		int flag = 0;
		while(startTime.getTime()<endTime.getTime()) {
			number++;
			Integer month = calendar.get(Calendar.MONTH)+1;
			Integer day = calendar.get(Calendar.DAY_OF_MONTH);
			xData.add(day+"");
			
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			tempTime = calendar.getTime();
			
			Integer runNumbers = timeDao.getabnormalNumbersByDay(startTime, endTime, day, 
					registNumber, deviceID);
			
			if(runNumbers == null) {
				runNumbers = 0;
				flag++;
			}	
			yData.add(runNumbers+"");
			startTime = tempTime;
		}
		if(number == flag) {
			return null;
		}
		map.put("category", xData);
		map.put("data", yData);
		return map;
	}

	//获得开始时间startTime~结束时间endTime中 每一天总共的运行时间
	public Map<String, List<String>> getRunTimeDataByDay(Date startTime,Date endTime,String registNumber,String deviceID) {
		Map<String,List<String>> map = new HashedMap();
		List<String> xData = new ArrayList<String>();
		List<String> yData = new ArrayList<String>();
		
		Date tempTime;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startTime);
		
		//number 和 flag 用于判断 返回的所有值是否都为null,若是，则向上一层返回空的Map
		int number = 0;
		int flag = 0;
		while(startTime.getTime()<endTime.getTime()) {
			number++;
			Integer month = calendar.get(Calendar.MONTH)+1;
			Integer day = calendar.get(Calendar.DAY_OF_MONTH);
			xData.add(day+"");
			
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			tempTime = calendar.getTime();
			
			Long runTime = timeDao.getRunTimeByDay(startTime, endTime, day, 
					registNumber, deviceID);
			
			if(runTime == null) {
				runTime = (long) 0;
				flag++;
			}
			double temp = runTime/60;
			DecimalFormat decimalFormat = new DecimalFormat(".0");
			yData.add(decimalFormat.format(temp));
			startTime = tempTime;
		}
		if(number == flag) 
			return null;
		map.put("category", xData);
		map.put("data", yData);
		return map;
	}
	
	//获得开始时间startTime~结束时间endTime中 的运行效率
	public Map getoperationalEfficiency(Date startTime,Date endTime,String registNumber,String deviceId) {
		Map<String, Long> map = new HashedMap();
		Long totalTime = (endTime.getTime()-startTime.getTime())/1000;
		Long totalrunTime = timeDao.getTotalRunTime(startTime, endTime, registNumber, deviceId);
		if(totalrunTime ==null) {
			return null;
		}
		
		Long idleTime = totalTime - totalrunTime;
		map.put("totalrunTime", totalrunTime/60);
		map.put("idleTime", idleTime/60);
		return map;
	}

	
	
	//获取每个小时的相关数据：总的运行时间，总的运行次数，总的异常次数
	public Map<String, List<String>> getRunTimeDataByHour(Date startTime, Date endTime, String registNumber,
			String deviceID) {
		Map<String,List<String>> map = new HashedMap();
		List<String> xData = new ArrayList<String>();
		List<String> yData = new ArrayList<String>();
		
		int flag = 0;
		for(int i =1;i<=24;i++) {
			xData.add(i+"");
			Long runTime = timeDao.getRunnTimeByHour(startTime, endTime, i, registNumber, deviceID);
			if(runTime == null) {
				runTime = (long) 0;
				flag++;
			}
			double temp = runTime/60;
			DecimalFormat decimalFormat = new DecimalFormat(".0");
			yData.add(decimalFormat.format(temp));
		}
		if(flag == 24) {
			return null;
		}
		map.put("category", xData);
		map.put("data", yData);
		return map;
	}

	public Map<String, List<String>> getRunNumbersDataByHour(Date startTime, Date endTime, String registNumber,
			String deviceID) {
		Map<String,List<String>> map = new HashedMap();
		List<String> xData = new ArrayList<String>();
		List<String> yData = new ArrayList<String>();
		
		int flag = 0;
		for(int i =1;i<=24;i++) {
			xData.add(i+"");
			Integer runNumber = timeDao.getRunnNumbersByHour(startTime, endTime, i, registNumber, deviceID);
			if(runNumber == null) {
				runNumber = 0;
				flag++;
			}
			yData.add(runNumber+"");
		}
		if(flag == 24) {
			return null;
		}
		
		map.put("category", xData);
		map.put("data", yData);
		return map;
	}

	public Map<String, List<String>> getAbnormalNumbersDataByHour(Date startTime, Date endTime, String registNumber,
			String deviceID) {
		Map<String,List<String>> map = new HashedMap();
		List<String> xData = new ArrayList<String>();
		List<String> yData = new ArrayList<String>();
		
		int flag = 0;
		for(int i =1;i<=24;i++) {
			xData.add(i+"");
			Integer abnormalNumber = timeDao.getAbnormalNumbersByHour(startTime, endTime, i, registNumber, deviceID);
			if(abnormalNumber == null) {
				abnormalNumber = 0;
				flag++;
			}
			yData.add(abnormalNumber+"");
		}
		if(flag == 24) {
			return null;
		}
		
		map.put("category", xData);
		map.put("data", yData);
		return map;
	}

	public Map<String, List<String>> getRunTimeDataByMonth(Date startTime, Date endTime, String registNumber,
			String deviceID) {
		Map<String,List<String>> map = new HashedMap();
		List<String> xData = new ArrayList<String>();
		List<String> yData = new ArrayList<String>();
		
		int flag = 0;
		for(int i =1;i<=12;i++) {
			xData.add(i+"");
			Long res = timeDao.getRunnTimeByMonth(startTime, endTime, i, registNumber, deviceID);
			if(res == null) {
				flag++;
				res = (long) 0;
			}
			yData.add(res/3600+"");
		}
		if(flag == 12) 
			return null;
		map.put("category", xData);
		map.put("data", yData);
		return map;
	}

	public Map<String, List<String>> getRunNumbersDataByMonth(Date startTime, Date endTime, String registNumber,
			String deviceID) {
		Map<String,List<String>> map = new HashedMap();
		List<String> xData = new ArrayList<String>();
		List<String> yData = new ArrayList<String>();
		
		int flag = 0;
		for(int i =1;i<=12;i++) {
			xData.add(i+"");
			Integer res = timeDao.getRunnNumberesByMonth(startTime, endTime, i, registNumber, deviceID);
			if(res == null) {
				flag++;
				res = 0;
			}
			yData.add(res+"");
		}
		if(flag == 12) 
			return null;
		map.put("category", xData);
		map.put("data", yData);
		return map;
	}

	public Map<String, List<String>> getAbnormalNumbersDataByMonth(Date startTime, Date endTime, String registNumber,
			String deviceID) {
		Map<String,List<String>> map = new HashedMap();
		List<String> xData = new ArrayList<String>();
		List<String> yData = new ArrayList<String>();
		
		int flag = 0;
		for(int i =1;i<=12;i++) {
			xData.add(i+"");
			Integer res = timeDao.getabnormalNumbersByMonth(startTime, endTime, i, registNumber, deviceID);
			if(res == null) {
				flag++;
				res = 0;
			}
			yData.add(res+"");
		}
		if(flag == 12) 
			return null;
		map.put("category", xData);
		map.put("data", yData);
		return map;
	}
}
