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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Users", schema = "dbo", catalog = "CampusTask")
public class Users implements java.io.Serializable {

	// Fields

	private Integer userId;
	private Students students;
	private String userName;
	private String userPassword;
	private String contact;
	private Integer userLevel;
	private Set<Tasks> taskses = new HashSet<Tasks>(0);
	private Set<Accepts> acceptses = new HashSet<Accepts>(0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(Students students, String userName, String userPassword,
			String contact, Integer userLevel) {
		this.students = students;
		this.userName = userName;
		this.userPassword = userPassword;
		this.contact = contact;
		this.userLevel = userLevel;
	}

	/** full constructor */
	public Users(Students students, String userName, String userPassword,
			String contact, Integer userLevel, Set<Tasks> taskses,
			Set<Accepts> acceptses) {
		this.students = students;
		this.userName = userName;
		this.userPassword = userPassword;
		this.contact = contact;
		this.userLevel = userLevel;
		this.taskses = taskses;
		this.acceptses = acceptses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "UserID", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StudentNo", nullable = false)
	public Students getStudents() {
		return this.students;
	}

	public void setStudents(Students students) {
		this.students = students;
	}

	@Column(name = "UserName", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "UserPassword", nullable = false, length = 50)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "Contact", nullable = false, length = 50)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "UserLevel", nullable = false)
	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	@OneToMany( fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Tasks> getTaskses() {
		return this.taskses;
	}

	public void setTaskses(Set<Tasks> taskses) {
		this.taskses = taskses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Accepts> getAcceptses() {
		return this.acceptses;
	}

	public void setAcceptses(Set<Accepts> acceptses) {
		this.acceptses = acceptses;
	}

}