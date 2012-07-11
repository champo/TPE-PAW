package ar.edu.itba.paw.grupo1.web;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.RangeValidator;

import ar.edu.itba.paw.grupo1.model.Room.RoomsType;

@SuppressWarnings("serial")
public class RoomFormPanel extends Panel {

	public RoomFormPanel(String id) {
		super(id);
		
		WicketUtils.addDropDownMenu(this, "roomsCombo", RoomsType.values());
		
		TextField<Double> lengthTextField = new TextField<Double>("length");
		lengthTextField.setRequired(true);
		lengthTextField.add(new RangeValidator<Double>(0.0, (double) Integer.MAX_VALUE));
		
		TextField<Double> widthTextField = new TextField<Double>("width");
		widthTextField.setRequired(true);
		widthTextField.add(new RangeValidator<Double>(0.0, (double) Integer.MAX_VALUE));
		
		add(lengthTextField);
		add(widthTextField);
	}


}
