package com.niit.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Category entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Category", schema = "dbo", catalog = "Foreign_trade")
public class Category implements java.io.Serializable {

	// Fields

	private Integer id;
	private String catName;
	private Set<News> newses = new HashSet<News>(0);

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** minimal constructor */
	public Category(String catName) {
		this.catName = catName;
	}

	/** full constructor */
	public Category(String catName, Set<News> newses) {
		this.catName = catName;
		this.newses = newses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CatName", nullable = false, length = 20)
	public String getCatName() {
		return this.catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
	public Set<News> getNewses() {
		return this.newses;
	}

	public void setNewses(Set<News> newses) {
		this.newses = newses;
	}

}