package cn.upshi.springquartz;

import java.io.IOException;

public class Testdump {
	public static void main(String[] args){
		String str = "cmd /c C:/mysqldump.exe -hlocalhost -p3306 -uroot -paaaa --databases favarite > d:/backupfile.sql";
		//完成备份
		try {
			Runtime.getRuntime().exec(str);
			System.out.println("备份成功...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
