
Ext.define('Dream.app.Viewer',{
	extend: 'Ext.container.Viewport',
	
	requires: [
	           'Dream.app.Center',
	           'Dream.app.model.sm.Resource'
	],
	
	initComponent: function(){
		Ext.apply(this, {
            layout: {
                type: 'border',
                padding: 5
            },
            items: [this.createNorthPanel(),this.createCenterPanel(), this.createWestPanel()]
        });
		this.callParent(arguments);
	},

	createNorthPanel:function(){
		this.north = Ext.create('Dream.app.North',{
			region: 'north'
		});
		return this.north;
	},
	
	createCenterPanel:function(){
		this.center = Ext.create('Dream.app.Center',{
			region: 'center'
		});
		return this.center;
	},
	
	createWestPanel:function(){
		var viewer = this;
		var treesData = viewer.config.treesData;
		var trees = [];
		for(var i = 0; i < treesData.length; ++i){
			 trees[i] = Ext.create("Ext.tree.Panel", {
                 title: treesData[i].name,
                 iconCls: treesData[i].iconCls,
                 displayField: 'name',
                 store: Ext.create('Ext.data.TreeStore', {
                	 	autoLoad: true,
                	    model: 'Dream.app.model.sm.Resource',
                	    data:treesData[i],
                		proxy : {
                			type : 'memory',
                			reader : {
                				type : 'json',
                				rootProperty:'data'
                			},
                			autoLoad : true
                		}
                 }),
                 rootVisible: false,
                 lines:false,
                 listeners: {
                     itemclick: function(a, b) {
                    	 viewer.center.removeAll(true);
	                 	 viewer.center.getLoader().load({
	             		       url: context + b.data.url,
	             		       autoLoad: true,
	             		       scripts:true,
		           		        listeners:{
		        		        	exception:function( thisLoader, response, options, eOpts ){
		        		        		if (response.status == 401) {
		        		                    Ext.Msg.alert(tip.title, tip.timeout, function() {
		        		                        location.replace('login');
		        		                    });
		        		                }else if(response.status == 403){
		        		                	Ext.Msg.alert(tip.title, tip.forbidden + ':['+b.data.url+']');
		        		                }else {
		        		                	Dream.util.msg(tip.title, tip.failure);
		        		                }
		        		        	}
		        		        }
	             		  });
                     }
                 }
             });
		}
		this.west = Ext.create('Ext.panel.Panel', {
			region: 'west',
			collapsible: true,
			title:'菜单栏',
			width: 225,
            split: true,
            minWidth: 175,
		    layout: {
		        type: 'accordion',
		        animate: true
		    },
		    items: trees
		});
		
		return this.west;
	}
});