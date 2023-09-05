package com.project.myapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "comment")
@Data
public class Comment {

	
	@Id
	Long id;
	Long postId;
	Long userId;
	
	@Lob
	@Column(columnDefinition = "text")
	String text;
	/*
	 *@Lob anotasyonunu kullanarak, bir sütunu "Large Object" olarak işaretlersiniz 
	 *ve bu sütunun veritabanında büyük verileri depolamak için uygun bir şekilde işlenmesini sağlarsınız. 
	 *"text" tipini kullanarak, bu sütunun bir metin veri türüne sahip olmasını belirtiyorsunuz. 
	 *"text" tipi genellikle uzun metin veya büyük metin verileri için kullanılır
	 * ve metin verilerini depolamak için uygun bir veri türüdür.
	 */
}
