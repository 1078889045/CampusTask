package com.niit.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RwPinjia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rw_pinjia", catalog = "renwu")
public class RwPinjia implements java.io.Serializable {

	// Fields

	private Integer pinjiaId;
	private Timestamp pinjiaZhurenAddTime;
	private Integer pinjiaZhurenValue;
	private String pinjiaZhurenContent;
	private Timestamp pinjiaRenlingAddTime;
	private Integer pinjiaRenlingValue;
	private String pinjiaRenlingContent;
	private Integer xuqiuId;

	// Constructors

	/** default constructor */
	public RwPinjia() {
	}

	/** minimal constructor */
	public RwPinjia(Integer xuqiuId) {
		this.xuqiuId = xuqiuId;
	}

	/** full constructor */
	public RwPinjia(Timestamp pinjiaZhurenAddTime, Integer pinjiaZhurenValue,
			String pinjiaZhurenContent, Timestamp pinjiaRenlingAddTime,
			Integer pinjiaRenlingValue, String pinjiaRenlingContent,
			Integer xuqiuId) {
		this.pinjiaZhurenAddTime = pinjiaZhurenAddTime;
		this.pinjiaZhurenValue = pinjiaZhurenValue;
		this.pinjiaZhurenContent = pinjiaZhurenContent;
		this.pinjiaRenlingAddTime = pinjiaRenlingAddTime;
		this.pinjiaRenlingValue = pinjiaRenlingValue;
		this.pinjiaRenlingContent = pinjiaRenlingContent;
		this.xuqiuId = xuqiuId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Pinjia_ID", unique = true, nullable = false)
	public Integer getPinjiaId() {
		return this.pinjiaId;
	}

	public void setPinjiaId(Integer pinjiaId) {
		this.pinjiaId = pinjiaId;
	}

	@Column(name = "Pinjia_ZhurenAddTime", length = 19)
	public Timestamp getPinjiaZhurenAddTime() {
		return this.pinjiaZhurenAddTime;
	}

	public void setPinjiaZhurenAddTime(Timestamp pinjiaZhurenAddTime) {
		this.pinjiaZhurenAddTime = pinjiaZhurenAddTime;
	}

	@Column(name = "Pinjia_ZhurenValue")
	public Integer getPinjiaZhurenValue() {
		return this.pinjiaZhurenValue;
	}

	public void setPinjiaZhurenValue(Integer pinjiaZhurenValue) {
		this.pinjiaZhurenValue = pinjiaZhurenValue;
	}

	@Column(name = "Pinjia_ZhurenContent", length = 2000)
	public String getPinjiaZhurenContent() {
		return this.pinjiaZhurenContent;
	}

	public void setPinjiaZhurenContent(String pinjiaZhurenContent) {
		this.pinjiaZhurenContent = pinjiaZhurenContent;
	}

	@Column(name = "Pinjia_RenlingAddTime", length = 19)
	public Timestamp getPinjiaRenlingAddTime() {
		return this.pinjiaRenlingAddTime;
	}

	public void setPinjiaRenlingAddTime(Timestamp pinjiaRenlingAddTime) {
		this.pinjiaRenlingAddTime = pinjiaRenlingAddTime;
	}

	@Column(name = "Pinjia_RenlingValue")
	public Integer getPinjiaRenlingValue() {
		return this.pinjiaRenlingValue;
	}

	public void setPinjiaRenlingValue(Integer pinjiaRenlingValue) {
		this.pinjiaRenlingValue = pinjiaRenlingValue;
	}

	@Column(name = "Pinjia_RenlingContent", length = 2000)
	public String getPinjiaRenlingContent() {
		return this.pinjiaRenlingContent;
	}

	public void setPinjiaRenlingContent(String pinjiaRenlingContent) {
		this.pinjiaRenlingContent = pinjiaRenlingContent;
	}

	@Column(name = "Xuqiu_ID", nullable = false)
	public Integer getXuqiuId() {
		return this.xuqiuId;
	}

	public void setXuqiuId(Integer xuqiuId) {
		this.xuqiuId = xuqiuId;
	}

}