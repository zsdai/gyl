package com.myself.gyl.basedata.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.myself.gyl.base.action.BaseAction;
import com.myself.gyl.basedata.service.ProductService;
import com.myself.gyl.domain.basedata.Product;
import com.opensymphony.xwork2.ActionContext;
@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction<Product>{
	
	private static final long serialVersionUID = 1L;
	@Resource(name="productService")
	private ProductService productService;
	
	public String showProduct(){
		Collection<Product> products = this.productService.findEntry();
		ActionContext.getContext().getValueStack().push(products);
		return SUCCESS;
	}
}
