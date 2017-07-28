/**
 * 该js的任务是动态的填充一个表格
 */
(function($){
	$.fn.GridPanel={
		/**
		 * 默认的参数配置
		 */
		defaultConfig:{
			url:'',
			data:'',
			fields:''//任意含有item的元素,可以获得表格中有多少列
		},
		/**
		 * 创建表格
		 */
		createTable:function(config){
			/**
			 * 要点：extend方法
			 * 把config中的内容覆盖到$.fn.GridPanel.defaultConfig 
			 */
			$.extend($.fn.GridPanel.defaultConfig,config);
			$.post($.fn.GridPanel.defaultConfig.url,null,function(data){
				/**
				 * data就是服务器传递到前台的数据
				 * data:[
				 * 	{dw:'',pid:'',shulv:'',spbm:''spmc:'',xh:''},
				 * 	{dw:'',pid:'',shulv:'',spbm:''spmc:'',xh:''},
				 * 	{dw:'',pid:'',shulv:'',spbm:''spmc:'',xh:''}
				 * ]
				 */
				$.fn.GridPanel.createTR(data);
			});
		},
		/**
		 * 创建tr
		 */
		createTR:function(data){
			$.each(data,function(){//循环行的tr
				var row=this;//this代表一条记录{dw:'',pid:'',shulv:'',spbm:''spmc:'',xh:''}
				var $tr=$("<tr/>");
				var fields=$.fn.GridPanel.defaultConfig.fields;
				$.each(fields,function(){//循环列的
					var $td=$.fn.GridPanel.createTD(this,row);//this为fields的每一个元素，每一列 如：<td width="500" item="spmc">商品名称</td>
					$tr.append($td);
				});
				//将tr添加到table下面
				$("#seek table").append($tr);
			});
		},
		/**
		 * 创建td
		 * field=<td width="500" item="spmc">商品名称</td>
		 */
		createTD:function(field,row){
			/**
			 * row:{dw:'',pid:'',shulv:'',spbm:''spmc:'',xh:''}
			 * $(field).attr('item')=spmc
			 * row[$(field).attr('item')]=row['spmc']
			 */
			if($(field).attr('item')=="radio"){
				var $raido=$("<input/>").attr("type","radio");
				return $("<td/>").attr("item",$(field).attr("item")).append($raido);
			}else{
				return $("<td/>").attr("item",$(field).attr("item")).text(row[$(field).attr('item')]);
			}
		}
	};
})($);
