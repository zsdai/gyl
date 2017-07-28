var privilege = {
    setting: {
        isSimpleData: true,
        treeNodeKey: "id",
        treeNodeParentKey: "pid",
        root: {
            isRoot: true,
            nodes: []
        }
    },
    loadPrivilegeTree: function(){
    $.post("privilegeAction_showPrivileMenuTree.action",null,function(data){
		$("#tree").zTree(privilege.setting,data);
	},"json");
    }
};
$().ready(function(){
	privilege.loadPrivilegeTree();
});
