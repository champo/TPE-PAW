package ar.edu.itba.paw.grupo1.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.controller.exception.InvalidParameterException;
import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.PictureService;
import ar.edu.itba.paw.grupo1.service.PropertyService;

@SuppressWarnings("serial")
public class AddPictureServlet extends AbstractPictureServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User user = getLoggedInUser(req);
		PropertyService propertyService = ApplicationContainer.get(PropertyService.class);
		
		int propId = -1;
		
		try {
			propId = Integer.parseInt(req.getParameter("propId"));
		} catch (NumberFormatException e) {
			throw new InvalidParameterException();
		}
		
		if(	propertyService.checkOwner(propId, user) ) {
			Picture picture = new Picture();
			picture.setPropId(Integer.parseInt(req.getParameter("propId")));
			req.setAttribute("picture", picture);
		} else {
			req.setAttribute("noPermissions", 1);
		}
		
		render(req, resp, "editPicture.jsp", "Add Picture");
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PictureService pictureService = ApplicationContainer.get(PictureService.class);
		PropertyService propertyService = ApplicationContainer.get(PropertyService.class);
		
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> items = null;
		// Parse the request
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			req.setAttribute("fatal", 1);
			render(req, resp, "editPicture.jsp", "Add Picture");
			return;
		}
		
		Picture picture = new Picture();		
		
		FileItem file = null;
		
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
		    FileItem item = (FileItem) iter.next();
		    if (item.getFieldName().equals("name")) {
		    	picture.setName(item.getString());
		    } else if (item.getFieldName().equals("propId")) {
		    	picture.setPropId(Integer.parseInt(item.getString()));
		    } else if (item.getFieldName().equals("file")) {
		    	file = item;
		    }
		}		
		
		boolean error = false;
		
		if (picture.getName().equals("") || picture.getName().length() > 50) {
			error = true;
			req.setAttribute("nameError", 1);
		}
		
		String extension = null;
		
		if (file.getName().equals("")) {
			error = true;
			req.setAttribute("fileError", 1);
		} else if (!file.getName().contains(".")) {
			req.setAttribute("extensionError", 1);
			error = true;
		} else {
			extension = file.getName().substring(file.getName().lastIndexOf('.'));
			if (!extension.equals(".jpg") && !extension.equals(".png") && !extension.equals(".jpeg") && !extension.equals(".gif")) {
				req.setAttribute("extensionError", 1);
				error = true;
			}
		}
		
		if (error) {
			req.setAttribute("picture", picture);
			render(req, resp, "editPicture.jsp", "Add Picture");
			return;
		}
		
		if (!propertyService.checkOwner(picture.getPropId(), getLoggedInUser(req))) {
			req.setAttribute("noPermissions", 1);
			render(req, resp, "editPicture.jsp", "Edit Picture");
			return;
		}
		
		picture.setExtension(extension);
		
		pictureService.save(picture);
		
		try {
			file.write(new File("src/main/webapp/images/" + picture.getId() + picture.getExtension()));
		} catch (Exception e) {
			req.setAttribute("picture", picture);
			req.setAttribute("writeError", 1);
			render(req, resp, "editPicture.jsp", "Add Picture");
			return;
		}
		
		resp.sendRedirect("editProperty?id=" + picture.getPropId());

	}

}