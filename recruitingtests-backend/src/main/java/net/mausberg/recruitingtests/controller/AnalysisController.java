package net.mausberg.recruitingtests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.mausberg.recruitingtests.service.AnswerService;
import net.mausberg.recruitingtests.service.QuestionService;

@RestController
@RequestMapping("/api/v0/analysis")
public class AnalysisController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService quizQuestionService;

    
}
