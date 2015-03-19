package com.coder.dream.introduce.web.controller.sm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.dream.base.web.controller.BaseDirectTreeController;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.introduce.entity.sm.DictionaryItem;
import com.coder.dream.introduce.entity.sm.Resource;
import com.coder.dream.introduce.repository.sm.ResourceDao;
import com.coder.dream.introduce.service.sm.DictionaryItemService;
import com.coder.dream.introduce.service.sm.ResourceService;
import com.coder.dream.introduce.service.sm.RoleResourceService;

@RestController
@RequestMapping(value="/sm/resource")
public class ResourceController extends BaseDirectTreeController<Resource, ResourceService, ResourceDao>{

	@Autowired
	private RoleResourceService roleResourceService;
	
	@Autowired
	private DictionaryItemService dictionaryItemService;
	
	@Override
	protected void doAfterSelect(Map<String, String> params,Model model,List<Resource> resources) {
		if(CollectionUtils.isEmpty(resources)){
			return ;
		}
		
		String roleId = params.get("roleId");
		if(StringUtils.isNotEmpty(roleId)){
			List<String> resourceIds = roleResourceService.findResourceIdsByRoleId(roleId);
			for (Resource resource : resources) {
				resource.setChecked(resourceIds.contains(resource.getId()));
			}
		}
	}

	@Override
	protected List<Resource> doChildren(Map<String, String> params, String id) {
		List<Resource> resources = super.doChildren(params, id);
		if(CollectionUtils.isNotEmpty(resources)){
			Map<String,String> map = new HashMap<String,String>();
			FilterMap filterMap = new FilterMap();
			for (Resource resource : resources) {
				String type = resource.getType();
				String typeAlias = map.get(type);
				if(StringUtils.isBlank(typeAlias)){
					filterMap.eq("value", type);
					DictionaryItem item = dictionaryItemService.findOne(filterMap);
					if(item == null){
						typeAlias = type;
					}else{
						typeAlias = item.getName();
					}
					map.put(type, typeAlias);
				}
				resource.setTypeAlias(typeAlias);
			}
		}
		return resources;
	}
}
