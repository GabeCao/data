package com.zytx.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TimeService {
	


	public Map getoperationalEfficiency(Date startTime,Date endTime,String registNumber,String deviceID);
	
	//获取一天中的相关内容:运行时间，运行次数，异常次数
	public Map<String, List<String>> getRunTimeDataByDay(Date startTime,Date endTime,String registNumber,String deviceID);
	public Map<String, List<String>> getRunNumbersDataByDay(Date startTime,Date endTime,String registNumber,String deviceID);
	public Map<String, List<String>> getAbnormalNumbersDataByDay(Date startTime,Date endTime,String registNumber,String deviceID);
	
	//获取一个小时中的相关内容:运行时间，运行次数，异常次数
	public Map<String, List<String>> getRunTimeDataByHour(Date startTime,Date endTime,String registNumber,String deviceID);
	public Map<String, List<String>> getRunNumbersDataByHour(Date startTime,Date endTime,String registNumber,String deviceID);
	public Map<String, List<String>> getAbnormalNumbersDataByHour(Date startTime,Date endTime,String registNumber,String deviceID);

	//获取一个月中的相关内容:运行时间，运行次数，异常次数
	public Map<String, List<String>> getRunTimeDataByMonth(Date startTime,Date endTime,String registNumber,String deviceID);
	public Map<String, List<String>> getRunNumbersDataByMonth(Date startTime,Date endTime,String registNumber,String deviceID);
	public Map<String, List<String>> getAbnormalNumbersDataByMonth(Date startTime,Date endTime,String registNumber,String deviceID);
}
