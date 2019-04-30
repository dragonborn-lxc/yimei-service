package com.ymcoffee.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Dictionary extends BaseModel {
	private Integer code;
	private Integer subcode;

	@Column(name = "code_desc")
	private String codeDesc;

	@Column(name = "subcode_desc")
	private String subcodeDesc;
}
