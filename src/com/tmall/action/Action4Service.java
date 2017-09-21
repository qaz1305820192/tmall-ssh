package com.tmall.action;


import java.lang.reflect.Method;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tmall.service.CategoryService;
import com.tmall.service.ProductImageService;
import com.tmall.service.ProductService;
import com.tmall.service.PropertyService;

public class Action4Service extends Action4Pojo {
	@Autowired
	CategoryService categoryService;
	@Autowired
	PropertyService propertyService;
	@Autowired
	ProductService  productService;
	@Autowired
	ProductImageService productImageService;
	
	/**
	 * transient to presistent
	 * 瞬时对象转换为持久对象
	 * @param o
	 */
	public void t2p(Object o){
		try{
			Class clazz =o.getClass();
			int id= (int) clazz.getMethod("getId").invoke(o);
			Object persistentBean =categoryService.get(clazz,id);
			String beanName =clazz.getSimpleName();
			Method setMethod = getClass().getMethod("set" + WordUtils.capitalize(beanName), clazz);
            setMethod.invoke(this, persistentBean);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
