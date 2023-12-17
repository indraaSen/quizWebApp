package com.QuizA.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.QuizA.Entity.Questions;
import com.QuizA.Service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService; 

	
	//This method will retun all the data from the database.
	@GetMapping("allQuestions")
	public ResponseEntity<List<Questions>> getAllData() {
		
		return questionService.getAllData();
	}
	
	//This method will return the questions by catageory.(for example. java,python,c++ etc)
	@GetMapping("catageory/{catageory}")
	public ResponseEntity<List<Questions>> getQuestionsByCatageory(@PathVariable("catageory") String catageory){
		
		return questionService.getQuestionsByCatageory(catageory);
	}
	
	//This method will save the new questions and it update also.
	@PostMapping("save")
	public ResponseEntity<String> saveQuestion(@RequestBody Questions questions) {
		
		return questionService.saveQuestion(questions);
	}

	//This method will delete the question with the help of question.
	@DeleteMapping("delete")
	public String deleteQuestiontitle(@RequestParam("questiontitle") String questiontitle) {
		
		return questionService.deleteQuestiontitle(questiontitle);
	}
	
}
