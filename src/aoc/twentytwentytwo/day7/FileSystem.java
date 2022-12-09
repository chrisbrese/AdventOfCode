package aoc.twentytwentytwo.day7;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {
	private Directory curDir;
	private Directory base;
	private List<Directory> knownDirs;
	
	public FileSystem() { 
		knownDirs = new ArrayList<Directory>();
	}
	
	public void setBase(Directory base) {
		this.base = base;
	}
	
	public Directory getBase() {
		return base;
	}
	
	public Directory setCurDir(Directory dir) {
		return curDir = dir;
	}
	
	public Directory getCurDir(Directory dir) {
		return curDir;
	}
	
	public void addKnownDir(Directory d) {
		knownDirs.add(d);
	}
	
	public List<Directory> getKnownDirs() {
		return knownDirs;
	}
	
	public int getSize() {
		int size = 0;
		for(Directory dir : knownDirs) {
			size += dir.getSize();
		}
		
		return size;
	}
}
