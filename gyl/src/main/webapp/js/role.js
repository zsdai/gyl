var roletree = {
	data:{
		treeNode:"",
		zTreePlugin:""
	},
    setting: {
        isSimpleData: true,
        treeNodeKey: "rid",
        treeNodeParentKey: "pid",
        showLine: true,
        root: {
            isRoot: true,
            nodes: []
        },
        callback: {
			/*
			 * treeNode被右键点击的节点 JSON 数据对象
			 */
            rightClick: function zTreeOnRightClick(event, treeId, treeNode){
				roletree.data.treeNode=treeNode;//给data中的treeNode赋值
                roletree.showRMenu("root", event.clientX, event.clientY);
            }
        }
    },
    loadRoleTree: function(){
        $.post("roleAction_showRoleTree.action", null, function(data){
			alert(data);
         roletree.data.zTreePlugin=$("#roleTree").zTree(roletree.setting, data);
        }, "json");
    },
    showRMenu: function(type, x, y){
        $("#rMenu ul").show();
        $("#rMenu").css({"top": y + "px","left": x + "px","display": "block"
        });
    },
    //添加 删除 修改事件
	crudEvent:function(){
	
			$("#addRole").unbind("click");
			$("#addRole").bind("click",function(){
				var roleName=window.prompt("请输入角色名称：");
				if(roleName){
					//判断是否已经存在该角色名称  后台验证
					$.post("roleAction_findRoleByName.action",{name:roleName},function(flag){
						if(flag=="0"){//如果数据库中不存在
							var newNodeInfo={
								name:roleName,
								pid:roletree.data.treeNode.rid,
								isParent:false
							};
							$.post("roleAction_add.action",newNodeInfo,function(data){
								roletree.data.zTreePlugin.addNodes(roletree.data.treeNode, newNodeInfo, false);
							});
						}else{
							alert("该角色已经存在");
						}
					});
				}				
			});
			/**
			 * 删除角色节点操作
			 * 	1.发送参数到后台  在db中删除该数据
			 * 	2.使用zTree API删除该节点
			 */
			$("#deleteRole").unbind("click");
			$("#deleteRole").bind("click",function(){
				if(window.confirm("确定要删除吗？")){
					$.post("roleAction_deleteRole.action",{rid:roletree.data.treeNode.rid},function(){
						roletree.data.zTreePlugin.removeNode(roletree.data.treeNode);
						$("#rMenu").hide();
					});
				}
			});
			$("#updateRole").unbind("click");
			$("#updateRole").bind("click",function(){
				var updatedRoleName=window.prompt("新的角色名称：",roletree.data.treeNode.name);
				//前台验证
				if(roletree.data.zTreePlugin.getNodeByParam("name", updatedRoleName)){//获取到了
					alert("已存在的角色名称");
				}else{
					$.post("roleAction_update.action",{
						rid:roletree.data.treeNode.rid,
						name:updatedRoleName,
						isParent:false
						},function(){
							var currentTreeNode=roletree.data.treeNode;
							currentTreeNode.name=updatedRoleName;
							roletree.data.zTreePlugin.updateNode(currentTreeNode, false);
					});
				}
			});
	}

};
$().ready(function(){
    //加载角色树
    roletree.loadRoleTree();
	//初始化邮件菜单显示事件
		$("#rMenu").hover(function(){
			$(this).show();
		},function(){
			$(this).hide();
		});
	//初始化crud事件
	roletree.crudEvent();
});
