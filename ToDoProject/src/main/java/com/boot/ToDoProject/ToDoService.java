package com.boot.ToDoProject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;
@Service
public class ToDoService {
	private static List<ToDoModelbean> todos = new ArrayList<>();
	private static int todosCount = 0;
	static {
		todos.add(new ToDoModelbean(++todosCount,"Gulam" ,"learn Boot" , LocalDate.now().plusYears(1), false));
		todos.add(new ToDoModelbean(++todosCount,"Gulam" ,"learn Spring" , LocalDate.now().plusYears(1), false));
		todos.add(new ToDoModelbean(++todosCount,"Gulam" ,"learn python" , LocalDate.now().plusYears(2), false));
	}
	
	public List<ToDoModelbean> findbyusername(String user){
		
		Predicate<? super ToDoModelbean> Predicate= todos -> todos.getUser().equalsIgnoreCase(user);
		
		return todos.stream().filter(Predicate).toList();
	}
	public void addTodo(String user, String description, LocalDate targetDate, boolean done) {
		ToDoModelbean todo = new ToDoModelbean(++todosCount,user,description,targetDate,done);
		todos.add(todo);
	}
	public void deletetodo (int id) {
		Predicate<? super ToDoModelbean> Predicate= todos -> todos.getId() == id;
		todos.removeIf(Predicate);
	}
	public static ToDoModelbean findbyid(int id) {
		Predicate<? super ToDoModelbean> Predicate= todos -> todos.getId() == id;
		ToDoModelbean toDoModelbean = todos.stream().filter(Predicate).findFirst().get();
		
		return toDoModelbean;
	}
	public void updatetodo(@Valid ToDoModelbean toDoModelbean) {

		deletetodo(toDoModelbean.getId());
		todos.add(toDoModelbean);
	}
}
