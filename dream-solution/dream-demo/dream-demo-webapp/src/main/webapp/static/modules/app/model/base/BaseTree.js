Ext.define('Dream.app.model.base.BaseTree',{
	requiers:['Dream.app.model.base.BaseEntity'],
	extend:'Dream.app.model.base.BaseEntity',
	
	fields: [{
        name: 'parentId'
    },{
        name: 'leaf'
    },{
        name: 'expanded'
    },{
        name: 'data'
    }]
})