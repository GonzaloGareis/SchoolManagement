package com.example.demo.dto;

import com.example.demo.entity.Teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SubjectDTO {

	private long id;

	private String name;

	private Teacher teacher;

	public SubjectDTO(String name) {
		this.name = name;
	}
}
