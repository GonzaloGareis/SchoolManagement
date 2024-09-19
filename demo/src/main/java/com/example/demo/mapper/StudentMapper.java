package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.StudentDTO;
import com.example.demo.dto.SubjectDTO;
import com.example.demo.entity.Student;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class StudentMapper {

	@Autowired
	private final SubjectMapper subjectMapper;

	public Student DTOToEntity(StudentDTO dto) {
		Student entity = new Student();

		entity.setDob(dto.getDob());
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());

		return entity;
	}

	public StudentDTO entityToDTO(Student entity) {
		StudentDTO dto = new StudentDTO();

		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setEmail(entity.getEmail());
		dto.setDob(entity.getDob());
		List<SubjectDTO> subjectList = entity.getSubjects().stream()
				.map(subject -> subjectMapper.entityToDTO(subject)).collect(Collectors.toList());
		dto.setSubjects(subjectList);
		return dto;
	}
}
