package com.myself.gyl.basedata.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.base.service.impl.BaseServiceImpl;
import com.myself.gyl.basedata.dao.ProductDao;
import com.myself.gyl.basedata.service.ProductService;
import com.myself.gyl.domain.basedata.Product;

@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
	@Resource(name = "productDao")
	private ProductDao productDao;

	@Override
	public BaseDao<Product> getBaseDao() {

		return this.productDao;
	}

}
