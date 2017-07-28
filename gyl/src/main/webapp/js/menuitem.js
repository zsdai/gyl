var menuitem = {
    setting: {
        isSimpleData: true,
        treeNodeKey: "mid",
        treeNodeParentKey: "pid",
        showLine: true,
        root: {
            isRoot: true,
            nodes: []
        }
    },
    loadMenuItemTree: function(){
    $.post("menuitemAction_showMenuitemTree",null,function(data){
		$("#tree").zTree(menuitem.setting,data);
	},"json");
    }
};
$().ready(function(){
	menuitem.loadMenuItemTree();
});
