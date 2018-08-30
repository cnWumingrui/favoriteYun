package com.yc.web.listeners;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yc.bean.Tag;
import com.yc.biz.TagBiz;

public class InitListener implements ServletContextListener {
	
	public InitListener(){
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		//通过WebAppcationContextUtils 得到容器的实例
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
		//从spring容器中取tagBizImpl
		TagBiz tagBiz = (TagBiz) ac.getBean("tagBizImpl");
		Map<String ,Tag> map = new HashMap<String ,Tag>();
		try {
			map = tagBiz.findAllTag();
			application.setAttribute("ALLTAG", map); //存好所有的tag
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
