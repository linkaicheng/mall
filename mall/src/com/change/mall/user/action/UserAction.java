package com.change.mall.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.change.mall.user.service.UserService;
import com.change.mall.user.vo.User;
import com.change.mall.utils.UUIDUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private static Logger logger = Logger.getLogger(UserAction.class);
	// 模型驱动使用的对象
	private User user = new User();
	private String rePassword;
	private String password;

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// 注入UserService
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getModel() {
		return user;
	}

	/**
	 * 跳转到注册页面
	 * 
	 * @return
	 */
	public String registPage() {
		return "registPage";
	}

	/**
	 * AJAX进行异步校验用户名的执行方法
	 * 
	 * @throws IOException
	 */
	public String findByName() throws IOException {
		// 调用Service进行查询:
		User existUser = userService.findByUsername(user.getUsername());

		// 获得response对象,项页面输出:
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// 判断
		if (existUser != null) {
			// 查询到该用户:用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		} else {
			// 没查询到该用户:用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	/**
	 * 用户注册的方法:
	 */
	public String regist() {
		// logger.error(user.getPassword() + " " + rePassword);
		user.setCode(UUIDUtils.getUUID() + UUIDUtils.getUUID());
		user.setState(0);
		userService.save(user);
		// // 判断验证码程序:
		// // 从session中获得验证码的随机值:
		// String checkcode1 = (String) ServletActionContext.getRequest()
		// .getSession().getAttribute("checkcode");
		// if(!checkcode.equalsIgnoreCase(checkcode1)){
		// this.addActionError("验证码输入错误!");
		// return "checkcodeFail";
		// }
		// userService.save(user);
		// this.addActionMessage("注册成功!请去邮箱激活!");
		// return "msg";
		return NONE;
	}

}
