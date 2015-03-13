Ext.define('Dream.app.store.sm.Role',{
	extend: 'Ext.data.Store',
	
	proxy:{
		type:'ajax',
		url:'sm/role/page',
		reader:{
			type:'json',
			rootProperty:'data'
		}
	},
	model:'Dream.app.model.sm.Role'
});