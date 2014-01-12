package com.niit.dao;

import com.niit.common.dao.BaseHibernateDAO;
import com.niit.model.Accepts;

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
 * Accepts entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.niit.model.Accepts
 * @author MyEclipse Persistence Tools
 */
@Repository
public class AcceptsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(AcceptsDAO.class);
	// property constants
	public static final String OFFER = "offer";
	public static final String EXPLAIN = "explain";

	public void save(Accepts transientInstance) {
		log.debug("saving Accepts instance");
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

	public void delete(Accepts persistentInstance) {
		log.debug("deleting Accepts instance");
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

	public Accepts findById(java.lang.Integer id) {
		log.debug("getting Accepts instance with id: " + id);
		try {
			Transaction tx =  getSession().beginTransaction();
			Accepts instance = (Accepts) getSession().get(
					"com.niit.model.Accepts", id);
			tx.commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Accepts> findByExample(Accepts instance) {
		log.debug("finding Accepts instance by example");
		try {
			Transaction tx =  getSession().beginTransaction();
			List<Accepts> results = (List<Accepts>) getSession()
					.createCriteria("com.niit.model.Accepts")
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
		log.debug("finding Accepts instance with property: " + propertyName
				+ ", value: " + value);
		try {
			Transaction tx =  getSession().beginTransaction();
			String queryString = "from Accepts as model where model."
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

	public List<Accepts> findByOffer(Object offer) {
		return findByProperty(OFFER, offer);
	}

	public List<Accepts> findByExplain(Object explain) {
		return findByProperty(EXPLAIN, explain);
	}

	public List findAll() {
		log.debug("finding all Accepts instances");
		try {
			Transaction tx =  getSession().beginTransaction();
			String queryString = "from Accepts";
			Query queryObject = getSession().createQuery(queryString);
			tx.commit();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Accepts merge(Accepts detachedInstance) {
		log.debug("merging Accepts instance");
		try {
			Transaction tx =  getSession().beginTransaction();
			Accepts result = (Accepts) getSession().merge(detachedInstance);
			tx.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Accepts instance) {
		log.debug("attaching dirty Accepts instance");
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

	public void attachClean(Accepts instance) {
		log.debug("attaching clean Accepts instance");
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