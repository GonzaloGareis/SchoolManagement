package com.example.demo.mapper;

import com.example.demo.dto.SubjectDTO;
import com.example.demo.entity.Subject;

public class SubjectMapper {

	public Subject DTOToEntity(SubjectDTO dto) {
		Subject entity = new Subject();

		entity.setName(dto.getName());

		return entity;
	}

	public SubjectDTO entityToDTO(Subject entity) {
		SubjectDTO dto = new SubjectDTO();

		dto.setName(entity.getName());

		return dto;
	}

}