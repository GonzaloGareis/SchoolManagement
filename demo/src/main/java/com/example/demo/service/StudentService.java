package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repository.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentService {

	@Autowired
	private final StudentRepository studentRepository;

	@Autowired
	private final StudentMapper studentMapper;

	public List<StudentDTO> getStudents() {
		List<StudentDTO> dtos = new ArrayList<>();

		List<Student> entities = studentRepository.findAll();

		for (Student student : entities) {
			dtos.add(studentMapper.entityToDTO(student));
		}

		return dtos;
	}

	public void addNewStudent(StudentDTO dto) {
		if (studentRepository.selectExistsEmail(dto.getEmail())) {
			throw new IllegalStateException("Email Taken");
		}

		Student entity = studentMapper.DTOToEntity(dto);
		studentRepository.save(entity);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if (!exists) {
			throw new IllegalStateException("Student with Id " + studentId + "does not exist");

		}
		studentRepository.deleteById(studentId);

	}

	@Transactional
	public void updateStudent(StudentDTO dto) {
		Student entity = studentRepository.findById(dto.getId())
				.orElseThrow(() -> new IllegalStateException("Student with Id " + dto.getId() + "does not exist"));

		if (dto.getName() != null && dto.getName().length() > 0 && !Objects.equals(entity.getName(), dto.getName())) {
			entity.setName(dto.getName());
		}
		if (dto.getEmail() != null && dto.getEmail().length() > 0
				&& !Objects.equals(entity.getEmail(), dto.getEmail())) {
			if (studentRepository.selectExistsEmail(dto.getEmail())) {
				throw new IllegalStateException("Email Taken");
			}
			entity.setEmail(dto.getEmail());
		}
	}
}
