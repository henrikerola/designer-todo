package org.vaadin.designerdemo.ui;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.designerdemo.backend.TodoListRepository;

import com.vaadin.ui.Button;

@Component
@Scope("prototype")
public class Sidebar extends SidebarDesign {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private TodoListRepository repository;
	
	@PostConstruct
	private void init() {
		
		repository.findAll().forEach(todoList -> {
			SidebarButton button = applicationContext.getBean(SidebarButton.class);
			button.setTodoList(todoList);
			addComponent(button);
		});
		
	}
	

}
