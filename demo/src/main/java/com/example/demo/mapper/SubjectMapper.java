package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.SubjectDTO;
import com.example.demo.entity.Subject;

@Component
public class SubjectMapper {

	public Subject DTOToEntity(SubjectDTO dto) {
		Subject entity = new Subject();

		entity.setName(dto.getName());

		return entity;
	}

	public SubjectDTO entityToDTO(Subject entity) {
		SubjectDTO dto = new SubjectDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setTeacher(entity.getTeacher());
		return dto;
	}

}
