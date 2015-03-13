Ext.define('Dream.app.store.sm.User',{
	extend: 'Ext.data.Store',
	
	proxy:{
		type:'ajax',
		url:'sm/user/page',
		reader:{
			type:'json',
			root:'data'
		}
	},
	model:'Dream.app.model.sm.User'
});