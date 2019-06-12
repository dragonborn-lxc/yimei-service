package com.ymcoffee.service;

import com.ymcoffee.dao.hibernate.ProductRepository;
import com.ymcoffee.entity.ProductClassifyVo;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Service
public class CollectService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ProductRepository productRepository;

	public List<ProductClassifyVo> getCollectList(long userId, int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber - 1, pageSize);
		StringBuilder queryHql = new StringBuilder("select a.id,a.name,a.brand,a.year,a.price,a.artist,a.thumbnail_img_url as thumbnailImgUrl from ym_product a inner join ym_collect b on a.id = b.prod_id where a.is_deleted = 0 and b.user_id = :userId order by b.create_time");
		String countHql = "select count(*) from ym_collect where user_id = :userId";

		Query query = entityManager.createNativeQuery(queryHql.toString());
		Query count = entityManager.createNativeQuery(countHql);
		query.setParameter("userId", userId);
		count.setParameter("userId", userId);
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());

		BigInteger num = (BigInteger) count.getSingleResult();
		Long total = num.longValue();
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(ProductClassifyVo.class));
		List<ProductClassifyVo> result = total > pageable.getOffset() ? query.getResultList() : Collections.<ProductClassifyVo>emptyList();

		return new PageImpl<>(result, pageable, total).getContent();
	}

}
