Ext.application({
    requires: ['Dream.app.Viewer'],
    name: 'Dream',

    appFolder: 'static/modules',

    launch: function() {
    	viewer = Ext.create('Dream.app.Viewer')
    }
});