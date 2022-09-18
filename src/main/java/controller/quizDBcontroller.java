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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller 
@RequestMapping("/question-answer")
public class quizDBcontroller {

	@RequestMapping("/process-qanda")
	public String addtodatabase(@Valid @ModelAttribute("qanda")questionandanswer qanda, 
			BindingResult validationErrors, Model model) {
		if(validationErrors.hasErrors()) 
			return "qanda-form";
	
		QandADAO dao = new QandADAOImpl();
			if(!dao.existsQandA(qanda))
				dao.insertQandA(qanda);
			else {
				model.addAttribute("message","<hr style=\"width:30%\"><br>Question : " 
						+ qanda.getQuestion()+"<br>Answer : "+ qanda.getAnswer()+ "<br>Knowledge : "+qanda.getKnowledge()+""
						+"<br><h1 style=\"color:Tomato;text-align:center;\">This Question and Answer"
						+" already Exists</h1><h2 h1 style=\"text-align:center;\" style=\"text-align:center;\"><br>Please try again</h1><hr style=\"width:30%\">");
				return "addtodatabase";
			}
			qanda = dao.retrieveQandA(qanda);
			model.addAttribute("message","Question : " + qanda.getQuestion()+"<br>Answer : "+ qanda.getAnswer()+ "<br>Knowledge : "+qanda.getKnowledge()+""
			+"<br><h1 style=\"color:green;text-align:center;\">The question has been successfully<br>added to the database");
		return "qanda-dbresult";
	}
}

