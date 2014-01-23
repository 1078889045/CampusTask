package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RwXuexiao entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rw_xuexiao", catalog = "renwu")
public class RwXuexiao implements java.io.Serializable {

	// Fields

	private Integer xuexiaoId;
	private String xuexiaoName;

	// Constructors

	/** default constructor */
	public RwXuexiao() {
	}

	/** full constructor */
	public RwXuexiao(String xuexiaoName) {
		this.xuexiaoName = xuexiaoName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Xuexiao_ID", unique = true, nullable = false)
	public Integer getXuexiaoId() {
		return this.xuexiaoId;
	}

	public void setXuexiaoId(Integer xuexiaoId) {
		this.xuexiaoId = xuexiaoId;
	}

	@Column(name = "Xuexiao_Name", length = 250)
	public String getXuexiaoName() {
		return this.xuexiaoName;
	}

	public void setXuexiaoName(String xuexiaoName) {
		this.xuexiaoName = xuexiaoName;
	}

}