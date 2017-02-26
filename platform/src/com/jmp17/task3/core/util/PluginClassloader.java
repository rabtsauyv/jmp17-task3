package com.jmp17.task3.core.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PluginClassloader extends ClassLoader {

	private final String path;
	private final DirectoryScanner dirScan;
	private List<Class<?>> loadedClasses = new ArrayList<>();
	
	public PluginClassloader(String path) {
		this.path = path;
		System.out.println("Init classloader for " + path);
		dirScan = new DirectoryScanner(path);
		dirScan.scan();
		System.out.println("Found classes: ");
		dirScan.print();
	}
	
	public void loadAll() throws ClassNotFoundException {
		for (String name : dirScan.getNames()) {
			if (name.endsWith(".class")) {
				name = name.substring(0, name.length()-6);
			}
			Class<?> cl = loadClass(name);
			loadedClasses.add(cl);
		}
	}
	
	public List<Class<?>> getLoadedClasses() {
		return loadedClasses;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		File classFile = dirScan.getFile(name + ".class");
		
		if (classFile == null) {
			throw new ClassNotFoundException(name);
		}
		byte[] bytecode = loadByteCode(classFile);
		
		return defineClass(name, bytecode, 0, bytecode.length);
	}
	
	private byte[] loadByteCode(File file) {
		try {
			return Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String toString() {
		return super.toString() + "[PluginClassloader:" + path + "]";
	}
}
