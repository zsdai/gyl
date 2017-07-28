package com.myself.gyl.basedata.dao.impl;

import org.springframework.stereotype.Repository;

import com.myself.gyl.base.dao.impl.BaseDaoImpl;
import com.myself.gyl.basedata.dao.ProductDao;
import com.myself.gyl.domain.basedata.Product;
@Repository("productDao")
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao{

}
