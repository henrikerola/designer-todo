package org.vaadin.designerdemo.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.vaadin.designerdemo.backend.Todo;
import org.vaadin.designerdemo.backend.TodoList;
import org.vaadin.designerdemo.backend.TodoListRepository;
import org.vaadin.designerdemo.backend.TodoRepository;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;

@SpringView(name = TodoListView.VIEW_NAME)
public class TodoListView extends TodoListViewDesign implements View {
	
	public static final String VIEW_NAME = "todos"; 
	
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private TodoListRepository todoListRepository;
	
	@Autowired
	private TodoRepository todoRepository;
	
	private BeanItemContainer<Todo> todosContainer;
	
	public TodoListView() {
		todosContainer = new BeanItemContainer<Todo>(Todo.class);
		
		todosGrid.setContainerDataSource(todosContainer);
		todosGrid.setSelectionMode(SelectionMode.NONE);
		todosGrid.setColumns("title");
		todosGrid.addItemClickListener(e -> {
			editTodo((BeanItem<Todo>) e.getItem());
		});
		
		addTodoButton.addClickListener(e -> {
			Notification.show("Not implemented!");
		});
		
		
	}
	
	private void editTodo(BeanItem<Todo> beanItem) {
		TodoEditorWindow editorWindow = applicationContext.getBean(TodoEditorWindow.class);
		editorWindow.setTodoItem(beanItem);
		getUI().addWindow(editorWindow);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		try {
			long listId = Long.parseLong(event.getParameters());
			showTodos(listId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void showTodos(long listId) {
		TodoList todoList = todoListRepository.findOne(listId);
		todoListNameLabel.setValue(todoList.getName());
		
		List<Todo> todos = todoRepository.findByTodoList(todoList);
		
		todosContainer.removeAllItems();
		todosContainer.addAll(todos);
	}

}
