Ext.define('Dream.app.view.sm.Role',{
	extend: 'Dream.app.view.base.tab.TabGrid',
	requires: [
	            'Dream.app.view.base.tab.TabGrid',
	            'Dream.app.store.sm.Role'
	        ],
	
	initContent:function(){
		return this.createGrid();
	},
	
	createGrid:function(gridConfig){
		var $this = this;
		
		var toolbarConfig = Ext.isArray($this.toolbarConfig) ? $this.toolbarConfig : [];
		
		var store = Ext.create('Dream.app.store.sm.Role');
		
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
			        	 dataIndex:'name'
			         },{
			        	 text:'角色编码',
			        	 dataIndex:'code'
			         },{
			        	 text:'备注',
			        	 dataIndex:'comments'
			         }
			],
			dockedItems: [{
				xtype:'toolbar',
				dock:'top',
				items:toolbarConfig
			},{
			    xtype: 'pagingtoolbar',
			    dock: 'bottom',
			    store: store,
		        dock: 'bottom',
		        displayInfo: true
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
	
	addHandler:function(){
		var $this = this;
		var formItemsConfig = Ext.create('Dream.app.view.base.config.form.BaseEntityConfig',{
			items:[{
				fieldLabel:'角色名称',
				name:'name',
				afterLabelTextTpl: Dream.util.required,
				allowBlank:false
			},{
				fieldLabel:'角色编码',
				name:'code',
				afterLabelTextTpl: Dream.util.required,
				allowBlank:false
			},{
				fieldLabel:'备注',
				name:'comments'
			}]
		});
		
		var form = Ext.create('Ext.form.Panel',{
			layout:'anchor',
			bodyPadding: 5,
			defaults:{
				anchor:'100%'
			},
			
			defaultType:'textfield',
			items:formItemsConfig.items,
			buttons:[{
				text:'保存',
				handler:function(){
					var target = {
							url:'sm/role/create',
							form:form,
							window:window,
							grid:$this.grid
					};
					
					$this.saveHandler(target);
				}
			},{
				text:'重置',
				handler:function(){
					var target = {
							form:form
					};
					$this.resetHandler(target);
				}
			}]
		});
		
		var window = Ext.create('Ext.window.Window',{
			title:'新增',
			layout:'fit',
			width: 600,
	        height: 400,
			items:[form]
		});
		
		window.show();
	},
	
	updateHandler:function(){
		var $this = this;
		var records = $this.grid.getSelectionModel().getSelection();
		if(records.length == 1){
			var formItemsConfig = Ext.create('Dream.app.view.base.config.form.BaseEntityConfig',{
				items:[{
					fieldLabel:'角色名称',
					name:'name',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
				},{
					fieldLabel:'角色编码',
					name:'code',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
				},{
					fieldLabel:'备注',
					name:'comments'
				}]
			});
			
			var form = Ext.create('Ext.form.Panel',{
				layout:'anchor',
				bodyPadding: 5,
				defaults:{
					anchor:'100%'
				},
				
				defaultType:'textfield',
				items:formItemsConfig.items,
				buttons:[{
					text:'保存',
					handler:function(){
						var target = {
								url:'sm/role/update',
								form:form,
								window:window,
								grid:$this.grid
						};
						
						$this.saveHandler(target);
					}
				},{
					text:'重置',
					handler:function(){
						var target = {
								url:'sm/role/get',
								form:form
						};
						$this.resetUpdateHandler(target);
					}
				}]
			});
			
			var window = Ext.create('Ext.window.Window',{
				title:'修改',
				layout:'fit',
				width: 600,
		        height: 400,
				items:[form]
			});
			
			var id = records[0].get("id");
            form.getForm().load({
                url: 'sm/role/get',
                params: {
                    id: id
                },
                success: function(form, action) {
                    window.show();
                }
            });
		}else{
			Dream.util.msg(tip.title, tip.select);
		}
	},
	
	/**
	 * 关联资源
	 * 
	 */
	relativeResource:function(target){
		var $this = this;
		var records = $this.grid.getSelectionModel().getSelection();
		if(records.length == 1){
			Ext.Ajax.request({
			    url: 'sm/resource/root',
			    success: function(response, opts) {
			        var obj = Ext.decode(response.responseText);
			        if(obj.success){
			        	var roleId = records[0].get('id');
			        	var selectResource = Ext.create('Dream.app.view.sm.SelectResource',{
			        		roleId:roleId,
			    			root:obj.data
			    		});
			        	var window = Ext.create('Ext.window.Window',{
							title:'关联资源（请勾选）',
							layout:'fit',
							width: 600,
					        height: 400,
							items:[selectResource]
						});
			        	window.show();
			        }
			    }
			});
		}else{
			Dream.util.msg(tip.title, tip.select);
		}
	}
});