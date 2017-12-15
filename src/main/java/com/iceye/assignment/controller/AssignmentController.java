package com.iceye.assignment.controller;

import com.iceye.assignment.model.JsonObject;
import com.iceye.assignment.service.AssignmentService;
import com.iceye.assignment.service.ImageService;
import com.iceye.assignment.util.AssignmentException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value="/")
public class AssignmentController {

	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private AssignmentService assignmentService;
	@Autowired
	private ResourceLoader resourceLoader;
	
	@RequestMapping(value="/assignment", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonObject[] getJson() throws AssignmentException {
    	try {
    		return assignmentService.getJson();
		} catch (Exception e) {
			log.error("Error: ",e);
			throw new AssignmentException("An error occurred while processing your request: " + e.getMessage());
		}
    }

	@RequestMapping(value="/ingest/{text}", method=RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE )
	@ResponseBody
	public String ingest(@PathVariable String text, HttpServletRequest request, HttpServletResponse response) throws AssignmentException {
		try {
			assignmentService.ingest(text);
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			String url = assignmentService.getBaseUrl(request);
			return "<a href=\"" + url + "/download/" + text + "\">Download image</a>";
		} catch (Exception e) {
			log.error("Error: ",e);
			throw new AssignmentException("An error occurred while processing your request: " + e.getMessage());
		}
	}

	@RequestMapping(value = "/download/{filename}", method = RequestMethod.GET, produces = "application/png")
	public ResponseEntity<InputStreamResource> downloadPDFFile(@PathVariable String filename) throws IOException {
		filename = filename + "." + ImageService.IMAGE_TYPE;
		File file = resourceLoader.getResource("file:/tmp/" + filename).getFile();
		InputStream inputStream = new FileInputStream(file);

		return ResponseEntity
				.ok()
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header("Content-Disposition", "attachment; filename=" + filename)
				.body(new InputStreamResource(inputStream));
	}
}
