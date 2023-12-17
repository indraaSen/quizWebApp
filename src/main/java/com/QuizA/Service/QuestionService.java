package com.QuizA.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.QuizA.Entity.Questions;
import com.QuizA.Repository.QuestionRepository;


@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	
	//This method will get all the data from the database.
	public ResponseEntity<List<Questions>> getAllData() {
		try {
			return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	//This method will find the data by catageory(for example: java,python,c++)
	public ResponseEntity<List<Questions>> getQuestionsByCatageory(String catageory) {
		try {
			if(questionRepository.findByCatageory(catageory).size() !=0 )
				return new ResponseEntity<>(questionRepository.findByCatageory(catageory), HttpStatus.OK);
			else 
				return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	
	//This method will save the new questions in DataBase and update also.
	public ResponseEntity<String> saveQuestion(Questions questions) {
		try {
			new ResponseEntity<>(questionRepository.save(questions), HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
		
	}

	//This method will find the question then of that question it will get that question's id then it will delete it by id.
	public String deleteQuestiontitle(String questiontitle) {

		int id = questionRepository.findByQuestiontitle(questiontitle).getId();
		questionRepository.deleteById(id);
		return "Question is deleted";
	}

}
