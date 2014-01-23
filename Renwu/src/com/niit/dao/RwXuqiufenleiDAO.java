package com.niit.dao;

import com.niit.common.dao.BaseHibernateDAO;
import com.niit.model.RwXuqiufenlei;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * RwXuqiufenlei entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.niit.model.RwXuqiufenlei
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RwXuqiufenleiDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RwXuqiufenleiDAO.class);
	// property constants
	public static final String XUQIU_FENLEI_NAME = "xuqiuFenleiName";

	public void save(RwXuqiufenlei transientInstance) {
		log.debug("saving RwXuqiufenlei instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RwXuqiufenlei persistentInstance) {
		log.debug("deleting RwXuqiufenlei instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RwXuqiufenlei findById(java.lang.Integer id) {
		log.debug("getting RwXuqiufenlei instance with id: " + id);
		try {
			RwXuqiufenlei instance = (RwXuqiufenlei) getSession().get(
					"com.niit.model.RwXuqiufenlei", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RwXuqiufenlei> findByExample(RwXuqiufenlei instance) {
		log.debug("finding RwXuqiufenlei instance by example");
		try {
			List<RwXuqiufenlei> results = (List<RwXuqiufenlei>) getSession()
					.createCriteria("com.niit.model.RwXuqiufenlei")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding RwXuqiufenlei instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RwXuqiufenlei as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<RwXuqiufenlei> findByXuqiuFenleiName(Object xuqiuFenleiName) {
		return findByProperty(XUQIU_FENLEI_NAME, xuqiuFenleiName);
	}

	public List findAll() {
		log.debug("finding all RwXuqiufenlei instances");
		try {
			String queryString = "from RwXuqiufenlei";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RwXuqiufenlei merge(RwXuqiufenlei detachedInstance) {
		log.debug("merging RwXuqiufenlei instance");
		try {
			RwXuqiufenlei result = (RwXuqiufenlei) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RwXuqiufenlei instance) {
		log.debug("attaching dirty RwXuqiufenlei instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RwXuqiufenlei instance) {
		log.debug("attaching clean RwXuqiufenlei instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}