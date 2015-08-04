package org.vaadin.designerdemo.backend;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TODO")
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    
    private String notes;

    private boolean done;
    
    @ManyToOne
    @JoinColumn(name = "TODO_LIST_ID")
    private TodoList todoList;

    public Todo() {
    }

    public Todo(String text) {
        this.title = text;
    }

    public Todo(String text, boolean done) {
        this.title = text;
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getNotes() {
		return notes;
	}
    
    public void setNotes(String notes) {
		this.notes = notes;
	}

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void toggleDone() {
        setDone(!isDone());
    }

}
