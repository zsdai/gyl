package com.myself.gyl.utils;

import java.util.Calendar;

public class GylUtils {
	public static String getDateToString(){
		Calendar cal = Calendar.getInstance();
		int year= cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;//得到月份
		int day=cal.get(Calendar.DAY_OF_MONTH);
		return ""+year+month+day;
	}
	public static String getDDH(String type){
		Calendar cal = Calendar.getInstance();
		int year= cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH);
		int day=cal.get(Calendar.DAY_OF_MONTH);
		return type+year+month+day;
	}
}
