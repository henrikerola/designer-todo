package org.vaadin.designerdemo.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme(ValoTheme.THEME_NAME) 
public class TodoUI extends UI {
	
	@Autowired
	private MyComponentFactory componentFactory;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private SpringViewProvider viewProvider;

	@Override
	protected void init(VaadinRequest request) {
		
		Design.setComponentFactory(componentFactory);
		
		MainLayout mainLayout = applicationContext.getBean(MainLayout.class);
		setContent(mainLayout);
		
		Navigator navigator = new Navigator(this, mainLayout.getContentPanel());
		navigator.addProvider(viewProvider);
	}

}
