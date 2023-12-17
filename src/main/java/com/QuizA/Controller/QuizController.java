package com.QuizA.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.QuizA.Entity.QuestionAndOptions;
import com.QuizA.Entity.Response;
import com.QuizA.Service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;

	//This method will create the quiz with catageory , number of questions you want and title of the quiz(ex: javaquiz, pythonquiz).
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String catageory, @RequestParam int numQ, @RequestParam String title){
		return quizService.createQuiz(catageory, numQ, title);
	}
	
	//This method will return the quiz by id(ex: quiz1: 1 , quiz2; 2 here 1&2 is id).
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionAndOptions>> getQuizQuestions(@PathVariable int id){
		return quizService.getQuizQuestions(id);
	}
	
	//This method will submit users respones and return score/result.
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> QuizScore(@PathVariable int id, @RequestBody List<Response> responses){
		return quizService.QuizScore(id, responses);
	}
	
}
