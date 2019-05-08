package com.ymcoffee.service;

import com.ymcoffee.entity.ProductClassifyVo;
import com.ymcoffee.entity.ProductParamsVo;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
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
public class ArtService {

	@PersistenceContext
	private EntityManager entityManager;

	public List<ProductClassifyVo> getArtList(ProductParamsVo params, int pageNumber, int pageSize, int sortIndex) {
		Pageable pageable = new PageRequest(pageNumber - 1, pageSize);
		StringBuilder queryHql = new StringBuilder("select a.id,a.name,a.brand,a.year,a.price,a.artist,b.subcode_desc as category,c.subcode_desc as theme,a.size,a.thumbnail_img_url as thumbnailImgUrl from ym_product a left join ym_dictionary b on b.code = 3 and a.category = b.subcode left join ym_dictionary c on c.code = 4 and a.theme = c.subcode where a.is_deleted = 0 and a.type = 1");
		String countHql = "select count(*) from ym_product where is_deleted = 0 and type = 1";
		if (params.getCategory() != 0) {
			queryHql.append(" and a.category = :category");
		}
		if (params.getTheme() != 0) {
			queryHql.append(" and a.theme = :theme");
		}
		switch (sortIndex) {
			case 0:
				queryHql.append(" order by a.create_time desc,a.update_time desc,a.id asc");
				break;
			case 1:
				queryHql.append(" order by a.price asc,a.id asc");
				break;
			case 2:
				queryHql.append(" order by a.price desc,a.id asc");
				break;
			case 3:
				queryHql.append(" order by a.year asc,a.id asc");
				break;
			case 4:
				queryHql.append(" order by a.year desc,a.id asc");
				break;
			default:
				queryHql.append(" order by a.create_time desc,a.update_time desc,a.id asc");
				break;
		}
		Query query = entityManager.createNativeQuery(queryHql.toString());
		Query count = entityManager.createNativeQuery(countHql);
		if (params.getCategory() != 0) {
			query.setParameter("category", params.getCategory());
		}
		if (params.getTheme() != 0) {
			query.setParameter("theme", params.getTheme());
		}
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());

		BigInteger num = (BigInteger) count.getSingleResult();
		Long total = num.longValue();
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(ProductClassifyVo.class));
		List<ProductClassifyVo> result = total > pageable.getOffset() ? query.getResultList() : Collections.<ProductClassifyVo>emptyList();

		return new PageImpl<>(result, pageable, total).getContent();
	}

}
