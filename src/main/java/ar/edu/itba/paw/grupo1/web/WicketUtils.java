package ar.edu.itba.paw.grupo1.web;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;

public class WicketUtils {

	public static void addToContainer(MarkupContainer container, Component c, boolean visibilityCondition) {
		c.setVisible(visibilityCondition);
		container.add(c);
	}

}
