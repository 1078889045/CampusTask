package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RwXuqiufenlei entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rw_xuqiufenlei", catalog = "renwu")
public class RwXuqiufenlei implements java.io.Serializable {

	// Fields

	private Integer xuqiuFenleiId;
	private String xuqiuFenleiName;

	// Constructors

	/** default constructor */
	public RwXuqiufenlei() {
	}

	/** full constructor */
	public RwXuqiufenlei(String xuqiuFenleiName) {
		this.xuqiuFenleiName = xuqiuFenleiName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "XuqiuFenlei_ID", unique = true, nullable = false)
	public Integer getXuqiuFenleiId() {
		return this.xuqiuFenleiId;
	}

	public void setXuqiuFenleiId(Integer xuqiuFenleiId) {
		this.xuqiuFenleiId = xuqiuFenleiId;
	}

	@Column(name = "XuqiuFenlei_Name", length = 50)
	public String getXuqiuFenleiName() {
		return this.xuqiuFenleiName;
	}

	public void setXuqiuFenleiName(String xuqiuFenleiName) {
		this.xuqiuFenleiName = xuqiuFenleiName;
	}

}