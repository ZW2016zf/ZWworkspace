package com.firstLink.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.base.xtgl.dao.page.PageList;
import com.firstLink.model.Role;


public interface IRoleService {

	PageList<Role> getList(Role query);

	void save(Role query);

	void delete(Role query);

	void update(Role query);
	
	
}
