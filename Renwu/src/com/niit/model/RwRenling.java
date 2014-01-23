package com.niit.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RwRenling entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rw_renling", catalog = "renwu")
public class RwRenling implements java.io.Serializable {

	// Fields

	private Integer renlingId;
	private String renlingContent;
	private Timestamp renlingAddTime;
	private Boolean renlingIsDeal;
	private Integer yonghuId;
	private Integer xuqiuId;

	// Constructors

	/** default constructor */
	public RwRenling() {
	}

	/** minimal constructor */
	public RwRenling(Integer yonghuId, Integer xuqiuId) {
		this.yonghuId = yonghuId;
		this.xuqiuId = xuqiuId;
	}

	/** full constructor */
	public RwRenling(String renlingContent, Timestamp renlingAddTime,
			Boolean renlingIsDeal, Integer yonghuId, Integer xuqiuId) {
		this.renlingContent = renlingContent;
		this.renlingAddTime = renlingAddTime;
		this.renlingIsDeal = renlingIsDeal;
		this.yonghuId = yonghuId;
		this.xuqiuId = xuqiuId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Renling_ID", unique = true, nullable = false)
	public Integer getRenlingId() {
		return this.renlingId;
	}

	public void setRenlingId(Integer renlingId) {
		this.renlingId = renlingId;
	}

	@Column(name = "Renling_Content", length = 2000)
	public String getRenlingContent() {
		return this.renlingContent;
	}

	public void setRenlingContent(String renlingContent) {
		this.renlingContent = renlingContent;
	}

	@Column(name = "Renling_AddTime", length = 19)
	public Timestamp getRenlingAddTime() {
		return this.renlingAddTime;
	}

	public void setRenlingAddTime(Timestamp renlingAddTime) {
		this.renlingAddTime = renlingAddTime;
	}

	@Column(name = "Renling_IsDeal")
	public Boolean getRenlingIsDeal() {
		return this.renlingIsDeal;
	}

	public void setRenlingIsDeal(Boolean renlingIsDeal) {
		this.renlingIsDeal = renlingIsDeal;
	}

	@Column(name = "Yonghu_ID", nullable = false)
	public Integer getYonghuId() {
		return this.yonghuId;
	}

	public void setYonghuId(Integer yonghuId) {
		this.yonghuId = yonghuId;
	}

	@Column(name = "Xuqiu_ID", nullable = false)
	public Integer getXuqiuId() {
		return this.xuqiuId;
	}

	public void setXuqiuId(Integer xuqiuId) {
		this.xuqiuId = xuqiuId;
	}

}