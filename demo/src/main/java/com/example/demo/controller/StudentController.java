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
import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;

import lombok.extern.slf4j.Slf4j;
import response.ResponseGenerator;

@RestController
@RequestMapping(path = "api/v1/student")
@Slf4j
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping
	public ResponseEntity<?> getStudents() {
		@SuppressWarnings("rawtypes")
		ResponseBodyDTO body = null;

		Date startCallDate = new Date();
		log.info(String.format("getStudents - startCallDate: %s", startCallDate));

		try {
			body = ResponseGenerator.generateResponse(studentService.getStudents(), HttpStatus.OK, startCallDate);
		} catch (Exception e) {
			log.error("Error occurred in getStudents: " + e.getMessage());
			body = ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate);
		}
		return ResponseEntity.ok(body);
	}

	@PostMapping
	public ResponseEntity<?> registerNewStudent(@RequestBody StudentDTO student) {

		@SuppressWarnings("rawtypes")
		ResponseBodyDTO body;

		Date startCallDate = new Date();
		log.info(String.format("Add new student - startCallDate: %s", startCallDate));

		try {
			studentService.addNewStudent(student);
			body = ResponseGenerator.generateResponse("Correctly added new student.", HttpStatus.OK, startCallDate);

		} catch (Exception e) {
			log.error("Error occurred in addNewStudent: " + e.getMessage());
			body = ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate);
		}
		return ResponseEntity.ok(body);
	}


	@DeleteMapping(path = "{studentId}")
	public ResponseEntity<ResponseBodyDTO<String>> deleteStudent(@PathVariable("studentId") Long studentId) {
		Date startCallDate = new Date();
		log.info(String.format("deleteStudent - startCallDate: %s", startCallDate));

		try {
			studentService.deleteStudent(studentId);
			return ResponseEntity.ok(
					ResponseGenerator.generateResponse("Student deleted successfully.", HttpStatus.OK, startCallDate));
		} catch (Exception e) {
			log.error("Error occurred in deleteStudent: " + e.getMessage());
			return ResponseEntity.badRequest()
					.body(ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate));
		}
	}


	@PutMapping(path = "{studentId}")
	public ResponseEntity<ResponseBodyDTO<String>> updateStudent(@PathVariable Long studentId,
			@RequestBody StudentDTO studentDTO) {
		Date startCallDate = new Date();
		log.info(String.format("updateStudent - startCallDate: %s", startCallDate));

		try {
			studentDTO.setId(studentId);
			studentService.updateStudent(studentDTO);
			return ResponseEntity.ok(
					ResponseGenerator.generateResponse("Student updated successfully.", HttpStatus.OK, startCallDate));
		} catch (Exception e) {
			log.error("Error occurred in updateStudent: " + e.getMessage());
			return ResponseEntity.badRequest()
					.body(ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate));
		}
	}
}
