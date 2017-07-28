package com.myself.gyl.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.query.PageResult;
import com.myself.gyl.query.QueryCondition;
import com.myself.gyl.utils.GylUtils;

//@Repository("baseDao")
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	@Resource(name = "hibernateTemplate")
	public HibernateTemplate hibernateTemplate;
	private Class<T> classt;
	private ClassMetadata classMetadata;//元数据 描述持久化类的对象
	/**
	 * 把泛型的参数提取出来的过程放入到构造函数中写， 因为当子类创建对象的时候直接调用父类的构造方法
	 */
	public BaseDaoImpl() {
		super();
		/**
		 * BaseDaoImpl不能给它实例化，this就代表子类
		 * this.getClass().getGenericSuperclass()就代表父类；即BaseDaoImpl<T> 泛型
		 * 
		 * Spring容器不支持带泛型的类；eg:BaseDaoImpl<T>，如果将他放到spring容器中，则
		 * this.getClass().getGenericSuperclass()的类型是一个Class，而不是一个Type
		 * 所以将一个Class强转为ParameterizedType会出错
		 * 【因为Class和ParameterizedType都继承于Type,不能将同级子类相互强转】
		 */
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 得到T的实际类型
		this.classt = (Class<T>) type.getActualTypeArguments()[0];
	}
	/**
	 * spring的init方法在注入之后执行的
	 */
	@PostConstruct
	public void init(){
		this.classMetadata=this.hibernateTemplate.getSessionFactory().getClassMetadata(classt);
	}

	@Override
	public PageResult<T> findPageResult(final QueryCondition condition) {
		return this.hibernateTemplate.execute(new HibernateCallback<PageResult<T>>() {
			@Override
			public PageResult<T> doInHibernate(Session session) throws HibernateException,SQLException {
				PageResult<T> pageResult = new PageResult<T>(condition.getCurrentPage(),
						condition.getPageSize(), getTotalCount(condition));
				/**
				 * 拼接hql语句的where条件 ;相当与 String sql="select * from XX where name=:name";
				 */
				StringBuffer buffer = new StringBuffer("from "+classt.getSimpleName()+" where 1=1");
				Map<String, Object> conditions = condition.getNameValues();
				for (Entry<String,Object> c : conditions.entrySet()) {
					if(c.getKey().contains(".")){
						String[] strs = c.getKey().split("\\.");
						buffer.append(" and " + c.getKey() + "=:"+strs[strs.length-1]);
					}else{
						buffer.append(" and " + c.getKey() + "=:"+ c.getKey());
					}
				}
				/**
				 * 执行hql语句；相当于pstm.executeQuery(sql);
				 */
				Query query = session.createQuery(buffer.toString());
				/**
				 * 给hql语句的问号赋值；相当与 pstm.setString("name":name);
				 */
				for (Entry<String,Object> c : conditions.entrySet()) {
					if(c.getKey().contains(".")){
						//包含.的情况  给where xsyddzhub.xsyddzhubid=:xsyddzhubid的=:后面的赋值
						String[] strs = c.getKey().split("\\.");
						query.setParameter(strs[strs.length-1], c.getValue());
					}else{
						query.setParameter(c.getKey(), c.getValue());
					}
				}
				//设置第一条记录的索引
				int firstResult=(condition.getCurrentPage()-1)*condition.getPageSize();
				//设置每页显示的最多记录条数
				int maxResults=condition.getPageSize();
				/**
				 * 用hibernate的方式设置分页
				 */
				query.setFirstResult(firstResult).setMaxResults(maxResults);
				List<T> rows = query.list();
				pageResult.setRows(rows);
				return pageResult;
			}
		});
	}

	@Override
	public Collection<T> findEntry() {

		return this.hibernateTemplate.find("from "+this.classt.getSimpleName());
	}

	@Override
	public void saveEntry(T t) {
		this.hibernateTemplate.save(t);
	}

	@Override
	public void updateEntry(T t) {
		this.hibernateTemplate.update(t);
	}


	@Override
	public void deleteEntriesByIds(Serializable[] ids) {
		/**
		 * 完成有[1,2,3]--->(1,2,3)
		 */
		StringBuffer sb = new StringBuffer("(");
		for (int i = 0; i < ids.length; i++) {
			if(i==ids.length-1){
				sb.append(ids[i]+")");
			}else{
				sb.append(ids[i]+",");
			}
		}
		/**
		 * select * from XX where id in(x,x,x);
		 */
		List<T> find = this.hibernateTemplate.find("from "+this.classt.getSimpleName()
				+" where "+this.classMetadata.getIdentifierPropertyName()
				+" in "+sb.toString());
		this.hibernateTemplate.deleteAll(find);
	}

	@Override
	public void deleteEntryById(Serializable id) {
		T entity = this.getEntryById(id);
		this.hibernateTemplate.delete(entity);
	}

	@Override
	public T getEntryById(Serializable id) {
		return (T) this.hibernateTemplate.get(this.classt, id);
	}

	@Override
	public Set<T> getEntriesByIds(Serializable[] ids) {
		/**
		 * 完成有[1,2,3]--->(1,2,3)
		 */
		StringBuffer sb = new StringBuffer("(");
		for (int i = 0; i < ids.length; i++) {
			if(i==ids.length-1){
				sb.append(ids[i]+")");
			}else{
				sb.append(ids[i]+",");
			}
		}
		/**
		 * select * from XX where id in(x,x,x);
		 */
		List<T> list = this.hibernateTemplate.find("from "+this.classt.getSimpleName()
				+" where "+this.classMetadata.getIdentifierPropertyName()
				+" in "+sb.toString());
		return new HashSet<T>(list);
	}

	@Override
	public int getTotalCount(final QueryCondition condition) {
		return this.hibernateTemplate.execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer buffer = new StringBuffer("select count("+classMetadata.getIdentifierPropertyName()
						+") from "+ classt.getSimpleName() + " where 1=1");
				// 获取所有的查询条件
				Map<String, Object> conditions = condition.buildQueryConditions();
				/** 遍历条件，拼接hql语句的where条件*/
				for (Entry<String, Object> condition : conditions.entrySet()) {
					/**
					 * 在一对多的情况下 例如：xsyddzhub.xsyddzhubid
					 * condition.getKey()得到的结果就是"xsyddzhub.xsyddzhubid",
					 * 而hibernate不支持这样的写法(eg:xx.ss=:xx.ss)冒号后面的不支持
					 * 我们需要这样xsyddzhub.xsyddzhubid=:xsyddzhubid
					 * 这样就需要一个判断
					 */
					if(condition.getKey().contains(".")){
						String[] strs = condition.getKey().split("\\.");
						buffer.append(" and " + condition.getKey() + "=:"+strs[strs.length-1]);
					}else{
						// 形如name=:name 第一个name表示数据库中的字段  第二个name为起的别名
						buffer.append(" and " + condition.getKey() + "=:"+ condition.getKey());
					}
				}
				Query query = session.createQuery(buffer.toString());
				/** 给where条件中的参数设置值*/
				for (Entry<String, Object> condition : conditions.entrySet()) {
					if(condition.getKey().contains(".")){
						//包含.的情况  给where xsyddzhub.xsyddzhubid=:xsyddzhubid的=:后面的赋值
						String[] strs = condition.getKey().split("\\.");
						query.setParameter(strs[strs.length-1], condition.getValue());
					}else{
						query.setParameter(condition.getKey(), condition.getValue());
					}
				}
				Long count = (Long) query.uniqueResult();
				return count.intValue();
			}
		});
	}
	@Override
	public String getMax() {
		/**
		 * 获得订单号的前8位，如果和今天的时间一样，说明今天已经产生订单号，
		 * 如果不一样，则说明今天还没有产生订单号
		 */
		List<T> list = this.hibernateTemplate.find("select max(ddh) from "+this.classt.getSimpleName()+" where ddh like '%"+GylUtils.getDateToString()+"%'");
		if(list.get(0)==null){
			return GylUtils.getDateToString()+"0001";
		}else{
			String temp=(String)list.get(0);//获得当前list中最大的那个订单号
			return ""+(Long.parseLong(temp)+1);
		}
	}

}
