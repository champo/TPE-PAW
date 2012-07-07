package ar.edu.itba.paw.grupo1.web;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;

import ar.edu.itba.paw.grupo1.model.Property.Services;

public class WicketUtils {

	public static void addToContainer(MarkupContainer container, Component c, boolean visibilityCondition) {
		c.setVisible(visibilityCondition);
		container.add(c);
	}
	
	public static <T extends Enum<T>, S> void addDropDownMenu(MarkupContainer container, String id, T[] selectOptions) {
		
		EnumChoiceRenderer<T> choiceRenderer = new EnumChoiceRenderer<T>(container);
		List<? extends T> choices = Arrays.asList(selectOptions);
		container.add(new DropDownChoice<T>(id, choices, choiceRenderer).setRequired(true));
	}	
}
