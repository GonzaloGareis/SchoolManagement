package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.dto.TeacherDTO;
import com.example.demo.entity.Teacher;

@Component
public class TeacherMapper {

	public Teacher DTOToEntity(TeacherDTO dto) {
		Teacher entity = new Teacher();

		entity.setName(dto.getName());
		return entity;
	}

	public TeacherDTO entityToDTO(Teacher entity) {
		TeacherDTO dto = new TeacherDTO();

		dto.setId(entity.getId());
		dto.setName(entity.getName());
		List<String> subjectList = entity.getSubjects().stream().map(subject -> subject.toString())
				.collect(Collectors.toList());
		dto.setSubjects(subjectList);

		return dto;
	}
}
