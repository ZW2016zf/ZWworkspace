package com.firstLink.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.base.xtgl.action.HrmAction;
import com.base.xtgl.dao.page.PageList;
import com.base.xtgl.util.StringUtil;
import com.firstLink.model.Role;
import com.firstLink.service.IRoleService;


public class RoleAction extends HrmAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7223984943988512662L;

	IRoleService roleService ;
	
	public Role query = new Role();
	
	private String op = "add";
	
	public String index(){
		return "index";
	}
	
	public String login(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(StringUtil.isEmpty(username)){
			HashMap map=new HashMap();
         	map.put("success", false);
         	map.put("message", "用户名不能为空");
			this.getValueStack().set(DATA, map);
			return DATA;
		}
		if(StringUtil.isEmpty(password)){
			HashMap map=new HashMap();
         	map.put("success", false);
         	map.put("message", "密码不能为空");
         	this.getValueStack().set(DATA, map);
			return DATA;
		}
		if(!username.equals("zhangxu") || !password.equals("123")){
			HashMap map=new HashMap();
         	map.put("success", false);
         	map.put("message", "用户名或密码不正确！");
         	this.getValueStack().set(DATA, map);
			return DATA;
		}
		HashMap map=new HashMap();
     	map.put("success", true);
     	//map.put("message", "登陆成功！");
		this.getValueStack().set(DATA, map);
		return DATA;
	}

    
    public String selectAll(){
    	
    	PageList<Role> roleList=roleService.getList(query);
        getValueStack().set("roleList", roleList);
        return "success";
    }
    
    public String save(){
    	if(StringUtil.isEmpty(query.getId())){
    		roleService.save(query);
    	}else{
    		roleService.update(query);
    	}
    	HashMap map=new HashMap();
     	map.put("success", true);
     	this.getValueStack().set(DATA, map);
     	return DATA;
    }

    public String tomodeify(){
    	query = roleService.getList(query).get(0);
    	op = "tomodeify";
    	return "add";
    }
    
    public String toadd(){
    	op = "add";
    	return "add";
    }
    
    public String delete(){
    	roleService.delete(query);
    	HashMap map=new HashMap();
     	map.put("success", true);
     	this.getValueStack().set(DATA, map);
     	return DATA;
    }



    public IRoleService getRoleService() {
		return roleService;
	}





	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public void setQuery(Role query) {
		this.query = query;
	}

	public Role getQuery() {
		return query;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getOp() {
		return op;
	}





}
