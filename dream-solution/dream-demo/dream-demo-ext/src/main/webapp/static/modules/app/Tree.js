
Ext.define('Dream.data.BaseTree', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'id'
    }, {
        name: 'parentId'
    }, {
        name: 'leaf'
    }, {
        name: 'expanded'
    }]
});

Ext.define('Dream.app.Tree',{
	extend: 'Ext.tab.Panel',
	
	constructor: function (config) {
		rootNode = config.root;
        this.callParent(arguments);
    },

	initComponent: function(){
		
		Ext.apply(this, {
			layout:'fit',
			items:[this.createTree()]
	    });
		this.callParent(arguments);
	},
	
	rootNode:undefined,
		
	createTree:function(){
		var me = this;
		var store = Ext.create('Ext.data.TreeStore',{
			root:rootNode,
			model : 'Dream.data.BaseTree',
			proxy : {
				type : 'ajax',
				url : 'sm/resource/children',
				reader : {
					type : 'json',
					root : 'data'
				}
			},
			autoLoad : true
		});
		
		var tree = Ext.create('Ext.tree.Panel',{
			title:'tree',
			store: store,
			layout: 'fit',
			columns:[
			         {
			            xtype: 'treecolumn', //this is so we know which column will show the tree
			            text: 'id',
			            width: 200,
			            sortable: true,
			            dataIndex: 'id'
			         }, {
			             text: 'parentId',
			             width: 150,
			             dataIndex: 'parentId',
			             sortable: true
			         }
			]
		});
		return tree;
	},
	
	createToolBar:function(){
		var addBtn = Ext.create('Ext.Button', {
            text: '新增',
            iconCls: 'Add',
            handler: function() {
            	/*thisModule.form.getForm().reset();
            	thisModule.window.show();*/
            	alert('1111');
            }
        });
		
		var toolbar = {
			    xtype: 'toolbar',
			    dock: 'top',
			    items: [
			        addBtn
			    ]
		};
		
		return toolbar;
	}
});