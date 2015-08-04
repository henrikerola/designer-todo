package org.vaadin.designerdemo.backend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	
	List<Todo> findByTodoList(TodoList todoList);

}
