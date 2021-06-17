package com.example.demo;

public class FileLoadDao {
	
	private String owner;
	private String filename;
	private String outputfile;
	private String status;
	private String path;
	private String date;
	
	public FileLoadDao() {}
	
	public FileLoadDao(FileLoadDao dao) {
		setOwner(dao.getOwner());
		setFilename(dao.getFilename());
		setOutputfile(dao.getOutputfile());
		setStatus(dao.getStatus());
		setPath(dao.getPath());
		setDate(dao.getDate());
	}
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getOutputfile() {
		return outputfile;
	}
	public void setOutputfile(String outputfile) {
		this.outputfile = outputfile;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

}
