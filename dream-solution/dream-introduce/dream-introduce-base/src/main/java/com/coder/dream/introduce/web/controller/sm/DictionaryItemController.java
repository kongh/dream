package com.coder.dream.introduce.web.controller.sm;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.coder.dream.base.utils.NullUtil;
import com.coder.dream.base.web.controller.BaseDirectTreeController;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.introduce.entity.sm.DictionaryItem;
import com.coder.dream.introduce.repository.sm.DictionaryItemDao;
import com.coder.dream.introduce.service.sm.DictionaryItemService;
import com.coder.dream.introduce.service.sm.DictionaryService;

@RestController
@RequestMapping(value="/sm/dictionary/item")
public class DictionaryItemController extends BaseDirectTreeController<DictionaryItem, DictionaryItemService, DictionaryItemDao>{
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Override
	public ModelAndView index(@RequestParam Map<String, String> params, Model model) {
		DictionaryItem root = service.getRoot();
		FilterMap filterMap = new FilterMap();
		filterMap.eq("parentId", root.getId());
		filterMap.eq("dictionaryCode", params.get("dictionaryCode"));
		DictionaryItem node = service.findNode(filterMap);
		if(NullUtil.isNull(node)){
			filterMap.clear();
			filterMap.eq("code", params.get("dictionaryCode"));
			node = service.create(root, dictionaryService.findOne(filterMap));
		}
		//强制设置为非叶子
		node.setLeaf(false);
		node.setExpanded(false);
		model.addAttribute("root", node);
		return new ModelAndView();
	}
	
}
