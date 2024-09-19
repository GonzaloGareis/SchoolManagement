package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.SubjectDTO;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.entity.Teacher;
import com.example.demo.mapper.SubjectMapper;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.repository.TeacherRepository;

import lombok.AllArgsConstructor;

/*
 * Service class for managing subjects, including CRUD operations, enrollment
 * of students and assignment of teachers.
 */
@Service
@AllArgsConstructor
public class SubjectService {

	@Autowired
	private final SubjectRepository subjectRepository;
	@Autowired
	private final SubjectMapper subjectMapper;

	@Autowired
	private final StudentRepository studentRepository;

	@Autowired
	private final TeacherRepository teacherRepository;

	/**
	 * Retrieves a list of all subjects in the system.
	 *
	 * @return A list of {@link SubjectDTO} representing the subjects.
	 */
	public List<SubjectDTO> getSubjects() {
		List<Subject> subjects = subjectRepository.findAll();
		return subjects.stream().map(subjectMapper::entityToDTO).collect(Collectors.toList());
	}

	/**
	 * Adds a new subject to the database.
	 */
	public void addNewSubject(SubjectDTO subjectDTO) {
		Subject subject = subjectMapper.DTOToEntity(subjectDTO);
		subjectRepository.save(subject);
	}

	/**
	 * Removes a subject from the database.
	 */
	public void deleteSubject(Long subjectId) {
		subjectRepository.deleteById(subjectId);
	}

	/**
	 * Updates the information of an existing subject
	 */
	public void updateSubject(SubjectDTO subjectDTO) {
		Subject subject = subjectMapper.DTOToEntity(subjectDTO);
		subjectRepository.save(subject);
	}


	/**
	 * Establishes a relationship between a given student and a subject (Enrolls a
	 * student to a subject).
	 */
	@Transactional
	public void enrollStudent(Long subjectId, Long studentId) {
		Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
		Optional<Student> studentOptional = studentRepository.findById(studentId);

		if (!studentOptional.isPresent()) {
			throw new IllegalStateException("Student with Id " + studentId + " does not exist");
		}

		if (!subjectOptional.isPresent()) {
			throw new IllegalStateException("Subject with Id " + subjectId + " does not exist");
		}

		Student student = studentOptional.get();
		Subject subject = subjectOptional.get();

		subject.getEnrolledStudents().add(student);
		subjectRepository.save(subject);
		student.getSubjects().add(subject);
		studentRepository.save(student);
	}


	/**
	 * Deletes the relationship between a given student and one subject.
	 */
	public void deleteEnrolledStudent(Long subjectId, Long studentId) {
		Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
		Optional<Student> studentOptional = studentRepository.findById(studentId);

		if (!studentOptional.isPresent()) {
			throw new IllegalStateException("Student with Id " + studentId + " does not exist");
		}

		if (!subjectOptional.isPresent()) {
			throw new IllegalStateException("Subject with Id " + subjectId + " does not exist");
		}

		Student student = studentOptional.get();
		Subject subject = subjectOptional.get();

		subject.getEnrolledStudents().remove(student);
		subjectRepository.save(subject);
		student.getSubjects().remove(subject);
		studentRepository.save(student);
	}

	/**
	 * Assigns a teacher to a subject.
	 */
	public void assignTeacher(Long subjectId, Long teacherId) {
		Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
		Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);

		if (!teacherOptional.isPresent()) {
			throw new IllegalStateException("Teacher with Id " + teacherId + " does not exist");
		}

		if (!subjectOptional.isPresent()) {
			throw new IllegalStateException("Subject with Id " + subjectId + " does not exist");
		}

		Teacher teacher = teacherOptional.get();
		Subject subject = subjectOptional.get();

		subject.setTeacher(teacher);
		subjectRepository.save(subject);
		teacher.getSubjects().add(subject);
		teacherRepository.save(teacher);
	}

	/**
	 * Deletes the assigned teacher from a subject and establishes it as null.
	 */
	public void deleteAssignedTeacher(Long subjectId) {
		Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);

		if (!subjectOptional.isPresent()) {
			throw new IllegalStateException("Subject with Id " + subjectId + " does not exist");
		}

		Subject subject = subjectOptional.get();
		subject.getTeacher().getSubjects().remove(subject);
		teacherRepository.save(subject.getTeacher());
		subject.setTeacher(null);
		subjectRepository.save(subject);
	}
}
