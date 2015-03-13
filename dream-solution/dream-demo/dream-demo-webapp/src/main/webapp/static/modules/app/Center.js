
Ext.define('Dream.app.Center',{
	extend: 'Ext.panel.Panel',
	
	initComponent: function(){
		
		Ext.apply(this, {
			layout:'fit',
			loader: {
		        url: 'grid',
		        renderer:'html',
		        autoLoad: true,
		        scripts:true
		    }
	    });
		this.callParent(arguments);
	}
});