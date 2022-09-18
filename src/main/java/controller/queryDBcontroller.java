package edu.deakin.sit218.quizapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.deakin.sit218.quizapp.dao.QandADAO;
import edu.deakin.sit218.quizapp.dao.QandADAOImpl;
import edu.deakin.sit218.quizapp.entity.questionandanswer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller 
@RequestMapping("/question-answer")
public class queryDBcontroller {
	
	@RequestMapping("/process-query")
	public String querydatabase(@Valid @ModelAttribute("query")questionandanswer qanda, 
			BindingResult validationErrors, Model model) {
			QandADAO dao = new QandADAOImpl();
			
			if(!dao.existsKnowledge(qanda)) {
				model.addAttribute("message","<h1 style=\"color:Tomato;text-align:center;\">Knowledge : "
						+ qanda.getKnowledge() +"</h1><h3 style=\"color:Tomato;text-align:center;\"> has no Question and Answers"
						+" in the database<br>Please try again</h3><hr style=\"width:50%\">");
				model.addAttribute("list",null);	
			}
			else {
				List<questionandanswer> qandalist;
				qandalist = dao.queryQandA(qanda);
				model.addAttribute("list",qandalist);
			}
		return "query-dbresult";
	}
	
	
}
