package edu.deakin.sit218.quizapp.entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;

@Entity
@Table(name="qanda")//just to be explicit
public class questionandanswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "idQandA")
	protected int idQandA;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Pattern(regexp = "^[A-Z].*" , message = " A Question should start with a capital letter")
	@Pattern(regexp = "^((?!OR|1=1|--).)*$" , message = "That phrase is not allowed")
	@Column(name="question")
	protected String question;

	@Size(min=1, message=" is required")
	@NotNull(message="is required")
	@Pattern(regexp = "^[A-Za-z0-9]+$" , message = " One word ANSWER only")
	@Column(name="answer")
	protected String answer;
	
	@Size(min=1, message="is required")
	@NotNull(message="is required")
	@Column(name="knowledge")
	protected String knowledge;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}
	
	public questionandanswer() {
		
	}
	public questionandanswer (String question, String answer, String Knowledge) {
		setQuestion(question);
		setAnswer(answer);
		setKnowledge(Knowledge);
	}
	@Override
	public String toString(){
		return "client = [name : " +getQuestion()+ ", age: " + getAnswer() +", workouts : " +getKnowledge() +"]";
		
	}
	
}
