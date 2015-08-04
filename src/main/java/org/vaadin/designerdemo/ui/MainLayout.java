package org.vaadin.designerdemo.ui;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.ui.Panel;

@Component
@Scope("prototype")
public class MainLayout extends MainLayoutDesign {
	
	public Panel getContentPanel() {
		return contentPanel;
	}
}
