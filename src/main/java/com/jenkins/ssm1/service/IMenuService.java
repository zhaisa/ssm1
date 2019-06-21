package com.jenkins.ssm1.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jenkins.ssm1.dao.MenuRepository;
import com.jenkins.ssm1.domain.Menu;

public interface IMenuService {


	PageRequest buildPageRequest(int num, int size, Sort.Direction asc, String string);

	public Page<Menu> findAll(int pageNo, int pageSize, Sort.Direction dir, String str);



	public List<Menu> findAllParentMenu();



	public List<Menu> findSubMenuById(int id) ;

	public void editM(Menu m) ;

	public void saveM(Menu m) ;

	public Menu findMenuById(int menuId);

}
