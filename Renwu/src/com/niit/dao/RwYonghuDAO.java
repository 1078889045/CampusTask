package com.niit.dao;

import com.niit.common.dao.BaseHibernateDAO;
import com.niit.model.RwYonghu;

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
 * RwYonghu entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.niit.model.RwYonghu
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RwYonghuDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RwYonghuDAO.class);
	// property constants
	public static final String YONGHU_NAME = "yonghuName";
	public static final String YONGHU_TRUE_NAME = "yonghuTrueName";
	public static final String YONGHU_PWD = "yonghuPwd";
	public static final String YONGHU_PHONE = "yonghuPhone";
	public static final String XIAOQU_ID = "xiaoquId";

	public void save(RwYonghu transientInstance) {
		log.debug("saving RwYonghu instance");
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

	public void delete(RwYonghu persistentInstance) {
		log.debug("deleting RwYonghu instance");
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

	public RwYonghu findById(java.lang.Integer id) {
		log.debug("getting RwYonghu instance with id: " + id);
		try {
			
			RwYonghu instance = (RwYonghu) getSession().get(
					"com.niit.model.RwYonghu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RwYonghu> findByExample(RwYonghu instance) {
		log.debug("finding RwYonghu instance by example");
		try {
			Transaction tx =  getSession().beginTransaction();
			List<RwYonghu> results = (List<RwYonghu>) getSession()
					.createCriteria("com.niit.model.RwYonghu")
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
		log.debug("finding RwYonghu instance with property: " + propertyName
				+ ", value: " + value);
		try {
			Transaction tx =  getSession().beginTransaction();
			String queryString = "from RwYonghu as model where model."
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

	public List<RwYonghu> findByYonghuName(Object yonghuName) {
		return findByProperty(YONGHU_NAME, yonghuName);
	}

	public List<RwYonghu> findByYonghuTrueName(Object yonghuTrueName) {
		return findByProperty(YONGHU_TRUE_NAME, yonghuTrueName);
	}

	public List<RwYonghu> findByYonghuPwd(Object yonghuPwd) {
		return findByProperty(YONGHU_PWD, yonghuPwd);
	}

	public List<RwYonghu> findByYonghuPhone(Object yonghuPhone) {
		return findByProperty(YONGHU_PHONE, yonghuPhone);
	}

	public List<RwYonghu> findByXiaoquId(Object xiaoquId) {
		return findByProperty(XIAOQU_ID, xiaoquId);
	}

	public List findAll() {
		log.debug("finding all RwYonghu instances");
		try {
			String queryString = "from RwYonghu";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RwYonghu merge(RwYonghu detachedInstance) {
		log.debug("merging RwYonghu instance");
		try {
			Transaction tx =  getSession().beginTransaction();
			RwYonghu result = (RwYonghu) getSession().merge(detachedInstance);
			log.debug("merge successful");
			tx.commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RwYonghu instance) {
		log.debug("attaching dirty RwYonghu instance");
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

	public void attachClean(RwYonghu instance) {
		log.debug("attaching clean RwYonghu instance");
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