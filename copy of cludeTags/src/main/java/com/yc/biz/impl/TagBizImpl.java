package com.yc.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.Tag;
import com.yc.biz.TagBiz;
import com.yc.dao.TagDao;

@Service
@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
public class TagBizImpl implements TagBiz {

	@Resource(name="tagDaoMybatisImpl")
	private TagDao tagDao;
	
	@Override
	public Map<String, Tag> findAllTag() throws Exception {
		List<Tag> list = tagDao.searchAll();
		Map<String,Tag> map = new HashMap<String,Tag>();
		for(Tag t : list){
			map.put(t.getTname(), t);
		}
		return map;
	}
	
	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}

}
