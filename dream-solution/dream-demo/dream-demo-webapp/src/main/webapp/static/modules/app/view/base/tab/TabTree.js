Ext.define('Dream.app.view.base.tab.TabTree',{
	extend: 'Ext.tab.Panel',
	requires: [
	            'Dream.app.view.base.config.form.BaseTreeConfig'
	        ],
	
	initComponent : function() {

		Ext.apply(this, {
			layout : 'fit',
			items : [ this.initContent() ]
		});
		this.callParent(arguments);
	},

	/**
	 * 初始化内容
	 */
	initContent : function() {
		return {title:'请重写initContent方法'};
	},
	
	/**
	 * 表格删除模板方法
	 * 
	 * @param target
	 * {
	 * 	tree:tree,
	 *  url:''
	 * }
	 */
	deleteHandler:function(target){
		var $this = this;
		var records = target.tree.getSelectionModel().getSelection(),ids=[];
		if(records.length > 0){
			var selId = records[0].get('id');
			var selNode = target.tree.getStore().getNodeById(selId);
			if(selNode.isRoot()){
				Ext.Msg.alert('提示','不能删除根节点，请选择其他节点');
				return ;
			}
			
			Ext.Msg.confirm('提示', '确认删除吗?', function(btn) {
                if (btn == 'yes') {
                    Ext.Array.each(records, function(r) {
                        ids.push(r.get('id'));
                    });
                    Ext.Ajax.request({
                        url:target.url,
                        params: {
                            ids: ids
                        },
                        success: function(response) {
                            if (Ext.decode(response.responseText).success) {
                                Dream.util.msg(tip.title, tip.success);
                            } else {
                                Dream.util.msg(tip.title, tip.failure);
                            }
                            var target = {
    								tree:$this.tree
    						};
                            if(selNode.parentNode){
                            	target.node = selNode.parentNode;
                            }
    						$this.refreshHandler(target);
                        },
                        failure: function(response) {
                            Dream.util.msg(tip.title, tip.failure);
                        }
                    });
                }
            });
		}else {
            Dream.util.msg(tip.title, tip.select);
        }
	},
	
	/**
	 * 表格刷新模板方法
	 * 
	 * @param target
	 */
	refreshHandler:function(target){
		var $this = this;
		if(target.node){
			var data = {
					node:target.node
			};
			target.tree.getStore().load(data);
		}else{
			target.tree.getStore().load();
		}
	},
	
	/**
	 * 保存(新增/修改)模板方法
	 * 
	 * @param target
	 * 
	 * {
	 *   url:'sm/user/save',
     *   form:form,
	 *   window:window,
	 *   tree:tree
	 * }
	 */
	saveHandler:function(target){
		var $this = this;
		if (target.form.getForm().isValid()) {
			target.form.getForm().submit({
				url:target.url,
				success: function(form, action) {
					Dream.util.msg(tip.title, tip.success);
					target.window.hide();
					
					var records = target.tree.getSelectionModel().getSelection();
					if(records.length > 0){
						var selId = records[0].get('id');
						var selNode = target.tree.getStore().getNodeById(selId);
						target.node = selNode;
						
						if(action.result.success){
							selNode.appendChild(action.result.data);
						}
					}
					
					
					//$this.refreshHandler(target);
				}
            });
        } else {
            Dream.util.msg(tip.title, tip.check);
        }
	},
	
	/**
	 * 重置(新增)模板方法
	 */
	resetHandler:function(target){
		target.form.getForm().reset();
	},
	
	/**
	 * 重置(修改)模板方法
	 */
	resetUpdateHandler:function(target){
		var data = target.form.getValues();
	    target.form.getForm().load({
             url: target.url,
             params: {
                 id: data.id
             }
        });
	}
});