package poly.persistance.mongo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;


import poly.dto.NewsDTO;
import poly.persistance.mongo.INewsMapper;

@Component("NewsMapper")
public class NewsMapper implements INewsMapper {
	
	@Autowired
	private MongoTemplate mongodb;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public boolean createCollection(String colNm) throws Exception {
		
		log.info("뉴스 크롤링 컬렉션 생성 start!");
		
		boolean res = false;
		
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm);
		}
		
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time",1).append("seq",1), "NewsIdx");
		
		res = true;
		
		log.info("크롤링 컬렉션 생성 end!");
		
		return res;
	}

	@Override
	public int insertTitle(List<NewsDTO> pList, String colNm) throws Exception {

		log.info(this.getClass().getName() + ".insertTitle start!");
		
		int res = 0;

		if (pList == null) {
			pList = new ArrayList<NewsDTO>();
		}

		Iterator<NewsDTO> it = pList.iterator();

		while (it.hasNext()) {
			NewsDTO pDTO = (NewsDTO) it.next();

			if (pDTO == null) {
				pDTO = new NewsDTO();
			}

			mongodb.insert(pDTO, colNm);

		}

		res = 1;

		log.info(this.getClass().getName() + ".insertTitle End!");

		return res;
	}
	

}
