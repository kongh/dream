Ext.define('Dream.app.view.sm.Resource',{
	extend: 'Dream.app.view.base.tab.TabTree',
	requires: [
	            //model
	            'Dream.app.model.sm.Resource',
	            //view
	            'Dream.app.view.base.tab.TabTree'
	        ],
	

	constructor : function(config) {
		this.rootNode = config.root;
		this.callParent(arguments);
	},
	
	initContent:function(){
		return this.createTree();
	},
	
	createTree:function(){
		var $this = this;
		var rootNode = $this.rootNode;
		
		var toolbarConfig = Ext.isArray($this.toolbarConfig) ? $this.toolbarConfig : [];
		
		var store = Ext.create('Dream.app.store.sm.Resource',{
			root:rootNode
		});
		
		var tree = Ext.create('Ext.tree.Panel',{
			title:'资源列表',
			store: store,
			layout: 'fit',
			columns:[
			         {
			        	 xtype: 'treecolumn',
			             text: '资源名称',
			             width: 150,
			             dataIndex: 'name',
			             sortable: true
			         }, {
			             text: '资源编码',
			             width: 150,
			             dataIndex: 'code',
			             sortable: true
			         }, {
			             text: '资源类型',
			             width: 150,
			             dataIndex: 'type',
			             sortable: true
			         }, {
			             text: '资源路径',
			             width: 150,
			             dataIndex: 'url',
			             sortable: true
			         }, {
			             text: '资源图标',
			             width: 150,
			             dataIndex: 'iconCls',
			             sortable: true
			         }, {
			             text: '描述',
			             width: 150,
			             dataIndex: 'comments',
			             sortable: true
			         }
			],
			dockedItems: [{
				xtype:'toolbar',
				dock:'top',
				items:toolbarConfig
			}]
		});
		
		this.tree = tree;
		
		return tree;
	},
	
	/**
	 * 新增
	 */
	addHandler:function(){
		var $this = this;
		var records = $this.tree.getSelectionModel().getSelection();
		if(records.length == 1){
			var formItemsConfig = Ext.create('Dream.app.view.base.config.form.BaseTreeConfig',{
				items:[{
					fieldLabel:'资源名称',
					name:'name',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
				},{
					fieldLabel:'资源编码',
					name:'code',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
				},{
					fieldLabel:'资源类型',
					name:'type',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
				},{
					fieldLabel:'资源路径',
					name:'url',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
				},{
					fieldLabel:'资源图标',
					name:'iconCls'
				}, {
	                xtype: 'textareafield',
	                fieldLabel: '备注',
	                name: 'comments',
	                allowBlank: true,
	                minLength: 0,
	                maxLength: 100
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
								url:'sm/resource/create',
								form:form,
								window:window,
								tree:$this.tree
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
			
			var parentId = records[0].get("id");
			var data = {
					parentId:parentId
			};
			
			form.getForm().setValues(data);
			
			var window = Ext.create('Ext.window.Window',{
				title:'新增',
				layout:'fit',
				width: 600,
		        height: 400,
				items:[form]
			});
			
			window.show();
		}else{
			Dream.util.msg(tip.title, tip.select);
		}
		
	},
	
	updateHandler:function(){
		var $this = this;
		var records = $this.tree.getSelectionModel().getSelection();
		if(records.length == 1){
			var formItemsConfig = Ext.create('Dream.app.view.base.config.form.BaseTreeConfig',{
				items:[{
					fieldLabel:'资源名称',
					name:'name',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
				},{
					fieldLabel:'资源编码',
					name:'code',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
				},{
					fieldLabel:'资源类型',
					name:'type',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
				},{
					fieldLabel:'资源路径',
					name:'url',
					afterLabelTextTpl: Dream.util.required,
					allowBlank:false
				},{
					fieldLabel:'资源图标',
					name:'iconCls'
				}, {
	                xtype: 'textareafield',
	                fieldLabel: '备注',
	                name: 'comments',
	                allowBlank: true,
	                minLength: 0,
	                maxLength: 100
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
								url:'sm/resource/update',
								form:form,
								window:window,
								tree:$this.tree
						};
						
						$this.saveHandler(target);
					}
				},{
					text:'重置',
					handler:function(){
						var target = {
								url:'sm/resource/get',
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
                url: 'sm/resource/get',
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