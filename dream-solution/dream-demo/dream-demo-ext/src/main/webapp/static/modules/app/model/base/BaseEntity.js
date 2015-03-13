Ext.define('Dream.app.model.base.BaseEntity',{
	requiers:['Dream.app.model.base.IdEntity'],
	extend:'Dream.app.model.base.IdEntity',
	
	fields: [{
        name: 'createUserId'
    },{
        name: 'createUserName'
    },{
        name: 'createDate',
        convert:Dream.util.convertDt
    },{
        name: 'updateUserId'
    },{
        name: 'updateUserName'
    },{
    	name:'updateDate',
    	convert:Dream.util.convertDt
    },{
        name: 'sort'
    },{
        name: 'status'
    },{
    	name:'checked'
    }]
})