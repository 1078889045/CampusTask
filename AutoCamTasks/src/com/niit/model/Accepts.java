package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Accepts entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Accepts", schema = "dbo", catalog = "CampusTask")
public class Accepts implements java.io.Serializable {

	// Fields

	private Integer aid;
	private Tasks tasks;
	private Users users;
	private Double offer;
	private String explain;

	// Constructors

	/** default constructor */
	public Accepts() {
	}

	/** minimal constructor */
	public Accepts(Tasks tasks, Users users, Double offer) {
		this.tasks = tasks;
		this.users = users;
		this.offer = offer;
	}

	/** full constructor */
	public Accepts(Tasks tasks, Users users, Double offer, String explain) {
		this.tasks = tasks;
		this.users = users;
		this.offer = offer;
		this.explain = explain;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AID", unique = true, nullable = false)
	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TaskID", nullable = false)
	public Tasks getTasks() {
		return this.tasks;
	}

	public void setTasks(Tasks tasks) {
		this.tasks = tasks;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AcceptID", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "Offer", nullable = false, precision = 53, scale = 0)
	public Double getOffer() {
		return this.offer;
	}

	public void setOffer(Double offer) {
		this.offer = offer;
	}

	@Column(name = "Explain")
	public String getExplain() {
		return this.explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

}