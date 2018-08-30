package com.yc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.yc.bean.Favorite;
import com.yc.bean.Tf;
import com.yc.dao.FavoriteDao;

@Repository
public class FavoriteDaoMybatisImpl implements FavoriteDao {

	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void add(Favorite favorite) {
		sqlSession.insert("com.yc.dao.favoriteDaoMapper.add",favorite);
	}

	@Override
	public void addtf(List<Tf> list) {
		sqlSession.insert("com.yc.dao.favoriteDaoMapper.addtf",list);
	}

	@Override
	public List<Favorite> search(Favorite favorite) {
		return sqlSession.selectList("com.yc.dao.favoriteDaoMapper.search",favorite);
	}

	@Override
	public List<Favorite> searchUntype() {
		return sqlSession.selectList("com.yc.dao.favoriteDaoMapper.searchUnType");
	}
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

}
