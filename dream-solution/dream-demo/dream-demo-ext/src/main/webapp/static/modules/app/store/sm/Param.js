Ext.define('Dream.app.store.sm.Param',{
	extend: 'Ext.data.Store',
	
	proxy:{
		type:'ajax',
		url:'sm/param/page',
		reader:{
			type:'json',
			rootProperty:'data'
		}
	},
	model:'Dream.app.model.sm.Param'
});