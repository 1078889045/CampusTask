package com.niit.dao;

import com.niit.common.dao.BaseHibernateDAO;
import com.niit.model.RwQu;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for RwQu
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.niit.model.RwQu
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RwQuDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(RwQuDAO.class);
	// property constants
	public static final String QU_NAME = "quName";
	public static final String SHI_ID = "shiId";

	public void save(RwQu transientInstance) {
		log.debug("saving RwQu instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RwQu persistentInstance) {
		log.debug("deleting RwQu instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RwQu findById(java.lang.Integer id) {
		log.debug("getting RwQu instance with id: " + id);
		try {
			RwQu instance = (RwQu) getSession().get("com.niit.model.RwQu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RwQu> findByExample(RwQu instance) {
		log.debug("finding RwQu instance by example");
		try {
			List<RwQu> results = (List<RwQu>) getSession()
					.createCriteria("com.niit.model.RwQu").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding RwQu instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RwQu as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<RwQu> findByQuName(Object quName) {
		return findByProperty(QU_NAME, quName);
	}

	public List<RwQu> findByShiId(Object shiId) {
		return findByProperty(SHI_ID, shiId);
	}

	public List findAll() {
		log.debug("finding all RwQu instances");
		try {
			String queryString = "from RwQu";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RwQu merge(RwQu detachedInstance) {
		log.debug("merging RwQu instance");
		try {
			RwQu result = (RwQu) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RwQu instance) {
		log.debug("attaching dirty RwQu instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RwQu instance) {
		log.debug("attaching clean RwQu instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}