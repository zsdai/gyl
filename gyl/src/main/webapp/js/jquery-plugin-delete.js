/**
 * JQuery插件开发
 * 	1.局部
 * 	2.全局
 * @param {Object} jQuery
 */
(function(jQuery){
	/**
	 * 删除事件的插件
	 * 需要	1.删除按钮的id	2.复选框的name值
	 */
	$.deleteObj=function(config){
		$("#"+config.id).unbind("click");//删除按钮的id
		$("#"+config.id).bind("click",function(){
			if($("input[name='"+config.checkboxname+"']:checked").length==0){//复选框的name值
				alert("请选择后再删除！");
				return false;
			}else{
				if(window.confirm("您确定要删除吗？")){
					var checkedStr='';
					/**
					 * 变成1,2,3,4,  
					 */
					$("input[name='"+config.checkboxname+"']:checked").each(function(){
						checkedStr+=$(this).attr("value")+",";
					});
					checkedStr=checkedStr.substring(0,checkedStr.length-1);//去除最后面一个逗号
					window.location.href=config.url+"?checkedStr="+checkedStr;
				}
			}			
		});
	};
	/**
	 * 全选不选和反选
	 */
	$.chooseObj=function(config){
		$("#"+config.choose.all).unbind("click");
		$("#"+config.choose.all).bind("click",function(){
			$("input[name='"+config.checkboxname+"']").attr("checked",true);
			return;
		});
		$("#"+config.choose.none).unbind("click");
		$("#"+config.choose.none).bind("click",function(){
				$("input[name='"+config.checkboxname+"']").attr("checked",false);
		});
		$("#"+config.choose.reverse).unbind("click");
		$("#"+config.choose.reverse).bind("click",function(){
			$("input[name='"+config.checkboxname+"']").each(function(){
				 $(this).attr("checked", !$(this).attr("checked"));  
			});
		});
	};
})(jQuery);
