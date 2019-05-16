package com.ymcoffee.controller.app;

import com.ymcoffee.dao.hibernate.SubjectRepository;
import com.ymcoffee.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/app/subject")
@RestController
public class AppSubjectController {

	@Autowired
	private SubjectRepository subjectRepository;

	@PostMapping("/index")
	public List<Subject> newsIndex() {
		return subjectRepository.findAll();
	}

}
