package com.yc.S3Lwcblog.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yc.S3Lwcblog.bean.Result;
import com.yc.S3Lwcblog.bean.User;
import com.yc.S3Lwcblog.biz.BizException;
import com.yc.S3Lwcblog.biz.UserBiz;

@RestController // json
public class UserAction {

	@Resource
	private UserBiz ub;

	@RequestMapping("login") // ==> PostMapping
	public Result login(@Valid User user, Errors errors, HttpSession session) {
		// 用户输入验证
		if (errors.hasFieldErrors("account") || errors.hasFieldErrors("pwd")) {
			return new Result(0, "字段验证错误", errors.getAllErrors());
		}

		// 业务逻辑验证
		try {
			User dbuser = ub.login(user);
			session.setAttribute("loginedUser", dbuser);
			return new Result(1, "登录成功！");
		} catch (BizException e) {
			e.printStackTrace();
			errors.rejectValue("account", "AccountOrPwdFailure", e.getMessage());
			return new Result(0, "业务逻辑验证错误", errors.getAllErrors());
		}
	}

	/**
	 * 
	 * @param user
	 * @param errors
	 * @return 要在被RestController标注的控制器里面跳转页面
	 * 			必须返回 ModelAndView 类型的值
	 * 
	 * 	register.html ==> 成功 index.html
	 * 				  ==> 失败 register.html	
	 */
	@RequestMapping("register")
	public ModelAndView register(@Valid User user, Errors errors, ModelAndView mav) {
		// 设置默认（成功）跳转的页面
		// 页面跳转行为： 1. 请求转发（默认）， 2 响应重定向
		// redirect:index 响应重定向跳转，页面的地址栏会变化
		mav.setViewName("redirect:index");

		if (errors.hasErrors()) {
		mav.setViewName("register");
		}else {
			try {
				ub.register(user);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				errors.rejectValue("account","AccountFail",e.getMessage());
				mav.setViewName("register");
			}
		}
		
		mav.addObject("user",user);
		mav.addObject("errors",errors.getFieldErrors());
		return mav;
		

	}
	
	private Object getFieldErrors() {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping("toreg")
	public ModelAndView toregister(ModelAndView mav) {
		mav.setViewName("register");
		return mav;
	}

}