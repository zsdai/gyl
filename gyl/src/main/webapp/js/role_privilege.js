var role_privilege = {
    /**
     * 数据模块
     */
    data: {
        role: {
            name:'',
            rid: ''
        },
        zTreePlugin: ''
    },
    /**
     * 操作模块
     */
    opt: {
        /**
         * div操作
         */
        divOpt: {
            showHideDiv: function(){
                $("div:hidden").show();
            }
        },
        /**
         * 角色操作
         */
        roleOpt: {
            showRoleName: function(){
                $("#roleImage").text("角色:" + role_privilege.data.role.name);
            }
        },
        /**
         * 权限树操作
         */
        privilegeTreeOpt: {
            setting: {
                isSimpleData: true,
                treeNodeKey: "id",
                treeNodeParentKey: "pid",
                showLine: true,
                checkable: true,
                root: {
                    isRoot: true,
                    nodes: []
                },
                callback: {
                    change: function(){
						role_privilege.opt.privilegeTreeOpt.isAllCheckedOnPrivilegeTree();
                    }
                }
            },
            loadPrivilegeTree: function(){
                /**
                 * 第三个参数为回调函数，是有服务器端出发的，
                 * 并且是在readystatus的值为4 并且status的值为200的时候触发的
                 * 故不知道什么时候能加载完，也就不能有效的确定代码执行的顺序
                 * 因此要把隐藏loading图标卸载回调函数中
                 * @param {Object} data
                 */
                $.post("privilegeAction_showPrivilegeTree.action", {
					rid:role_privilege.data.role.rid
					}, function(data){
                    role_privilege.data.zTreePlugin = $("#privilegeTree").zTree(role_privilege.opt.privilegeTreeOpt.setting, data);
                    /**
                     * 权限树加载完之后，隐藏loading图标
                     * 显示权限树
                     */
                    $("#loading").hide();
						//设置全选框的初始化状态
						role_privilege.opt.privilegeTreeOpt.isAllCheckedOnPrivilegeTree();
                    $("#privilegeTree").show();
						/**
						 * 设置全选按钮为可用状态
						 */
						$("#allchecked").attr("disabled",false);
                }, "json");
            },
            /**
             * 判断权限树上的权限是否全部被选中
             */
			isAllCheckedOnPrivilegeTree:function(){
				if(role_privilege.data.zTreePlugin.getCheckedNodes(false).length==0){//如果被全部选中的
					$("#allchecked").attr("checked",true);
				}else{
					$("#allchecked").attr("checked",false);
				}
			},
			/**
			 * 建立角色和权限之间的关系
			 */
			savePrivilege:function(){
				//得到被选中的权限
				var checkedNodes=role_privilege.data.zTreePlugin.getCheckedNodes(true);
				var checkedStr="";
				$(checkedNodes).each(function(){
					checkedStr+=$(this).get(0).id+",";
				});
				checkedStr=checkedStr.substring(0,checkedStr.length-1);
					$.post("privilegeAction_savePrivilege.action",{
					rid:role_privilege.data.role.rid,
					checkedStr:checkedStr
				},function(){
					alert("添加成功！");
				});
			}
        },
    },
    /**
     * 初始化模块
     */
    init: {
        initData: function(){
            role_privilege.data.role.name = $(this).parent().siblings("td:first").text();
            role_privilege.data.role.rid = $(this).parent().siblings("input[type='hidden']").attr("value");
        },
        initEvent: function(){
            /**
             * 给设置权限添加click事件
             */
            $("a").each(function(){
                if ($(this).text() == "设置权限") {
                    $(this).unbind("click");
                    $(this).bind("click", function(){
                        /**
                         * 1.显示被隐藏的div
                         * 2.动态显示角色名
                         * 3.对已有权限进行回显
                         */
                        role_privilege.opt.divOpt.showHideDiv();
                        //给	role_privilege.data.role中的name和rid赋值
                        role_privilege.init.initData.call(this);
                        //动态显示角色名称
                        role_privilege.opt.roleOpt.showRoleName();
                        /**
                         * 加载权限树前，显示loading图标，隐藏权限树ul
                         */
                        $("#loading").show();
                        $("#privilegeTree").hide();
						/**
						 * 设置全选按钮为不可用状态
						 */
						$("#allchecked").attr("disabled",true);
                        //显示权限树
                        role_privilege.opt.privilegeTreeOpt.loadPrivilegeTree();
                    });
                }
            });
            $("#allchecked").unbind("change");
            $("#allchecked").bind("change", function(){
                if ($(this).attr("checked")) {//被选中的时候
                    role_privilege.data.zTreePlugin.checkAllNodes(true);
                }
                else {//未被选中的时候
                    role_privilege.data.zTreePlugin.checkAllNodes(false);
                }
            });
			$("#savePrivilege").unbind("click");
			$("#savePrivilege").bind("click",function(){
				role_privilege.opt.privilegeTreeOpt.savePrivilege();
			});
        }
        
    }
};
$().ready(function(){
    role_privilege.init.initEvent();
});
