
Ext.define('User', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'param1'
    }, {
        name: 'param2'
    }, {
        name: 'param3'
    }]
});

Ext.define('Dream.app.Grid',{
	extend: 'Ext.tab.Panel',
	
	initComponent: function(){
		
		Ext.apply(this, {
			layout:'fit',
			items:[this.createGrid()]
	    });
		this.callParent(arguments);
	},
	
	createGrid:function(){
		
		var store = Ext.create('Ext.data.Store',{
			 model: 'User',
			 proxy:{
				 type:'ajax',
				 url:'users',
				 reader:{
					 type:'json',
					 root: 'data'
				 }
			 },
			 autoLoad:true
		});
		
		this.grid = Ext.create('Ext.grid.Panel',{
			title:'用户列表',
			layout: 'fit',
			store:store,
			selModel: Ext.create('Ext.selection.CheckboxModel'),
			columns:[
			         {
			        	 text:'参数1',
			        	 dataIndex:'param1'
			         },{
			        	 text:'参数2',
			        	 dataIndex:'param2'
			         },{
			        	 text:'参数3',
			        	 dataIndex:'param3'
			         }
			],
			dockedItems: [this.createToolBar(),{
			    xtype: 'pagingtoolbar',
			    dock: 'bottom',
			    store: store,
		        dock: 'bottom',
		        displayInfo: true
			}]
		});
		
		return this.grid;
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