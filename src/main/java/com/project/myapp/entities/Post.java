package com.project.myapp.entities;

import java.sql.Date;

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
@Table(name = "post")
@Data
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	// post objlerini cektigimde bana ilgili user lari getirme
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	User user;


	String title;

	@Lob
	@Column(columnDefinition = "text")
	String text;
	/*
	@Temporal(TemporalType.TIMESTAMP)
	Date createDate;
	*/

	/*
	 * OnDeleteAction.CASCADE: Eğer bir kayıt siliniyorsa, bu kaynağa bağlı kayıtlar
	 * da otomatik olarak silinir. Yani, bu özellik sayesinde ilişkili kayıtların
	 * silinmesi tetiklenir.
	 * 
	 * OnDeleteAction.NO_ACTION: Bu değer kullanıldığında, silme işlemi sırasında
	 * hiçbir şey yapılmaz. İlişkili kayıtlar silinmez ve bağlantılar korunur.
	 * 
	 * @JsonIgnore anotasyonu, Jackson veya diğer JSON dönüşüm kütüphaneleri
	 * tarafından kullanılan bir anotasyondur. Bu anotasyon, bir Java sınıfındaki
	 * belirli bir alanın veya getter metodunun JSON serileştirilmesini (nesneyi
	 * JSON formatına dönüştürme) sırasında göz ardı edilmesini (ignore) sağlar.
	 * 
	 * @JsonIgnore anotasyonunu kullanmanın bazı yaygın senaryoları şunlar olabilir:
	 * 
	 * Hassas Verilerin Gizlenmesi: Bir sınıfın içinde hassas bilgileri temsil eden
	 * 
	 * 
	 * Döngüsel Bağlantıları Önleme: İki sınıf arasında döngüsel bir bağlantı
	 * (örneğin, ebeveyn-çocuk ilişkisi) varsa, bu döngüsel bağlantıyı çözmek ve
	 * sonsuz döngüyü önlemek için bir sınıfın alanları üzerinde @JsonIgnore
	 * kullanabilirsiniz.
	 */
}
