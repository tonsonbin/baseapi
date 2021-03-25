/**
 * 
 */
package com.tb.app.model.sys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.app.common.web.BaseController;
import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.model.sys.entity.Dict;
import com.tb.app.model.sys.service.DictService;

/**
 * 字典Controller
 * 
 * @version 2021-03-25
 */
@RequestMapping("${apiPath}/sys/dict")
@RestController
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;

    @RequestMapping("/unauth/list")
	public Result listData(String type) {
    	
		List<Dict> dicts = dictService.getDictList(type);
		
		return ResultGenerator.genSuccessResult(dicts);
	}
}
