package com.iceye.assignment.controller;

import com.iceye.assignment.model.JsonObject;
import com.iceye.assignment.service.AssignmentService;
import com.iceye.assignment.util.AssignmentException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/")
public class AssignmentController {

	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private AssignmentService assignmentService;
	
	@RequestMapping(value="/assignment", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonObject[] getJson() throws AssignmentException {
    	try {
    		return assignmentService.getJson();
		} catch (Exception e) {
			log.error("Error: ",e);
			throw new AssignmentException("An error occurred while processing your request: " + e.getMessage());
		}
    }

	@RequestMapping(value="/ingest/{text}", method=RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] ingest(@PathVariable String text) throws AssignmentException {
		try {
			return assignmentService.ingest(text);
		} catch (Exception e) {
			log.error("Error: ",e);
			throw new AssignmentException("An error occurred while processing your request: " + e.getMessage());
		}
	}
}
