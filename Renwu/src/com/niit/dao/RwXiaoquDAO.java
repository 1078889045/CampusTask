package com.niit.dao;

import com.niit.common.dao.BaseHibernateDAO;
import com.niit.model.RwXiaoqu;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * RwXiaoqu entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.niit.model.RwXiaoqu
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RwXiaoquDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RwXiaoquDAO.class);
	// property constants
	public static final String XIAOQU_NAME = "xiaoquName";
	public static final String QU_ID = "quId";
	public static final String XUEXIAO_ID = "xuexiaoId";

	public void save(RwXiaoqu transientInstance) {
		log.debug("saving RwXiaoqu instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RwXiaoqu persistentInstance) {
		log.debug("deleting RwXiaoqu instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RwXiaoqu findById(java.lang.Integer id) {
		log.debug("getting RwXiaoqu instance with id: " + id);
		try {
			RwXiaoqu instance = (RwXiaoqu) getSession().get(
					"com.niit.model.RwXiaoqu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RwXiaoqu> findByExample(RwXiaoqu instance) {
		log.debug("finding RwXiaoqu instance by example");
		try {
			List<RwXiaoqu> results = (List<RwXiaoqu>) getSession()
					.createCriteria("com.niit.model.RwXiaoqu")
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
		log.debug("finding RwXiaoqu instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RwXiaoqu as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<RwXiaoqu> findByXiaoquName(Object xiaoquName) {
		return findByProperty(XIAOQU_NAME, xiaoquName);
	}

	public List<RwXiaoqu> findByQuId(Object quId) {
		return findByProperty(QU_ID, quId);
	}

	public List<RwXiaoqu> findByXuexiaoId(Object xuexiaoId) {
		return findByProperty(XUEXIAO_ID, xuexiaoId);
	}

	public List findAll() {
		log.debug("finding all RwXiaoqu instances");
		try {
			String queryString = "from RwXiaoqu";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RwXiaoqu merge(RwXiaoqu detachedInstance) {
		log.debug("merging RwXiaoqu instance");
		try {
			RwXiaoqu result = (RwXiaoqu) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RwXiaoqu instance) {
		log.debug("attaching dirty RwXiaoqu instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RwXiaoqu instance) {
		log.debug("attaching clean RwXiaoqu instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}