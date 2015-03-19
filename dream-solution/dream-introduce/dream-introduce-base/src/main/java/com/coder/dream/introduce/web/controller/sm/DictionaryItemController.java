package com.coder.dream.introduce.web.controller.sm;

import java.util.ArrayList;
import java.util.List;
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
import com.coder.dream.base.web.vo.OrderMap;
import com.coder.dream.base.web.vo.ResultMap;
import com.coder.dream.introduce.entity.sm.DictionaryItem;
import com.coder.dream.introduce.repository.sm.DictionaryItemDao;
import com.coder.dream.introduce.service.sm.DictionaryItemService;
import com.coder.dream.introduce.service.sm.DictionaryService;
import com.coder.dream.introduce.web.vo.sm.DictionaryItemComboVo;

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
	
	@Override
	public ResultMap create(DictionaryItem entity) {
		DictionaryItem dictionaryItem = service.get(entity.getParentId());
		String code = dictionaryItem.getDictionaryCode();
		entity.setDictionaryCode(code);
		return super.create(entity);
	}
	
	@RequestMapping(value="/combo")
	public ResultMap combo(@RequestParam Map<String, String> params){
		ResultMap resultMap = new ResultMap();
		try{
			DictionaryItem root = service.getRoot();
			FilterMap filterMap = new FilterMap();
			OrderMap orderMap = new OrderMap();
			String dictionaryCode = params.get("dictionaryCode");
			filterMap.eq("parentId", root.getId());
			filterMap.eq("dictionaryCode", dictionaryCode);
			DictionaryItem item = service.findOne(filterMap);
			
			filterMap.clear();
			filterMap.eq("parentId", item.getId());
			filterMap.eq("dictionaryCode", item.getDictionaryCode());
			List<DictionaryItem> items = service.list(filterMap, orderMap);
			List<DictionaryItemComboVo> vos = new ArrayList<DictionaryItemComboVo>(items.size());
			for (DictionaryItem di : items) {
				vos.add(new DictionaryItemComboVo(di));
			}
			resultMap.success(vos);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		
		return resultMap;
	}
}
