package ar.edu.itba.paw.grupo1.web.LabelsExample;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class LabelsExamplePage extends WebPage {
	
	public LabelsExamplePage(Weather weather) {
		add(new Label("city", weather.getCity()));
		add(new Label("min", String.valueOf(weather.getMin())));
		add(new Label("max", String.valueOf(weather.getMax())));
	}
}
