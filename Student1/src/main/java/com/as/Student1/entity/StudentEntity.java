package com.as.Student1.entity;

public class StudentEntity {

	private int id;
    private String name;
    private int totalMarks;
    
    
	public StudentEntity() {
		super();
	}


	public StudentEntity(int id, String name, int totalMarks) {
		super();
		this.id = id;
		this.name = name;
		this.totalMarks = totalMarks;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getTotalMarks() {
		return totalMarks;
	}


	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	
}
