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
import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.service.PictureService;

@SuppressWarnings("serial")
public class AddPictureServlet extends AbstractPictureServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Picture picture = new Picture();
		picture.setPropId(Integer.parseInt(req.getParameter("propId")));
		req.setAttribute("picture", new Picture());
		render(req, resp, "editPicture.jsp", "Add Picture");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PictureService pictureService = ApplicationContainer.get(PictureService.class);
		
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> items = null;
		// Parse the request
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Picture picture = new Picture();
		
		FileItem file = null;
		
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
		    FileItem item = (FileItem) iter.next();

		    if (item.isFormField()) {
		        processFormField(item, picture);
		    } else {
		        file = item;
		    }
		}		
		
		picture.setExtension(file.getName().substring(file.getName().lastIndexOf('.')));
		
		pictureService.save(picture);
		
		try {
			file.write(new File("img/" + picture.getId() + "." + picture.getExtension()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp.sendRedirect("propertyDetail?id=" + picture.getPropId());

	}

	private void processFormField(FileItem item, Picture picture) {
		 String name = item.getFieldName();
		 String value = item.getString();
		 if (name == "name") {
			 picture.setName(value);
		 } else if (name == "propId") {
			picture.setPropId(Integer.parseInt(value));
		 }
	}

}