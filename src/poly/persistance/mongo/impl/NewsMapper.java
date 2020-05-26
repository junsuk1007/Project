package poly.persistance.mongo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import poly.dto.NewsDTO;
import poly.dto.NewsTitleDTO;
import poly.persistance.mongo.INewsMapper;
import poly.util.CmmUtil;

@Component("NewsMapper")
public class NewsMapper implements INewsMapper {

	@Autowired
	private MongoTemplate mongodb;

	private Logger log = Logger.getLogger(this.getClass());

	// 크롤링 된 데이터 컬렉션 생성
	@Override
	public boolean createCollection(String colNm) throws Exception {

		log.info("뉴스 크롤링 컬렉션 생성 start!");

		boolean res = false;

		// 컬렉션이 존재하면 삭제하고 새로 생성
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm);
		}

		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("seq", 1), "NewsIdx");

		res = true;

		log.info("크롤링 컬렉션 생성 end!");

		return res;
	}

	// 분류된 제목 컬렉션 생성
	@Override
	public boolean createTitleCollection(String colNm2) throws Exception {

		log.info("제목 분류 컬렉션 생성 start!");

		boolean res = false;

		// 컬렉션이 존재하면 삭제하고 새로 생성
		if (mongodb.collectionExists(colNm2)) {
			mongodb.dropCollection(colNm2);
		}

		mongodb.createCollection(colNm2).createIndex(new BasicDBObject("title", 1), "NewsIdx");

		res = true;

		log.info("제목 분류 컬렉션 생성 end!");

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

	@Override
	public int insertSortedTitle(List<NewsTitleDTO> pList, String colNm2) throws Exception {

		log.info(this.getClass().getName() + ".insertTitle start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<NewsTitleDTO>();
		}

		Iterator<NewsTitleDTO> it = pList.iterator();

		while (it.hasNext()) {
			NewsTitleDTO pDTO = (NewsTitleDTO) it.next();

			if (pDTO == null) {
				pDTO = new NewsTitleDTO();
			}

			mongodb.insert(pDTO, colNm2);

		}

		res = 1;

		log.info(this.getClass().getName() + ".insertSortedTitle End!");

		return res;
	}

	@Override
	public List<NewsDTO> getNews(String colNm) throws Exception {

		log.info(this.getClass().getName() + ".getNews Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<NewsDTO> rList = new ArrayList<NewsDTO>();

		// 퀴즈팩별 정답률 일자별 저장하기
		NewsDTO rDTO = null;

		while (cursor.hasNext()) {

			rDTO = new NewsDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String title = CmmUtil.nvl((String) current.get("title")); // 제목
			String Stringseq = CmmUtil.nvl(String.valueOf(current.get("seq"))); // 순위
			int seq = Integer.parseInt(Stringseq);

			rDTO.setCollect_time(collect_time);
			rDTO.setSeq(seq);
			rDTO.setTitle(title);

			rList.add(rDTO); // List에 데이터 저장

			rDTO = null;

		}

		log.info(this.getClass().getName() + ".getNews End!");

		return rList;
	}

	//NewsCrol 컬렉션에서 제목 가져오기
	@Override
	public List<NewsDTO> getTitle(String colNm) throws Exception {

		log.info(this.getClass().getName() + ".getTitle Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<NewsDTO> rList = new ArrayList<NewsDTO>();

		// 퀴즈팩별 정답률 일자별 저장하기
		NewsDTO rDTO = null;

		while (cursor.hasNext()) {

			rDTO = new NewsDTO();

			final DBObject current = cursor.next();

			String title = CmmUtil.nvl((String) current.get("title")); // 제목

			System.out.println("title : " + title);

			rDTO.setTitle(title);

			rList.add(rDTO); // List에 데이터 저장

			rDTO = null;

		}

		log.info(this.getClass().getName() + ".getTitle End!");

		return rList;
	}

}
