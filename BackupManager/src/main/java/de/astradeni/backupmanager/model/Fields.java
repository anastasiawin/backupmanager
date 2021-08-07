package de.astradeni.backupmanager.model;

public class Fields {
	
	public String path;
	
	public Fields() {
	
		this (null);
	}
	
	

	public Fields(String path) {
		this.path = new String (path);
	}

	public String getPath() {
		return path;
	}
	
	public void setField (String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return "Paths [path =" + path + "]";
	}
}
