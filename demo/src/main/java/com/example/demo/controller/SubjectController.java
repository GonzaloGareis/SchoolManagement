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
import com.example.demo.dto.SubjectDTO;
import com.example.demo.service.SubjectService;

import lombok.extern.slf4j.Slf4j;
import response.ResponseGenerator;

@RestController
@RequestMapping(path = "api/v1/subject")
@Slf4j
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@GetMapping
	public ResponseEntity<?> getSubjects() {

		@SuppressWarnings("rawtypes")
		ResponseBodyDTO body = null;

		Date startCallDate = new Date();
		log.info(String.format("getSubjects - startCallDate: %s", startCallDate));

		try {
			body = ResponseGenerator.generateResponse(subjectService.getSubjects(), HttpStatus.OK, startCallDate);
		} catch (Exception e) {
			log.error("Error occurred in getSubjects: " + e.getMessage());
			body = ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate);
		}
		return ResponseEntity.ok(body);
	}


	@PostMapping
	public ResponseEntity<?> addNewSubject(@RequestBody SubjectDTO subjectDTO) {

		@SuppressWarnings("rawtypes")
		ResponseBodyDTO body;

		Date startCallDate = new Date();
		log.info(String.format("addNewSubject - startCallDate: %s", startCallDate));

		try {
			subjectService.addNewSubject(subjectDTO);
			body = ResponseGenerator.generateResponse("Correctly added new subject.", HttpStatus.OK,
					startCallDate);

		} catch (Exception e) {
			log.error("Error occurred in addNewSubject: " + e.getMessage());
			body = ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate);
		}
		return ResponseEntity.ok(body);
	}


	@DeleteMapping(path = "{subjectId}")
	public ResponseEntity<ResponseBodyDTO<String>> deleteSubject(@PathVariable("subjectId") Long subjectId) {
		Date startCallDate = new Date();
		log.info(String.format("deleteSubject - startCallDate: %s", startCallDate));

		try {
			subjectService.deleteSubject(subjectId);
			return ResponseEntity.ok(
					ResponseGenerator.generateResponse("Subject deleted successfully.", HttpStatus.OK, startCallDate));
		} catch (Exception e) {
			log.error("Error occurred in deleteSubject: " + e.getMessage());
			return ResponseEntity.badRequest()
					.body(ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate));
		}
	}

	@PutMapping
	public ResponseEntity<ResponseBodyDTO<String>> updateSubject(@PathVariable Long subjectId,
			@RequestBody SubjectDTO subjectDTO) {
		Date startCallDate = new Date();
		log.info(String.format("updateSubject - startCallDate: %s", startCallDate));

		try {
			subjectDTO.setId(subjectId);
			subjectService.updateSubject(subjectDTO);
			return ResponseEntity.ok(
					ResponseGenerator.generateResponse("Subject updated successfully.", HttpStatus.OK, startCallDate));
		} catch (Exception e) {
			log.error("Error occurred in updateSubject: " + e.getMessage());
			return ResponseEntity.badRequest()
					.body(ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate));
		}
	}

	@PutMapping("{subjectId}/student/{studentId}")
	public ResponseEntity<ResponseBodyDTO<String>> enrollStudentInSubject(@PathVariable("subjectId") Long subjectId,
			@PathVariable("studentId") Long studentId) {
		Date startCallDate = new Date();
		log.info(String.format("enrollStudentInSubject - startCallDate: %s", startCallDate));

		try {
			subjectService.enrollStudent(subjectId, studentId);
			return ResponseEntity.ok(ResponseGenerator.generateResponse("Student enrolled in subject successfully.",
					HttpStatus.OK, startCallDate));
		} catch (Exception e) {
			log.error("Error occurred in enrollStudentInSubject: " + e.getMessage());
			return ResponseEntity.badRequest()
					.body(ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate));
		}
	}

	@PutMapping("{subjectId}/remove_student/{studentId}")
	public ResponseEntity<ResponseBodyDTO<String>> deleteEnrolledStudentFromSubject(
			@PathVariable("subjectId") Long subjectId, @PathVariable("studentId") Long studentId) {
		Date startCallDate = new Date();
		log.info(String.format("deleteEnrolledStudentFromSubject - startCallDate: %s", startCallDate));

		try {
			subjectService.deleteEnrolledStudent(subjectId, studentId);
			return ResponseEntity.ok(ResponseGenerator.generateResponse("Student removed from subject successfully.",
					HttpStatus.OK, startCallDate));
		} catch (Exception e) {
			log.error("Error occurred in deleteEnrolledStudentFromSubject: " + e.getMessage());
			return ResponseEntity.badRequest()
					.body(ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate));
		}
	}

	@PutMapping("{subjectId}/teacher/{teacherId}")
	public ResponseEntity<ResponseBodyDTO<String>> assignTeacherToSubject(@PathVariable("subjectId") Long subjectId,
			@PathVariable("teacherId") Long teacherId) {
		Date startCallDate = new Date();
		log.info(String.format("assignTeacherToSubject - startCallDate: %s", startCallDate));

		try {
			subjectService.assignTeacher(subjectId, teacherId);
			return ResponseEntity.ok(ResponseGenerator.generateResponse("Teacher assigned to subject successfully.",
					HttpStatus.OK, startCallDate));
		} catch (Exception e) {
			log.error("Error occurred in assignTeacherToSubject: " + e.getMessage());
			return ResponseEntity.badRequest()
					.body(ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate));
		}
	}

	@PutMapping("{subjectId}/remove_teacher")
	public ResponseEntity<ResponseBodyDTO<String>> removeAssignedTeacher(@PathVariable("subjectId") Long subjectId) {
	    Date startCallDate = new Date();
	    log.info(String.format("removeAssignedTeacher - startCallDate: %s", startCallDate));

	    try {
	        subjectService.deleteAssignedTeacher(subjectId);
	        return ResponseEntity.ok(ResponseGenerator.generateResponse("Assigned teacher removed from subject successfully.", HttpStatus.OK, startCallDate));
	    } catch (Exception e) {
	        log.error("Error occurred in removeAssignedTeacher: " + e.getMessage());
	        return ResponseEntity.badRequest().body(ResponseGenerator.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, startCallDate));
	    }
	}
}
