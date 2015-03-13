 Ext.define("Dream.app.view.base.config.form.BaseEntityConfig", {
	 
	 extend:'Dream.app.view.base.config.form.IdEntityConfig',
	 requires: [
	            'Dream.app.view.base.config.form.IdEntityConfig'
	        ],
	 
     constructor: function (config) {
    	 this.addMoreItems(this.getBaseEntityItems());
    	 this.callParent(arguments);
     },
     
     getBaseEntityItems:function(){
    	 return [{
	       fieldLabel: 'createUserId',
	       name: 'createUserId',
	       hidden:true
	   },{
	       fieldLabel: 'createUserName',
	       name: 'createUserName',
	       hidden:true
	   },{
	       fieldLabel: 'createDate',
	       name: 'createDate',
	       hidden:true
	   },{
	       fieldLabel: 'updateUserId',
	       name: 'updateUserId',
	       hidden:true
	   },{
	       fieldLabel: 'updateUserName',
	       name: 'updateUserName',
	       hidden:true
	   },{
	       fieldLabel: 'updateDate',
	       name: 'updateDate',
	       hidden:true
	   },{
	       fieldLabel: 'updateUserName',
	       name: 'updateUserName',
	       hidden:true
	   }];
    	 
     }
 });