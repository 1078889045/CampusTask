package com.niit.dao;

import java.util.Date;
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
import com.niit.model.Tasks;

/**
 * A data access object (DAO) providing persistence and search support for Tasks
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.niit.model.Tasks
 * @author MyEclipse Persistence Tools
 */
@Repository
public class TasksDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TasksDAO.class);
	// property constants
	public static final String TASK_CONTENT = "taskContent";
	public static final String REWARD = "reward";
	public static final String ACCEPT_ID = "acceptId";
	public static final String IF_COMPLETE = "ifComplete";

	public void save(Tasks transientInstance) {
		log.debug("saving Tasks instance");
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

	public void delete(Tasks persistentInstance) {
		log.debug("deleting Tasks instance");
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

	public Tasks findById(java.lang.Integer id) {
		log.debug("getting Tasks instance with id: " + id);
		try {
			Transaction tx =  getSession().beginTransaction();
			Tasks instance = (Tasks) getSession().get("com.niit.model.Tasks", id);
			tx.commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Tasks> findByExample(Tasks instance) {
		log.debug("finding Tasks instance by example");
		try {
			Transaction tx =  getSession().beginTransaction();
			List<Tasks> results = (List<Tasks>) getSession()
					.createCriteria("com.niit.model.Tasks").add(create(instance))
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
		log.debug("finding Tasks instance with property: " + propertyName
				+ ", value: " + value);
		try {
			Transaction tx =  getSession().beginTransaction();
			String queryString = "from Tasks as model where model."
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

	public List<Tasks> findByTaskContent(Object taskContent) {
		return findByProperty(TASK_CONTENT, taskContent);
	}

	public List<Tasks> findByReward(Object reward) {
		return findByProperty(REWARD, reward);
	}

	public List<Tasks> findByAcceptId(Object acceptId) {
		return findByProperty(ACCEPT_ID, acceptId);
	}

	public List<Tasks> findByIfComplete(Object ifComplete) {
		return findByProperty(IF_COMPLETE, ifComplete);
	}

	public List findAll() {
		log.debug("finding all Tasks instances");
		try {
			Transaction tx =  getSession().beginTransaction();
			String queryString = "from Tasks";
			Query queryObject = getSession().createQuery(queryString);
			tx.commit();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tasks merge(Tasks detachedInstance) {
		log.debug("merging Tasks instance");
		try {
			Transaction tx =  getSession().beginTransaction();
			Tasks result = (Tasks) getSession().merge(detachedInstance);
			tx.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tasks instance) {
		log.debug("attaching dirty Tasks instance");
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

	public void attachClean(Tasks instance) {
		log.debug("attaching clean Tasks instance");
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