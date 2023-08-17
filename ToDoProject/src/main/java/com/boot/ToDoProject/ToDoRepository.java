package com.boot.ToDoProject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDoModelbean, Integer> {
	public List<ToDoModelbean>  findByUser(String user);
}
