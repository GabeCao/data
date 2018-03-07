package com.zytx.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TimeDao {
	
	//获得一个月的相关内容
	//获得一个月的总共运行时间
	public Long getRunnTimeByMonth(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("month") int month,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	//获得一个月的总共运行次数
	public Integer getRunnNumberesByMonth(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("month") int month,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	//获得一个月的总共异常次数
	public Integer getabnormalNumbersByMonth(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("month") int month,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	
	//获取一个小时中的相关内容
	//获取一个小时的总运行时间
	public Long getRunnTimeByHour(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("hour") int hour,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	
	//获取一个小时的总运行次数
	public Integer getRunnNumbersByHour(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("hour") int hour,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	//获取一个小时的总异常次数
	public Integer getAbnormalNumbersByHour(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("hour") int hour,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	
	//获得每天的异常次数
	public Integer getabnormalNumbersByDay(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("day") int day,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	
	//获得每天的总共运行时间
	public Long getRunTimeByDay(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("day") int day,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	//获得每天的总共运行次数
	public Integer getRunNumbersByDay(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,@Param("day") int day,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	
	//获得startTime~endTime的总共运行时间
	public Long getTotalRunTime(@Param("startTime") Date startTime,@Param("endTime") Date endTime,
			@Param("registNumber") String registNumber,@Param("deviceID") String deviceID);
	
}
