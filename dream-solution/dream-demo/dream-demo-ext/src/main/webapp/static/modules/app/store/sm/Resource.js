Ext.define('Dream.app.store.sm.Resource',{
	extend:'Ext.data.TreeStore',
	
	model : 'Dream.app.model.sm.Resource',
	
	proxy : {
		type : 'ajax',
		url : 'sm/resource/children',
		reader : {
			type : 'json',
			rootProperty:'data'
		},
		nodeParam:'id',
		autoLoad : true
	}
});