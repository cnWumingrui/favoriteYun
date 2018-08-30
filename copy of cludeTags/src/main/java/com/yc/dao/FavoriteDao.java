package com.yc.dao;

import java.util.List;

import com.yc.bean.Favorite;
import com.yc.bean.Tf;

public interface FavoriteDao {
	public void add(Favorite favorite);
	
	/**
	 * 添加中间关系表
	 * @param tfs
	 */
	public void addtf(List<Tf> tfs);
	
	/**
	 * 根据tagname查网址
	 * @param favorite
	 * @return
	 */
	public List<Favorite> search(Favorite favorite); //根据tagname
	
	/**
	 * 查没有分类的网址
	 * @return
	 */
	public List<Favorite> searchUntype();
}
