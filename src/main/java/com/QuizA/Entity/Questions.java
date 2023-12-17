package com.QuizA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "q")
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String questiontitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String catageory;
	private String difficultylevel;
	private String rightanswer;
	
}
