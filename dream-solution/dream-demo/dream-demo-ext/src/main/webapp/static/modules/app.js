Ext.application({
    requires: ['Dream.app.Viewer'],
    name: 'Dream',

    appFolder: 'static/modules',

    launch: function() {
    	Ext.Ajax.request({
			method : 'POST',
			url : 'sm/resource/menus',
			success : function(response) {
				var result = Ext.decode(response.responseText);
				if(result.success){
					viewer = Ext.create('Dream.app.Viewer',{
						treesData:result.data
					});
				}else{
					
				}
			}
		});
    }
});