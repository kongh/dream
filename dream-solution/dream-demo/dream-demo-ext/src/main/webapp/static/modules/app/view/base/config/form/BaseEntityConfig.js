 Ext.define("Dream.app.view.base.config.form.BaseEntityConfig", {
	 
	 extend:'Dream.app.view.base.config.form.IdEntityConfig',
	 requires: [
	            'Dream.app.view.base.config.form.IdEntityConfig'
	        ],
	 
     constructor: function (config) {
    	 this.callParent(arguments);
    	 this.addMoreItems(this.getBaseEntityItems());
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
		   xtype : 'datefield',
		   format : 'Y-m-d H:i:s',
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
		   xtype : 'datefield',
		   format : 'Y-m-d H:i:s',
	       fieldLabel: 'updateDate',
	       name: 'updateDate',
	       hidden:true
	   }];
     }
 });