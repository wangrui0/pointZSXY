package com.system.phone.web;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.system.core.jsonpacket.JsonResponse;
import com.system.manager.entity.MainCourseWare;
import com.system.manager.service.MainCourseWareService;
import com.system.manager.web.model.MainCourseWareModel;
import com.system.user.web.base.BaseController;
//知识课件
@RestController
@RequestMapping(value="/ms")
public class PMainCourseWareController extends BaseController{
	
	@Autowired
	private MainCourseWareService mainCourseWareService;
	/**
	 * 分页查询知识课件
	 * @param start
	 * @param size
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryAllZSCourseWarepage/{id}/{start}/{size}",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResponse queryAllZSCourseWarepage(@PathVariable Integer id,@PathVariable Integer start,@PathVariable Integer size,
			@ModelAttribute MainCourseWareModel model,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jsonResponse = super.sucessResponse();
		List<Map<String,Object>> items= new ArrayList<Map<String,Object>>();
		Pageable pageable = new PageRequest(start-1, size);
		try {
			Page<Map<String,Object>> page = mainCourseWareService.findByPagePhone(id,model, pageable);
			items =page.getContent();
			jsonResponse.setItems(items);
			return jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return super.failureResponse();
		}
	}
	
	/**
	 * 查询所有知识课件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllZSCourseWare", method = {RequestMethod.GET,RequestMethod.POST})
	public JsonResponse findAllZSCourseWare(@ModelAttribute MainCourseWareModel model,HttpServletRequest request
			, HttpServletResponse response) {
		JsonResponse jsonResponse = super.sucessResponse();
		List<MainCourseWare> items = new ArrayList<MainCourseWare>();
		try{
			List<MainCourseWare> list = mainCourseWareService.findByPageType(model);
			
			items.addAll(list);
			jsonResponse.setItems(items);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonResponse;
	}
	
}
