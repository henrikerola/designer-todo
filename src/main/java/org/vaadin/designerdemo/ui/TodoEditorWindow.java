package org.vaadin.designerdemo.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.vaadin.designerdemo.backend.Todo;
import org.vaadin.designerdemo.backend.TodoRepository;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

@SpringComponent
@Scope("prototype")
public class TodoEditorWindow extends Window {
	
	@Autowired
	private TodoRepository todoRepository;
	
	private TodoEditorDesign todoEditorDesign;
	
	private FieldGroup binder = new FieldGroup();

	private BeanItem<Todo> beanItem;
	
	public TodoEditorWindow() {
		setCaption("Editor");
		center();
		setWidth("80%");
		setHeight("80%");
		
		todoEditorDesign = new TodoEditorDesign();
		setContent(todoEditorDesign);
		
		todoEditorDesign.notes.setNullRepresentation("");
		
		todoEditorDesign.saveButton.addClickListener(this::save);
		todoEditorDesign.cancelButton.addClickListener(e -> close());
	}
	
	private void save(ClickEvent event) {
		try {
			binder.commit();
			todoRepository.saveAndFlush(beanItem.getBean());
			close();
		} catch (CommitException e) {
			e.printStackTrace();
		}
	}
	
	public void setTodoItem(BeanItem<Todo> beanItem) {
		this.beanItem = beanItem;
		binder.setItemDataSource(beanItem);
		binder.bindMemberFields(todoEditorDesign);
	}

}
