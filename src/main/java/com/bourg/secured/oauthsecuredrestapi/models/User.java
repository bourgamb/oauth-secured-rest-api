package com.bourg.secured.oauthsecuredrestapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

	private String fName;
	private String lName;
	private int age;
}
