package org.vaadin.designerdemo.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.vaadin.designerdemo.backend.TodoList;
import org.vaadin.designerdemo.backend.TodoListRepository;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button.ClickEvent;

@SpringComponent
@Scope("prototype")
public class SidebarButton extends SidebarButtonDesign {
	
	@Autowired
	private TodoListRepository todoListRepository;

	private TodoList todoList;

	public SidebarButton() {
		
		setEditModeEnabled(false);
		
		nameButton.addClickListener(e -> {
			getUI().getNavigator().navigateTo(TodoListView.VIEW_NAME + "/" + todoList.getId());
		});

		editButton.addClickListener(e -> {
			setEditModeEnabled(true);
		});

		deleteButton.addClickListener(e -> {

		});
		
		saveEditButton.addClickListener(this::save);

		cancelEditButton.addClickListener(e -> {
			setEditModeEnabled(false);
		});
	}
	
	public void setTodoList(TodoList todoList) {
		this.todoList = todoList;
		nameTextField.setValue(todoList.getName());
		nameButton.setCaption(todoList.getName());
	}
	
	private void save(ClickEvent event) {
		todoList.setName(nameTextField.getValue());
		todoListRepository.saveAndFlush(todoList);
		setEditModeEnabled(false);
	}
	
	private void setEditModeEnabled(boolean enabled) {
		nameTextField.setVisible(enabled);
		saveEditButton.setVisible(enabled);
		cancelEditButton.setVisible(enabled);
		
		nameButton.setVisible(!enabled);
		editButton.setVisible(!enabled);
		deleteButton.setVisible(!enabled);
	}

}
