package com.base.xtgl.entity;

import java.io.Serializable;


/**
 * 角色
 * 
 * @author gonghui
 * 2013-8-28
 */
public class Role implements Serializable{
	
	private static final long serialVersionUID = -2044533545247893221L;

	private String jsdm;//角色代码
	
	private String jsmc;//角色名称
	
	private String jssm;//角色说明
	
	private String jscjr;//角色创建人

	public String getJsdm() {
		return jsdm;
	}

	public void setJsdm(String jsdm) {
		this.jsdm = jsdm;
	}

	public String getJsmc() {
		return jsmc;
	}

	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}

	public String getJssm() {
		return jssm;
	}

	public void setJssm(String jssm) {
		this.jssm = jssm;
	}

	public String getJscjr() {
		return jscjr;
	}

	public void setJscjr(String jscjr) {
		this.jscjr = jscjr;
	}
	
	
}
