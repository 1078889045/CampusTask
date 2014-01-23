package com.niit.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RwXuqiu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rw_xuqiu", catalog = "renwu")
public class RwXuqiu implements java.io.Serializable {

	// Fields

	private Integer xuqiuId;
	private String xuqiuTitle;
	private String xuqiuContent;
	private Double xuqiuMoney;
	private Timestamp xuqiuAddTime;
	private Timestamp xuqiuDeadLine;
	private Integer yonghuId;
	private Integer xuqiuFenleiId;

	// Constructors

	/** default constructor */
	public RwXuqiu() {
	}

	/** minimal constructor */
	public RwXuqiu(Integer yonghuId, Integer xuqiuFenleiId) {
		this.yonghuId = yonghuId;
		this.xuqiuFenleiId = xuqiuFenleiId;
	}

	/** full constructor */
	public RwXuqiu(String xuqiuTitle, String xuqiuContent, Double xuqiuMoney,
			Timestamp xuqiuAddTime, Timestamp xuqiuDeadLine, Integer yonghuId,
			Integer xuqiuFenleiId) {
		this.xuqiuTitle = xuqiuTitle;
		this.xuqiuContent = xuqiuContent;
		this.xuqiuMoney = xuqiuMoney;
		this.xuqiuAddTime = xuqiuAddTime;
		this.xuqiuDeadLine = xuqiuDeadLine;
		this.yonghuId = yonghuId;
		this.xuqiuFenleiId = xuqiuFenleiId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Xuqiu_ID", unique = true, nullable = false)
	public Integer getXuqiuId() {
		return this.xuqiuId;
	}

	public void setXuqiuId(Integer xuqiuId) {
		this.xuqiuId = xuqiuId;
	}

	@Column(name = "Xuqiu_Title", length = 250)
	public String getXuqiuTitle() {
		return this.xuqiuTitle;
	}

	public void setXuqiuTitle(String xuqiuTitle) {
		this.xuqiuTitle = xuqiuTitle;
	}

	@Column(name = "Xuqiu_Content", length = 2000)
	public String getXuqiuContent() {
		return this.xuqiuContent;
	}

	public void setXuqiuContent(String xuqiuContent) {
		this.xuqiuContent = xuqiuContent;
	}

	@Column(name = "Xuqiu_Money", precision = 10)
	public Double getXuqiuMoney() {
		return this.xuqiuMoney;
	}

	public void setXuqiuMoney(Double xuqiuMoney) {
		this.xuqiuMoney = xuqiuMoney;
	}

	@Column(name = "Xuqiu_AddTime", length = 19)
	public Timestamp getXuqiuAddTime() {
		return this.xuqiuAddTime;
	}

	public void setXuqiuAddTime(Timestamp xuqiuAddTime) {
		this.xuqiuAddTime = xuqiuAddTime;
	}

	@Column(name = "Xuqiu_DeadLine", length = 19)
	public Timestamp getXuqiuDeadLine() {
		return this.xuqiuDeadLine;
	}

	public void setXuqiuDeadLine(Timestamp xuqiuDeadLine) {
		this.xuqiuDeadLine = xuqiuDeadLine;
	}

	@Column(name = "Yonghu_ID", nullable = false)
	public Integer getYonghuId() {
		return this.yonghuId;
	}

	public void setYonghuId(Integer yonghuId) {
		this.yonghuId = yonghuId;
	}

	@Column(name = "XuqiuFenlei_ID", nullable = false)
	public Integer getXuqiuFenleiId() {
		return this.xuqiuFenleiId;
	}

	public void setXuqiuFenleiId(Integer xuqiuFenleiId) {
		this.xuqiuFenleiId = xuqiuFenleiId;
	}

}