package com.niit.dao;

import com.niit.common.dao.BaseHibernateDAO;
import com.niit.model.RwXuexiao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * RwXuexiao entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.niit.model.RwXuexiao
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RwXuexiaoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RwXuexiaoDAO.class);
	// property constants
	public static final String XUEXIAO_NAME = "xuexiaoName";

	public void save(RwXuexiao transientInstance) {
		log.debug("saving RwXuexiao instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RwXuexiao persistentInstance) {
		log.debug("deleting RwXuexiao instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RwXuexiao findById(java.lang.Integer id) {
		log.debug("getting RwXuexiao instance with id: " + id);
		try {
			RwXuexiao instance = (RwXuexiao) getSession().get(
					"com.niit.model.RwXuexiao", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RwXuexiao> findByExample(RwXuexiao instance) {
		log.debug("finding RwXuexiao instance by example");
		try {
			List<RwXuexiao> results = (List<RwXuexiao>) getSession()
					.createCriteria("com.niit.model.RwXuexiao")
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
		log.debug("finding RwXuexiao instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RwXuexiao as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<RwXuexiao> findByXuexiaoName(Object xuexiaoName) {
		return findByProperty(XUEXIAO_NAME, xuexiaoName);
	}

	public List findAll() {
		log.debug("finding all RwXuexiao instances");
		try {
			String queryString = "from RwXuexiao";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RwXuexiao merge(RwXuexiao detachedInstance) {
		log.debug("merging RwXuexiao instance");
		try {
			RwXuexiao result = (RwXuexiao) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RwXuexiao instance) {
		log.debug("attaching dirty RwXuexiao instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RwXuexiao instance) {
		log.debug("attaching clean RwXuexiao instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}