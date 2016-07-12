<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html class="no-js">
<head>
  <%@ include file="/commons/head.ini" %>
  <script type="text/javascript">
		$(function(){
			$("#save_btn").bind('click',function(e){
				if(!check()) return false;
				
				$.post("<%=request.getContextPath() %>/role/role_save.html",
		                $("#form_edit").serialize()  ,function(data){
								if(data.success){
									window.parent.location.href="<%=request.getContextPath() %>/role/role_selectAll.html";
						}
	            },"json");
			});
			$("#cancle_btn").bind('click',function(e){
				$('#search',window.parent.document).click();
			});
		});
		
		function check(){
			var name   = $("input[name='query.name']").val();
			var userid = $("input[name='query.userId']").val();
			if(name==null || name==''){
				dangerAlert("名称不能为空");
	            return false;
	        }
			if(userid==null || userid==''){
				dangerAlert("用户id不能为空");
	            return false;
	        }
			return true;
		}
  </script>
</head>

<body>


 <div class="admin-content" >
 <jsp:include page="/commons/alert.jsp" flush="true"></jsp:include>
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户基本信息</strong> / <small>Personal information</small></div>
    </div>

    <hr/>



      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-12">
        <form class="am-form am-form-horizontal" action="<%=request.getContextPath() %>/role/role_save.html" name="search" id="form_edit" method="post">
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">名称</label>
            <div class="am-u-sm-9">
              <input type="text"  placeholder="名称/name" name="query.name" value="${query.name }">
              
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-email" class="am-u-sm-3 am-form-label">日期 </label>
            <div class="am-u-sm-9">
              <input type="text" name="query.date" value="${query.date }" placeholder="日期/date">
              
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-phone" class="am-u-sm-3 am-form-label">用户id</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-phone" name="query.userId" value="${query.userId }" placeholder="用户id / Telephone">
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-QQ" class="am-u-sm-3 am-form-label">fmemo字段</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-QQ" name="query.memo" value="${query.memo }" placeholder="fmemo字段">
            </div>
          </div>


          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <button type="button" class="am-btn am-btn-primary" id="save_btn">保存修改</button>
              <button type="button" class="am-btn am-btn-primary" id="cancle_btn">关闭页面</button>
            </div>
          </div>
        </form>
    </div>
  </div>

</body>
</html>