package com.sistemtyse.challenge.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.AccessLevel;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Event {

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "event_level")
	private LevelEnum level;

	@Size(max = 500)
	private String description;

	@NotBlank
	@Size(max = 255)
	private String log;

	@NotBlank
	@Size(max = 255)
	private String system;

	@Setter(AccessLevel.NONE)
	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "created_at")
	private Date createdAt;
	private Integer quantity;
	
}
