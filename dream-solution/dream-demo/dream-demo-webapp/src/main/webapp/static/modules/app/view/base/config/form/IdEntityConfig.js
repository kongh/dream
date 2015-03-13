 Ext.define("Dream.app.view.base.config.form.IdEntityConfig", {
	 
	//表单配置项
	items:[{
	       fieldLabel: 'id',
	       name: 'id',
	       hidden:true
	   }],
	 
     constructor: function (config) {
    	if(config && config.items){
    		this.addMoreItems(config.items);
    	}
     },
 
 	addMoreItems:function(items){
 		var $this = this;
 		
 		Ext.Array.each(items, function(item, index, countriesItSelf) {
 			var index = $this.indexOfItem(item);
 			if(index != -1){
 				$this.items[index] = item;
 			}else{
 				$this.items.push(item);
 			}
 		});
 	},
 	
    hasItem:function(compared){
    	var $this = this;
    	return $this.indexOfItem(compared) != -1;
    },
 	
    indexOfItem:function(compared){
    	var $this = this;
    	var index = -1;
    	Ext.Array.each($this.items, function(item, indexOfC, countriesItSelf) {
 		   if(item.name == compared.name){
 			   index = indexOfC;
 			   return false;//break;
 		   }
 		});
    	return index;
    }
 	
 });