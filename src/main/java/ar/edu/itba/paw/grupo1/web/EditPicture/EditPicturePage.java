package ar.edu.itba.paw.grupo1.web.EditPicture;

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
import ar.edu.itba.paw.grupo1.repository.PictureRepository;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.service.ImageResource;
import ar.edu.itba.paw.grupo1.service.exception.PermissionDeniedException;
import ar.edu.itba.paw.grupo1.web.WicketSession;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.EditProperty.EditPropertyPage;

@SuppressWarnings("serial")
@AuthorizeInstantiation(WicketSession.USER)
public class EditPicturePage extends BasePage {
	
	@SpringBean
	private PictureRepository pictures;
	
	@SpringBean
	private PropertyRepository properties;
	
	private transient String name;
	
	public EditPicturePage(final Property property, final Picture picture) {
		
		//EntityModel<Property> propertyModel = new EntityModel<Property>(Property.class, property);
		//EntityModel<Picture> pictureModel = new EntityModel<Picture>(Picture.class, picture);
		final Integer idProperty = property.getId();
		final Integer idPicture = picture.getId();
		
		name = picture.getName();
		
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setVisible(false);
		
		final Form<EditPicturePage> editPictureForm = new Form<EditPicturePage>(
				"editPictureForm", new CompoundPropertyModel<EditPicturePage>(this)) {
			
			@Override
			protected void onSubmit() {
				Property property = properties.get(idProperty);
				Picture picture = pictures.get(idPicture);
				
				if (!isMine(property)) {
					throw new PermissionDeniedException();
				}			
				
				picture.setName(name);
				
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
		editPictureForm.add(new NonCachingImage("picture", 
		                new AbstractReadOnlyModel() { 
		                    @Override 
		                    public Object getObject() { 
		                        // TODO Auto-generated method stub 
		                        return new ImageResource(picture.getData(), picture.getExtension()); 
		                    } 
		                }));		
		
		Link<Picture> deletePictureLink = new Link<Picture>("picDelete") {
			
		     public void onClick() {
		    	 Property property = properties.get(idProperty);
		    	 Picture picture = pictures.get(idPicture);
		    	 
		    	 pictures.delete(picture);
		    	 
		    	 setResponsePage(new EditPropertyPage(property));
		     }
		};
		
		editPictureForm.add(deletePictureLink);
		
		add(editPictureForm);
	}

}
