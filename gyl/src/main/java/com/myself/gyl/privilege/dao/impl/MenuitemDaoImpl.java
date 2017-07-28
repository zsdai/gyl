package com.myself.gyl.privilege.dao.impl;

import org.springframework.stereotype.Repository;

import com.myself.gyl.base.dao.impl.BaseDaoImpl;
import com.myself.gyl.domain.privilege.Menuitem;
import com.myself.gyl.privilege.dao.MenuitemDao;
@Repository("menuitemDao")
public class MenuitemDaoImpl extends BaseDaoImpl<Menuitem> implements MenuitemDao{

}
