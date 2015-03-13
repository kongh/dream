package com.coder.dream.introduce.service.sm;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.coder.dream.base.service.BaseTreeService;
import com.coder.dream.introduce.entity.sm.Dictionary;
import com.coder.dream.introduce.entity.sm.DictionaryItem;
import com.coder.dream.introduce.repository.sm.DictionaryItemDao;

@Service
@Transactional
public class DictionaryItemService extends BaseTreeService<DictionaryItem, DictionaryItemDao>{

	@Override
	protected DictionaryItem createRoot() {
		DictionaryItem item = new DictionaryItem();
		item.setName("字典根节点");
		item.setDictionaryCode("root");
		item.setValue("root");
		return item;
	}

	public DictionaryItem create(DictionaryItem root , Dictionary dictionary){
		DictionaryItem item = new DictionaryItem();
		item.setParentId(root.getId());
		item.setDictionaryCode(dictionary.getCode());
		item.setName(dictionary.getName());
		return dao.save(item);
	}
}
