package com.firstLink.dao;

import java.util.List;

import com.base.xtgl.dao.page.PageList;
import com.firstLink.model.Role;

public interface IRoleDao {

	public int getListCount(Role query);
	
	public List<Role> getList(Role query);

	public void save(Role query);

	public void delete(Role query);

	public void update(Role query);
}
