package com.niit.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.niit.common.dao.BaseHibernateDAO;
import com.niit.model.News;

/**
 * A data access object (DAO) providing persistence and search support for News
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.niit.model.News
 * @author MyEclipse Persistence Tools
 */
@Repository
public class NewsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(NewsDAO.class);
	// property constants
	public static final String NEWS_TITLE = "newsTitle";
	public static final String NEWS_CONTENT = "newsContent";
	public static final String NEWS_ADD_USER = "newsAddUser";
	public static final String NEWS_ADD_IP = "newsAddIp";

	public void save(News transientInstance) {
		log.debug("saving News instance");
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

	public void delete(News persistentInstance) {
		log.debug("deleting News instance");
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

	public News findById(java.lang.Integer id) {
		log.debug("getting News instance with id: " + id);
		try {
			Transaction tx =  getSession().beginTransaction(); 
			News instance = (News) getSession().get("com.niit.model.News", id);
			tx.commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<News> findByExample(News instance) {
		log.debug("finding News instance by example");
		try {
			Transaction tx =  getSession().beginTransaction(); 
			List<News> results = (List<News>) getSession()
					.createCriteria("com.niit.model.News").add(create(instance))
					.list();
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
		log.debug("finding News instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from News as model where model."
					+ propertyName + "= ?";
			Transaction tx =  getSession().beginTransaction(); 
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			tx.commit();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<News> findByNewsTitle(Object newsTitle) {
		return findByProperty(NEWS_TITLE, newsTitle);
	}

	public List<News> findByNewsContent(Object newsContent) {
		return findByProperty(NEWS_CONTENT, newsContent);
	}

	public List<News> findByNewsAddUser(Object newsAddUser) {
		return findByProperty(NEWS_ADD_USER, newsAddUser);
	}

	public List<News> findByNewsAddIp(Object newsAddIp) {
		return findByProperty(NEWS_ADD_IP, newsAddIp);
	}

	public List findAll() {
		log.debug("finding all News instances");
		try {
			String queryString = "from News";
			Transaction tx =  getSession().beginTransaction(); 
			Query queryObject = getSession().createQuery(queryString);
			tx.commit();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public News merge(News detachedInstance) {
		log.debug("merging News instance");
		try {
			Transaction tx =  getSession().beginTransaction(); 
			News result = (News) getSession().merge(detachedInstance);
			tx.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(News instance) {
		log.debug("attaching dirty News instance");
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

	public void attachClean(News instance) {
		log.debug("attaching clean News instance");
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