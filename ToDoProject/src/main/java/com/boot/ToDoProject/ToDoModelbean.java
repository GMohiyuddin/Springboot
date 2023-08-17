package com.boot.ToDoProject;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity(name = "todo")
public class ToDoModelbean {
	
	@Id
	@GeneratedValue
	private int id;
	private String user;
	
	@Size(min = 10,message = "min 10 characters required")
	private String desc;
	
	private LocalDate targetDate;
	private boolean done;
	
	public ToDoModelbean() {
		
	}
	
	
	public ToDoModelbean(int id, String user, String desc, LocalDate targetDate, boolean done) {
		super();
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.targetDate = targetDate;
		this.done = done;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "ToDoModelbean [id=" + id + ", username=" + user + ", desc=" + desc + ", targetDate=" + targetDate
				+ ", done=" + done + "]";
	}
}
