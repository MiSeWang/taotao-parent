package Test;

import org.junit.Test;

import com.mrlv.taotao.common.utils.IDUtils;

/*
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mrlv.taotao.mapper.ItemMapper;
import com.mrlv.taotao.pojo.Item;
import com.mrlv.taotao.pojo.ItemExample;
*/
public class TextNo1 {
	/*
	@Test
	public void testPageHelper() {
		//创建一个spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
		//从spring容器中获取Mapper的代理对象
		ItemMapper mapper = ac.getBean(ItemMapper.class);
		//执行查询，并分页
		ItemExample ie = new ItemExample();
		//分页处理
		PageHelper.startPage(2, 10);
		List<Item> list = mapper.selectByExample(ie);
		for(Item i : list) {
			System.out.println(i);
		}oldName = "QQ图片20160307133814.jpg";
		String newName = oldName.substring(oldName.lastIndexOf("."));
		System.out.println(newName);
		//取分页信息
		PageInfo<Item> pageInfo = new PageInfo<Item>(list);
		long total = pageInfo.getTotal();
		
		System.out.println(total);
	}
	*/
	@Test
	public void testPageHelper() {
		String newName = IDUtils.genImageName();
		String oldName = "QQ图片20160307133814.jpg";
		newName = newName + oldName.substring(oldName.lastIndexOf("."));
		System.out.println(newName);
	}
	
}
