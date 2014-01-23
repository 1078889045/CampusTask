package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RwXiaoqu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rw_xiaoqu", catalog = "renwu")
public class RwXiaoqu implements java.io.Serializable {

	// Fields

	private Integer xiaoquId;
	private String xiaoquName;
	private Integer quId;
	private Integer xuexiaoId;

	// Constructors

	/** default constructor */
	public RwXiaoqu() {
	}

	/** minimal constructor */
	public RwXiaoqu(Integer quId, Integer xuexiaoId) {
		this.quId = quId;
		this.xuexiaoId = xuexiaoId;
	}

	/** full constructor */
	public RwXiaoqu(String xiaoquName, Integer quId, Integer xuexiaoId) {
		this.xiaoquName = xiaoquName;
		this.quId = quId;
		this.xuexiaoId = xuexiaoId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Xiaoqu_ID", unique = true, nullable = false)
	public Integer getXiaoquId() {
		return this.xiaoquId;
	}

	public void setXiaoquId(Integer xiaoquId) {
		this.xiaoquId = xiaoquId;
	}

	@Column(name = "Xiaoqu_Name", length = 250)
	public String getXiaoquName() {
		return this.xiaoquName;
	}

	public void setXiaoquName(String xiaoquName) {
		this.xiaoquName = xiaoquName;
	}

	@Column(name = "Qu_ID", nullable = false)
	public Integer getQuId() {
		return this.quId;
	}

	public void setQuId(Integer quId) {
		this.quId = quId;
	}

	@Column(name = "Xuexiao_ID", nullable = false)
	public Integer getXuexiaoId() {
		return this.xuexiaoId;
	}

	public void setXuexiaoId(Integer xuexiaoId) {
		this.xuexiaoId = xuexiaoId;
	}

}