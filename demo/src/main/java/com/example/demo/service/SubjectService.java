package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SubjectDTO;
import com.example.demo.entity.Subject;
import com.example.demo.mapper.SubjectMapper;
import com.example.demo.repository.SubjectRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectService {

	@Autowired
	private final SubjectRepository subjectRepository;
	@Autowired
	private final SubjectMapper subjectMapper;

	public List<SubjectDTO> getSubjects() {
		List<Subject> subjects = subjectRepository.findAll();
		return subjects.stream().map(subjectMapper::entityToDTO).collect(Collectors.toList());
	}

	public void addNewSubject(SubjectDTO subjectDTO) {
		Subject subject = subjectMapper.DTOToEntity(subjectDTO);
		subjectRepository.save(subject);
	}

	public void deleteSubject(Long subjectId) {
		subjectRepository.deleteById(subjectId);
	}

	public void updateSubject(SubjectDTO subjectDTO) {
		Subject subject = subjectMapper.DTOToEntity(subjectDTO);
		subjectRepository.save(subject);
	}
}