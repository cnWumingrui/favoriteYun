package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;
import com.yc.bean.Tf;
import com.yc.biz.FavoriteBiz;
import com.yc.dao.FavoriteDao;
import com.yc.dao.TagDao;

@Service
@Transactional
public class FavoriteBizImpl implements FavoriteBiz {

	@Resource(name="tagDaoMybatisImpl")
	private TagDao tagDao;
	@Resource(name="favoriteDaoMybatisImpl")
	private FavoriteDao favoriteDao; 
	
	/**
	 * 完成了批量添加中间关系表，批量更新tag表中的count值，但增加tag不能使用批量添加方式
	 */
	@Override
	public Favorite addFavorite(Favorite fav, Map<String, Tag> tagMap)
			throws Exception {
		//先判断是否有ftags,没有执行c,有执行a
		String tags = fav.getFtags().trim();
		List<Tf> list = new ArrayList<Tf>();
		List<Tag> tagListForUpdate = new ArrayList<Tag>();
		//c.将favorite存到favorite表中
		favoriteDao.add(fav); //先添加了fav之后，就有fid
		if(tags != null && !"".equals(tags)){
			//a.截取ftags，根据,=>String[] 存的是所有的tags
			String[] ts = tags.split(",");
			//b.查一下，是否在tag表中存在这个标签名，存在，则count++
			if(ts != null && ts.length>0){
				for(String t : ts){
					Tf tf = new Tf();
					tf.setFid(fav.getFid());
					Tag tag = null;
					if(tagMap != null && tagMap.size() > 0 && tagMap.containsKey(t)){
						//更新操作
						tag = tagMap.get(t);
						tag.setTcount(tag.getTcount()+1);
						//tagDao.update(tag); //TODO:批量更新
						tagListForUpdate.add(tag);
						tf.setTid(tag.getTid());
					}else{
						//添加操作
						tag = new Tag();
						tag.setTname(t);
						tag.setTcount(1);
						tagDao.add(tag);
						tf.setTid(tag.getTid());
						tagMap.put(t, tag);
					}
					list.add(tf); //tf tid fid
				}
				for(Tag t : tagListForUpdate){
					tagDao.update(t);
				}
				favoriteDao.addtf(list); //批量添加中间关系表
			}
		}
		return fav;
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<Favorite> findFavoriteByTagName(Favorite favorite) {
		if("未分类".equals(favorite.getTname())){
			return favoriteDao.searchUntype();
		}
		return favoriteDao.search(favorite);
	}
	
	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}
	
}
