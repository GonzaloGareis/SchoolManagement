package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseBodyDTO;
import com.example.demo.dto.TeacherDTO;
import com.example.demo.service.TeacherService;

import lombok.extern.slf4j.Slf4j;
import response.ResponseGenerator;

@RestController
@RequestMapping(path = "api/v1/teacher")
@Slf4j
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@GetMapping
	public ResponseEntity<?> getTeachers() {
		@SuppressWarnings("rawtypes")
		ResponseBodyDTO body = null;

		Date startCallDate = new Date();
		log.info(String.format("getTeachers - startCallDate: %s", startCallDate));

		try {
			body = ResponseGenerator.generateResponse(teacherService.getTeachers(), HttpStatus.OK, startCallDate);
		} catch (Exception e) {
			log.error("Error occurred in getTeachers: " + e.getMessage());
			body = ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate);
		}
		return ResponseEntity.ok(body);
	}

	@PostMapping
	public ResponseEntity<?> registerNewTeacher(@RequestBody TeacherDTO teacher) {

		@SuppressWarnings("rawtypes")
		ResponseBodyDTO body;

		Date startCallDate = new Date();
		log.info(String.format("Add new teacher - startCallDate: %s", startCallDate));

		try {
			teacherService.addNewTeacher(teacher);
			body = ResponseGenerator.generateResponse("Correctly added new teacher.", HttpStatus.OK, startCallDate);

		} catch (Exception e) {
			log.error("Error occurred in addNewTeacher: " + e.getMessage());
			body = ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate);
		}
		return ResponseEntity.ok(body);
	}

	@DeleteMapping(path = "{teacherId}")
	public ResponseEntity<ResponseBodyDTO<String>> deleteTeacher(@PathVariable("teacherId") Long teacherId) {
		Date startCallDate = new Date();
		log.info(String.format("deleteTeacher - startCallDate: %s", startCallDate));

		try {
			teacherService.deleteTeacher(teacherId);
			return ResponseEntity.ok(
					ResponseGenerator.generateResponse("Teacher deleted successfully.", HttpStatus.OK, startCallDate));
		} catch (Exception e) {
			log.error("Error occurred in deleteTeacher: " + e.getMessage());
			return ResponseEntity.badRequest()
					.body(ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate));
		}
	}

	@PutMapping(path = "{teacherId}")
	public ResponseEntity<ResponseBodyDTO<String>> updateTeacher(@PathVariable Long teacherId,
			@RequestBody TeacherDTO teacher) {
		Date startCallDate = new Date();
		log.info(String.format("updateTeacher - startCallDate: %s", startCallDate));

		try {
			teacher.setId(teacherId);
			teacherService.updateTeacher(teacher);
			return ResponseEntity.ok(
					ResponseGenerator.generateResponse("Teacher updated successfully.", HttpStatus.OK, startCallDate));
		} catch (Exception e) {
			log.error("Error occurred in updateTeacher: " + e.getMessage());
			return ResponseEntity.badRequest()
					.body(ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate));
		}
	}

}
