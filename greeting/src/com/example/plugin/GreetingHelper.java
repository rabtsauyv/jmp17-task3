package com.example.plugin;

import java.util.ArrayList;
import java.util.List;

public class GreetingHelper {

	private List<String> greetings;
	
	{
		greetings = new ArrayList<>();
		greetings.add("Hello!");
		greetings.add("Guten Tag!");
		greetings.add("Ciao!");
	}
	
	public int getGreetingsCapasity() {
		return greetings.size();
	}
	
	public String getGreeting(int index) {
		if (index<0) {
			index = 0;
		} else if (index>=greetings.size()) {
			index %= greetings.size();
		}
		return greetings.get(index);
	}
}
