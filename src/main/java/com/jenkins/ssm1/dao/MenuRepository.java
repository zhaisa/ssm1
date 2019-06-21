package com.jenkins.ssm1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.jenkins.ssm1.domain.Menu;

public interface MenuRepository extends PagingAndSortingRepository<Menu, Integer> {

	/**
	 * 获取所有的上级菜单，并按菜单序号排序
	 * @return
	 */
	@Query(value = "select m from Menu m where m.parentId=0 order by m.menuOrder asc")
	public List<Menu> findAllParentMenu();
	
	/**
	 * 根据上级菜单Id获取二级菜单，并按菜单序号排序
	 * @param id
	 * @return
	 */
	@Query(value = "select m from Menu m where m.parentId=:id order by m.menuOrder asc")
	public List<Menu> findSubMenuByParentId(@Param("id") int id);

	/**
	 * 通过菜单Id获取菜单信息
	 * @return
	 */
	@Query(value = "select m from Menu m where m.menuId=:id")
	public Menu findMenuByMenuId(@Param("id") int id);


}
