package com.myself.gyl.privilege.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.base.service.impl.BaseServiceImpl;
import com.myself.gyl.domain.privilege.Menuitem;
import com.myself.gyl.privilege.dao.MenuitemDao;
import com.myself.gyl.privilege.service.MenuitemService;
@Service("menuitemService")
public class MenuitemServiceImpl extends BaseServiceImpl<Menuitem> implements MenuitemService{

	@Resource(name="menuitemDao")
	private MenuitemDao menuitemDao;
	@Override
	public BaseDao<Menuitem> getBaseDao() {
		
		return this.menuitemDao;
	}

}
