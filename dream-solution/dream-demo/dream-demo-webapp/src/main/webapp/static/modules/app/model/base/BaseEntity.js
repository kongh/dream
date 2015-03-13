Ext.define('Dream.app.model.base.BaseEntity',{
	requiers:['Dream.app.model.base.IdEntity'],
	extend:'Dream.app.model.base.IdEntity',
	
	fields: [{
        name: 'createUserId'
    },{
        name: 'createUserName'
    },{
        name: 'createDate'
    },{
        name: 'updateUserId'
    },{
        name: 'updateUserName'
    },{
        name: 'sort'
    },{
        name: 'status'
    }]
})