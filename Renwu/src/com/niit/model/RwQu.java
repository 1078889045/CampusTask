package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RwQu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rw_qu", catalog = "renwu")
public class RwQu implements java.io.Serializable {

	// Fields

	private Integer quId;
	private String quName;
	private Integer shiId;

	// Constructors

	/** default constructor */
	public RwQu() {
	}

	/** minimal constructor */
	public RwQu(Integer shiId) {
		this.shiId = shiId;
	}

	/** full constructor */
	public RwQu(String quName, Integer shiId) {
		this.quName = quName;
		this.shiId = shiId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Qu_ID", unique = true, nullable = false)
	public Integer getQuId() {
		return this.quId;
	}

	public void setQuId(Integer quId) {
		this.quId = quId;
	}

	@Column(name = "Qu_Name", length = 50)
	public String getQuName() {
		return this.quName;
	}

	public void setQuName(String quName) {
		this.quName = quName;
	}

	@Column(name = "Shi_ID", nullable = false)
	public Integer getShiId() {
		return this.shiId;
	}

	public void setShiId(Integer shiId) {
		this.shiId = shiId;
	}

}