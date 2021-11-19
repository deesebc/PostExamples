package es.home.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InicioController {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "HolaMundo";
	}
}
