(function($) {
	// 设置了一个jqueryUtil的全局系统对象
	window['jqueryUtil'] = {};

	var easyuiErrorFunction = function(XMLHttpRequest) {
		$.messager.progress('close');
		$.messager.alert('错误', XMLHttpRequest.responseText);
	};
	$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
	$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
	$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
	$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
	$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
	$.fn.form.defaults.onLoadError = easyuiErrorFunction;
	/**
	 * 取消easyui默认开启的parser 在页面加载之前，先开启一个进度条 然后在页面所有easyui组件渲染完毕后，关闭进度条
	 * 
	 * @requires jQuery,EasyUI
	 */
	$.parser.auto = false;
	$(function() {
		$.messager.progress({
			text : '加载中....',
			interval : 100
		});
		$.parser.parse(window.document);
		window.setTimeout(function() {
			$.messager.progress('close');
			if (self != parent) {
				window.setTimeout(function() {
					try {
						parent.$.messager.progress('close');
					} catch (e) {
					}
				}, 500);
			}
		}, 1);
		$.parser.auto = true;
	});
	// IE检测
	jqueryUtil.isLessThanIe8 = function() {
		return ($.browser.msie && $.browser.version < 8);
	};
	/**
	 * 使panel和datagrid在加载时提示
	 * 
	 * @requires jQuery,EasyUI
	 */
	$.fn.panel.defaults.loadingMessage = '加载中....';
	$.fn.datagrid.defaults.loadMsg = '加载中....';

	/**
	 * @requires jQuery,EasyUI 防止panel/window/dialog组件超出浏览器边界
	 * @param left
	 * @param top
	 */
	var easyuiPanelOnMove = function(left, top) {
		var l = left;
		var t = top;
		if (l < 1) {
			l = 1;
		}
		if (t < 1) {
			t = 1;
		}
		var width = parseInt($(this).parent().css('width')) + 14;
		var height = parseInt($(this).parent().css('height')) + 14;
		var right = l + width;
		var buttom = t + height;
		var browserWidth = $(window).width();
		var browserHeight = $(window).height();
		if (right > browserWidth) {
			l = browserWidth - width;
		}
		if (buttom > browserHeight) {
			t = browserHeight - height;
		}
		$(this).parent().css({/* 修正面板位置 */
			left : l,
			top : t
		});
	};
	$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
	$.fn.window.defaults.onMove = easyuiPanelOnMove;
	$.fn.panel.defaults.onMove = easyuiPanelOnMove;

	/**
	 * @requires jQuery,EasyUI panel关闭时回收内存
	 */
	$.fn.panel.defaults.onBeforeDestroy = function() {
		var frame = $('iframe', this);
		try {
			if (frame.length > 0) {
				frame[0].contentWindow.document.write('');
				frame[0].contentWindow.close();
				frame.remove();
				if ($.browser.msie) {
					CollectGarbage();
				}
			}
		} catch (e) {
		}
	};

	// 序列化表单到对象
	jqueryUtil.serializeObject = function(form) {
		var o = {};
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + ","
						+ (this['value'] == '' ? ' ' : this['value']);
			} else {
				o[this['name']] = this['value'] == '' ? ' ' : this['value'];
			}
		});
		return o;
	};

	// cookies
	jqueryUtil.cookies = (function() {
		var fn = function() {
		};
		fn.prototype.get = function(name) {
			var cookieValue = "";
			var search = name + "=";
			if (document.cookie.length > 0) {
				offset = document.cookie.indexOf(search);
				if (offset != -1) {
					offset += search.length;
					end = document.cookie.indexOf(";", offset);
					if (end == -1)
						end = document.cookie.length;
					cookieValue = decodeURIComponent(document.cookie.substring(
							offset, end))
				}
			}
			return cookieValue;
		};
		fn.prototype.set = function(cookieName, cookieValue, DayValue) {
			var expire = "";
			var day_value = 1;
			if (DayValue != null) {
				day_value = DayValue;
			}
			expire = new Date((new Date()).getTime() + day_value * 86400000);
			expire = "; expires=" + expire.toGMTString();
			document.cookie = cookieName + "="
					+ encodeURIComponent(cookieValue) + ";path=/" + expire;
		}
		fn.prototype.remvoe = function(cookieName) {
			var expire = "";
			expire = new Date((new Date()).getTime() - 1);
			expire = "; expires=" + expire.toGMTString();
			document.cookie = cookieName + "=" + escape("") + ";path=/"
					+ expire;
			/* path=/ */
		};

		return new fn();
	})();
	// 获取随机时间
	jqueryUtil.getRandTime = function() {
		var nowDate = new Date();
		var str = "";
		var hour = nowDate.getHours();// HH
		str += ((hour < 10) ? "0" : "") + hour;
		var min = nowDate.getMinutes();// MM
		str += ((min < 10) ? "0" : "") + min;
		var sec = nowDate.getSeconds(); // SS
		str += ((sec < 10) ? "0" : "") + sec;
		return Number(str);
	};
	// 切换皮肤
	jqueryUtil.chgSkin = function(selectId, cookiesColor) {
		docchgskin(document, selectId, cookiesColor);
		$("iframe").each(function() {
			var dc = this.contentWindow.document;
			docchgskin(dc, selectId, cookiesColor);
		});
		function docchgskin(dc, selectId, cookiesColor) {
			removejscssfile(dc, "themes/" + cookiesColor + "/easyui.css", "css");
			createLink(dc, "themes/" + selectId + "/easyui.css");
		}
		function createLink(dc, url) {
			var urls = url.replace(/[,]\s*$/ig, "").split(",");
			var links = [];
			for ( var i = 0; i < urls.length; i++) {
				links[i] = dc.createElement("link");
				links[i].rel = "stylesheet";
				links[i].href = urls[i];
				dc.getElementsByTagName("head")[0].appendChild(links[i]);
			}
		}
		function removejscssfile(dc, filename, filetype) {
			var targetelement = (filetype == "js") ? "script"
					: (filetype == "css") ? "link" : "none"
			var targetattr = (filetype == "js") ? "src"
					: (filetype == "css") ? "href" : "none"
			var allsuspects = dc.getElementsByTagName(targetelement)
			for ( var i = allsuspects.length; i >= 0; i--) {
				if (allsuspects[i]
						&& allsuspects[i].getAttribute(targetattr) != null
						&& allsuspects[i].getAttribute(targetattr).indexOf(
								filename) != -1)
					allsuspects[i].parentNode.removeChild(allsuspects[i])
			}
		}
	};

	/**
	 * 增加formatString功能
	 * 
	 * @author haojiahong
	 * 
	 * @example sy.formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
	 * 
	 * @returns 格式化后的字符串
	 */
	jqueryUtil.formatString = function(str) {
		for ( var i = 0; i < arguments.length - 1; i++) {
			str = str.replace("{" + i + "}", arguments[i + 1]);
		}
		return str;
	};
	/**
	 * 创建一个模式化的dialog(iframe方式)
	 * 
	 * @author haojiahong
	 * 
	 * @requires jQuery,EasyUI
	 * 
	 */
	jqueryUtil.modalDialog = function(options) {
		var opts = $.extend({
			title : '&nbsp;',
			width : 640,
			height : 480,
			modal : true,
			onClose : function() {
				$(this).dialog('destroy');
			}
		}, options);
		opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
		if (options.url) {
			opts.content = '<iframe id="" src="'
					+ options.url
					+ '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
		}
		return $('<div id="myDialog"/>').dialog(opts);
	};

	/**
	 * @author 孙宇
	 * 
	 * @requires jQuery,EasyUI
	 * 
	 * 创建一个模式化的dialog
	 * 
	 * @returns $.modalDialog.handler 这个handler代表弹出的dialog句柄
	 * 
	 * @returns $.modalDialog.xxx
	 *          这个xxx是可以自己定义名称，主要用在弹窗关闭时，刷新某些对象的操作，可以将xxx这个对象预定义好
	 */
	$.modalDialog = function(options) {
		if ($.modalDialog.handler == undefined) {// 避免重复弹出
			var opts = $.extend({
				title : '',
				width : 840,
				height : 680,
				modal : true,
				onClose : function() {
					$.modalDialog.handler = undefined;
					$(this).dialog('destroy');
				}
			/*
			 * onOpen : function() { parent.$.messager.progress({ title : '提示',
			 * text : '数据处理中，请稍后....' }); }
			 */
			}, options);
			opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
			return $.modalDialog.handler = $('<div/>').dialog(opts);
		}
	};

	/**
	 * 扩展树表格级联选择（点击checkbox才生效）：
	 * 		自定义两个属性：
	 * 		cascadeCheck ：普通级联（不包括未加载的子节点）
	 * 		deepCascadeCheck ：深度级联（包括未加载的子节点）
	 */
	$.extend($.fn.treegrid.defaults,{
		onLoadSuccess : function() {
			var target = $(this);
			var opts = $.data(this, "treegrid").options;
			var panel = $(this).datagrid("getPanel");
			var gridBody = panel.find("div.datagrid-body");
			var idField = opts.idField;//这里的idField其实就是API里方法的id参数
			gridBody.find("div.datagrid-cell-check input[type=checkbox]").unbind(".treegrid").click(function(e){
				if(opts.singleSelect) return;//单选不管
				if(opts.cascadeCheck||opts.deepCascadeCheck){
					var id=$(this).parent().parent().parent().attr("node-id");
					var status = false;
					if($(this).attr("checked")){
						target.treegrid('select',id);
						status = true;
					}else{
						target.treegrid('unselect',id);
					}
					//级联选择父节点
					selectParent(target,id,idField,status);
					selectChildren(target,id,idField,opts.deepCascadeCheck,status);
				}
				e.stopPropagation();//停止事件传播
			});
		}
	});
	/**
	 * 扩展树表格级联勾选方法：
	 * @param {Object} container
	 * @param {Object} options
	 * @return {TypeName} 
	 */
	$.extend($.fn.treegrid.methods,{
		/**
		 * 级联选择
	     * @param {Object} target
	     * @param {Object} param 
		 *		param包括两个参数:
	     *			id:勾选的节点ID
	     *			deepCascade:是否深度级联
	     * @return {TypeName} 
		 */
		cascadeCheck : function(target,param){
			var opts = $.data(target[0], "treegrid").options;
			if(opts.singleSelect)
				return;
			var idField = opts.idField;//这里的idField其实就是API里方法的id参数
			var status = false;//用来标记当前节点的状态，true:勾选，false:未勾选
			var selectNodes = $(target).treegrid('getSelections');//获取当前选中项
			for(var i=0;i<selectNodes.length;i++){
				if(selectNodes[i][idField]==param.id)
					status = true;
			}
			//级联选择父节点
			selectParent(target,param.id,idField,status);
			selectChildren(target,param.id,idField,param.deepCascade,status);
		}
	});
	
	
	
	/**
	 * 级联选择父节点
	 * @param {Object} target
	 * @param {Object} id 节点ID
	 * @param {Object} status 节点状态，true:勾选，false:未勾选
	 * @return {TypeName} 
	 */
	function selectParent(target,id,idField,status){
		var parent = target.treegrid('getParent',id);
		if(parent){
			var parentId = parent[idField];
			if(status)
				target.treegrid('select',parentId);
			else
				target.treegrid('unselect',id);
			selectParent(target,parentId,idField,status);
		}
	}
	/**
	 * 级联选择子节点
	 * @param {Object} target
	 * @param {Object} id 节点ID
	 * @param {Object} deepCascade 是否深度级联
	 * @param {Object} status 节点状态，true:勾选，false:未勾选
	 * @return {TypeName} 
	 */
	function selectChildren(target,id,idField,deepCascade,status){
		//深度级联时先展开节点
		if(status&&deepCascade)
			target.treegrid('expand',id);
		//根据ID获取下层孩子节点
		var children = target.treegrid('getChildren',id);
		for(var i=0;i<children.length;i++){
			var childId = children[i][idField];
			if(status)
				target.treegrid('select',childId);
			else
				target.treegrid('unselect',childId);
			selectChildren(target,childId,idField,deepCascade,status);//递归选择子节点
		}
	}
	
	/*
	 * 定义图标样式的数组
	 */
	$.iconData = [ {
		value : '',
		text : '默认'
	}, {
		value : 'icon-adds',
		text : 'icon-adds'
	}, {
		value : 'icon-ok',
		text : 'icon-ok'
	}, {
		value : 'icon-edit',
		text : 'icon-edit'
	}, {
		value : 'icon-tip',
		text : 'icon-tip'
	}, {
		value : 'icon-back',
		text : 'icon-back'
	}, {
		value : 'icon-remove',
		text : 'icon-remove'
	}, {
		value : 'icon-undo',
		text : 'icon-undo'
	}, {
		value : 'icon-cancel',
		text : 'icon-cancel'
	}, {
		value : 'icon-save',
		text : 'icon-save'
	}, {
		value : 'icon-config',
		text : 'icon-config'
	}, {
		value : 'icon-comp',
		text : 'icon-comp'
	}, {
		value : 'icon-sys',
		text : 'icon-sys'
	}, {
		value : 'icon-db',
		text : 'icon-db'
	}, {
		value : 'icon-pro',
		text : 'icon-pro'
	}, {
		value : 'icon-role',
		text : 'icon-role'
	}, {
		value : 'icon-end',
		text : 'icon-end'
	}, {
		value : 'icon-bug',
		text : 'icon-bug'
	}, {
		value : 'icon-badd',
		text : 'icon-badd'
	}, {
		value : 'icon-bedit',
		text : 'icon-bedit'
	}, {
		value : 'icon-bdel',
		text : 'icon-bdel'
	}, {
		value : 'icon-item',
		text : 'icon-item'
	}, {
		value : 'icon-excel',
		text : 'icon-excel'
	}, {
		value : 'icon-auto',
		text : 'icon-auto'
	}, {
		value : 'icon-time',
		text : 'icon-time'
	} ];
})(jQuery);

/**
 * 表单提交,刷新表格,执行reload方法
 */
var ez_formSub = {
	onSubmit : function() {
		var isValid = $(this).form('validate');
		if (isValid) {
			$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
		}
		return isValid;
	},
	success : function(data) {
		result = $.parseJSON(data);
		// parent.reload();
		$.messager.progress('close');
		$.messager.alert('提醒', result.message, 'info');
		if ($.modalDialog.handler) {
			$.modalDialog.handler.dialog('close');

		}
	}
};
