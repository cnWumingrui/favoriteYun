package com.yc.biz;

import java.util.List;
import java.util.Map;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;

public interface FavoriteBiz {
	/**
	 * 添加地址，业务规则：添加网址，再切分标签（按,切分），再循环去tag表中查是否有这个标签，有，则加数量
	 * 没有，则插入一条新的标签到tag类
	 * @param fav
	 * @param tagMap
	 * @return
	 */
	public Favorite addFavorite(Favorite fav,Map<String,Tag> tagMap) throws Exception;
	
	/**
	 * 根据标签名查找这个标签分类下的网址，如果标签名为null，则查询所有，
	 * 标签名为“未分类”则查询所有“未分类”，如果有标签名，则按标签名查询
	 * @param favorite
	 * @return
	 */
	public List<Favorite> findFavoriteByTagName(Favorite favorite);
}
