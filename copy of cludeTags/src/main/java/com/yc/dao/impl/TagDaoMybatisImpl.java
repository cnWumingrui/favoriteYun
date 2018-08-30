package com.yc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.yc.bean.Tag;
import com.yc.dao.TagDao;

@Repository
public class TagDaoMybatisImpl implements TagDao {

	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Tag add(Tag tag) {
		sqlSession.insert("com.yc.dao.tagDaoMapper.add",tag);
		return tag;
	}

	@Override
	public void update(Tag tag) {
		sqlSession.insert("com.yc.dao.tagDaoMapper.update",tag);
	}

	@Override
	public List<Tag> searchAll() {
		return sqlSession.selectList("com.yc.dao.tagDaoMapper.searchAll");
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

}
