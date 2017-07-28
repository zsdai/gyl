var GylUtils = {
    /**
     * 基础数据模块
     */
    basedata: {
        /**
         * 修改的
         * @param {Object} config
         */
        updateObj: {
            updateFunction: function(config){
                $.updateObj(config);
            }
        },
        /**
         * 基础数据模块的全选 不选和反选
         * @param {Object} config
         */
        chooseObj: {
            chooseFunction: function(config){
                $.chooseObj(config);
            }
        },
        /**
         * 基础数据模块查询页面删除功能的js代码实现
         * @param {Object} config
         */
        deleteObj: {
            /**
             * 当前页面的复选框被选中以后，点击删除按钮要做的事
             */
            deleteFunction: function(config){
                $.deleteObj(config);
            }
        },
        /**
         * 点击首页 上一页 下一页 尾页的共同链接方法
         * window.location.href传参的时候有"."会不灵，eg:condition.currentPage
         * 这样只有在DepartmentAction中对其重新命名
         */
        dispage: {
            linkSomePage: function(){
                var pageNum = $(this).attr("param");
                var url = $("body").data("url");
                window.location.href = url + "?currentPage=" + pageNum;
            }
        },
        initEvent: function(){
            /**
             * 给首页绑定点击事件
             */
            $("input[flag='firstPage']").unbind("click");
            $("input[flag='firstPage']").bind("click", function(){
                /**
                 * this代表首页按钮
                 * call代表传入的对象调用前面的方法
                 * eg:add.call(someObj) 表示someObj对象调用add方法
                 */
                GylUtils.basedata.dispage.linkSomePage.call(this);
            });
            /**
             * 给上一页绑定点击事件
             */
            $("input[flag='prePage']").unbind("click");
            $("input[flag='prePage']").bind("click", function(){
                if ($(this).attr("param") < 1) {
                    return false;
                }
                else {
                    GylUtils.basedata.dispage.linkSomePage.call(this);
                }
            });
            /**
             * 给下一页绑定点击事件
             */
            $("input[flag='nextPage']").unbind("click");
            $("input[flag='nextPage']").bind("click", function(){
                var lastPage = $(this).attr("last");
                if ($(this).attr("param") > lastPage) {
                    return false;
                }
                else {
                    GylUtils.basedata.dispage.linkSomePage.call(this);
                }
            });
            /**
             * 给尾页绑定点击事件
             */
            $("input[flag='lastPage']").unbind("click");
            $("input[flag='lastPage']").bind("click", function(){
                GylUtils.basedata.dispage.linkSomePage.call(this);
            });
            /**
             * 给跳转绑定点击事件
             */
            $("input[flag='jumpPage']").unbind("click");
            $("input[flag='jumpPage']").bind("click", function(){
                var lastPage = $(this).attr("last");
                var pageNo = $("#pageNo").val();
                if (pageNo > lastPage) {
                    pageNo = lastPage;
                }
                this.param = pageNo;//给this对象添加一个属性param		************
                GylUtils.basedata.dispage.linkSomePage.call(this);
            });
        }
    },
    /**
     * 权限模块
     */
    privilege: {},
    /**
     * 业务模块
     */
    business: {
        /**
         * 业务模块中的所有add操作
         */
        add: {
			calculateMoney:function(){
				//数量
				var sl = $(this).parent().siblings("td[item='sl']").children("input").attr("value");
				//税率
				var shulv = $(this).parent().siblings("td[item='shulv']").children("input").attr("value");
				//单品扣率
				var dpkl = $(this).parent().siblings("td[item='dpkl']").children("input").attr("value");
				//无税单价
				var wsdj = $(this).attr("value");
				//含税单价
				var hsdj = parseFloat(wsdj)*(1+parseFloat(shulv));
				$(this).parent().siblings("td[item='hsdj']").children("input").attr("value",hsdj);
				//无税金额
				var wsje = parseInt(sl)*parseFloat(wsdj);
				$(this).parent().siblings("td[item='wsje']").children("input").attr("value",wsje);
				//含税金额
				var hsje = parseFloat(hsdj)*parseInt(sl);
				$(this).parent().siblings("td[item='hsje']").children("input").attr("value",hsje);
				//税额
				var se = hsje-wsje;
				$(this).parent().siblings("td[item='se']").children("input").attr("value",se);
				//折扣额
				var zke = parseFloat(dpkl)*hsje;
				$(this).parent().siblings("td[item='zke']").children("input").attr("value",zke);
			},
			/**
			 * 给无税单价下面<td> 中的input添加失去焦点事件
			 * 当事件触发时，含税单价、含税金额、税额、折扣额自动计算
			 */
			wsdjBlurEvent:function(){
				$("td[item='wsdj']").delegate("input","blur",function(){
					GylUtils.business.add.calculateMoney.call(this);
				});
			},
            addRow: function(){
				/**
				 * 在添加新行之前，需要将行号的值+1，也就是trClone中td的
				 */
				var $trClone=GylUtils.business.add.divOpt.trClone;//得到被克隆的tr
				var $inputHH=$trClone.children("td[item='hh']").children("input");//得到行号的input框
				$inputHH.attr("value",parseInt($inputHH.attr("value"))+1);//将行号+1
				/**
				 * xsyddzhibs[0].hh-->xsyddzhibs[1].hh
				 */
				$trClone.children("td").each(function(){
					if($(this).attr("item")=="spmc"){
						/**
						 * 商品名称结构特殊
						 * td
						 *    div
						 *    	 input
						 */
						var oldName=$(this).children("div").children("input").attr("name");
						var preName=oldName.split("[")[0];
						var midName=oldName.split("[")[1].split("]")[0];
						var lastName=oldName.split("[")[1].split("]")[1];
						var newName=preName+"["+(parseInt(midName)+1)+"]"+lastName;
						$(this).children("div").children("input").attr("name",newName);
					}else{
						var oldName=$(this).children("input").attr("name");
						var preName=oldName.split("[")[0];
						var midName=oldName.split("[")[1].split("]")[0];
						var lastName=oldName.split("[")[1].split("]")[1];
						var newName=preName+"["+(parseInt(midName)+1)+"]"+lastName;
						$(this).children("input").attr("name",newName);
					}
				});
                $("#right_center table").append(GylUtils.business.add.divOpt.trClone);
            },
            /**
             * 给弹出的右键菜单添加click事件
             */
            RMenuClick: function(){
                $("#addRow").unbind("click");
                $("#addRow").bind("click", function(){
                    GylUtils.business.add.addRow();
                });
            },
            /**
             * 隐藏右键菜单
             */
            hideRMenu: function(){
                $("#menu").hover(function(){
                    GylUtils.business.add.showRMenu();
                }, function(){//移除出发的 事件
                    $("#menu").hide();
                });
            },
            /**
             * 显示右键菜单的方法
             */
            showRMenu: function(e){
                $("#menu").css("left", e.clientX - 50);
                $("#menu").css("top", e.clientY - 50);
                $("#menu").show();
            },
            /**
             *给行号添加右键事件
             */
            addHhContextMenu: function(){
                /**
                 * delegate方法可以为之后出现的控件添加事件，而unbind、bind不行
                 * @param {Object} e
                 */
                $("body").delegate("td[item='hh']", "contextmenu", function(e){
                    GylUtils.business.add.showRMenu(e);
                    return false;//关闭浏览器的默认右键菜单				
                });
            },
            /**
             * 关于div的操作
             */
            divOpt: {
                tr: '',
                trClone: '',
                showDiv: function(){
                    $("#seek").show();
                    $.fn.GridPanel.createTable({
                        url: 'productJSONAction_showProduct.action',
                        fields: $("#seek *[item]")//表示任意含有item的元素
                    });
                },
                /**
                 * 获取被选中的radio，radio所在的那行的值填充到主表行对应的列中
                 */
                fillValueToField: function(){
                    //所有radio-->被选中的
                    var checkedRadio = $(":radio:checked");//被选中的radio
                    var divTDS = checkedRadio.parent().siblings("td");//被选中的radio所在的所有TD的兄弟节点
                    var $tr = GylUtils.business.add.divOpt.tr;
                    $.each(divTDS, function(){
                        var itemValue = $(this).attr("item");//正在遍历的每一个TD的item属性的值
                        if (itemValue == 'spmc') {
                            $tr.children("td[item='" + itemValue + "']").children("div").children("input").attr("value", $(this).text());
                        }
                        else {
                            $tr.children("td[item='" + itemValue + "']").children("input").attr("value", $(this).text());
                        }
                    });
                    $("#seek").hide();
                }
            },
            divProductFrame: {
                /**
                 * 点击商品名称的查询弹出div框
                 */
                divProductFrameEvent: function(){
                    $(".searRR").unbind("click");
                    $(".searRR").bind("click", function(){
                        /**
                         * 在显示之前克隆一份,里面没有数据
                         */
                        GylUtils.business.add.divOpt.trClone = $(this).parent().parent().parent().clone(true);
                        GylUtils.business.add.divOpt.showDiv();
                        //在点击查询菜单的时候，确定主表的所在的tr
                        GylUtils.business.add.divOpt.tr = $(this).parent().parent().parent();
                    });
                },
                /**
                 * 点击确定按钮，将选中的商品信息动态添加到子表中
                 */
                divProductFrameSureEvent: function(){
                    $(".btn").unbind("click");
                    $(".btn").bind("click", function(){
                        GylUtils.business.add.divOpt.fillValueToField();
						/**
						 * 移除id为seek的div
						 */
						$("#seek").remove();
						return false;//防止提交表单
                    });
                }
            }
        },
        /**
         * 当点击主表中的某一行的逻辑处理
         */
        getZhibByZhubID: function(){
            var zhuid = $(this).attr("id");
            //获得被点击的主表id，并赋值给隐藏域的value值
            $("input[name='query_zhib.xsyddzhubid']").attr("value", zhuid);
            window.location.href = $("body").data("url") + zhuid;
            $(this).css("background", "blue");
        },
        /**
         * 给主表的每个tr添加点击事件
         */
        zhubTRClick: function(){
            $("tr[field='item_zhub']").unbind("click");
            $("tr[field='item_zhub']").bind("click", function(){
                GylUtils.business.getZhibByZhubID.call(this);
            });
        },
        /**
         * 关于分页的处理
         */
        disPage: {
            /**
             * 主表：
             * 点击上一页、下一页、首页、尾页要处理的事件
             */
            zhubLinkHref: function(){
                var currentPage = $(this).attr("param");
                $("#pageNo").attr("value", currentPage);//通关表单元素给query_zhub.currentPage赋值
                document.forms[0].submit();
            },
            /**
             * 关于主表的分页
             */
            zhubDispageEvent: function(){
                //首页
                $("input[flag='zhub_firstPage']").unbind("click");
                $("input[flag='zhub_firstPage']").bind("click", function(){
                    GylUtils.business.disPage.zhubLinkHref.call(this);
                });
                //上一页
                $("input[flag='zhub_prePage']").unbind("click");
                $("input[flag='zhub_prePage']").bind("click", function(){
                    if ($(this).attr("param") < 1) {
                        alert("已经是第一页了");
                    }
                    else {
                        GylUtils.business.disPage.zhubLinkHref.call(this);
                    }
                });
                //下一页
                $("input[flag='zhub_nextPage']").unbind("click");
                $("input[flag='zhub_nextPage']").bind("click", function(){
                    var lastPage = $("input[flag='zhub_lastPage']").attr("param");
                    if ($(this).attr("param") > lastPage) {
                        alert("已经是最后一页了");
                    }
                    else {
                        GylUtils.business.disPage.zhubLinkHref.call(this);
                    }
                });
                //尾页
                $("input[flag='zhub_lastPage']").unbind("click");
                $("input[flag='zhub_lastPage']").bind("click", function(){
                    GylUtils.business.disPage.zhubLinkHref.call(this);
                });
            },
            /**
             * 子表：
             * 点击上一页、下一页、首页、尾页要处理的事件
             */
            zhibLinkHref: function(){
                var currentPage = $(this).attr("param");
                $("#pageNo_zhib").attr("value", currentPage);//通关表单元素给query_zhub.currentPage赋值
                document.forms[0].submit();
            },
            /**
             * 关于子表的分页
             */
            zhibDispageEvent: function(){
                //首页
                $("input[flag='zhib_firstPage']").unbind("click");
                $("input[flag='zhib_firstPage']").bind("click", function(){
                    GylUtils.business.disPage.zhibLinkHref.call(this);
                });
                //上一页
                $("input[flag='zhib_prePage']").unbind("click");
                $("input[flag='zhib_prePage']").bind("click", function(){
                    if ($(this).attr("param") < 1) {
                        alert("已经是第一页了");
                    }
                    else {
                        GylUtils.business.disPage.zhibLinkHref.call(this);
                    }
                });
                //下一页
                $("input[flag='zhib_nextPage']").unbind("click");
                $("input[flag='zhib_nextPage']").bind("click", function(){
                    var lastPage = $("input[flag='zhib_lastPage']").attr("param");
                    if ($(this).attr("param") > lastPage) {
                        alert("已经是最后一页了");
                    }
                    else {
                        GylUtils.business.disPage.zhibLinkHref.call(this);
                    }
                });
                //尾页
                $("input[flag='zhib_lastPage']").unbind("click");
                $("input[flag='zhib_lastPage']").bind("click", function(){
                    GylUtils.business.disPage.zhibLinkHref.call(this);
                });
            }
        }
    }
};
