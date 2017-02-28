package com.jmp17.task3.core.run;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jmp17.task3.core.util.PluginClassloader;
import com.jmp17.task3.plugin.IPlugin;

public class App {
	
	private Map<String, IPlugin> plugins = new LinkedHashMap<>();

	public static void main(String[] args) {

		App app = new App();
		try {
			app.discoverPlugins();
		} catch (Exception e) {
			e.printStackTrace();
		}
		app.printPlugins();
		app.runPlugins();
		
	}
	
	public void printPlugins() {
		System.out.println("Loaded plugins: ");
		for (String plName : plugins.keySet()) {
			System.out.println(plName);
		}
	}
	
	public void runPlugins() {
		System.out.println();
		System.out.println("Running plugins...");
		for (IPlugin plugin : plugins.values()) {
			System.out.println("running " + plugin.getClass().getSimpleName());
			System.out.println("loaded by " + plugin.getClass().getClassLoader());
			plugin.doAction();
		}
	}

	public void discoverPlugins() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		File pluginsFolder = new File("plugins/");
		if (pluginsFolder.isDirectory()) {
			File[] dirs = pluginsFolder.listFiles(f -> f.isDirectory());
			for (File innerDir : dirs) {
				PluginClassloader pCl = new PluginClassloader(innerDir.getPath());
				pCl.loadAll();
				List<Class<?>> classes = pCl.getLoadedClasses();
				for (Class<?> class1 : classes) {
					Class<?>[] interfaces = class1.getInterfaces();
					for (Class<?> interface1 : interfaces) {
						if (IPlugin.class.equals(interface1)) {
							IPlugin obj = (IPlugin) class1.newInstance();
							plugins.put(innerDir.getName(), obj);
						}
					}
				}
			}
		}
	}
}
