package com.jenkins.ssm1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.jenkins.ssm1.config.Constants;
import com.jenkins.ssm1.config.ResultVO;
import com.jenkins.ssm1.domain.Menu;
import com.jenkins.ssm1.domain.Permission;
import com.jenkins.ssm1.domain.Role;
import com.jenkins.ssm1.domain.User;
import com.jenkins.ssm1.service.IUserService;
import com.jenkins.ssm1.util.ListSortUtils;
import com.jenkins.ssm1.util.MenuTreeUtils;
import com.jenkins.ssm1.util.Tools;

import io.swagger.annotations.ApiOperation;

@Controller
public class LoginController extends BaseController {
	@Autowired
	private IUserService userservice;

	private void getRemortIP(String username) {
		HttpServletRequest request = this.getRequest();
		Map<String, String> map = new HashMap<String, String>();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		map.put("username", username);
		map.put("loginIp", ip);
		userservice.saveIP(map);
	}

	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "跳转到登录页面", notes = "")
	public ModelAndView toLogin() throws ClassNotFoundException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}

	@RequestMapping(value = "/logincheck", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
//@ResponseBody
	// @LogController
	@ApiOperation(value = "登录验证接口", notes = "根据用户名、密码、验证码进行验证")
	public ResultVO loginCheck(HttpServletRequest request) throws AuthenticationException {
		String errInfo = "";// 错误信息
		String logindata[] = request.getParameter("LOGINDATA").split(",");
		if (logindata != null && logindata.length == 3) {
			// 获取Shiro管理的Session
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			String codeSession = (String) session.getAttribute(Constants.SESSION_SECURITY_CODE);
			String code = logindata[2];
			/** 检测页面验证码是否为空，调用工具类检测 **/
			if (Tools.isEmpty(code)) {
				errInfo = "nullcode";
			} else {
				String username = logindata[0];
				String password = logindata[1];
				if (Tools.isNotEmpty(codeSession)/* &&code.equalsIgnoreCase(codeSession) */) {
					// Shiro框架SHA加密
					String passwordsha = new SimpleHash("SHA-1", username, password).toString();
					log.info("SHA加密密码：{}", passwordsha);
					// System.out.println(passwordsha);
					// 检测用户名和密码是否正确
					User user = userservice.doLoginCheck(username, passwordsha);
					if (user != null) {
						if (Boolean.TRUE.equals(user.getLocked())) {
							errInfo = "locked";
						} else {
							// Shiro添加会话
							session.setAttribute("username", username);
							session.setAttribute(Constants.SESSION_USER, user);
							// 删除验证码Session
							// session.removeAttribute(Constants.SESSION_SECURITY_CODE);
							// 保存登录IP
							this.getRemortIP(username);
							/** Shiro加入身份验证 **/
							Subject sub = SecurityUtils.getSubject();
							UsernamePasswordToken token = new UsernamePasswordToken(username, password);
							sub.login(token);
						}
					} else {
						// 账号或者密码错误
						errInfo = "uerror";
					}
					if (Tools.isEmpty(errInfo)) {
						errInfo = "success";
					}
				} else {
					// 缺少参数
					errInfo = "codeerror";
				}
			}
		}
		return ResultVO.successful("result", null);
	}

	@RequestMapping(value = "/index")
	public ModelAndView toMain() {
		ModelAndView mv = new ModelAndView();
		/** 获取Shiro管理的Session **/
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		User user = (User) session.getAttribute(Constants.SESSION_USER);

		if (user != null) {
			Set<Role> roles = user.getRoles();
			Set<Permission> permissions = new HashSet<Permission>();
			for (Role r : roles) {
				permissions.addAll(r.getPermissions());
			}

			/** 获取用户可以查看的菜单 **/
			List<Menu> menuList = new ArrayList<Menu>();
			for (Permission p : permissions) {
				menuList.add(p.getMenu());
			}

			menuList = (List<Menu>) ListSortUtils.sortByDesc(menuList, "menuOrder");

			MenuTreeUtils treeUtil = new MenuTreeUtils();
			List<Menu> treemenus = treeUtil.menuList(menuList);

			String json = JSON.toJSONString(treemenus);

			// json = json.replaceAll("menuId","id").replaceAll("parentId","pId").
			// replaceAll("menuName","name").replaceAll("hasSubMenu","checked");

			mv.addObject("menus", json);

		} else {
			// 会话失效，返回登录界面
			mv.setViewName("admin/frame/login");
		}
		mv.setViewName("admin/frame/index");
		return mv;
	}
}
