package com.ymcoffee.controller.app;

import com.ymcoffee.base.exception.ExceptionCode;
import com.ymcoffee.base.exception.ServiceException;
import com.ymcoffee.entity.ProductClassifyVo;
import com.ymcoffee.entity.ProductParamsVo;
import com.ymcoffee.service.ArtService;
import com.ymcoffee.service.DerivativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/app/classify")
@RestController
public class AppClassifyController {

	@Autowired
	private ArtService artService;

	@Autowired
	private DerivativeService derivativeService;

	@PostMapping("/art/index")
	public List<ProductClassifyVo> artIndex(@RequestBody ProductParamsVo request) {
		if (request == null) {
			throw new ServiceException(ExceptionCode.PARAM_TYPE_ERROR, "参数不正确");
		}
		List<ProductClassifyVo> list = artService.getArtList(request, request.getPageNumber(), request.getPageSize(), request.getSort());
		return list;
	}

	@PostMapping("/derivative/index")
	public List<ProductClassifyVo> derivativeIndex(@RequestBody ProductParamsVo request) {
		if (request == null) {
			throw new ServiceException(ExceptionCode.PARAM_TYPE_ERROR, "参数不正确");
		}
		List<ProductClassifyVo> list = derivativeService.getDerivativeList(request, request.getPageNumber(), request.getPageSize(), request.getSort());
		return list;
	}

}
