package cn.upshi.springquartz;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class TestMail {

	public static void main(String[] args) throws MessagingException {
		String str = "cmd /c C:/mysqldump.exe -hlocalhost -p3306 -uroot -paaaa --databases favarite > d:/backupfile.sql";
		//完成备份
		try {
			Runtime.getRuntime().exec(str);
			System.out.println("备份成功...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();  
        // 设定mail server  
        senderImpl.setHost("smtp.163.com");  
        // 建立邮件消息,发送简单邮件和html邮件的区别  
        MimeMessage mailMessage = senderImpl.createMimeMessage();  
        // 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，  
        // multipart模式 为true时发送附件 可以设置html格式  
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true, "utf-8");  
        // 设置收件人，寄件人  
        messageHelper.setTo("1069595532@qq.com");  
        //messageHelper.setTo("1016732082@qq.com");
        messageHelper.setFrom("cnKamisama@163.com");  
        messageHelper.setSubject("测试邮件！");  
        // true 表示启动HTML格式的邮件  
        messageHelper.setText(  
                "<html><head></head><body><h1>老师您好！我是YC43班伍明瑞</h1><br/>"
                + "<p>这是测试邮件</p>"
                + "</body></html>",  
                true);  
  
        FileSystemResource file = new FileSystemResource(new File("d:/backupfile.sql"));  
        // 这里的方法调用和插入图片是不同的。  
        messageHelper.addAttachment("backupfile.sql", file);  
        senderImpl.setUsername("cnKamisama@163.com"); // 根据自己的情况,设置username  
        senderImpl.setPassword("cnKamisama970410"); // 根据自己的情况, 设置password  
        Properties prop = new Properties();  
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
        prop.put("mail.smtp.timeout", "25000");  
        senderImpl.setJavaMailProperties(prop);  
        // 发送邮件  
        senderImpl.send(mailMessage);  
  
        System.out.println("邮件发送成功..");
	}

}
