package com.mrlv.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页面
 * @author Administrator
 *
 */
@Controller
public class IndexController {
	@RequestMapping("/{itemId}")
	public String Index(@PathVariable String itemId) {
		return itemId;
	}
	@RequestMapping("/")
	public String Index() {
		return "index";
	}
}
