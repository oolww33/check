package com.fithe.youtube.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fithe.youtube.service.YoutubeService;

@Controller
public class YoutubeController {
	
	private Logger logger = Logger.getLogger(YoutubeController.class); 
	
	private YoutubeService youtubeService;
	
	@Autowired(required=false)
	public YoutubeController(YoutubeService youtubeService) {
		this.youtubeService = youtubeService;
	}
}
