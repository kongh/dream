var tip = {
    title: '提示',
    timeout: '您尚未登录或者登录信息已经超时,请重新登录!',
    forbidden:'您请求的资源被限制',
    load: '加载中,请稍后...',
    submit: '提交中,请稍后...',
    success: '操作成功!',
    failure: '操作失败!',
    select: '请选择一条数据!',
    check: '请检查当前数据!',
    noAdd: '不能新增数据!',
    noUpdate: '不能修改数据!',
    noDelete: '不能删除数据!',
    rePwd: '密码修改成功,请重新登录!',
    upLoadText: '选择...'
};

var Dream = {};

Dream.util = {
	    box: function(t, s) {
	        return '<div class="box"><h3>' + t + '</h3><p>' + s + '</p></div>';
	    },
	    msg: function(t, s) {
	        if (Ext.getDom('main-msg')) {
	            this.msgCt = Ext.getDom('main-msg');
	        } else {
	            this.msgCt = Ext.DomHelper.insertFirst(document.body, {
	                id: 'main-msg'
	            }, true);
	            this.msgCt.alignTo(document.body, 't-t');
	        }
	        var m = Ext.DomHelper.append(this.msgCt, Dream.util.box(t, s), true);
	        m.hide();
	        m.slideIn('t').ghost("t", {
	            delay: 1000,
	            remove: true
	        })
	    },
	    /* 
	     * 检测对象是否是空对象(不包含任何可读属性)。
	     * 方法只既检测对象本身的属性，不检测从原型继承的属性。
	     */
	    isOwnEmpty: function(obj) {
	        for (var name in obj) {
	            if (obj.hasOwnProperty(name)) {
	                return false;
	            }
	        }
	        return true;
	    },
	    /* 
	     * 检测对象是否是空对象(不包含任何可读属性)。
	     * 方法既检测对象本身的属性，也检测从原型继承的属性。
	     */
	    isEmpty: function(obj) {
	        for (var name in obj) {
	            return false;
	        }
	        return true;
	    },
	    convertDt: function(val) {
	        if (val) {
	            return new Date(val);
	        } else {
	            return;
	        }
	    },
	    checkchange: function(node, checked, options) {
	        node.cascadeBy(function(n) {
	            n.set('checked', checked);
	        });
	        if (checked) {
	            node.bubble(function(n) {
	                if (!n.isRoot()) {
	                    n.set('checked', checked);
	                }
	            });
	        }
	    },
	    required:'<span style="color:red;font-weight:bold" data-qtip="必填项">*</span>'
	};

Ext.override(Ext.data.proxy.Server, {
    listeners: {
        exception: function(obj, response) {
            if (response.status == 401) {
                Ext.Msg.alert(tip.title, tip.timeout, function() {
                    location.replace('login');
                });
            }else if(response.status == 403){
            	Ext.Msg.alert(tip.title, tip.forbidden + ':['+obj.config.url+']');
            }else {
            	Dream.util.msg(tip.title, tip.failure);
            }
        }
    }
});

Ext.override(Ext.form.action.Action, {
    failure: function(obj, action) {
        if (action.response.status == 401) {
            Ext.Msg.alert(tip.title, tip.timeout, function() {
                location.replace('login');
            });
        }else if(action.response.status == 403){
        	Ext.Msg.alert(tip.title, tip.forbidden + ':['+action.url+']');
        } else {
        	if(action && action.result && action.result.msg){
        		Dream.util.msg(tip.title, action.result.msg);
        	}else{
        		Dream.util.msg(tip.title, tip.failure);
        	}
        }
    },
    submitEmptyText: false
});

Ext.Loader.setConfig({
    enabled: true,
    paths: {
        'Dream': 'static/modules'
    }
});

Ext.override(Ext.window.Window, {
//    closeAction: 'hide',
    modal: true,
    maximizable: true,
    constrainHeader: true
});

Ext.override(Ext.data.TreeStore,{
	nodeParam:'id'
});

Ext.override(Ext.grid.Panel, {
    autoScroll: false,
    columnLines: true,
    viewConfig: {
        stripeRows: true,
        listeners: {
            beforerefresh: function(view) {
                var store = view.getStore();
                var model = view.getSelectionModel();
                var records = [];
                store.queryBy(function(record) {
                    if (record.get('checked') === true) {
                        records.push(record);
                    }
                });
                model.select(records);
            }
        }
    }
});