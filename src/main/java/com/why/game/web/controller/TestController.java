package com.why.game.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.why.game.service.LandService;
import com.why.game.service.UserService;


@Controller
@RequestMapping("/testController")
public class TestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private LandService landService;
	
	@RequestMapping(value="/test")
	public void test(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//response.setContentType("application/json;charset=UTF-8");
		//response.setHeader("access-control-allow-origin", "*");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("status", 2);
			result.put("id", "10001");
			result.put("name", "whg333");
			result.put("info", "测试消息！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", -1);
			result.put("error", e.getMessage());
		} finally {
			newJsonGenerator(response).writeObject(result);
		}
	}
	
	private static JsonGenerator newJsonGenerator(HttpServletResponse response) throws IOException{
		return new ObjectMapper().getJsonFactory().createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
	}
	
	@RequestMapping(value="/test2")
	//因为已经在ControllerInterceptor做拦截跨域访问，所以不需要@ModelAttribute和HttpServletResponse response参数了
	//@ModelAttribute 
	@ResponseBody
	public Map<String, Object> test2(/*HttpServletResponse response, */@RequestParam String userName, @RequestParam String userId){
		//System.out.println("userName="+userName);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 2);
		result.put("id", userId);
		result.put("name", userName);
		result.put("info", "测试消息！");
		return result;
	}
	
	@RequestMapping(value="/getUserInfo")
	@ResponseBody
	public Map<String, Object> getUserInfo(@RequestParam String requestInfoStr){
		//System.out.println("id="+id);
		return userService.getUserInfo(requestInfoStr);
	}
	
	@RequestMapping(value="/rename")
	@ResponseBody
	public Map<String, Object> rename(@RequestParam String userIdStr, @RequestParam String name){
		//System.out.println("id="+id+", name="+name);
		return userService.rename(userIdStr, name);
	}
	
	@RequestMapping(value="/test3")
	@ResponseBody
	public Map<String, Object> test3(@RequestParam String userIdStr){
		//System.out.println("id="+id);
		return landService.test(userIdStr);
	}
	
}
