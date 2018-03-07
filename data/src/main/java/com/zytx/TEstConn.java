package com.zytx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TEstConn {
	
	public static void main(String[] args) throws ParseException {
		String time = "2018";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		Date date = simpleDateFormat.parse(time);
		System.out.println(date);
	
	}
}
