package com.base.xtgl.dao.page;

import com.base.xtgl.util.StringUtil;



/** 
 * 分页内容生成(BaseQuery)
 * @author jinjj
 * @date 2012-11-8 上午09:10:34 
 *  
 */
public class PageHtml {

	private Paginator paginator;
	private String function;
	private String queryName;
	
	public PageHtml(Paginator paginator,String function,String queryName){
		this.paginator = paginator;
		this.function = function;
		this.queryName = queryName;
	}
	
	public String regenerator(){
		StringBuilder sb = new StringBuilder();
		sb.append("<div class='am-fl'><ul class='am-pagination'><li>每页显示    10 条记录</li></ul></div>")
		.append("<div class='am-fr'><ul class='am-pagination'>")
		.append("<li><a id='first' class='first' title='首页' href='#' alt='1'" + initPageAnchor() + " >«</a></li>")
		.append("<li>……&nbsp;<input id='toPage' value='"+paginator.getPage()+"' name='"+getPrefix()+"toPage' type='hidden'>" +
				"<input id='pageSize' value='" +paginator.getItemsPerPage()+"'name='"+getPrefix()+"perPageSize' type='hidden'></li>")
		.append("<li><a id='pre'  title='上一页' href='#' alt='"+paginator.getPreviousPage()+"' "+initPageAnchor()+"><</a></li>")
		.append("<li class='am-active'><a href='#'>" + paginator.getPage() + "</a></li>")
		.append("<li><a id='next'  title='下一页' href='#' alt='"+paginator.getNextPage()+"' "+initPageAnchor()+">></a></li>")
		.append("<li>……&nbsp;</li>")
		.append("<li><a id='last' class='first' title='末页' href='#' alt='"+paginator.getLastPage()+"'" + initPageAnchor() + " >»</a></li>")
		.append("</ul></div>");
		return sb.toString();
		
	}
	
	public String generator(){
		StringBuilder sb = new StringBuilder();
		sb.append("<div class='pagination'>");
		sb.append(pageLeft());
		sb.append(pageRight());
		sb.append("</div>");
		return sb.toString();
	}
	
	private String pageLeft(){
		StringBuilder sb = new StringBuilder();
		sb.append("<div class='am-fr' style='padding:4px 6px;'>");
		sb.append("<p class='pagenum'>");
		sb.append("第<input id='toPage' type='text' name='"+getPrefix()+"toPage' style='color:red; text-align:center;width:50px;' value='"+paginator.getPage()+"' size='2' "
				+initPageInput()+" title='页码,可修改,回车跳转'>页 / 共");
        sb.append("<span class='red' id='totalPage'>"+paginator.getPages()+"</span>页， 每页显示");
        sb.append("<input type='text' id='pageSize' name='"+getPrefix()+"perPageSize' style='width:50px;' value='"
        		+paginator.getItemsPerPage()+"' size='2' maxlength='2' "+initPageInput()+" title='每页大小,可修改,回车生效'>");
        sb.append("<input type='hidden' id='defalutPageSize' value='"+paginator.getItemsPerPage()+"'/>");
        sb.append("条 /  共<span class='red'>"+paginator.getItems()+"</span>条记录 ");
    	sb.append("</p>");
        sb.append("</div>");
        return sb.toString();
	}
	
	/**
	 * 输入框事件
	 * @return
	 */
	private String initPageInput(){
		StringBuilder sb = new StringBuilder();
		sb.append(" onmouseover=\"initPageInput(this");
		if(!StringUtil.isEmpty(function)){
			sb.append(","+function);
		}
		sb.append(")\"");
		return sb.toString();
	}
	
	private String pageRight(){
		StringBuilder sb = new StringBuilder();
		sb.append("<div class='pageright'>");
		sb.append("<div id='pagediv' class='paging'>");
		sb.append(firstPage());
		sb.append(prePage());
		sb.append(nextPage());
		sb.append(endPage());
		sb.append("</div>");
		sb.append("</div>");
		return sb.toString();
	}
	
	private String firstPage(){
		StringBuilder sb = new StringBuilder();
		if(paginator.getPage()>1&&paginator.getPages()>0){
			sb.append("<a id='first' class='first' title='首页' href='#' alt='1' "+initPageAnchor()+">首&#12288;页</a> ");
		}else{
			sb.append("<span class='text'>首&#12288;页</span> ");
		}
		return sb.toString();
	}
	
	private String nextPage(){
		StringBuilder sb = new StringBuilder();
		if(paginator.getPages()>paginator.getPage()){
			sb.append("<a id='next' class='next' title='下一页' href='#' alt='"+paginator.getNextPage()+"' "+initPageAnchor()+">下一页</a> ");
		}else{
			sb.append("<span class='text'>下一页</span> ");
		}
		return sb.toString();
	}
	
	private String prePage(){
		StringBuilder sb = new StringBuilder();
		if(paginator.getPage()>1){
			sb.append("<a id='pre' class='prev' title='上一页' href='#' alt='"+paginator.getPreviousPage()+"' "+initPageAnchor()+">上一页</a> ");
		}else{
			sb.append("<span class='text'>上一页</span> ");
		}
		return sb.toString();
	}
	
	private String endPage(){
		StringBuilder sb = new StringBuilder();
		if(paginator.getPages()>paginator.getPage()){
			sb.append("<a id='last' class='last' title='末页' href='#' alt='"+paginator.getLastPage()+"' "+initPageAnchor()+">末&#12288;页</a> ");
		}else{
			sb.append("<span class='text'>末&#12288;页</span> ");
		}
		return sb.toString();
	}
	
	private String initPageAnchor(){
		StringBuilder sb = new StringBuilder();
		sb.append(" onmouseover=\"initPageAnchor(this");
		if(!StringUtil.isEmpty(function)){
			sb.append(","+function);
		}
		sb.append(")\"");
		return sb.toString();
	}
	
	private String getPrefix(){
		return queryName+".";
	}
}
