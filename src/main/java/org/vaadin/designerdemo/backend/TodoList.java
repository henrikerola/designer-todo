package org.vaadin.designerdemo.backend;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TODOLIST")
public class TodoList implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    
    public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
