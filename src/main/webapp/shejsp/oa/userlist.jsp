<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
    <title>用户列表</title>
    <jsp:include page="/shejsp/sys/inc.jsp"></jsp:include>
</head>
<body>
	<!-- <div class="well well-small" style="margin-left: 5px;margin-top: 5px">
		<span class="badge">提示</span>
		<p>
			在此你可以对<span class="label-info"><strong>用户</strong></span>进行编辑!
		</p>
	</div> -->
	
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:true">
			<table id="easyTable"  pagination="true"  rownumbers="true"  checkOnSelect="true" singleSelect="true"
				data-options="url:'${pageContext.request.contextPath}/oa/userAction!retrieve.do',
				toolbar:'#toolbar',fit:true,striped:true,border:false">
				<thead>
					<tr>
					    <th field="userId" data-options="formatter:ezEditFromat" width="150" align="center">编辑</th>
						<th field="myid" width="100" sortable="true">用户编码</th>
						<th field="account" width="100" sortable="true">用户账号</th>
						<th field="name" width="100" sortable="true">用户名称</th>
						<th field="password" width="100" sortable="true">用户密码</th>
						<th field="tel" width="120">电话</th>
						<th field="email" width="120">邮箱</th>
						<th field="description" width="120">用户说明</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="toolbar">
			<table>
				<tr>
					<td>
						<table style="font-size: 13px;font-family: '微软雅黑';">
							<tr>
								<td>用户名称</td>
								<td><input id="userNameSch" name="userNameSch" upload="true" class="textbox" /></td>
								<td><a href="javascript:void(0)" class="easyui-linkbutton " data-options="iconCls:'icon-search',plain:true" onclick="reload()" >查询</a></td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<table>
						<tr>
							<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()" >添加</a> </td>
							<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="delUsers()">删除</a></td>
							<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="saveUsers()">保存</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="printExcel()">导出excel模板</a></td>
							<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="uploadifyFile(fileCallback,'fileTemp','xls;xlsx')">导入Excel数据</a></td>
							<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="myPrint()">打印</a></td>
							<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="myEChart()">图表</a></td>
						</tr>
					</table>
				</tr>
			</table>
		 
		</div>
	</div>
	<input type="hidden" name="text1" />
<script type="text/javascript">
	$(function(){
		$(".com_search").keydown(function(event) {
				if (event.which == 13) {
					reload();
				}
			});
		user_grid = $('#easyTable').datagrid({
			
		});
// 		getButtons("easyTable",add);
	});
	
	function ezEditFromat(userId,row,index){
		var str = "";
		str += jqueryUtil.formatString('<img title="编辑" src="{1}" onclick="editUser(\'{0}\');"/>', userId,'${pageContext.request.contextPath}/cssstyle/images/extjs_icons/pencil.png');
		str += jqueryUtil.formatString('<img title="删除" src="{1}" onclick="delUser(\'{0}\');"/>', userId,'${pageContext.request.contextPath}/cssstyle/images/extjs_icons/cancel.png');
		str += jqueryUtil.formatString('<img title="附件管理" src="{1}" onclick="uploadUser(\'{0}\');"/>', userId,'${pageContext.request.contextPath}/cssstyle/images/extjs_icons/lock/lock_edit.png');
		str += jqueryUtil.formatString('<img title="初始化密码" src="{1}" onclick="initPassword(\'{0}\');"/>',userId,'${pageContext.request.contextPath}/cssstyle/images/extjs_icons/key.png');
		return str;
	}
	
	function editUser(userId){
		jqueryUtil.modalDialog({
			title : '编辑',
			width : 600,
			height : 400,
			href : '${pageContext.request.contextPath}/oa/userAction!editUser.do?userId=' + userId,
			buttons : [ {
				text : 	'保存',
				handler : function() {
					$('#user_edit_form').submit();
				}
			} ]
		});
	}
	
	function reload() {
		$('#easyTable').datagrid("reload",{
			userNameSch:$("#userNameSch").val()
		});
	}
	
	function getButtons(detaGridId, addFunction) {
		if (addFunction) {
			var pager = $('#' + detaGridId).datagrid('getPager');
			pager.pagination({
				buttons : [ {
					iconCls : 'icon-add',
					title:'添加',
					handler : add
				} ]
			});
		}
	}
	
	
	
	//var myBtn = '<button type="button">保存</button>';
	function add() {
		$.modalDialog({
			title : '添加',
			width : 600,
			height : 500,
			href : 'userAction!add.do?',
			buttons : [ {
				text : "保存",
				handler : function() {
					$('#form').submit();
				}
			} ]
		});
	}
	
	

	//单个删除用户
	function delUser(userId) {
		$.messager.confirm('确认删除', '确定删除本条记录?', function(r) {
			if (r) {
				$.ajax({
					url : "userAction!del.do?userId="
							+ userId,
					type : "post",
					dataType : "json",
					success : function(data) {
						if (data) {
							$.messager.alert("提示信息", data, '', reload);
						}
					}
				});
			}
		});
	}
	//批量删除用户
	function delUsers(){
		var users = $("#easyTable").datagrid("getChecked"),userIds = "";
		
		if(users.length==0){
			$.messager.alert("提示信息","请选择删除项");
			return false;
		}
		for(var i=0,len=users.length;i<len;i++){
			userIds += users[i].userId+",";
		}
		$.messager.confirm('确认删除', '确定删除本条记录?', function(r) {
			if (r) {
				$.ajax({
					url : "userAction!delUsers.do",
					type : "post",
					data : {userIds:userIds},
					dataType : "json",
					success : function(data) {
						if (data) {
							$.messager.alert("提示信息", data, '', reload);
						}
					}
				});
			}
		});
	}
	
	//excel导入后批量保存用户
	function saveUsers(){
		var users = $("#easyTable").datagrid("getRows");
		 
		$.ajax({
				type : "POST",
				url : "userAction!saveUsers.do",
				data : {
					'result' : JSON.stringify(users)
				},
				dataType : "json",
				success : function(data) {
					$.messager.alert("提示信息", data, '', reload);
				}
			});
	
		}
	
	function uploadUser(userId) {
		comFileUpload(userId, "User");
	
	}
	
	function printExcel() {
		var url = "userAction!exportExcel.do";
		window.open(url);
	}
	
	function fileCallback(fileName) {
		$("#easyTable").datagrid("load", {
			fileName : fileName
		});
	}
	//初始化密码(1234)
	function initPassword(userId){
		$.messager.confirm('确认初始化密码', '确定初始化密码本条记录?', function(r) {
			
			if (r) {
				$.ajax({
					url : "${pageContext.request.contextPath}/oa/userAction!initPassword.do?userId="
							+ userId,
					type : "post",
					dataType : "json",
					success : function(data) {
						if (data) {
							$.messager.alert("提示信息", data, '', reload);
						}
					}
				});
			}
		});
	}
	
	//打印
	function myPrint(){
		var dialog = parent.hjh.modalDialog({
			title : '打印',
			width : 640,
			height : 600,
			url : 'userAction!myprint.do',
		});
	}
  	function myEChart() {
		var dialog = parent.hjh.modalDialog({
			title : '用户图表',
			url : 'userEchartAction.do',
		});
	};
</script>
</body>
</html>
