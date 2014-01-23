package com.niit.dao;

import com.niit.common.dao.BaseHibernateDAO;
import com.niit.model.RwRenling;

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
 * RwRenling entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.niit.model.RwRenling
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RwRenlingDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RwRenlingDAO.class);
	// property constants
	public static final String RENLING_CONTENT = "renlingContent";
	public static final String RENLING_IS_DEAL = "renlingIsDeal";
	public static final String YONGHU_ID = "yonghuId";
	public static final String XUQIU_ID = "xuqiuId";

	public void save(RwRenling transientInstance) {
		log.debug("saving RwRenling instance");
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

	public void delete(RwRenling persistentInstance) {
		log.debug("deleting RwRenling instance");
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

	public RwRenling findById(java.lang.Integer id) {
		log.debug("getting RwRenling instance with id: " + id);
		try {
			Transaction tx =  getSession().beginTransaction();
			RwRenling instance = (RwRenling) getSession().get(
					"com.niit.model.RwRenling", id);
			tx.commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RwRenling> findByExample(RwRenling instance) {
		log.debug("finding RwRenling instance by example");
		try {
			Transaction tx =  getSession().beginTransaction();
			List<RwRenling> results = (List<RwRenling>) getSession()
					.createCriteria("com.niit.model.RwRenling")
					.add(create(instance)).list();
			tx.commit();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding RwRenling instance with property: " + propertyName
				+ ", value: " + value);
		try {
			Transaction tx =  getSession().beginTransaction();
			String queryString = "from RwRenling as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			tx.commit();
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<RwRenling> findByRenlingContent(Object renlingContent) {
		return findByProperty(RENLING_CONTENT, renlingContent);
	}

	public List<RwRenling> findByRenlingIsDeal(Object renlingIsDeal) {
		return findByProperty(RENLING_IS_DEAL, renlingIsDeal);
	}

	public List<RwRenling> findByYonghuId(Object yonghuId) {
		return findByProperty(YONGHU_ID, yonghuId);
	}

	public List<RwRenling> findByXuqiuId(Object xuqiuId) {
		return findByProperty(XUQIU_ID, xuqiuId);
	}

	public List findAll() {
		log.debug("finding all RwRenling instances");
		try {
			Transaction tx =  getSession().beginTransaction();
			String queryString = "from RwRenling";
			Query queryObject = getSession().createQuery(queryString);
			tx.commit();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RwRenling merge(RwRenling detachedInstance) {
		log.debug("merging RwRenling instance");
		try {
			Transaction tx =  getSession().beginTransaction();
			RwRenling result = (RwRenling) getSession().merge(detachedInstance);
			tx.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RwRenling instance) {
		log.debug("attaching dirty RwRenling instance");
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

	public void attachClean(RwRenling instance) {
		log.debug("attaching clean RwRenling instance");
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