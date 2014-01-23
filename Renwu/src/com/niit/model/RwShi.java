package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RwShi entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rw_shi", catalog = "renwu")
public class RwShi implements java.io.Serializable {

	// Fields

	private Integer shiId;
	private String shiName;
	private Integer shengId;

	// Constructors

	/** default constructor */
	public RwShi() {
	}

	/** minimal constructor */
	public RwShi(Integer shengId) {
		this.shengId = shengId;
	}

	/** full constructor */
	public RwShi(String shiName, Integer shengId) {
		this.shiName = shiName;
		this.shengId = shengId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Shi_ID", unique = true, nullable = false)
	public Integer getShiId() {
		return this.shiId;
	}

	public void setShiId(Integer shiId) {
		this.shiId = shiId;
	}

	@Column(name = "Shi_Name", length = 50)
	public String getShiName() {
		return this.shiName;
	}

	public void setShiName(String shiName) {
		this.shiName = shiName;
	}

	@Column(name = "Sheng_ID", nullable = false)
	public Integer getShengId() {
		return this.shengId;
	}

	public void setShengId(Integer shengId) {
		this.shengId = shengId;
	}

}