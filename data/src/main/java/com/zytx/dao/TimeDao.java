package com.zytx.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TimeDao {
	
	//���һ���µ��������
	//���һ���µ��ܹ�����ʱ��
	public Long getRunnTimeByMonth(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("month") int month,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	//���һ���µ��ܹ����д���
	public Integer getRunnNumberesByMonth(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("month") int month,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	//���һ���µ��ܹ��쳣����
	public Integer getabnormalNumbersByMonth(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("month") int month,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	
	//��ȡһ��Сʱ�е��������
	//��ȡһ��Сʱ��������ʱ��
	public Long getRunnTimeByHour(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("hour") int hour,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	
	//��ȡһ��Сʱ�������д���
	public Integer getRunnNumbersByHour(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("hour") int hour,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	//��ȡһ��Сʱ�����쳣����
	public Integer getAbnormalNumbersByHour(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("hour") int hour,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	
	//���ÿ����쳣����
	public Integer getabnormalNumbersByDay(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("day") int day,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	
	//���ÿ����ܹ�����ʱ��
	public Long getRunTimeByDay(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("day") int day,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	//���ÿ����ܹ����д���
	public Integer getRunNumbersByDay(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("day") int day,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	
	//���startTime~endTime���ܹ�����ʱ��
	public Long getTotalRunTime(@Param("startTime") Date startTime,@Param("endTime") Date endTime,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	
}
