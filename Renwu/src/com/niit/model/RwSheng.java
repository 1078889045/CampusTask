package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RwSheng entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rw_sheng", catalog = "renwu")
public class RwSheng implements java.io.Serializable {

	// Fields

	private Integer shengId;
	private String shengName;

	// Constructors

	/** default constructor */
	public RwSheng() {
	}

	/** full constructor */
	public RwSheng(String shengName) {
		this.shengName = shengName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Sheng_ID", unique = true, nullable = false)
	public Integer getShengId() {
		return this.shengId;
	}

	public void setShengId(Integer shengId) {
		this.shengId = shengId;
	}

	@Column(name = "Sheng_Name", length = 50)
	public String getShengName() {
		return this.shengName;
	}

	public void setShengName(String shengName) {
		this.shengName = shengName;
	}

}