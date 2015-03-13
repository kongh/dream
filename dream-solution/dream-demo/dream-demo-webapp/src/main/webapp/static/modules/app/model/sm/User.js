Ext.define('Dream.app.model.sm.User',{
	requiers:['Dream.app.model.base.BaseEntity'],
	extend: 'Dream.app.model.base.BaseEntity',
	
	fields: [{
        name: 'account'
    },{
        name: 'name'
    },{
        name: 'phone'
    },{
        name: 'email'
    }]
})