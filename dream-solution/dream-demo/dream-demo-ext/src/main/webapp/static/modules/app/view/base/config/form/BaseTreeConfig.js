 Ext.define("Dream.app.view.base.config.form.BaseTreeConfig", {
	 
	 extend:'Dream.app.view.base.config.form.BaseEntityConfig',
	 requires: [
	            'Dream.app.view.base.config.form.BaseEntityConfig'
	        ],
	 
     constructor: function (config) {
    	 this.callParent(arguments);
    	 this.addMoreItems(this.getBaseTreeItems());
     },
     
     getBaseTreeItems:function(){
    	 return [{
	       fieldLabel: 'parentId',
	       name: 'parentId',
	       hidden:true
	   },{
	       fieldLabel: 'leaf',
	       name: 'leaf',
	       hidden:true
	   },{
	       fieldLabel: 'expanded',
	       name: 'expanded',
	       hidden:true
	   }];
    	 
     }
 });