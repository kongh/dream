Ext.define('Dream.app.model.base.BaseTree',{
	requiers:['Ext.data.TreeModel'],
	extend:'Ext.data.TreeModel',

	fields: [{
        name:'id'
    },{
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
    },{
        name: 'parentId'
    },{
        name: 'leaf'
    },{
        name: 'expanded'
    },{
        name: 'data'
    }]
})