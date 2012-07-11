package ar.edu.itba.paw.grupo1.web;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;

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
	
	public static <T extends Enum<T>, S> void addRadioChoice(MarkupContainer container, String id, T[] selectOptions) {
		
		EnumChoiceRenderer<T> choiceRenderer = new EnumChoiceRenderer<T>(container);
		List<? extends T> choices = Arrays.asList(selectOptions);
		RadioChoice<T> radioChoice = new RadioChoice<T>(id, choices, choiceRenderer);
		radioChoice.setSuffix("");
		container.add(radioChoice);
	}	
	
}
