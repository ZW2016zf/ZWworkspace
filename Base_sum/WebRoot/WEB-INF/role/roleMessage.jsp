<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html class="no-js">
<head>
  <%@ include file="/commons/head.ini" %>
</head>

<body id="blog-article-sidebar">


<div class="am-cf am-padding" >
    <div class="am-fl am-cf"  ><strong class="am-text-primary am-text-lg">表格</strong> / <small>Table</small></div>
</div>

<jsp:include page="/commons/modal.jsp" flush="true"></jsp:include>
<form class="am-form" action="<%=request.getContextPath() %>/role/role_selectAll.html" method="post" name="search" id="form">
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6">
        <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
                <button type="button" class="am-btn am-btn-default" id="to_add"><span class="am-icon-plus"></span> 新增</button>
                <%--<button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 保存</button>--%>
                <button type="button" class="am-btn am-btn-default" id="to_modify"><span class="am-icon-archive"></span> 编辑</button>
                <button type="button" class="am-btn am-btn-default" id="to_delete"><span class="am-icon-trash-o"></span> 删除</button>
            </div>
        </div>
    </div>
    <div class="am-u-sm-12 am-u-md-3">
        <div class="am-form-group">
            <select data-am-selected="{btnSize: 'sm'}">
                <option value="option1">所有类别</option>
                <option value="option2">IT业界</option>
                <option value="option3">数码产品</option>
                <option value="option3">笔记本电脑</option>
                <option value="option3">平板电脑</option>
                <option value="option3">只能手机</option>
                <option value="option3">超极本</option>
            </select>
        </div>
    </div>
    <div class="am-u-sm-12 am-u-md-3">
        <div class="am-input-group am-input-group-sm">
       		
            <input type="text" class="am-form-field" placeholder="根据名称搜索" name="query.name" value="${query.name }">
          <span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="submit" id="search" name="search">搜索</button>
          </span>
        </div>
    </div>
</div>

<div class="am-g">
    <div class="am-u-sm-12">
        
            <table class="am-table am-table-striped am-table-hover table-main">
                <thead id="list_head">
                <tr>
                    <th class="table-check"><input type="checkbox" /></th>
                    <th class="table-id">ID</th>
                    <th class="table-title">名称</th>
                    <th class="table-type">日期</th>
                    <th class="table-author">用户id</th>
                    <th class="table-date">fmemo字段</th>
                    <th class="table-set">操作</th>
                </tr>
                </thead>
                <tbody id="list_body" >
                <c:forEach items="${roleList}" var="item"> 
                <tr>
                    <td><input id="id"  name="id" type="checkbox" value="${item.id }"></td>
                    <td>${item.id }</td>
                    <td>${item.name }</td>
                    <td>${item.date }</td>
                    <td>${item.userId }</td>
                    <td>${item.memo }</td>
                    <td>
                        <div class="am-btn-toolbar">
                            <div class="am-btn-group am-btn-group-xs">
                                <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
                                <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
                                <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
                            </div>
                        </div>
                    </td>
                </tr>
                </c:forEach> 
                
                
                
                
                

                </tbody>
            </table>
            <div class="am-cf">
                <ct:page pageList="${roleList }" query="${query }" />
            </div>
            <hr />
            <p>注：.....</p>
        
    </div>	
</form>
 <script type="text/javascript">
		$(function(){
			
			$('#list_body tr').find('input').bind('click',function(e){
				e.stopPropagation();
 			});   	     
		    $('#list_body tr').bind('click',function(e){
				 var status = $(this).find('input[name="id"]').prop('checked');
				 if (status) {
					$(this).find('input[name="id"]').removeAttr('checked');
				 } else {
					$(this).find('input[name="id"]').prop('checked',true);
				 }
				 
			});
			fillRows("10", "", "", false);//填充空行
			$("#to_add").bind('click',function(e){
				setIfram("40%","50%","500","800","<%=request.getContextPath() %>/role/role_toadd.html");
	            $('#my-modal').modal();
	        });
			
			$("#to_delete").bind('click',function(e){
				if($("input[id='id']:checked").length > 1){
						errorAlert("不能同时选中多行！");
	                    return false;
                }
				var id = $("input[id='id']:checked").val();
				$.post("<%=request.getContextPath() %>/role/role_delete.html",
		                "query.id=" + id  ,function(data){
								if(data.success){
									window.location.href="<%=request.getContextPath() %>/role/role_selectAll.html";
								}
		                            
	            },"json");
			});
			
			$("#to_modify").bind('click',function(e){
				if($("input[id='id']:checked").length > 1){
						errorAlert("不能同时选中多行！");
	                    return false;
                }
				var id = $("input[id='id']:checked").val();
				setIfram("40%","50%","500","800","<%=request.getContextPath() %>/role/role_tomodeify.html?query.id="+id);
	            $('#my-modal').modal();
				
			});
			
		});
  </script>
</body>
</html>