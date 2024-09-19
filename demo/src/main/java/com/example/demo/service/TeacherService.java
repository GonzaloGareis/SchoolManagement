package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.TeacherDTO;
import com.example.demo.entity.Teacher;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.repository.TeacherRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TeacherService {

	@Autowired
	private final TeacherRepository teacherRepository;

	@Autowired
	private final TeacherMapper teacherMapper;

	public List<TeacherDTO> getTeachers() {
		List<TeacherDTO> dtos = new ArrayList<>();
		List<Teacher> entities = teacherRepository.findAll();

		for (Teacher teacher : entities) {
			dtos.add(teacherMapper.entityToDTO(teacher));
		}

		return dtos;
	}

	/**
	 * Adds a new teacher to the database.
	 */
	public void addNewTeacher(TeacherDTO dto) {
		Teacher entity = teacherMapper.DTOToEntity(dto);
		teacherRepository.save(entity);
	}


	/**
	 * Deletes an existing teacher from the database.
	 */
	public void deleteTeacher(Long teacherId) {
		boolean exists = teacherRepository.existsById(teacherId);
		if (!exists) {
			throw new IllegalStateException("Teacher with Id " + teacherId + "does not exist");

		}
		teacherRepository.deleteById(teacherId);
	}

	/**
	 * Updates the information of an existing teacher in the database.
	 */
	@Transactional
	public void updateTeacher(TeacherDTO dto) {
		Teacher entity = teacherRepository.findById(dto.getId())
				.orElseThrow(() -> new IllegalStateException("Teacher with Id " + dto.getId() + " does not exist"));

		if (dto.getName() != null && dto.getName().length() > 0 && !Objects.equals(entity.getName(), dto.getName())) {
			entity.setName(dto.getName());
		}
	}
}
