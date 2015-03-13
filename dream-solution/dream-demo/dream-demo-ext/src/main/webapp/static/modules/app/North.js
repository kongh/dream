
Ext.define('Dream.app.North',{
	extend: 'Ext.panel.Panel',
	
	initComponent: function(){
		
		var defaultUrl = 'main/header';
		Ext.apply(this, {
			layout:'fit',
			header : false,
			border : false,
			height : 75,
			split : true,
			minHeight : 75,
			maxHeight : 75,
			collapsible : true,
			collapseMode : 'mini',
			loader: {
		        url: defaultUrl,
		        renderer: function (loader, response, active) {
					var text = response.responseText;
					loader.getTarget().update(text, true);
					return true;
				},
		        autoLoad: true,
		        scripts:true,
		        listeners:{
		        	exception:function( thisLoader, response, options, eOpts ){
		        		if (response.status == 401) {
		                    Ext.Msg.alert(tip.title, tip.timeout, function() {
		                        location.replace('login');
		                    });
		                }else if(response.status == 403){
		                	Ext.Msg.alert(tip.title, tip.forbidden + ':['+defaultUrl+']');
		                }else {
		                	Dream.util.msg(tip.title, tip.failure);
		                }
		        	}
		        }
		    }
	    });
		this.callParent(arguments);
	}
});