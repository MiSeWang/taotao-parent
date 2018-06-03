package com.mrlv.taotao.mapper;

import com.mrlv.taotao.pojo.ItemParam;
import com.mrlv.taotao.pojo.ItemParamCat;
import com.mrlv.taotao.pojo.ItemParamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemParamMapper {	
	//自添
	List<ItemParamCat> getItemParamList();
	
	
	
	
	
    int countByExample(ItemParamExample example);

    int deleteByExample(ItemParamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ItemParam record);

    int insertSelective(ItemParam record);

    List<ItemParam> selectByExampleWithBLOBs(ItemParamExample example);

    List<ItemParam> selectByExample(ItemParamExample example);

    ItemParam selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ItemParam record, @Param("example") ItemParamExample example);

    int updateByExampleWithBLOBs(@Param("record") ItemParam record, @Param("example") ItemParamExample example);

    int updateByExample(@Param("record") ItemParam record, @Param("example") ItemParamExample example);

    int updateByPrimaryKeySelective(ItemParam record);

    int updateByPrimaryKeyWithBLOBs(ItemParam record);

    int updateByPrimaryKey(ItemParam record);
}