
package com.coder.dream.base.repository.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import com.coder.dream.base.constant.BaseConstant;
import com.coder.dream.base.utils.NullUtil;
import com.coder.dream.base.web.vo.FilterMap;

public class SpecificationBuildUtil {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Predicate> buildPredicates(CriteriaBuilder cb, Root root, FilterMap filterMap){
		List<Predicate> predicates = new ArrayList<Predicate>();
		for (String key : filterMap.keySet()) {
			String[] pt = key.split(BaseConstant.SPLIT);
			String propertyName = pt[0];
			String type = pt[1];
			Object value = filterMap.get(key);
			if (NullUtil.isNotNull(value)) {
				if (type.equals(BaseConstant.EQ)) {
					predicates.add(cb.equal(root.get(propertyName), value));
				} else if (type.equals(BaseConstant.NE)) {
					predicates.add(cb.notEqual(root.get(propertyName), value));
				} else if (type.equals(BaseConstant.GT)) {
					predicates.add(cb.greaterThan(root.get(propertyName).as(Integer.class), (Integer) value));
				} else if (type.equals(BaseConstant.GTDATE)) {
					predicates.add(cb.greaterThan(root.get(propertyName).as(Date.class), (Date) value));
				} else if (type.equals(BaseConstant.GE)) {
					predicates.add(cb.greaterThanOrEqualTo(root.get(propertyName).as(Integer.class), (Integer) value));
				} else if (type.equals(BaseConstant.GEDATE)) {
					predicates.add(cb.greaterThanOrEqualTo(root.get(propertyName).as(Date.class), (Date) value));
				} else if (type.equals(BaseConstant.LT)) {
					predicates.add(cb.lessThan(root.get(propertyName).as(Integer.class), (Integer) value));
				} else if (type.equals(BaseConstant.LTDATE)) {
					predicates.add(cb.lessThan(root.get(propertyName).as(Date.class), (Date) value));
				} else if (type.equals(BaseConstant.LE)) {
					predicates.add(cb.lessThanOrEqualTo(root.get(propertyName).as(Integer.class), (Integer) value));
				} else if (type.equals(BaseConstant.LEDATE)) {
					predicates.add(cb.lessThanOrEqualTo(root.get(propertyName).as(Date.class), (Date) value));
				} else if (type.equals(BaseConstant.LIKE)) {
					predicates.add(cb.like(root.get(propertyName).as(String.class), String.valueOf(value)));
				} else if (type.equals(BaseConstant.NOTLIKE)) {
					predicates.add(cb.notLike(root.get(propertyName).as(String.class), String.valueOf(value)));
				} else if (type.equals(BaseConstant.IN)) {
					Collection collection = (Collection) value;
					if (collection.size() > 0) {
						In in = cb.in(root.get(propertyName));
						Iterator iterator = collection.iterator();
						while (iterator.hasNext()) {
							in.value(iterator.next());
						}
						predicates.add(in);
					} else {
						predicates.add(cb.isNull(root.get(propertyName)));
					}
				} else if (type.equals(BaseConstant.NOTIN)) {
					Collection collection = (Collection) value;
					if (collection.size() > 0) {
						In in = cb.in(root.get(propertyName));
						Iterator iterator = collection.iterator();
						while (iterator.hasNext()) {
							in.value(iterator.next());
						}
						predicates.add(cb.not(in));
					} else {
						predicates.add(cb.isNotNull(root.get(propertyName)));
					}
				} else if (type.equals(BaseConstant.BETWEEN)) {
					Map<String, Integer> map = (Map<String, Integer>) value;
					Integer lo = map.get(BaseConstant.LO);
					Integer go = map.get(BaseConstant.GO);
					predicates.add(cb.between(root.get(propertyName).as(Integer.class), lo, go));
				} else if (type.equals(BaseConstant.BETWEENDATE)) {
					Map<String, Date> map = (Map<String, Date>) value;
					Date lo = map.get(BaseConstant.LO);
					Date go = map.get(BaseConstant.GO);
					predicates.add(cb.between(root.get(propertyName).as(Date.class), lo, go));
				}else if (type.equals(BaseConstant.OR)) {
					FilterMap orMap = (FilterMap) value;
					predicates.add(cb.or(buildPredicates(cb, root, orMap).toArray(new Predicate[0])));
				}
			} else {
				if (type.equals(BaseConstant.ISNULL)) {
					predicates.add(cb.isNull(root.get(propertyName)));
				} else if (type.equals(BaseConstant.ISNOTNULL)) {
					predicates.add(cb.isNotNull(root.get(propertyName)));
				} else if (type.equals(BaseConstant.ISEMPTY)) {
					predicates.add(cb.equal(root.get(propertyName), ""));
				} else if (type.equals(BaseConstant.ISNOTEMPTY)) {
					predicates.add(cb.notEqual(root.get(propertyName), ""));
				}
			}
		}
		return predicates;
	}
	
	public static <T> Specification<T> buildSpecification(final FilterMap filterMap){
		return new Specification<T>() {
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = buildPredicates(cb,root,filterMap);
				if(CollectionUtils.isEmpty(predicates)){
					return cb.conjunction();
				}
				Predicate[] array = predicates.toArray(new Predicate[predicates.size()]);
				return cb.and(array);
			}
		};
	}
}

