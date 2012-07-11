package ar.edu.itba.paw.grupo1.web;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.itba.paw.grupo1.model.Room.RoomsType;

@SuppressWarnings("serial")
public class RoomFormPanel extends Panel {

	public RoomFormPanel(String id) {
		super(id);
		
		WicketUtils.addDropDownMenu(this, "roomsCombo", RoomsType.values());
		add(new TextField<Double>("length").setRequired(true));
		add(new TextField<Double>("width").setRequired(true));
	}


}
