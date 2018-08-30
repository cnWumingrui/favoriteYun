package com.yc.test.biz;

import java.util.Map;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.bean.Favorite;
import com.yc.biz.FavoriteBiz;
import com.yc.biz.TagBiz;
import com.yc.biz.impl.FavoriteBizImpl;


//junit启动时，就会自动地: ApplicationContext ac=new
//ClassPathXmlApplicationContext("classpath:beans.xml");
//创建容器自动经了.
@RunWith(SpringJUnit4ClassRunner.class) // 值: SpringJUnit4ClassRunner 表示使用spring
										// junit测试 -> 区别：自动地完成 ioc,di
										// JUnit 表示使用原生的 junit测试
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContext-dao.xml" }) // 告诉容器,
public class TestBizImpl {
	@Resource(name = "tagBizImpl")
	private TagBiz tagBiz;
	@Resource(name = "favoriteBizImpl")
	private FavoriteBiz favoriteBiz;
	
	
	@Test   //测试添加一个全新的网址，且标签也是全新的
	public void testAdd() throws Exception{
		Map map=tagBiz.findAllTag();
		//添加一个没有的网址
		Favorite f=new Favorite();
		f.setFlabel("网易");
		f.setFtags("门户,新闻");
		f.setFdesc("大网站");
		f.setFurl("http://www.163.com");
		favoriteBiz.addFavorite(f, map);
	}
	
	@Test   //测试添加一个全新的网址，但标签是已经有的
	public void testAdd2() throws Exception{
		Map map=tagBiz.findAllTag();
		//添加一个没有的网址
		Favorite f=new Favorite();
		f.setFlabel("网易8");
		f.setFtags("新闻,门户");   // 新闻  3        门户2
		f.setFdesc("大网站");
		f.setFurl("http://www.1632.com");
		favoriteBiz.addFavorite(f, map);
		
		// update tag set tcount=20+1 where tid=  新闻
		// update tag set tcount=2+1   where tid= 娱乐
		
		//   错误:  update tag set tcount=xx+1  , tcount=xx+1   where tid in( 新闻，娱乐)
	}
	
	@Test   //测试添加一个没有分类的网址
	public void testAdd3() throws Exception{
		Map map=tagBiz.findAllTag();
		//添加一个没有的网址
		Favorite f=new Favorite();
		f.setFlabel("网易5");
		f.setFtags("");
		f.setFdesc("大网站");
		f.setFurl("http://www.1634.com");
		favoriteBiz.addFavorite(f, map);
	}
	

	/**
	 * 查询所有的标签
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		System.out.println(  tagBiz.findAllTag()  );
	}
	
	/**
	 * 查询没有分类的网址
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		Favorite f=new Favorite();
		f.setTname("未分类");
		System.out.println(    favoriteBiz.findFavoriteByTagName(f)   );
	}
	
	/**
	 * 查询所有的网址
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		Favorite f=new Favorite();
		f.setTname(null);
		System.out.println(    favoriteBiz.findFavoriteByTagName(f)   );
	}
	
	/**
	 * 按标签名查询网址
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		Favorite f=new Favorite();
		f.setTname("娱乐");
		System.out.println(    favoriteBiz.findFavoriteByTagName(f)   );
	}
	
	
	


}
