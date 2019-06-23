package com.showTime.common.entity;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class IdEntity {

	@Id
	@GenericGenerator(name="pk",strategy="uuid")
	@GeneratedValue(generator="pk",strategy= GenerationType.IDENTITY)
	protected String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
