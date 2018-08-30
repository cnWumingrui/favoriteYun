package com.yc.biz;

import java.util.Map;

import com.yc.bean.Tag;

public interface TagBiz {
	/**
	 * 查找所有的tag
	 * @return Map：这样判断一个标签名是否已经存在就容易了
	 * Map.containKey("标签名")
	 * @throws Exception
	 */
	public Map<String,Tag> findAllTag() throws Exception;
}
