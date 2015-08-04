package org.vaadin.designerdemo.ui;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.declarative.Design.ComponentFactory;
import com.vaadin.ui.declarative.Design.DefaultComponentFactory;
import com.vaadin.ui.declarative.DesignContext;

@SpringComponent
public class MyComponentFactory implements ComponentFactory {
	
	private ComponentFactory componentFactory = new DefaultComponentFactory();

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public Component createComponent(String fullyQualifiedClassName, DesignContext context) {
		if (!fullyQualifiedClassName.startsWith("org.vaadin.designerdemo")) {
			return componentFactory.createComponent(fullyQualifiedClassName, context);
		}
		
		try {
			Component bean = (Component) applicationContext.getBean(Class.forName(fullyQualifiedClassName));
			return bean;
		} catch (ClassNotFoundException | NoSuchBeanDefinitionException e) {
			return componentFactory.createComponent(fullyQualifiedClassName, context);
		}
	}

}
