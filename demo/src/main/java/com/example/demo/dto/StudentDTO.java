package com.example.demo.dto;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

	private long id;

	private String name;

	private String email;

	private LocalDate dob;

	private Integer age;

	private List<SubjectDTO> subjects;

	public StudentDTO(String name, String email, LocalDate dob) {
		this.name = name;
		this.email = email;
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", age=" + age + "]";
	}

	public int getAge() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}

}
