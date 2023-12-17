package com.QuizA.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.QuizA.Entity.QuestionAndOptions;
import com.QuizA.Entity.Questions;
import com.QuizA.Entity.Quiz;
import com.QuizA.Entity.Response;
import com.QuizA.Repository.QuizRepository;
import com.QuizA.Repository.QuestionRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizRepository;
	
	//This repository in injected because questions will be needed and questions are in questionsRepository.
	@Autowired
	QuestionRepository questionRepository;

	
	//This method will create quiz with the help of catageory, num of questions and title.
	public ResponseEntity<String> createQuiz(String catageory, int numQ, String title) {
		
		List<Questions> questions = questionRepository.findRandomQuestionsByCatageory(catageory, numQ);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizRepository.save(quiz);
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}
	
	//This method will get user quiz by id.
	public ResponseEntity<List<QuestionAndOptions>> getQuizQuestions(int id) {
		
		Optional<Quiz> quiz = quizRepository.findById(id);
		List<Questions> questionsFromDB = quiz.get().getQuestions();
		List<QuestionAndOptions> questionForUsers = new ArrayList<>();
		
		for(Questions q : questionsFromDB) {
			
			QuestionAndOptions QAO = new QuestionAndOptions(q.getId(),q.getQuestiontitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionForUsers.add(QAO);
		}
		
		return new ResponseEntity<>(questionForUsers, HttpStatus.OK);
	}

	//This method will calculate correct answers and send result back.
	public ResponseEntity<Integer> QuizScore(int id, List<Response> responses) {
		
		Quiz quiz = quizRepository.findById(id).get();
		List<Questions> questions = quiz.getQuestions();
		
		int right = 0;
		int i =0;
		for(Response response: responses) {
			
			if(response.getResponse().equals(questions.get(i).getRightanswer())) 
				right++;
			
			i++;
		}
		
		return new ResponseEntity<>(right, HttpStatus.OK);
	}
	
}
