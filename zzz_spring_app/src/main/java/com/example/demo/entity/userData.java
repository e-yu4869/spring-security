package com.example.demo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class userData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String userName;

	private String password;

	private String admin;


}
