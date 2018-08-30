package com.yc.biz.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.yc.biz.DataSourceBiz;

@Component
public class DataSourceBizImpl implements DataSourceBiz {

	@Override
	public String backUpSqlTable() {
		//TODO:读取配置文件 jdbc.properties文件，获取username，password，host
		//ResourceBundle rb = ResourceBundle.getBundle("jdbc");
		//TODO: 保存sql脚本的位置，利用反射中的classloder来获取位置
		String str = "cmd /c C:/mysqldump.exe -hlocalhost -p3306 -uroot -paaaa --databases favarite > d:/backupfile.sql";
		//完成备份
		String str1 = "";
		try {
			Runtime.getRuntime().exec(str);
			System.out.println("备份成功...");
			str1 = "success"; 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str1;
	}

}
