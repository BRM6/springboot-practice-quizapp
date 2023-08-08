package com.di.quizapp.service;

import com.di.quizapp.dao.QuestionDao;
import com.di.quizapp.dao.QuizDao;
import com.di.quizapp.model.Question;
import com.di.quizapp.model.QuestionWrapper;
import com.di.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuestionDao questionDao;
    @Autowired
    QuizDao quizDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("SUCCESS CREATE QUIZ", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion(), q.getOption());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }
}
