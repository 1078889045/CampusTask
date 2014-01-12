package com.niit.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Students entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Students", schema = "dbo", catalog = "CampusTask")
public class Students implements java.io.Serializable {

	// Fields

	private Integer studentNo;
	private String studentName;
	private String class_;
	private Set<Users> userses = new HashSet<Users>(0);

	// Constructors

	/** default constructor */
	public Students() {
	}

	/** minimal constructor */
	public Students(String studentName, String class_) {
		this.studentName = studentName;
		this.class_ = class_;
	}

	/** full constructor */
	public Students(String studentName, String class_, Set<Users> userses) {
		this.studentName = studentName;
		this.class_ = class_;
		this.userses = userses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "StudentNo", unique = true, nullable = false)
	public Integer getStudentNo() {
		return this.studentNo;
	}

	public void setStudentNo(Integer studentNo) {
		this.studentNo = studentNo;
	}

	@Column(name = "StudentName", nullable = false, length = 50)
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Column(name = "Class", nullable = false, length = 50)
	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "students")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

}