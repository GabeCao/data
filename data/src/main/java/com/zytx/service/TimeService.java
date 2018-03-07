package com.zytx.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TimeService {
	


	public Map getoperationalEfficiency(Date startTime,Date endTime,String registNumber,String deviceID);
	
	//��ȡһ���е��������:����ʱ�䣬���д������쳣����
	public Map<String, List<String>> getRunTimeDataByDay(Date startTime,Date endTime,String registNumber,String deviceID);
	public Map<String, List<String>> getRunNumbersDataByDay(Date startTime,Date endTime,String registNumber,String deviceID);
	public Map<String, List<String>> getAbnormalNumbersDataByDay(Date startTime,Date endTime,String registNumber,String deviceID);
	
	//��ȡһ��Сʱ�е��������:����ʱ�䣬���д������쳣����
	public Map<String, List<String>> getRunTimeDataByHour(Date startTime,Date endTime,String registNumber,String deviceID);
	public Map<String, List<String>> getRunNumbersDataByHour(Date startTime,Date endTime,String registNumber,String deviceID);
	public Map<String, List<String>> getAbnormalNumbersDataByHour(Date startTime,Date endTime,String registNumber,String deviceID);

	//��ȡһ�����е��������:����ʱ�䣬���д������쳣����
	public Map<String, List<String>> getRunTimeDataByMonth(Date startTime,Date endTime,String registNumber,String deviceID);
	public Map<String, List<String>> getRunNumbersDataByMonth(Date startTime,Date endTime,String registNumber,String deviceID);
	public Map<String, List<String>> getAbnormalNumbersDataByMonth(Date startTime,Date endTime,String registNumber,String deviceID);
}
