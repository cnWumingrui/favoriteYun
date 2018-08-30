package com.yc.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.bean.Favorite;
import com.yc.bean.JsonModel;
import com.yc.bean.Tag;
import com.yc.biz.FavoriteBiz;

@Controller
@Scope(value="prototype")
public class FavoriteController {
	
	@Resource(name="favoriteBizImpl")
	private FavoriteBiz favoriteBiz;
	//favoriteBizImpl
	@RequestMapping("favorite_findAll.action")
	public String findAll(@Param(value="tname") String tname,HttpServletRequest request,HttpSession session){
		JsonModel jsonModel = new JsonModel();
		
		Favorite f = new Favorite();
		f.setTname(tname);
		List<Favorite> list = favoriteBiz.findFavoriteByTagName(f);
		
		jsonModel.setCode(1);
		jsonModel.setObj(list);;
		
		session.setAttribute("jsonModel", jsonModel);
		return "result";
	}
	
	@RequestMapping("favorite_doAdd.action")
	public String doAdd( Favorite favorite, HttpServletRequest request, HttpSession session ) throws Exception {
		JsonModel jsonModel = new JsonModel();
		
		ServletContext application = request.getServletContext();
		Map<String, Tag> map = new HashMap<String, Tag>();
		if (application.getAttribute("ALLTAG") != null) {
			map = (Map<String, Tag>) application.getAttribute("ALLTAG");
		}
		
		Favorite f=favoriteBiz.addFavorite(favorite, map);
		
		jsonModel.setCode(1);
		jsonModel.setObj( f );
		
		session.setAttribute("jsonModel", jsonModel);
		return "result";
	}
	
	@RequestMapping("favorite_toAdd.action")
	public String toAdd( ) {
		return "toAdd";
	}

}
