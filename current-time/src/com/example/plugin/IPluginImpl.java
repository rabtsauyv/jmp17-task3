package com.example.plugin;

import java.util.Date;

import com.jmp17.task3.plugin.IPlugin;

public class IPluginImpl implements IPlugin {
	
	static {
		System.out.println("Current-time loaded by " + IPluginImpl.class.getClassLoader());
	}
	
	private TimeHelper helper = new TimeHelper();

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		Date date = new Date();
		System.out.println(helper.convert(date));
	}

}
