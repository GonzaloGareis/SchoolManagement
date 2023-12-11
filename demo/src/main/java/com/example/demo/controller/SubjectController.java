package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SubjectDTO;
import com.example.demo.service.SubjectService;

@RestController
@RequestMapping(path = "api/v1/subject")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@GetMapping
	public List<SubjectDTO> getSubjects() {
		return subjectService.getSubjects();
	}

	@PostMapping
	public void addNewSubject(@RequestBody SubjectDTO subjectDTO) {
		subjectService.addNewSubject(subjectDTO);
	}

	@DeleteMapping(path = "{subjectId}")
	public void deleteSubject(@PathVariable("subjectId") Long subjectId) {
		subjectService.deleteSubject(subjectId);
	}

	@PutMapping
	public void updateSubject(@RequestBody SubjectDTO subjectDTO) {
		subjectService.updateSubject(subjectDTO);
	}
}