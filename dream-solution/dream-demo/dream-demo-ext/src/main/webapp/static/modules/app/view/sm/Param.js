Ext.define('Dream.app.view.sm.Param',{
	extend: 'Dream.app.view.base.tab.TabGrid',
	requires: [
	            'Dream.app.view.base.tab.TabGrid'
	        ],
	
	initContent:function(){
		return this.createGrid();
	},
	
	createGrid:function(gridConfig){
		var $this = this;
		
		var toolbarConfig = Ext.isArray($this.toolbarConfig) ? $this.toolbarConfig : [];
		
		var store = Ext.create('Dream.app.store.sm.Param');
		
		this.grid = Ext.create('Ext.grid.Panel',{
			title:'参数列表',
			layout: 'fit',
			store:store,
			selModel: Ext.create('Ext.selection.CheckboxModel'),
			columns:[{
						xtype: 'rownumberer'
					 },
			         {
			        	 text:'参数名称',
			        	 dataIndex:'name'
			         },{
			        	 text:'参数编码',
			        	 dataIndex:'code'
			         },{
			        	 text:'参数值',
			        	 dataIndex:'value'
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
				fieldLabel:'参数名称',
				name:'name',
				afterLabelTextTpl: Dream.util.required,
				allowBlank:false
			},{
				fieldLabel:'参数编码',
				name:'code',
				afterLabelTextTpl: Dream.util.required,
				allowBlank:false
			},{
				fieldLabel:'参数值',
				name:'value',
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
							url:'sm/param/create',
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
					fieldLabel:'参数名称',
					name:'name',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
				},{
					fieldLabel:'参数编码',
					name:'code',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
				},{
					fieldLabel:'参数值',
					name:'value',
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
								url:'sm/param/update',
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
								url:'sm/param/get',
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
                url: 'sm/param/get',
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
	}
});