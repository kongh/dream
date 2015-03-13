Ext.define('Dream.app.view.base.tab.TabGrid',{
	extend: 'Ext.tab.Panel',
	requires: [
	            'Dream.app.view.base.config.form.BaseEntityConfig'
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
	 * 	grid:grid,
	 *  url:''
	 * }
	 */
	deleteHandler:function(target){
		var $this = this;
		var records = target.grid.getSelectionModel().getSelection(),ids=[];
		if(records.length > 0){
			Ext.Msg.confirm('提示', '确认删除吗?', function(btn) {
                if (btn == 'yes') {
                	//target.grid.getView().loadMask.show();
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
    						$this.refreshHandler(target);
                        },
                        failure: function(response) {
                        	if (response.status == 401) {
                                Ext.Msg.alert(tip.title, tip.timeout, function() {
                                    location.replace('login');
                                });
                            }else if(response.status == 403){
                            	Ext.Msg.alert(tip.title, tip.forbidden + ':['+target.url+']');
                            }else {
                            	Dream.util.msg(tip.title, tip.failure);
                            }
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
		target.grid.getSelectionModel().deselectAll(true);
		target.grid.getStore().load();
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
	 *   grid:grid
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
					$this.refreshHandler(target);
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