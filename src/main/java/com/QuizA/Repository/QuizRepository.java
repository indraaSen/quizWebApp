package com.QuizA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QuizA.Entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}
