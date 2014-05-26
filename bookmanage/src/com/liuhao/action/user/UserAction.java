package com.liuhao.action.user;

import com.liuhao.bean.User;
import com.liuhao.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	private UserService userService;

	private User user;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String login() throws Exception {
		user = userService.findUser(user);
		if (user != null) {
			ActionContext.getContext().getSession().put("user", user);
			return "loginsuccess";
		} else {
			this.addFieldError("NameOrPasswordError", "用户名或密码错误，请重新输入！！！");
			return ERROR;
		}
	}

	public String register() throws Exception {
		userService.saveUser(user);
		ActionContext.getContext().getSession().put("user", user);
		return "registersuccess";
	}

	public String pupdate() throws Exception {
		user = userService.findUserById(user.getUserid());
		System.out.println(user.getGender());
		if (user != null) {
			return "pupdatesuccess";
		} else {
			this.addFieldError("userNotExsit", "要修改的用户不存在！！！");
			return ERROR;
		}
	}

	public String update() throws Exception {
		userService.updateUser(user);
		ActionContext.getContext().getSession().put("user", user);
		return "updatesuccess";
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

}
