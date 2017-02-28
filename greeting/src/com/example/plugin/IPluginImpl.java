package com.example.plugin;

import java.util.Random;

import com.jmp17.task3.plugin.IPlugin;

public class IPluginImpl implements IPlugin {

	static {
		System.out.println("Greeting loaded by " + IPluginImpl.class.getClassLoader());
	}
	
	private GreetingHelper helper = new GreetingHelper();
	private Random r = new Random();
	
	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		System.out.println(helper.getGreeting(r.nextInt(helper.getGreetingsCapasity())));
	}

}
