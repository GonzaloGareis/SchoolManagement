package com.example.demo.dto;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBodyDTO<T> {

	private T data;

	private Date startCallDate;
	private Date endCallDate;
	private HttpStatus status;

	public ResponseBodyDTO() {
	}

	public ResponseBodyDTO(T data, Date startCallDate, Date endCallDate, HttpStatus status) {
		this.data = data;
		this.startCallDate = startCallDate;
		this.endCallDate = endCallDate;
		this.status = status;
	}

	public ResponseBodyDTO(Date startCallDate, Date endCallDate, HttpStatus status) {
		this.startCallDate = startCallDate;
		this.endCallDate = endCallDate;
		this.status = status;
	}
}
