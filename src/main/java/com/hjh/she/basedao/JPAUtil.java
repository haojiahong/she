package com.hjh.she.basedao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.hjh.she.model.base.PageInfo;
import com.hjh.she.model.base.QueryParamList;
import com.hjh.she.model.base.SortParamList;

@SuppressWarnings("unchecked")
public class JPAUtil {

	// 这是第一种拿到applicationContext的方法。(通过applicationContextAware)
	// 静态属性，spring初始化MyApplicationContextUtil的时候，将applicationContext属性set到这里面了。
	/*
	 * private static ApplicationContext applicationContext;
	 * 
	 * public static Object getBean(String beanName) { return
	 * getCtx().getBean(beanName); }
	 * 
	 * public static void setApplicationContext(ApplicationContext ctx) {
	 * applicationContext = ctx; }
	 * 
	 * private static ApplicationContext getCtx() { return applicationContext; }
	 */

	private static IDAO dao = (IDAO) ApplicationUtil.getBean("baseDao");

	// 这是第二种拿到applicationContext的方法
	// private static ApplicationContext ac = new
	// ClassPathXmlApplicationContext(
	// "applicationContext.xml");

	/**
	 * find条件查询数据带分页排序
	 * 
	 * @param jpql
	 * @param params
	 * @param sortParams
	 * @param pageInfo
	 * @return
	 */

	public static <T> List<T> find(String jpql, QueryParamList params, SortParamList sortParams, PageInfo pageInfo) {
		return dao.find(jpql, params, sortParams, pageInfo);
	}

	public static <T> List<T> find(String jpql) {
		return dao.find(jpql);
	}

	public static <T> List<T> find(String jpql, QueryParamList params) {
		return find(jpql, params, null, null);
	}

	public static <T> List<T> executeNativeQuery(String nativeSql) throws Exception {
		return executeNativeQuery(nativeSql, null);
	}

	public static <T> List<T> executeNativeQuery(String nativeSql, QueryParamList params) throws Exception {
		return dao.executeNativeQuery(nativeSql, params);
	}

	/**
	 * 创建一条数据
	 * 
	 * @param t
	 */
	public static <T> void create(T t) {
		dao.create(t);
	}

	/**
	 * 根据id查询数据
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	public static <T> T loadById(Class<T> clazz, Serializable id) {
		return dao.loadById(clazz, id);
	}

	/**
	 * 更新数据
	 * 
	 * @param t
	 */
	public static <T> void update(T t) {
		dao.update(t);
	}

	/**
	 * 
	 * @param t
	 */
	public static <T> void refresh(T t) {
		dao.refresh(t);
	}

	/**
	 * 删除数据
	 * 
	 * @param clazz
	 * @param id
	 */
	public static <T> void remove(Class<T> clazz, Serializable id) {
		dao.remove(clazz, id);
	}

	public static <T> void remove(Collection<T> entities) {
		dao.remove(entities);
	}

	/**
	 * 缓存和数据库同步
	 */
	public static void flush() {
		dao.flush();
	}

	/**
	 * 
	 * @param jpql
	 * @param params
	 * @return
	 */
	public static Long getTotalCount(String jpql, QueryParamList params) {
		return dao.getTotalCount(jpql, params);
	}

}
