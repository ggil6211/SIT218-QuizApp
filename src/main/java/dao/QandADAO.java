package edu.deakin.sit218.quizapp.dao;

import java.util.List;

import edu.deakin.sit218.quizapp.entity.questionandanswer;

public interface QandADAO {
	
	public void insertQandA(questionandanswer qanda);
	
	public boolean existsQandA(questionandanswer qanda);

	public  questionandanswer retrieveQandA(questionandanswer qanda);

	public void updateQandA(questionandanswer qanda);
	
	public boolean existsKnowledge(questionandanswer qanda);

	public List<questionandanswer> queryQandA(questionandanswer qanda);
}

