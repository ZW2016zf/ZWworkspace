package com.firstLink.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.xtgl.dao.page.PageList;
import com.base.xtgl.dao.page.Paginator;
import com.firstLink.dao.IRoleDao;
import com.firstLink.model.Role;
import com.firstLink.service.IRoleService;

public class RoleServiceImpl implements IRoleService {
	IRoleDao loginDao;
	public PageList<Role> getList(Role query) {
		PageList<Role> pageList = new PageList<Role>();
		Paginator paginator = new Paginator();
		if(query!=null){
			paginator.setItemsPerPage(query.getPerPageSize());
			paginator.setPage((Integer)query.getToPage());
			paginator.setItems(loginDao.getListCount(query));
			pageList.setPaginator(paginator);
			
			if(paginator.getBeginIndex() <= paginator.getItems()){
				query.setStartRow(paginator.getBeginIndex());
				query.setEndRow(paginator.getEndIndex());
				List<Role> list = loginDao.getList(query);
				pageList.addAll(list);
			}
		}
		return pageList;
	}
	public IRoleDao getLoginDao() {
		return loginDao;
	}
	public void setLoginDao(IRoleDao loginDao) {
		this.loginDao = loginDao;
	}
	public void save(Role query) {
		loginDao.save(query);
	}
	public void delete(Role query) {
		loginDao.delete(query);
	}
	public void update(Role query) {
		loginDao.update(query);
	}
	 
    
}
