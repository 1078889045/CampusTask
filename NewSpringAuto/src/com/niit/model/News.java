package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * News entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "News", schema = "dbo", catalog = "Foreign_trade")
public class News implements java.io.Serializable {

	// Fields

	private Integer newsId;
	private Category category;
	private String newsTitle;
	private String newsContent;
	private String newsAddUser;
	private String newsAddIp;

	// Constructors

	/** default constructor */
	public News() {
	}

	/** full constructor */
	public News(Category category, String newsTitle, String newsContent,
			String newsAddUser, String newsAddIp) {
		this.category = category;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsAddUser = newsAddUser;
		this.newsAddIp = newsAddIp;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "News_ID", unique = true, nullable = false)
	public Integer getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "News_Title", nullable = false, length = 100)
	public String getNewsTitle() {
		return this.newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	@Column(name = "News_Content", nullable = false, length = 500)
	public String getNewsContent() {
		return this.newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	@Column(name = "News_AddUser", nullable = false, length = 100)
	public String getNewsAddUser() {
		return this.newsAddUser;
	}

	public void setNewsAddUser(String newsAddUser) {
		this.newsAddUser = newsAddUser;
	}

	@Column(name = "News_AddIP", nullable = false, length = 50)
	public String getNewsAddIp() {
		return this.newsAddIp;
	}

	public void setNewsAddIp(String newsAddIp) {
		this.newsAddIp = newsAddIp;
	}

}