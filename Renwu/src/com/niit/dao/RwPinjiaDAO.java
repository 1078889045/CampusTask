package com.niit.dao;

import com.niit.common.dao.BaseHibernateDAO;
import com.niit.model.RwPinjia;

import java.sql.Timestamp;
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
 * RwPinjia entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.niit.model.RwPinjia
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RwPinjiaDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RwPinjiaDAO.class);
	// property constants
	public static final String PINJIA_ZHUREN_VALUE = "pinjiaZhurenValue";
	public static final String PINJIA_ZHUREN_CONTENT = "pinjiaZhurenContent";
	public static final String PINJIA_RENLING_VALUE = "pinjiaRenlingValue";
	public static final String PINJIA_RENLING_CONTENT = "pinjiaRenlingContent";
	public static final String XUQIU_ID = "xuqiuId";

	public void save(RwPinjia transientInstance) {
		log.debug("saving RwPinjia instance");
		try {
			Transaction tx =  getSession().beginTransaction();
			getSession().save(transientInstance);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RwPinjia persistentInstance) {
		log.debug("deleting RwPinjia instance");
		try {
			Transaction tx =  getSession().beginTransaction();
			getSession().delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RwPinjia findById(java.lang.Integer id) {
		log.debug("getting RwPinjia instance with id: " + id);
		try {
			Transaction tx =  getSession().beginTransaction();
			RwPinjia instance = (RwPinjia) getSession().get(
					"com.niit.model.RwPinjia", id);
			tx.commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RwPinjia> findByExample(RwPinjia instance) {
		log.debug("finding RwPinjia instance by example");
		try {
			Transaction tx =  getSession().beginTransaction();
			List<RwPinjia> results = (List<RwPinjia>) getSession()
					.createCriteria("com.niit.model.RwPinjia")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			tx.commit();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding RwPinjia instance with property: " + propertyName
				+ ", value: " + value);
		try {
			Transaction tx =  getSession().beginTransaction();
			String queryString = "from RwPinjia as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			tx.commit();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<RwPinjia> findByPinjiaZhurenValue(Object pinjiaZhurenValue) {
		return findByProperty(PINJIA_ZHUREN_VALUE, pinjiaZhurenValue);
	}

	public List<RwPinjia> findByPinjiaZhurenContent(Object pinjiaZhurenContent) {
		return findByProperty(PINJIA_ZHUREN_CONTENT, pinjiaZhurenContent);
	}

	public List<RwPinjia> findByPinjiaRenlingValue(Object pinjiaRenlingValue) {
		return findByProperty(PINJIA_RENLING_VALUE, pinjiaRenlingValue);
	}

	public List<RwPinjia> findByPinjiaRenlingContent(Object pinjiaRenlingContent) {
		return findByProperty(PINJIA_RENLING_CONTENT, pinjiaRenlingContent);
	}

	public List<RwPinjia> findByXuqiuId(Object xuqiuId) {
		return findByProperty(XUQIU_ID, xuqiuId);
	}

	public List findAll() {
		log.debug("finding all RwPinjia instances");
		try {
			Transaction tx =  getSession().beginTransaction();
			String queryString = "from RwPinjia";
			Query queryObject = getSession().createQuery(queryString);
			tx.commit();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RwPinjia merge(RwPinjia detachedInstance) {
		log.debug("merging RwPinjia instance");
		try {
			Transaction tx =  getSession().beginTransaction();
			RwPinjia result = (RwPinjia) getSession().merge(detachedInstance);
			log.debug("merge successful");
			tx.commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RwPinjia instance) {
		log.debug("attaching dirty RwPinjia instance");
		try {
			Transaction tx =  getSession().beginTransaction();
			getSession().saveOrUpdate(instance);
			tx.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RwPinjia instance) {
		log.debug("attaching clean RwPinjia instance");
		try {
			Transaction tx =  getSession().beginTransaction();
			getSession().lock(instance, LockMode.NONE);
			tx.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}