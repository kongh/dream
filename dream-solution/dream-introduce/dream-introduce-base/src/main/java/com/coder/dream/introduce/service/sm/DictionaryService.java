package com.coder.dream.introduce.service.sm;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.coder.dream.base.repository.support.SpecificationBuildUtil;
import com.coder.dream.base.service.BaseService;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.introduce.entity.sm.Dictionary;
import com.coder.dream.introduce.entity.sm.DictionaryItem;
import com.coder.dream.introduce.repository.sm.DictionaryDao;
import com.coder.dream.introduce.repository.sm.DictionaryItemDao;
import com.mysql.jdbc.StringUtils;

@Service
@Transactional
public class DictionaryService extends BaseService<Dictionary, DictionaryDao>{

	@Autowired
	private DictionaryItemDao itemDao;
	
	@Override
	public void delete(String id) {
		if(StringUtils.isNullOrEmpty(id)){
			return ;
		}
		Dictionary dictionary = dao.findOne(id);
		delete(dictionary);
	}
	
	public void delete(Dictionary dictionary){
		FilterMap filterMap = new FilterMap();
		filterMap.eq("dictionaryCode", dictionary.getCode());
		Specification<DictionaryItem> specification = SpecificationBuildUtil.buildSpecification(filterMap);
		List<DictionaryItem> items = itemDao.findAll(specification);
		dao.delete(dictionary);
		itemDao.deleteInBatch(items);
	}
	
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0){
			return ;
		}
		List<Dictionary> deleting = dao.findAll(Arrays.asList(ids));
		for (Dictionary dictionary : deleting) {
			delete(dictionary);
		}
	}
}
