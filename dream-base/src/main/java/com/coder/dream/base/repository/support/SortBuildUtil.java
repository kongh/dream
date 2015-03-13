package com.coder.dream.base.repository.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.coder.dream.base.constant.BaseConstant;
import com.coder.dream.base.web.vo.OrderMap;

public class SortBuildUtil {

	public static Sort buildSort(OrderMap orderMap){
		if(orderMap.isEmpty()) orderMap.desc("sort");
		List<Order> orders = new ArrayList<Order>();
		for (String key : orderMap.keySet()) {
			String[] pt = key.split(BaseConstant.SPLIT);
			String propertyName = pt[0];
			String type = pt[1];
			if (type.equals(BaseConstant.ASC)) {
				orders.add(new Order(Direction.ASC, propertyName));
			} else if (type.equals(BaseConstant.DESC)) {
				orders.add(new Order(Direction.DESC, propertyName));
			}
		}
		return new Sort(orders);
	}
}
