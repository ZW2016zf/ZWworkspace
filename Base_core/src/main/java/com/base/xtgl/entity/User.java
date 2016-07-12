package com.base.xtgl.entity;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 
* 
* 类名称：User 
* 类描述：存储用户信息 
* 创建人：hhy 
* 创建时间：2011-12-20 上午10:51:28 
* 修改人：caozf 
* 修改时间：2012-07-04 上午13:51:28 
* 修改备注：  
* @version 
*
 */
public class User implements Serializable {
	private static final long serialVersionUID = -1244035756450161717L;
	//用户共用信息（学生、老师）
	private String yhm; //用户名
	private String xm;	//姓名
	private String yhlx; //用户类型：student（学生）-teacher（老师）
	private String sfqy;//是否启用
	private List<Role> allRoles; //用户拥有的角色
	
	
	/**
	 * 单角色登陆时：得到上次登陆角色名称
	 * @return
	 */
	public int getMaxJsmcLength(){
		int i=0;
		for(Role r:allRoles){
			if(i<r.getJsmc().length()){
				i=r.getJsmc().length();
			}
		}
		return i;
	}
	
	
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getYhlx() {
		return yhlx;
	}
	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}
	
	public List<Role> getAllRoles() {
		return allRoles;
	}
	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}


	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}


	public String getSfqy() {
		return sfqy;
	}
	
 
}

