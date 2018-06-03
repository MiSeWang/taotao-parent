package com.mrlv.taotao.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mrlv.taotao.common.pojo.EasyUITreeNode;
import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.mapper.ContentCategoryMapper;
import com.mrlv.taotao.pojo.ContentCategory;
import com.mrlv.taotao.pojo.ContentCategoryExample;
import com.mrlv.taotao.pojo.ContentCategoryExample.Criteria;
import com.mrlv.taotao.service.IContentCategroyService;

/**
 * 广告分类
 * @author Administrator
 *
 */
@Service("contentCategroyService")
public class ContentCategroyServiceImpl implements IContentCategroyService {
	
	@Resource
	private ContentCategoryMapper contentCategroyMapper;

	@Override
	public List<EasyUITreeNode> getCategoryList(long parentId) {
		//根据parenid节点查询
		ContentCategoryExample example = new ContentCategoryExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		//执行查询
		List<ContentCategory> list = contentCategroyMapper.selectByExample(example);
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (ContentCategory contentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(contentCategory.getId());
			node.setText(contentCategory.getName());
			node.setState(contentCategory.getIsParent() ? "closed":"open");
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		//创建一个pojo
		ContentCategory contentCategory = new ContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		//状态。可选值：1(正常),2(删除)
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategroyMapper.insert(contentCategory);
		//查看父节点的isParent列是否是true，如果不是true则改为true
		ContentCategory parentCat = contentCategroyMapper.selectByPrimaryKey(parentId);
		if (!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			//更新父节点
			contentCategroyMapper.updateByPrimaryKey(parentCat);
		}
		//返回结果
		return TaotaoResult.ok(contentCategory);
	}

	@Override
	public TaotaoResult delectContentCategory(long id) {
		System.out.println(id);
		//修改当前节点为删除状态
		ContentCategory contentCategory = contentCategroyMapper.selectByPrimaryKey(id);
		contentCategory.setStatus(2);
		contentCategory.setUpdated(new Date());
		contentCategroyMapper.updateByPrimaryKeySelective(contentCategory);

		//修改其父节点是否为父节点的状态
		ContentCategory parentContentCategory = new ContentCategory();
		parentContentCategory.setId(contentCategory.getParentId());
		parentContentCategory.setIsParent(false);
		parentContentCategory.setUpdated(new Date());
		contentCategroyMapper.updateByPrimaryKeySelective(parentContentCategory);
		
		return TaotaoResult.ok();
	}

}
