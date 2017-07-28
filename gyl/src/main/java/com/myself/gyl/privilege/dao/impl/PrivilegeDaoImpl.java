package com.myself.gyl.privilege.dao.impl;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.myself.gyl.base.dao.impl.BaseDaoImpl;
import com.myself.gyl.domain.privilege.Privilege;
import com.myself.gyl.privilege.dao.PrivilegeDao;

@SuppressWarnings("unchecked")
@Repository("privilegeDao")
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements
		PrivilegeDao {

	@Override
	public Collection<Privilege> getPrivilegesByRoleId(Serializable rid) {
		/**
		 * 需求： 要显示权限树的所有权限，要显示选择角色已有的权限 而某角色已有权限表示为Privilege中的checked为true
		 */
		Collection<Privilege> allPrivileges = (Collection<Privilege>) this.hibernateTemplate
				.find("from Privilege");
		Collection<Privilege> rolePrivileges = (Collection<Privilege>) this.hibernateTemplate
				.find("from Privilege p inner join fetch p.roles r where r.rid="
						+ rid);
		// 遍历所有权限
		for (Privilege ap : allPrivileges) {
			// 遍历角色已拥有权限
			for (Privilege rp : rolePrivileges) {
				if (ap.getId() == rp.getId()) {
					ap.setChecked(true);
				}
			}
		}
		return allPrivileges;
	}

	@Override
	public Collection<Privilege> getMenuPrivilegesByUserId(Serializable uid) {
		if ((Long) uid == 1) {// 说明是管理员
			return this.hibernateTemplate
					.find("from Privilege p where p.type='1'");
		} else {// 普通员工
			return this.hibernateTemplate
					.find("from Privilege p inner join fetch p.roles r inner join fetch r.users u where p.type='1' and u.uid=?",
							uid);
		}
	}

	@Override
	public Collection<Privilege> getFuntionPrivilegesByUserId(Long uid) {
		if (uid == 1) {// 说明是管理员
			return this.hibernateTemplate
					.find("from Privilege p where p.type='2'");
		} else {// 普通员工
			return this.hibernateTemplate
					.find("from Privilege p inner join fetch p.roles r inner join fetch r.users u where p.type='2' and u.uid=?",
							uid);
		}
	}

}
