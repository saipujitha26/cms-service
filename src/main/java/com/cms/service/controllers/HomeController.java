package com.cms.service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling home page requests.
 */
@RestController
public class HomeController {
	
	/**
     * Handles GET requests to the root URL ("/").
     *
     * @return A string indicating that this is the home controller.
     */
	@GetMapping
	public String homeControllerHandler() {
		return "this is home controller";
	}
	

}
