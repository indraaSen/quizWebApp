package com.QuizA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.QuizA.Entity.Questions;


public interface QuestionRepository extends JpaRepository<Questions, Integer>{

	//This is user define method for searcing the data by catageory.
	List<Questions> findByCatageory(String catageory);

	//This query will return data according to the catageory and number of questions with Randomly selected questions.
	@Query(value = "SELECT * FROM questions q Where q.catageory=:catageory ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	List<Questions> findRandomQuestionsByCatageory(String catageory, int numQ);

	Questions findByQuestiontitle(String question);


}
