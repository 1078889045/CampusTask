package com.niit.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.niit.common.dao.BaseHibernateDAO;
import com.niit.model.Category;

/**
 * A data access object (DAO) providing persistence and search support for
 * Category entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.niit.model.Category
 * @author MyEclipse Persistence Tools
 */
@Repository
public class CategoryDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CategoryDAO.class);
	// property constants
	public static final String CAT_NAME = "catName";

	public void save(Category transientInstance) {
		log.debug("saving Category instance");
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

	public void delete(Category persistentInstance) {
		log.debug("deleting Category instance");
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

	public Category findById(java.lang.Integer id) {
		log.debug("getting Category instance with id: " + id);
		try {
			Transaction tx =  getSession().beginTransaction(); 
			Category instance = (Category) getSession().get(
					"com.niit.model.Category", id);
			tx.commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Category> findByExample(Category instance) {
		log.debug("finding Category instance by example");
		try {
			Transaction tx =  getSession().beginTransaction(); 
			List<Category> results = (List<Category>) getSession()
					.createCriteria("com.niit.model.Category")
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
		log.debug("finding Category instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Category as model where model."
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

	public List<Category> findByCatName(Object catName) {
		return findByProperty(CAT_NAME, catName);
	}

	public List findAll() {
		log.debug("finding all Category instances");
		try {
			String queryString = "from Category";
			Transaction tx =  getSession().beginTransaction(); 
			Query queryObject = getSession().createQuery(queryString);
			tx.commit();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Category merge(Category detachedInstance) {
		log.debug("merging Category instance");
		try {
			Transaction tx =  getSession().beginTransaction(); 
			Category result = (Category) getSession().merge(detachedInstance);
			tx.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Category instance) {
		log.debug("attaching dirty Category instance");
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

	public void attachClean(Category instance) {
		log.debug("attaching clean Category instance");
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