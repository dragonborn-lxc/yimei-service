package com.ymcoffee.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.ymcoffee.base.exception.ExceptionCode;
import com.ymcoffee.base.exception.ServiceException;
import com.ymcoffee.dao.hibernate.DetailImageRepository;
import com.ymcoffee.dao.hibernate.ProductRepository;
import com.ymcoffee.model.DetailImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/app/detail")
@RestController
public class AppDetailController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private DetailImageRepository detailImageRepository;

	@PostMapping("/index")
	public JSONObject index(Long id) {
		if (id == null) {
			throw new ServiceException(ExceptionCode.PARAM_TYPE_ERROR, "参数不正确");
		}
		List<DetailImage> imageList = detailImageRepository.findAllByProdId(id);
		List<DetailImage> coverList = imageList.stream().filter(obj -> obj.getImgType() == 1).collect(Collectors.toList());
		String cover = null;
		if (coverList != null && coverList.size() != 0) {
			cover = coverList.get(0).getImgUrl();
		}
		List<String> diagram = imageList.stream().filter(obj -> obj.getImgType() == 2).map(obj -> obj.getImgUrl()).collect(Collectors.toList());
		JSONObject json = new JSONObject();
		json.put("product", productRepository.findOne(id));
		json.put("cover", cover);
		json.put("diagram", diagram);
		return json;
	}

}
