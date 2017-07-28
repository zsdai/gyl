package com.myself.gyl.privilege.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.myself.gyl.base.dao.impl.BaseDaoImpl;
import com.myself.gyl.domain.privilege.Role;
import com.myself.gyl.privilege.dao.RoleDao;
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

	@Override
	public Role findRoleByName(final String name) {
		
		return this.hibernateTemplate.execute(new HibernateCallback<Role>() {
			@Override
			public Role doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from Role where name=:roleName");
				query.setParameter("roleName", name);
				return (Role)query.uniqueResult();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Role> getRolesByUserId(Serializable uid) {
		Collection<Role> allRoles=this.hibernateTemplate.find("from Role");
		Collection<Role> userRoles=this.hibernateTemplate.find("from Role r inner join fetch r.users u where u.uid=?",uid);
		for (Role ar : allRoles) {
			for (Role ur : userRoles) {
				if(ar.getRid()==ur.getRid()){
					ar.setChecked(true);
				}
			}
		}
		return allRoles;
	}

}
