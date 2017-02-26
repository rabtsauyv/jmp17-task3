package com.example.plugin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {

	private final String formatStr = "hh:mm:ss.SSS";
	private final SimpleDateFormat format = new SimpleDateFormat(formatStr);
	
	public String convert(Date date) {
		return format.format(date);
	}
}
