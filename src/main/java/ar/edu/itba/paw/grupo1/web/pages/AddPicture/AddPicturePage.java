package ar.edu.itba.paw.grupo1.web.pages.AddPicture;

import java.util.Collection;
import java.util.List;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;
import org.apache.wicket.validation.validator.StringValidator;

import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.repository.PictureRepository;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.service.exception.PermissionDeniedException;
import ar.edu.itba.paw.grupo1.web.WicketSession;
import ar.edu.itba.paw.grupo1.web.pages.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.pages.EditProperty.EditPropertyPage;

@SuppressWarnings("serial")
@AuthorizeInstantiation(WicketSession.USER)
public class AddPicturePage extends BasePage {

	@SpringBean
	private PictureRepository pictures;
	
	@SpringBean
	private PropertyRepository properties;
	
	private transient String name;
	
	private transient List<FileUpload> fileUpload;
	
	public AddPicturePage(final Property property) {
		final Integer id = property.getId();
		
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setVisible(false);
		
		final Form<AddPicturePage> addPictureForm = new Form<AddPicturePage>(
				"addPictureForm", new CompoundPropertyModel<AddPicturePage>(this)) {
			
			@Override
			protected void onSubmit() {
				Property property = properties.get(id);
				
				if (!isMine(property)) {
					throw new PermissionDeniedException();
				}
											
				FileUpload file = AddPicturePage.this.fileUpload.get(0);
				Picture picture = new Picture(name, property, file.getContentType(), file.getBytes());
								
				pictures.save(picture);
				
				setResponsePage(new EditPropertyPage(property));
			}
			
			@Override
			protected void onError() {
				super.onError();
				feedbackPanel.setVisible(true);
			}
		};
		
		FileUploadField fileUploadField = new FileUploadField("fileUpload");
		
		fileUploadField.add(new AbstractValidator<Collection<FileUpload>>() {
			protected void onValidate(IValidatable<Collection<FileUpload>> validatable) {
				for (FileUpload fileUpload : validatable.getValue()) {
                    String uploadContentType = fileUpload.getContentType();
                    if (uploadContentType == null || (!uploadContentType.contains("gif") 
                    		&& !uploadContentType.contains("jpg") && 
                    		!uploadContentType.contains("jpeg") && !uploadContentType.contains("png"))) {
                    	error(validatable);
                    }
				}
			}
		}); 
		
		fileUploadField.setRequired(true);
		
		addPictureForm.setMultiPart(true);
		addPictureForm.add(fileUploadField);
		
		TextField<String> nameTextField = new TextField<String>("name");
		nameTextField.setRequired(true);
		nameTextField.add(StringValidator.maximumLength(50));
		
		addPictureForm.add(nameTextField);
		
		addPictureForm.add(new Button("submit", new ResourceModel("submit")));
		addPictureForm.add(feedbackPanel);
		
		add(addPictureForm);
	}

}