package com.ymcoffee.controller.app;

import com.ymcoffee.dao.hibernate.CollectRepository;
import com.ymcoffee.dao.hibernate.ProductRepository;
import com.ymcoffee.entity.CollectParamsVo;
import com.ymcoffee.entity.ProductClassifyVo;
import com.ymcoffee.model.Collect;
import com.ymcoffee.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/app/operate")
@RestController
public class AppOperationController {

	@Autowired
	private CollectRepository collectRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CollectService collectService;

	@PostMapping("/collect/collect")
	public int collect(@RequestBody CollectParamsVo request) {
		if (request.getUserId() == null || request.getProdId() == null) {
			return 0;
		}
		Collect collect = new Collect();
		collect.setVersion(1);
		collect.setUserId(request.getUserId());
		collect.setProdId(request.getProdId());
		collectRepository.save(collect);
		return 1;
	}

	@PostMapping("/collect/uncollect")
	public int uncollect(@RequestBody CollectParamsVo request) {
		if (request.getUserId() == null || request.getProdId() == null) {
			return 0;
		}
		Collect collect = new Collect();
		collect.setUserId(request.getUserId());
		collect.setProdId(request.getProdId());
		collectRepository.deleteByUserIdAndProdId(request.getUserId(), request.getProdId());
		return 1;
	}

	@PostMapping("/collect/exist")
	public boolean exist(@RequestBody CollectParamsVo request) {
		List<Collect> list = collectRepository.findAllByUserIdAndProdId(request.getUserId(), request.getProdId());
		if (list == null || list.size() == 0) {
			return false;
		}
		return true;
	}

	@PostMapping("/collect/all")
	public List<ProductClassifyVo> all(@RequestBody CollectParamsVo request) {
		return collectService.getCollectList(request.getUserId(), request.getPageNumber(), request.getPageSize());
	}

	@PostMapping("/collect/sum")
	public long count(@RequestBody CollectParamsVo request) {
		if (request.getUserId() == null || request.getProdId() == null) {
			return 0;
		}
		Collect collect = new Collect();
		collect.setUserId(request.getUserId());
		return collectRepository.countByUserId(request.getUserId());
	}

}
