Ext.define('Dream.app.view.sm.SelectResource',{
	extend: 'Dream.app.view.base.tab.TabTree',
	requires: [
	            //model
	            'Dream.app.model.sm.Resource',
	            //view
	            'Dream.app.view.base.tab.TabTree'
	        ],
	

	constructor : function(config) {
		this.roleId = config.roleId;
		this.rootNode = config.root;
		this.callParent(arguments);
	},
	
	initContent:function(){
		return this.createTree();
	},
	
	createTree:function(){
		var $this = this;
		var rootNode = $this.rootNode;
		
		var data = {
			roleId:$this.roleId
		};
		var store = Ext.create('Ext.data.TreeStore',{
			root:rootNode,
			model : 'Dream.app.model.sm.Resource',
			proxy : {
				type : 'ajax',
				url : 'sm/resource/checkedChildren',
				reader : {
					type : 'json',
					rootProperty:'data'
			},
			extraParams:data,
			nodeParam:'id',
			autoLoad : true
			}
		});
		
		var tree = Ext.create('Ext.tree.Panel',{
			title:'资源列表',
			store: store,
			layout: 'fit',
		    disableSelection: false,
			columns:[
			         {
			        	 xtype: 'treecolumn',
			             text: '资源名称',
			             width: 150,
			             dataIndex: 'name',
			             sortable: true,
			             flex:1
			         }
			],
			dockedItems:[{
				xtype:'toolbar',
				dock:'bottom',
				items:[{
					text:'确定',
					iconCls: 'Add',
					handler:function(){
						var target = {
							url:'sm/roleResource/relative',
							roleId:$this.roleId,
							store:$this.tree.getStore()
						};
						$this.saveRoleResource(target);
					}
				},{
					text:'重置',
					iconCls: 'Add',
					handler:function(){
						alert(2);
					}
				}]
			}]
		});
		
		tree.expandAll();
		
		this.tree = tree;
		
		return tree;
	},
	
	/**
	 * 保存角色资源
	 */
	saveRoleResource:function(target){
		var resourceIds = [];
		target.store.each(function(record){
			if(record.get('checked')){
				resourceIds.push(record.get('id'));
			}
		});
		
		Ext.Ajax.request({
            url:target.url,
            params: {
            	roleId:target.roleId,
            	resourceIds: resourceIds
            },
            success: function(response) {
                if (Ext.decode(response.responseText).success) {
                    Dream.util.msg(tip.title, tip.success);
                } else {
                    Dream.util.msg(tip.title, tip.failure);
                }
            },
            failure: function(response) {
                Dream.util.msg(tip.title, tip.failure);
            }
        });
	}
});