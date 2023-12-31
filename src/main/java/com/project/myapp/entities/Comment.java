package com.project.myapp.entities;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "comment")
@Data
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	User user;

	@Lob
	@Column(columnDefinition = "text")
	String text;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	LocalDateTime createDate;

	
	
	/*
	 * @Lob anotasyonunu kullanarak, bir sütunu "Large Object" olarak işaretlersiniz
	 * ve bu sütunun veritabanında büyük verileri depolamak için uygun bir şekilde
	 * işlenmesini sağlarsınız. "text" tipini kullanarak, bu sütunun bir metin veri
	 * türüne sahip olmasını belirtiyorsunuz. "text" tipi genellikle uzun metin veya
	 * büyük metin verileri için kullanılır ve metin verilerini depolamak için uygun
	 * bir veri türüdür.
	 */
}
