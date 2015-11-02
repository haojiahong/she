package com.hjh.she.basedao;

import java.io.Serializable;
import java.util.List;

import com.hjh.she.model.commonmodel.PageInfo;
import com.hjh.she.model.commonmodel.QueryParamList;
import com.hjh.she.model.commonmodel.SortParamList;



public abstract interface IDAO {
	/**
	 * find查询
	 * 
	 * @param jpql
	 * @param params
	 * @param sortParams
	 * @param pageInfo
	 * @return
	 */
	public abstract List find(String jpql, QueryParamList params,
			SortParamList sortParams, PageInfo pageInfo);

	/**
	 * 创建一条数据
	 * 
	 * @param paramT
	 */
	public abstract <T> void create(T paramT);

	/**
	 * 按照id查询数据
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	public abstract <T> T loadById(Class<T> clazz, Serializable id);

	/**
	 * 更新数据
	 * 
	 * @param t
	 */
	public abstract <T> void update(T t);

	/**
	 * 
	 * @param t
	 */
	public abstract <T> void refresh(T t);

	/**
	 * 删除
	 * 
	 * @param clazz
	 * @param id
	 */
	public abstract <T> void remove(Class<T> clazz, Serializable id);

	public abstract void flush();

	/**
	 * 
	 * @param jpql
	 * @param params
	 * @return
	 */
	public abstract Long getTotalCount(String jpql, QueryParamList params);
}