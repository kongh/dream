
Ext.define('Dream.app.Viewer',{
	extend: 'Ext.container.Viewport',
	
	requires: [
	           'Dream.app.Center'
	],
	
	initComponent: function(){
		Ext.apply(this, {
            layout: {
                type: 'border',
                padding: 5
            },
            items: [this.createCenterPanel(), this.createWestPanel()]
        });
		this.callParent(arguments);
	},

	createNorthPanel:function(){
		
	},
	
	createCenterPanel:function(){
		this.center = Ext.create('Dream.app.Center',{
			region: 'center'
		});
		return this.center;
	},
	
	createWestPanel:function(){
		var viewer = this;
		this.west = Ext.create('Ext.panel.Panel',{
			region: 'west',
			collapsible: true,
			title:'导航',
			width: 225,
            split: true,
            minWidth: 175,
            items:[{
            	xtype:'button',
            	text:'表格',
            	handler:function(){
            		viewer.center.removeAll(true);
            		viewer.center.getLoader().load( {
        		        url: 'sm/param',
        		        autoLoad: true,
        		        scripts:true
        		    });
            	}
            },{
            	xtype:'button',
            	text:'树',
            	handler:function(){
            		viewer.center.removeAll(true);
            		viewer.center.getLoader().load( {
        		        url: 'sm/resource',
        		        autoLoad: true,
        		        scripts:true
        		    });
            	}
            },{
            	xtype:'button',
            	text:'用户',
            	handler:function(){
            		viewer.center.removeAll(true);
            		viewer.center.getLoader().load( {
        		        url: 'sm/user',
        		        autoLoad: true,
        		        scripts:true
        		    });
            	}
            }]
		});
		return this.west;
	}
});