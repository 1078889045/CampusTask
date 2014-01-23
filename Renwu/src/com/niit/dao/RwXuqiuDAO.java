package com.niit.dao;

import com.niit.common.dao.BaseHibernateDAO;
import com.niit.model.RwXuqiu;

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
 * RwXuqiu entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.niit.model.RwXuqiu
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RwXuqiuDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(RwXuqiuDAO.class);
	// property constants
	public static final String XUQIU_TITLE = "xuqiuTitle";
	public static final String XUQIU_CONTENT = "xuqiuContent";
	public static final String XUQIU_MONEY = "xuqiuMoney";
	public static final String YONGHU_ID = "yonghuId";
	public static final String XUQIU_FENLEI_ID = "xuqiuFenleiId";

	public void save(RwXuqiu transientInstance) {
		log.debug("saving RwXuqiu instance");
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

	public void delete(RwXuqiu persistentInstance) {
		log.debug("deleting RwXuqiu instance");
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

	public RwXuqiu findById(java.lang.Integer id) {
		log.debug("getting RwXuqiu instance with id: " + id);
		try {
			RwXuqiu instance = (RwXuqiu) getSession().get(
					"com.niit.model.RwXuqiu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RwXuqiu> findByExample(RwXuqiu instance) {
		log.debug("finding RwXuqiu instance by example");
		try {
			Transaction tx =  getSession().beginTransaction();
			List<RwXuqiu> results = (List<RwXuqiu>) getSession()
					.createCriteria("com.niit.model.RwXuqiu")
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
		log.debug("finding RwXuqiu instance with property: " + propertyName
				+ ", value: " + value);
		try {
			Transaction tx =  getSession().beginTransaction();
			String queryString = "from RwXuqiu as model where model."
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

	public List<RwXuqiu> findByXuqiuTitle(Object xuqiuTitle) {
		return findByProperty(XUQIU_TITLE, xuqiuTitle);
	}

	public List<RwXuqiu> findByXuqiuContent(Object xuqiuContent) {
		return findByProperty(XUQIU_CONTENT, xuqiuContent);
	}

	public List<RwXuqiu> findByXuqiuMoney(Object xuqiuMoney) {
		return findByProperty(XUQIU_MONEY, xuqiuMoney);
	}

	public List<RwXuqiu> findByYonghuId(Object yonghuId) {
		return findByProperty(YONGHU_ID, yonghuId);
	}

	public List<RwXuqiu> findByXuqiuFenleiId(Object xuqiuFenleiId) {
		return findByProperty(XUQIU_FENLEI_ID, xuqiuFenleiId);
	}

	public List findAll() {
		log.debug("finding all RwXuqiu instances");
		try {
			Transaction tx =  getSession().beginTransaction();
			String queryString = "from RwXuqiu r order by r.xuqiuAddTime";
			Query queryObject = getSession().createQuery(queryString);
			tx.commit();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RwXuqiu merge(RwXuqiu detachedInstance) {
		log.debug("merging RwXuqiu instance");
		try {
			Transaction tx =  getSession().beginTransaction();
			RwXuqiu result = (RwXuqiu) getSession().merge(detachedInstance);
			tx.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RwXuqiu instance) {
		log.debug("attaching dirty RwXuqiu instance");
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

	public void attachClean(RwXuqiu instance) {
		log.debug("attaching clean RwXuqiu instance");
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