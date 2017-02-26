package com.jmp17.task3.core.util;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DirectoryScanner {

	private final String rootPath;
	private final Map<String, File> classes = new HashMap<>();
	
	public DirectoryScanner(String rootPath) {
		this.rootPath = rootPath;
	}
	
	public void scan() {
		File root = new File(rootPath);
		if (root.exists() && root.isDirectory()) {
			classes.clear();
			scanDirectory(root, "");
		}
	}
	
	private void scanDirectory(File dir, String relativePath) {
		File[] files = dir.listFiles((d, fName) -> fName.endsWith(".class"));
		for (File file : files) {
			classes.put(relativePath + file.getName(), file);
		}
		File[] dirs = dir.listFiles(f -> f.isDirectory());
		for (File innerDir : dirs) {
			scanDirectory(innerDir, relativePath + innerDir.getName() + ".");
		}
	}
	
	public File getFile(String name) {
		return classes.get(name);
	}
	
	public Collection<String> getNames() {
		return classes.keySet();
	}
	
	public void print() {
		for (String key : classes.keySet()) {
			System.out.println(key);
		}
	}
}
