package com.yc.web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.bean.JsonModel;
import com.yc.bean.Tag;

@Controller
@Scope(value="prototype")
public class TagController {
	
	@RequestMapping("tag_findAll.action")
	public String findAll(HttpServletRequest request,HttpSession session){
		JsonModel jsonModel = new JsonModel();
		//从application中取出所有的tag即可
		ServletContext application = request.getServletContext();
		Map<String, Tag> map = new HashMap<String ,Tag>();
		if(application.getAttribute("ALLTAG") != null){
			map = (Map<String, Tag>) application.getAttribute("ALLTAG");
		}
		jsonModel.setCode(1);
		jsonModel.setObj(map.values());
		
		session.setAttribute("jsonModel", jsonModel);
		return "result";
		
	}
}
