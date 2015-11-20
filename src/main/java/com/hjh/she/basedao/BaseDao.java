package com.hjh.she.basedao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hjh.she.model.base.AuditEntityBean;
import com.hjh.she.model.base.PageInfo;
import com.hjh.she.model.base.QueryParam;
import com.hjh.she.model.base.QueryParamList;
import com.hjh.she.model.base.SortParamList;
import com.hjh.she.util.CommonUtil;

@Service("baseDao")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class BaseDao implements IDAO {
	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 获取当前可用session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List find(String jpql, QueryParamList params, SortParamList sortParams, PageInfo pageInfo) {
		List<Object> returnList = new ArrayList<Object>();
		try {
			// 增加OrderBy条件
			if (sortParams != null && sortParams.getParams().size() > 0) {
				jpql = JpqlUtil.addSortParam(jpql, sortParams);
			}
			Query query = getSession().createQuery(jpql);

			QueryParamList allParams = new QueryParamList();
			allParams.addParamList(params);

			setQueryParamList(query, allParams);
			// 处理分页
			if (pageInfo != null) {
				if (pageInfo.getStartIndex() != null) {// new Grid不查询总数
					query.setFirstResult(pageInfo.getStartIndex());
					query.setMaxResults(pageInfo.getEndIndex() - pageInfo.getStartIndex());
				} else {

					// 设置总行数
					// 使用游标形式计算总页数,效率会稍慢,大数据必须单独写查询总数sql
					int totalCount = 0;
					if (pageInfo.getPageHql() == null) {// pageInfo增加了自定义分页(使用hibernate的游标进行查询总数，效率较慢)
						String pageJpql = JpqlUtil.deleteOuterOrderBy(jpql);
						Query pageQuery = getSession().createQuery(pageJpql);
						setQueryParamList(pageQuery, allParams);
						ScrollableResults scrollCursor = pageQuery.scroll(ScrollMode.SCROLL_SENSITIVE);
						scrollCursor.last();
						totalCount = Integer.valueOf(scrollCursor.getRowNumber() + 1);
					} else {// PageInfo有hql,使用自定义hql(手动分页,查询总数的效率比hibernate快)
						Query innPageQuery = getSession().createQuery(pageInfo.getPageHql());
						innPageQuery.setCacheable(true);// 使用缓存
						setQueryParamList(innPageQuery, pageInfo.getPageParams());
						totalCount = Integer.valueOf(innPageQuery.uniqueResult() + "");
					}
					pageInfo.setAllRowNum(totalCount);

					// 只有大于0才分页
					if (pageInfo.getRowOfPage() > 0) {
						int startRowNo = 0;
						if (totalCount == 0) {
							pageInfo.setAllPageNum(0);
							pageInfo.setCurPageNum(0);
						} else {
							// 设置总页数
							int tpPageNum = totalCount % pageInfo.getRowOfPage();
							int allPageNum = (totalCount - tpPageNum) / pageInfo.getRowOfPage();
							if (tpPageNum > 0) {
								allPageNum = allPageNum + 1;
							}
							pageInfo.setAllPageNum(allPageNum);

							if (pageInfo.getCurPageNum() == 0) {
								pageInfo.setCurPageNum(1);
							}
							startRowNo = (pageInfo.getCurPageNum() - 1) * pageInfo.getRowOfPage();
							/* 如果指定的页面没有数据，则检索第一页的数据 */
							if (totalCount <= startRowNo) {
								pageInfo.setCurPageNum(1);
								startRowNo = 0;
							}
						}

						query.setFirstResult(startRowNo);
						query.setMaxResults(pageInfo.getRowOfPage());

					}

				}
			}
			returnList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnList;
	}

	@Override
	public <T> List<T> find(String jpql) {
		List<T> returnList = new ArrayList<T>();
		try {
			Query query = getSession().createQuery(jpql);
			returnList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return returnList;
	}

	public List executeNativeQuery(String nativeSql) throws Exception {
		return executeNativeQuery(nativeSql, null);
	}

	@SuppressWarnings("unchecked")
	public List executeNativeQuery(String nativeSql, QueryParamList params) throws Exception {
		List<Object> returnList = new ArrayList<Object>();
		try {
			Query query = getSession().createSQLQuery(nativeSql);
			setQueryParamList(query, params);
			returnList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnList;
	}

	private void setQueryParamList(Query query, QueryParamList paramLs) {
		if (query != null && paramLs != null && paramLs.getParams().size() > 0) {
			List<QueryParam> list = paramLs.getParams();
			for (int i = 0; i < list.size(); i++) {
				QueryParam param = list.get(i);
				if (param == null) {
					continue;
				}
				setQueryParam(query, param);
			}
		}
	}

	private void setQueryParam(Query query, QueryParam param) {
		if (param.getValue() instanceof Collection) {
			query.setParameterList(param.getName(), (List) param.getValue());
		} else {
			query.setParameter(param.getName(), param.getValue());
		}

	}

	@Override
	public <T> void create(T t) {
		try {
			if (t instanceof AuditEntityBean) {
				((AuditEntityBean) t).setCreated(CommonUtil.getDateTime());
				if (CommonUtil.getCurrendUser() != null) {
					((AuditEntityBean) t).setCreater(CommonUtil.getCurrendUser().getAccount());
				}
			}
			getSession().save(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> T loadById(Class<T> clazz, Serializable id) {
		T t = null;
		try {
			t = (T) getSession().load(clazz, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public <T> void update(T t) {
		if (t instanceof AuditEntityBean) {
			((AuditEntityBean) t).setLastmod(CommonUtil.getDateTime());
			if (CommonUtil.getCurrendUser() != null) {
				((AuditEntityBean) t).setModifyer(CommonUtil.getCurrendUser().getAccount());
			}
		}
		try {
			getSession().saveOrUpdate(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	@Override
	public <T> void refresh(T t) {
		try {
			getSession().refresh(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	@Override
	public <T> void remove(Class<T> clazz, Serializable id) {
		this.remove(this.loadById(clazz, id));
	}

	public <T> void remove(T t) {
		try {
			getSession().delete(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> void remove(Collection<T> entities) {
		try {
			if (entities != null && entities.size() > 0) {
				Iterator<T> iterator = entities.iterator();
				while (iterator.hasNext()) {
					T t = iterator.next();
					getSession().delete(t);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void flush() {
		try {
			getSession().flush();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Long getTotalCount(String jpql, QueryParamList params) {
		Long returnCount = 0L;
		try {
			// 附加查询条件
			String countJpql = JpqlUtil.deleteOuterOrderBy(jpql);
			Query pageQuery = getSession().createQuery(countJpql);
			setQueryParamList(pageQuery, params);
			ScrollableResults scrollCursor = pageQuery.scroll(ScrollMode.SCROLL_INSENSITIVE);
			scrollCursor.last();// 使游标移动到最后一行
			return Long.valueOf(scrollCursor.getRowNumber() + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnCount;
	}
}
