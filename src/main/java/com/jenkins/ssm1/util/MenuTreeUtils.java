package com.jenkins.ssm1.util;


import java.util.ArrayList;
import java.util.List;

import com.jenkins.ssm1.domain.Menu;

public class MenuTreeUtils {

    public List<Menu> menuCommon;
    public List<Menu> list = new ArrayList<Menu>();
      
    public List<Menu> menuList(List<Menu> menus){
        this.menuCommon = menus;
        for (Menu x : menus) {
            Menu m = new Menu();
            if(x.getParentId()==0){
                m.setParentId(x.getParentId());
                m.setMenuId(x.getMenuId());
                m.setMenuName(x.getMenuName());
                m.setMenuIcon(x.getMenuIcon());
                m.setMenuUrl(x.getMenuUrl());
                m.setSubMenu(menuChild(x.getMenuId()));
                list.add(m);
            }  
        }     
        return list;  
    }  
   
    public List<Menu> menuChild(int id){
        List<Menu> lists = new ArrayList<Menu>();
        for(Menu a:menuCommon){
            Menu m = new Menu();
            if(a.getParentId() == id){
                m.setParentId(a.getParentId());
                m.setMenuId(a.getMenuId());
                m.setMenuName(a.getMenuName());
                m.setMenuIcon(a.getMenuIcon());
                m.setMenuUrl(a.getMenuUrl());
                lists.add(m);
            }  
        }  
        return lists; 
    }  

}