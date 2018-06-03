package com.mrlv.taotao.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mrlv.taotao.common.pojo.EasyUIResult;
import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.mapper.ItemParamMapper;
import com.mrlv.taotao.pojo.ItemParam;
import com.mrlv.taotao.pojo.ItemParamCat;
import com.mrlv.taotao.pojo.ItemParamExample;
import com.mrlv.taotao.pojo.ItemParamExample.Criteria;
import com.mrlv.taotao.service.IItemParamService;

/**
 * 商品规格参数列表
 * @author Administrator
 */
@Service("itemParamService")
public class ItemParamServiceImpl implements IItemParamService {

	@Resource
	private ItemParamMapper itemParamMapper;
	
	@Override
	public EasyUIResult getItemParamList(Integer page, Integer rows) {
		//分页拦截
		PageHelper.startPage(page, rows);	
		List<ItemParamCat> itemParams = itemParamMapper.getItemParamList();
		EasyUIResult result = new EasyUIResult();
		result.setRows(itemParams);
		
		//取总记录数
		PageInfo<ItemParamCat> pageInfo = new PageInfo<ItemParamCat>(itemParams);	
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}
	
	@Override
	public TaotaoResult getItemParamByCid(Long cid) {
		ItemParamExample itemParamExample = new ItemParamExample();
		Criteria itemParamCriteria = itemParamExample.createCriteria();
		itemParamCriteria.andItemCatIdEqualTo(cid);
		//selectByExampleWithBLOBs查询列包含大文本列
		List<ItemParam> itemParams = itemParamMapper.selectByExampleWithBLOBs(itemParamExample);
		if(itemParams!=null && itemParams.size()>0) {
			System.out.println(itemParams.get(0).getParamData());
			return TaotaoResult.ok(itemParams.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(ItemParam itemParam) {
		//补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

}
