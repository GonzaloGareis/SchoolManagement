package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

	private long id;

	private String name;
	
	private List<String> subjects;


	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + "]";
	}
}
