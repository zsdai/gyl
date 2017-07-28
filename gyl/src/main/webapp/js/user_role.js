var user_role = {
    /**
     * 数据部分
     */
    data: {
        user: {
            uid: '',
            username: ''
        },
        zTreePlugin: ''
    },
    /**
     * 操作部分
     */
    opt: {
        divOpt: {
            showHideDiv: function(){
                $("div:hidden").show();
            }
        },
        userOpt: {
            showUserName: function(){
                $("#userImage").text("用户:" + user_role.data.user.username);
            }
        },
        roleTreeOpt: {
            setting: {
                isSimpleData: true,
                treeNodeKey: "rid",
                treeNodeParentKey: "pid",
                showLine: true,
                checkable: true,
                root: {
                    isRoot: true,
                    nodes: []
                }
            },
            loadRoleTree: function(){
                $.post("roleAction_showUserRoles.action", {
                    uid: user_role.data.user.uid
                }, function(data){
                    user_role.data.zTreePlugin = $("#roleTree").zTree(user_role.opt.roleTreeOpt.setting, data);
                    /**
                     * 权限树加载完之后，隐藏loading图标
                     * 判断是否全选了，如果是，则将全选框的值设置为勾选
                     * 显示权限树,并设置全选框的状态为显示
                     */
                    $("#loading").hide();
                    user_role.opt.roleTreeOpt.isAllCheckedOnRoleTree();
                    $("#roleTree").show();
                    $("#allchecked").attr("disabled", false);
                });
            },
            /**
             * 判断权限树上的权限是否全部被选中
             */
            isAllCheckedOnRoleTree: function(){
                if (user_role.data.zTreePlugin.getCheckedNodes(false).length == 0) {//如果被全部选中了
                    $("#allchecked").attr("checked", true);
                }
                else {
                    $("#allchecked").attr("checked", false);
                }
            },
            checkAllOrNot: function(){
                $("#allchecked").unbind("change");
                $("#allchecked").bind("change", function(){
                    if ($(this).attr("checked")) {//被选中的时候
                        user_role.data.zTreePlugin.checkAllNodes(true);
                    }
                    else {//未被选中的时候
                        user_role.data.zTreePlugin.checkAllNodes(false);
                    }
                });
            },
            saveRole: function(){
                $("#saveRole").unbind("click");
                $("#saveRole").bind("click", function(){
                    var checkedNodes = user_role.data.zTreePlugin.getCheckedNodes(true);
                    var checkedStr = "";
                    $(checkedNodes).each(function(){
                        checkedStr += this.rid + ",";
                    });
                    checkedStr = checkedStr.substring(0, checkedStr.length - 1);
                    $.post("roleAction_updateUserRoles.action", {
                        uid: user_role.data.user.uid,
                        checkedStr: checkedStr
                    }, function(){
                        alert("保存成功！");
                    });
                });
            }
        }
    },
    /**
     * 初始化部分
     */
    init: {
        initData: function(){
            var username = $(this).parent().siblings("td:first").text();
            var uid = $(this).parent().siblings("input[type='hidden']").attr("value");
            user_role.data.user.username = username;
            user_role.data.user.uid = uid;
        },
        initEvent: function(){
            $("a").each(function(){
                if ($(this).text() == "设置角色") {
                    $(this).unbind("click");
                    $(this).bind("click", function(){
                        //显示隐藏的div
                        user_role.opt.divOpt.showHideDiv();
                        //给user_role.data.user赋值
                        user_role.init.initData.call(this);
                        //动态显示用户名称
                        user_role.opt.userOpt.showUserName();
                        /**
                         * 加载权限树前，显示loading图标，隐藏权限树ul
                         */
                        $("#loading").show();
                        $("#roleTree").hide();
                        /**
                         * 设置全选按钮为不可用状态
                         */
                        $("#allchecked").attr("disabled", true);
                        //根据uid查询出用户所具有的角色
                        user_role.opt.roleTreeOpt.loadRoleTree();
                        //全选或不选
						user_role.opt.roleTreeOpt.checkAllOrNot();
                        //保存用户角色方法
                        user_role.opt.roleTreeOpt.saveRole();
                    });
                }
            });
        }
    }
};
$().ready(function(){
    user_role.init.initEvent();
});
