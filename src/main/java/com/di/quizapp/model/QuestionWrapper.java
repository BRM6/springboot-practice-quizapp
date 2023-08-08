package com.di.quizapp.model;

import lombok.Data;

@Data
public class QuestionWrapper {

    private Integer id;
    private String question;
    private String option;

    public QuestionWrapper(Integer id, String question, String option) {
        this.id = id;
        this.question = question;
        this.option = option;
    }
}
