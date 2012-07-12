package ar.edu.itba.paw.grupo1.web.pages.EditPicture;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.support.EntityModel;
import ar.edu.itba.paw.grupo1.repository.PictureRepository;
import ar.edu.itba.paw.grupo1.service.ImageResource;
import ar.edu.itba.paw.grupo1.service.exception.PermissionDeniedException;
import ar.edu.itba.paw.grupo1.web.WicketSession;
import ar.edu.itba.paw.grupo1.web.pages.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.pages.EditProperty.EditPropertyPage;

@SuppressWarnings("serial")
@AuthorizeInstantiation(WicketSession.USER)
public class EditPicturePage extends BasePage {
	
	@SpringBean
	private PictureRepository pictures;
		
	public EditPicturePage(Property property, Picture picture) {
		
		setDefaultModel(new EntityModel<Property>(Property.class, property));
		EntityModel<Picture> pictureModel = new EntityModel<Picture>(Picture.class, picture);
				
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setVisible(false);
		
		final Form<Picture> editPictureForm = new Form<Picture>("editPictureForm", new CompoundPropertyModel<Picture>(pictureModel)) {
			
			@Override
			protected void onSubmit() {
				Property property = getProperty();
				
				if (!isMine(property)) {
					throw new PermissionDeniedException();
				}			
				
				setResponsePage(new EditPropertyPage(property));
			}
			
			@Override
			protected void onError() {
				super.onError();
				feedbackPanel.setVisible(true);
			}
		};
		
		TextField<String> nameTextField = new TextField<String>("name");
		nameTextField.setRequired(true);
		nameTextField.add(StringValidator.maximumLength(50));
		
		editPictureForm.add(nameTextField);
		editPictureForm.add(new Button("submit", new ResourceModel("submit")));
		editPictureForm.add(feedbackPanel);
		
		final byte[] data = picture.getData();
		final String extension = picture.getExtension();
		AbstractReadOnlyModel model = new AbstractReadOnlyModel() { 
			
		    @Override 
		    public Object getObject() {
				return new ImageResource(data, extension); 
		    } 
		};
		editPictureForm.add(new NonCachingImage("picture", model));		
		
		Link<Picture> deletePictureLink = new Link<Picture>("picDelete", pictureModel) {
			
		     public void onClick() {
		    	 Property property = getProperty();
		    	 Picture picture = getModelObject();
		    	 
		    	 pictures.delete(picture);
		    	 
		    	 setResponsePage(new EditPropertyPage(property));
		     }
		};
		
		editPictureForm.add(deletePictureLink);
		add(editPictureForm);
	}

	protected Property getProperty() {
		return (Property) getDefaultModelObject();
	}
}
