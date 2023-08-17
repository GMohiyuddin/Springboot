package com.boot.ToDoProject;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class ToDoController {
	
	private ToDoService todoservice;
	
	public ToDoController(ToDoService todoservice) {
		super();
		this.todoservice = todoservice;
	}

	@RequestMapping("list-todos")
	public String listAlltodo(ModelMap model) {
		String user = getLoggedinUser(model);
		List<ToDoModelbean> todos = todoservice.findbyusername(user);
		model.addAttribute("todo", todos);
		
		return "list-todos"; 
	}

	private String getLoggedinUser(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	@GetMapping("add-todo")
	public String addtodo(ModelMap model) {
		String user = getLoggedinUser(model);
		ToDoModelbean toDoModelbean = new ToDoModelbean(0, user, "", LocalDate.now().plusYears(1), false);
		model.put("toDoModelbean", toDoModelbean);
		return "add-todo";
	}
	@PostMapping("add-todo")
	public String addnewtodo(ModelMap model, @Valid ToDoModelbean toDoModelbean, BindingResult result) {
		if (result.hasErrors()) {
			return "add-todo";
		}
		else {
			String user = getLoggedinUser(model); 
			todoservice.addTodo(user, toDoModelbean.getDesc(), toDoModelbean.getTargetDate(), false);
			return "redirect:list-todos";
		}
	}
	@RequestMapping("delete-todo")
	public String deletetodo(@RequestParam int id) {
		todoservice.deletetodo(id);
		return "redirect:list-todos";
	}
	@RequestMapping("update-todo")
	public String showudpatepage(@RequestParam int id, ModelMap model) {
		ToDoModelbean todo = ToDoService.findbyid(id);
		model.addAttribute("toDoModelbean", todo);
		return "add-todo";
	}
	@PostMapping("update-todo")
	public String updatetodo(ModelMap model, @Valid ToDoModelbean toDoModelbean, BindingResult result) {
		if (result.hasErrors()) {
			return "add-todo";
		}
		else {
			String user = getLoggedinUser(model);
			toDoModelbean.setUser(user);
			todoservice.updatetodo(toDoModelbean);
			return "redirect:list-todos";
		}
	}
}
