package edu.deakin.sit218.quizapp.controller;


import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.deakin.sit218.quizapp.entity.questionandanswer;

@Controller
@RequestMapping("/question-answer")
public class querycontroller {
	
	@RequestMapping("/showquery-Form")
	public String showForm(Model model) {
		questionandanswer qanda = new questionandanswer();
		model.addAttribute("qanda", qanda);
		return "query-form";
	}
}
