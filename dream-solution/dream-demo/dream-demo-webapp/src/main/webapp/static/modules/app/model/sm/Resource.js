Ext.define('Dream.app.model.sm.Resource',{
	requiers:['Dream.app.model.base.BaseTree'],
	extend: 'Dream.app.model.base.BaseTree',
	
	fields: [{
        name: 'name'
    },{
        name: 'code'
    },{
        name: 'url'
    },{
        name: 'iconCls'
    },{
    	name:'comments'
    }]
})