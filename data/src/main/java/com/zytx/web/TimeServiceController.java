package com.zytx.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zytx.entity.TwoCodeElevatorInfo;
import com.zytx.service.TimeService;
import com.zytx.service.TwoCodeElevatorInfoService;


@Controller
public class TimeServiceController {
	
	@Autowired
	private TimeService timeService;
	
	@Autowired
	private TwoCodeElevatorInfoService TwoCodeElevatorInfoService;
	
	@RequestMapping("/error")
	public String test(String EleID,String DeviceID, Model model) throws ParseException {
		
		return "error";
	}
	
	@RequestMapping("/show")
	public String showInfo(String EleID,String DeviceID,Model model) throws ParseException {
		model.addAttribute("registNumber",EleID);
		model.addAttribute("deviceID",DeviceID);
		
		TwoCodeElevatorInfo twoCodeElevatorInfo = TwoCodeElevatorInfoService.getTwoCodeElevatorInfoByRegistNumber(EleID);
		model.addAttribute("Addr",twoCodeElevatorInfo.getAddress());
		model.addAttribute("Building",twoCodeElevatorInfo.getBuildingName());
		
		return "show";
	}
	
	//处理年份相关的请求
	@RequestMapping("/yearlyRunTimeData")
	@ResponseBody
	public Map getyearlyRunTimeData(String time,String registNumber,String deviceID) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		Date startDate = simpleDateFormat.parse(time);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.YEAR, 1);
		Date endDate = calendar.getTime();
		Map map = timeService.getRunTimeDataByMonth(startDate, endDate, registNumber,deviceID);
		return map;
	}
	
	@RequestMapping("/yearlyRunNumbersData")
	@ResponseBody
	public Map getyearlyRunNumbersData(String time,String registNumber,String deviceID) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		Date startDate = simpleDateFormat.parse(time);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.YEAR, 1);
		Date endDate = calendar.getTime();
		
		Map map = timeService.getRunNumbersDataByMonth(startDate, endDate,registNumber,deviceID);
		return map;
	}
	
	
	@RequestMapping("/yearlyOperationalEfficiency")
	@ResponseBody
	public Map getyearlyOperationalEfficiency(String time,String registNumber,String deviceID) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		Date startDate = simpleDateFormat.parse(time);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.YEAR, 1);
		Date endDate = calendar.getTime();
		Map map = timeService.getoperationalEfficiency(startDate, endDate,registNumber,deviceID);
		return map;
	}
	
	@RequestMapping("/yearlyAbnormalNumbersData")
	@ResponseBody
	public Map getyearlyAbnormalNumbersData(String time,String registNumber,String deviceID) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		Date startDate = simpleDateFormat.parse(time);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.YEAR, 1);
		Date endDate = calendar.getTime();
		Map map = timeService.getAbnormalNumbersDataByMonth(startDate, endDate,registNumber,deviceID);
		return map;
	}
	
	
	
	
	//处理日的相关请求
	@RequestMapping("/daylyRunTimeData")
	@ResponseBody
	public Map getdayRunTimeData(String time,String registNumber,String deviceID) throws ParseException {
		String startTime = time+" 00:00:00";
		String endTime = time+" 23:59:59";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = simpleDateFormat.parse(startTime);
		Date endDate = simpleDateFormat.parse(endTime);
		Map map = timeService.getRunTimeDataByHour(startDate, endDate,registNumber,deviceID);
		return map;
	}
	
	@RequestMapping("/daylyRunNumbersData")
	@ResponseBody
	public Map getdayRunNumbersData(String time,String registNumber,String deviceID) throws ParseException {
		String startTime = time+" 00:00:00";
		String endTime = time+" 23:59:59";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = simpleDateFormat.parse(startTime);
		Date endDate = simpleDateFormat.parse(endTime);
		Map map = timeService.getRunNumbersDataByHour(startDate, endDate,registNumber,deviceID);
		return map;
	}
	
	@RequestMapping("/daylyAbnormalNumbersData")
	@ResponseBody
	public Map getdayAbnormalNumbersData(String time,String registNumber,String deviceID) throws ParseException {
		String startTime = time+" 00:00:00";
		String endTime = time+" 23:59:59";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = simpleDateFormat.parse(startTime);
		Date endDate = simpleDateFormat.parse(endTime);
		Map map = timeService.getAbnormalNumbersDataByHour(startDate, endDate,registNumber,deviceID);
		return map;
	}
	
	@RequestMapping("/daylyOperationalEfficiency")
	@ResponseBody
	public Map getdayOperationalEfficiency(String time,String registNumber,String deviceID) throws ParseException {
		String startTime = time+" 00:00:00";
		String endTime = time+" 23:59:59";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = simpleDateFormat.parse(startTime);
		Date endDate = simpleDateFormat.parse(endTime);
		Map map = timeService.getoperationalEfficiency(startDate, endDate,registNumber,deviceID);
		return map;
	}
	
	//处理月份的相关请求：
	@RequestMapping("/monthlyRunTimeData")
	@ResponseBody
	public Map getmonthData(String time,String registNumber,String deviceID) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		Date startDate = simpleDateFormat.parse(time);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.MONTH, 1);
		Date endDate = calendar.getTime();
		Map map = timeService.getRunTimeDataByDay(startDate, endDate, registNumber,deviceID);
		return map;
	} 
	
	@RequestMapping("/monthlyRunNumbersData")
	@ResponseBody
	public Map getmonthlyRunNumbersData(String time,String registNumber,String deviceID) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		Date startDate = simpleDateFormat.parse(time);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.MONTH, 1);
		Date endDate = calendar.getTime();
		
		Map map = timeService.getRunNumbersDataByDay(startDate,endDate,registNumber,deviceID);
		return map;
	} 
	
	@RequestMapping("/monthlyOperationalEfficiency")
	@ResponseBody
	public Map getmonthlyOperationalEfficiency(String time,String registNumber,String deviceID) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		Date date = simpleDateFormat.parse(time);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		Date endTime = calendar.getTime();
		Map map = timeService.getoperationalEfficiency(date, endTime,registNumber,deviceID);
		return map;
	} 
	
	@RequestMapping("/monthlyAbnormalNumbersData")
	@ResponseBody
	public Map getmonthlyAbnormalNumbersData(String time,String registNumber,String deviceID) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		Date date = simpleDateFormat.parse(time);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		Date endTime = calendar.getTime();
		
		Map map = timeService.getAbnormalNumbersDataByDay(date, endTime, registNumber,deviceID);
		return map;
	}
	
	//处理时间段的相关请求
	@RequestMapping("/operationalEfficiency")
	@ResponseBody
	public Map getoperationalEfficiency(String startTime,String endTime,String registNumber,String deviceID) throws ParseException {
		startTime = startTime+" 00:00:00";
		endTime = endTime+" 23:59:59";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = simpleDateFormat.parse(startTime);
		Date endDate = simpleDateFormat.parse(endTime);
		Map map = timeService.getoperationalEfficiency(startDate, endDate, registNumber,deviceID);
		return map;
	} 
	
	@RequestMapping("/daysIntervalRunTimeData")
	@ResponseBody
	public Map getDaysIntervalRunTimeData(String startTime,String endTime,String registNumber,String deviceID) throws ParseException {
		startTime = startTime+" 00:00:00";
		endTime = endTime+" 23:59:59";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = simpleDateFormat.parse(startTime);
		Date endDate = simpleDateFormat.parse(endTime);
		Map map = timeService.getRunTimeDataByDay(startDate, endDate, registNumber,deviceID);
		return map;
	}
	
	@RequestMapping("/daysIntervalRunNumbersData")
	@ResponseBody
	public Map getDaysIntervalRunNumbersData(String startTime,String endTime,String registNumber,String deviceID) throws ParseException {
		startTime = startTime+" 00:00:00";
		endTime = endTime+" 23:59:59";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = simpleDateFormat.parse(startTime);
		Date endDate = simpleDateFormat.parse(endTime);
		Map map = timeService.getRunNumbersDataByDay(startDate, endDate, registNumber,deviceID);
		return map;
	}
	
	@RequestMapping("/daysIntervalAbnormalNumbersData")
	@ResponseBody
	public Map getdaysIntervalAbnormalNumbersData(String startTime,String endTime,String registNumber,String deviceID) throws ParseException {
		startTime = startTime+" 00:00:00";
		endTime = endTime+" 23:59:59";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = simpleDateFormat.parse(startTime);
		Date endDate = simpleDateFormat.parse(endTime);
		Map map = timeService.getAbnormalNumbersDataByDay(startDate, endDate, registNumber,deviceID);
		return map;
	}
}
