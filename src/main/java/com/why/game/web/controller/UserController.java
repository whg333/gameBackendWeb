package com.why.game.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.why.game.service.LandService;
import com.why.game.service.UserService;

@Controller
@RequestMapping("/userController")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LandService landService;
	
	@RequestMapping(value="/getUserInfo")
	@ResponseBody
	public Map<String, Object> getUserInfo(@RequestParam String requestInfoStr){
		return userService.getUserInfo(requestInfoStr);
	}
	
	@RequestMapping(value="/rename")
	@ResponseBody
	public Map<String, Object> rename(@RequestParam String userIdStr, @RequestParam String name){
		return userService.rename(userIdStr, name);
	}

}
