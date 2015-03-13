Ext.define('Dream.app.view.sm.SelectRole',{
	extend: 'Dream.app.view.base.tab.TabGrid',
	requires: [
	            'Dream.app.view.base.tab.TabGrid',
	            'Dream.app.store.sm.Role'
	        ],
	 
	constructor : function(config) {
		this.userId = config.userId;
		this.callParent(arguments);
	},
	    	
	initContent:function(){
		return this.createGrid();
	},
	
	createGrid:function(gridConfig){
		var $this = this;
		
		var data = {
			userId:$this.userId
		};
		
		var store = Ext.create('Dream.app.store.sm.Role',{
			proxy:{
				url:'sm/role/checkedList',
				extraParams:data
			}
		});
		
		this.grid = Ext.create('Ext.grid.Panel',{
			title:'角色列表',
			layout: 'fit',
			store:store,
			selModel: Ext.create('Ext.selection.CheckboxModel'),
			columns:[{
						xtype: 'rownumberer'
					 },
			         {
			        	 text:'角色名称',
			        	 dataIndex:'name',
			        	 flex:1
			         },{
			        	 text:'角色编码',
			        	 dataIndex:'code',
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
							url:'sm/userRole/relative',
							userId:$this.userId,
							grid:$this.grid
						};
						$this.saveUserRole(target);
					}
				},{
					text:'重置',
					iconCls: 'Add',
					handler:function(){
						alert(2);
					}
				}]
			}],
			listeners: {
                render: function() {
                	var target = {
							grid:$this.grid
					};
					$this.refreshHandler(target);
                }
            }
		});
		
		return this.grid;
	},
	
	/**
	 * 保存角色资源
	 */
	saveUserRole:function(target){
		var resourceIds = [];
		var records = target.grid.getSelectionModel().getSelection();
		
		var roleIds = [];
		Ext.each(records,function(record){
			roleIds.push(record.get('id'));
		});
		
		Ext.Ajax.request({
            url:target.url,
            params: {
            	userId:target.userId,
            	roleIds: roleIds
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