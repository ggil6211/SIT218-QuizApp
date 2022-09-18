package edu.deakin.sit218.quizapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.deakin.sit218.quizapp.entity.questionandanswer;

public class QandADAOImpl implements QandADAO {
	
	SessionFactory factory;
	public QandADAOImpl() {
		 	factory= new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(questionandanswer.class)
				.buildSessionFactory();
	}
	
	@Override
	public void updateQandA(questionandanswer qanda) {
		Session session = factory.getCurrentSession();
		
		try{
					
			session.beginTransaction();
			
			session.update(qanda);
			
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	}

	//inserts a q and a to the database
	@Override
	public void insertQandA(questionandanswer qanda) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		
		try{
					
			session.beginTransaction();
			
			session.save(qanda);
			
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	}
	
	//retrieves the q and a from the database
	@Override
	public questionandanswer retrieveQandA(questionandanswer qanda) {
	Session session = factory.getCurrentSession();
		
		try{
					
			session.beginTransaction();
			
			String hql = "from questionandanswer where question = '"+
					qanda.getQuestion()+"' and answer = '"+
					qanda.getAnswer()+"'";
			List<questionandanswer> questions = session.createQuery(hql).getResultList();
			if (questions.isEmpty()) {
				throw new RuntimeException("There is no question and answer: "+qanda.toString());
			}
			else if (questions.size() > 1) { 
				throw new RuntimeException("More than one question and answer: "
						+ qanda.toString() +" exists");
			}
			else {
				return questions.get(0);
			}
		}
		finally {
			session.close();
		}
	}

	//checks if the question exists in the database
	@Override
	public boolean existsQandA(questionandanswer qanda) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();

			String hql = "from questionandanswer where question = '"+
					qanda.getQuestion()+"' and answer = '"+
					qanda.getAnswer()+"'";
			System.out.print(session.createQuery(hql));
			List<questionandanswer> questions =session.createQuery(hql).getResultList();
			
			return !questions.isEmpty();
			
		}
		finally {
			session.close();
		}
	}
	
	//checks if the knowledge base exists
	@Override
	public boolean existsKnowledge(questionandanswer qanda) {
		
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();

			String hql = "from questionandanswer where knowledge = '"+ qanda.getKnowledge()+"'";
			System.out.print(session.createQuery(hql));
			List<questionandanswer> questions =session.createQuery(hql).getResultList();
			
			return !questions.isEmpty();	
		}
		finally {
			session.close();
		}
	}
	
	//queries the database to get all questions for a knowledge base
	@Override
	public  List<questionandanswer> queryQandA(questionandanswer qanda) {
		
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			
			String hql = "from questionandanswer where knowledge = '"+ qanda.getKnowledge()+"'";
			List<questionandanswer> questions = session.createQuery(hql).getResultList();
			
			return questions;
		}
		finally {
			session.close();
		}
	}

	@Override
	protected void finalize() throws Throwable{
		factory.close();
		
	}
}
