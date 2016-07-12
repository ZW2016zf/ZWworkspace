package com.firstLink.model;

import com.base.xtg.dao.query.BaseQuery;

public class Role extends BaseQuery{
    /**
	 * 
	 */
	private static final long serialVersionUID = 168148605371820079L;
	private String id;
    private String name;
    private String date;
    private String userId;
    private String memo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
    
    

    
    
}
