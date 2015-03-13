Ext.define('Dream.app.view.sm.User',{
	extend: 'Dream.app.view.base.tab.TabGrid',
	requires: [
	            'Dream.app.view.base.tab.TabGrid',
	            'Dream.app.view.sm.SelectRole'
	        ],
	
	initContent:function(){
		return this.createGrid();
	},
	
	createGrid:function(gridConfig){
		var $this = this;
		
		var toolbarConfig = Ext.isArray($this.toolbarConfig) ? $this.toolbarConfig : [];
		
		var store = Ext.create('Dream.app.store.sm.User');
		
		this.grid = Ext.create('Ext.grid.Panel',{
			title:'用户列表',
			layout: 'fit',
			store:store,
			selModel: Ext.create('Ext.selection.CheckboxModel'),
			columns:[{
						xtype: 'rownumberer'
					 },
			         {
			        	 text:'账户',
			        	 dataIndex:'account'
			         },{
			        	 text:'用户名',
			        	 dataIndex:'name'
			         },{
			        	 text:'手机',
			        	 dataIndex:'phone'
			         },{
			        	 text:'邮件',
			        	 dataIndex:'email'
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
				fieldLabel:'账户',
				name:'account',
				afterLabelTextTpl: Dream.util.required,
				allowBlank:false
			},{
				fieldLabel:'用户名',
				name:'name'
			},{
				fieldLabel:'密码',
				name:'password',
				afterLabelTextTpl: Dream.util.required,
				allowBlank:false
			},{
				fieldLabel:'手机',
				name:'phone',
				afterLabelTextTpl: Dream.util.required,
				allowBlank:false
			},{
				fieldLabel:'邮箱',
				name:'email',
				afterLabelTextTpl: Dream.util.required,
				allowBlank:false
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
							url:'sm/user/create',
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
					fieldLabel:'账户',
					name:'account',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
				},{
					fieldLabel:'用户名',
					name:'name'
				},{
				       fieldLabel: '密码',
				       name: 'password',
				       hidden:true	
				},{
					fieldLabel:'手机',
					name:'phone',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
				},{
					fieldLabel:'邮箱',
					name:'email',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
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
								url:'sm/user/update',
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
								url:'sm/user/get',
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
                url: 'sm/user/get',
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
	
	relativeRole:function(target){
		var $this = this;
		var records = $this.grid.getSelectionModel().getSelection();
		if(records.length == 1){
			var userId = records[0].get('id');
			var selectRole = Ext.create('Dream.app.view.sm.SelectRole',{
        		userId:userId
    		});
        	var window = Ext.create('Ext.window.Window',{
				title:'关联角色（请勾选）',
				layout:'fit',
				width: 600,
		        height: 400,
				items:[selectRole]
			});
        	
        	window.show();
		}else{
			Dream.util.msg(tip.title, tip.select);
		}
	}
});