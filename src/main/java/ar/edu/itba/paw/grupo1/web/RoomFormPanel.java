package ar.edu.itba.paw.grupo1.web;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;

@SuppressWarnings("serial")
public class RoomFormPanel extends Panel {

	public RoomFormPanel(String id) {
		super(id);
		
		add(new TextField<String>("name").setRequired(true));
		add(new TextField<Double>("length").setRequired(true));
		add(new TextField<Double>("width").setRequired(true));
	}


}
