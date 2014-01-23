package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RwYonghu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rw_yonghu", catalog = "renwu")
public class RwYonghu implements java.io.Serializable {

	// Fields

	private Integer yonghuId;
	private String yonghuName;
	private String yonghuTrueName;
	private String yonghuPwd;
	private String yonghuPhone;
	private Integer xiaoquId;

	// Constructors

	/** default constructor */
	public RwYonghu() {
	}

	/** minimal constructor */
	public RwYonghu(Integer xiaoquId) {
		this.xiaoquId = xiaoquId;
	}

	/** full constructor */
	public RwYonghu(String yonghuName, String yonghuTrueName, String yonghuPwd,
			String yonghuPhone, Integer xiaoquId) {
		this.yonghuName = yonghuName;
		this.yonghuTrueName = yonghuTrueName;
		this.yonghuPwd = yonghuPwd;
		this.yonghuPhone = yonghuPhone;
		this.xiaoquId = xiaoquId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Yonghu_ID", unique = true, nullable = false)
	public Integer getYonghuId() {
		return this.yonghuId;
	}

	public void setYonghuId(Integer yonghuId) {
		this.yonghuId = yonghuId;
	}

	@Column(name = "Yonghu_Name", length = 50)
	public String getYonghuName() {
		return this.yonghuName;
	}

	public void setYonghuName(String yonghuName) {
		this.yonghuName = yonghuName;
	}

	@Column(name = "Yonghu_TrueName", length = 50)
	public String getYonghuTrueName() {
		return this.yonghuTrueName;
	}

	public void setYonghuTrueName(String yonghuTrueName) {
		this.yonghuTrueName = yonghuTrueName;
	}

	@Column(name = "Yonghu_Pwd", length = 50)
	public String getYonghuPwd() {
		return this.yonghuPwd;
	}

	public void setYonghuPwd(String yonghuPwd) {
		this.yonghuPwd = yonghuPwd;
	}

	@Column(name = "Yonghu_Phone", length = 50)
	public String getYonghuPhone() {
		return this.yonghuPhone;
	}

	public void setYonghuPhone(String yonghuPhone) {
		this.yonghuPhone = yonghuPhone;
	}

	@Column(name = "Xiaoqu_ID", nullable = false)
	public Integer getXiaoquId() {
		return this.xiaoquId;
	}

	public void setXiaoquId(Integer xiaoquId) {
		this.xiaoquId = xiaoquId;
	}

}