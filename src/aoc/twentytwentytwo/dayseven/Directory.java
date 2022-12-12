package aoc.twentytwentytwo.dayseven;

import java.util.ArrayList;
import java.util.List;

public class Directory {
	private String name;
	private Directory parentDir;
	private List<Directory> dirs;
	private List<File> files;
	
	public Directory(String name, Directory parentDir) {
		this.name = name;
		this.parentDir = parentDir;
		dirs = new ArrayList<Directory>();
		files = new ArrayList<File>();
	}
	
	public Directory addDirectory(Directory dir) {
		dirs.add(dir);
		return dir;
	}
	
	public void addFile(File file) {
		files.add(file);
	}
	
	public String getName() {
		return name;
	}
	
	public Directory getParentDir() {
		return parentDir;
	}
	
	public int getSize() {
		int size = 0;
		
		for(File f : files) {
			size += f.getSize();
		}
		
		for(Directory d : dirs) {
			size += d.getSize();
		}
		
		return size;
	}
	
	public List<Directory> getDirs(){
		return dirs;
	}
	
	public Directory findDirectory(String dirName) {
		for(Directory d : dirs) {
			if(d.getName().equals(dirName)) {
				return d;
			}
		}
		
		return null;
	}
}
