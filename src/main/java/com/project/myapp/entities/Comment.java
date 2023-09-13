package com.project.myapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "comment")
@Data
public class Comment {

	@Id
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
	/*
	 * @Lob anotasyonunu kullanarak, bir sütunu "Large Object" olarak işaretlersiniz
	 * ve bu sütunun veritabanında büyük verileri depolamak için uygun bir şekilde
	 * işlenmesini sağlarsınız. "text" tipini kullanarak, bu sütunun bir metin veri
	 * türüne sahip olmasını belirtiyorsunuz. "text" tipi genellikle uzun metin veya
	 * büyük metin verileri için kullanılır ve metin verilerini depolamak için uygun
	 * bir veri türüdür.
	 */
}
