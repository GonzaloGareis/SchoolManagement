package com.example.demo.mapper;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;

public class StudentMapper {

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

		return dto;
	}

}
