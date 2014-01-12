package com.niit.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Tasks entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Tasks", schema = "dbo", catalog = "CampusTask")
public class Tasks implements java.io.Serializable {

	// Fields

	private Integer taskId;
	private Users users;
	private String taskContent;
	private Double reward;
	private Date releaseTime;
	private Date stopTime;
	private Integer acceptId;
	private Boolean ifComplete;
	private Set<Accepts> acceptses = new HashSet<Accepts>(0);

	// Constructors

	/** default constructor */
	public Tasks() {
	}

	/** minimal constructor */
	public Tasks(Users users, String taskContent, Double reward,
			Date releaseTime, Date stopTime, Boolean ifComplete) {
		this.users = users;
		this.taskContent = taskContent;
		this.reward = reward;
		this.releaseTime = releaseTime;
		this.stopTime = stopTime;
		this.ifComplete = ifComplete;
	}

	/** full constructor */
	public Tasks(Users users, String taskContent, Double reward,
			Date releaseTime, Date stopTime, Integer acceptId,
			Boolean ifComplete, Set<Accepts> acceptses) {
		this.users = users;
		this.taskContent = taskContent;
		this.reward = reward;
		this.releaseTime = releaseTime;
		this.stopTime = stopTime;
		this.acceptId = acceptId;
		this.ifComplete = ifComplete;
		this.acceptses = acceptses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TaskID", unique = true, nullable = false)
	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IssueID", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "TaskContent", nullable = false)
	public String getTaskContent() {
		return this.taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	@Column(name = "Reward", nullable = false, precision = 53, scale = 0)
	public Double getReward() {
		return this.reward;
	}

	public void setReward(Double reward) {
		this.reward = reward;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ReleaseTime", nullable = false, length = 10)
	public Date getReleaseTime() {
		return this.releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "StopTime", nullable = false, length = 10)
	public Date getStopTime() {
		return this.stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	@Column(name = "AcceptID")
	public Integer getAcceptId() {
		return this.acceptId;
	}

	public void setAcceptId(Integer acceptId) {
		this.acceptId = acceptId;
	}

	@Column(name = "IfComplete", nullable = false)
	public Boolean getIfComplete() {
		return this.ifComplete;
	}

	public void setIfComplete(Boolean ifComplete) {
		this.ifComplete = ifComplete;
	}

	@OneToMany( fetch = FetchType.LAZY, mappedBy = "tasks")
	public Set<Accepts> getAcceptses() {
		return this.acceptses;
	}

	public void setAcceptses(Set<Accepts> acceptses) {
		this.acceptses = acceptses;
	}

}