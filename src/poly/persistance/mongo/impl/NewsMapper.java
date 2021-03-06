package poly.persistance.mongo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
	public int updateSortedTitle(List<NewsTitleDTO> uList, String colNm2) throws Exception {

		log.info(this.getClass().getName() + ".insertTitle start!");

		int res = 0;

		if (uList == null) {
			uList = new ArrayList<NewsTitleDTO>();
		}

		/*
		 * Iterator<NewsTitleDTO> it = uList.iterator();
		 * 
		 * while (it.hasNext()) { NewsTitleDTO pDTO = (NewsTitleDTO) it.next();
		 * 
		 * if (pDTO == null) { pDTO = new NewsTitleDTO(); }
		 */
		for (int i = 0; i < uList.size(); i++) {

			String title = uList.get(i).getTitle();
			int repeat = uList.get(i).getRepeat();

			Criteria criteria = new Criteria("title");
			criteria.is(title);

			Query query = new Query(criteria);

			Update update = new Update();

			update.set("repeat", repeat);

			// (찾을 컬럼,바꿀 컬럼,컬렉션 명)
			mongodb.updateFirst(query, update, colNm2);
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
			String url = CmmUtil.nvl((String)current.get("url"));

			rDTO.setCollect_time(collect_time);
			rDTO.setSeq(seq);
			rDTO.setTitle(title);
			rDTO.setUrl(url);

			rList.add(rDTO); // List에 데이터 저장

			rDTO = null;

		}

		log.info(this.getClass().getName() + ".getNews End!");

		return rList;
	}

	// NewsCrol 컬렉션에서 제목 가져오기
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

	// SortedTitle 컬렉션에서 제목 가져오기
	@Override
	public List<NewsTitleDTO> getRepeat(String colNm2) throws Exception {

		log.info(this.getClass().getName() + ".getTitle Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm2);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<NewsTitleDTO> nList = new ArrayList<NewsTitleDTO>();

		NewsTitleDTO nDTO = null;

		while (cursor.hasNext()) {

			nDTO = new NewsTitleDTO();

			final DBObject current = cursor.next();

			String title = CmmUtil.nvl((String) current.get("title")); // 제목
			String StringRepeat = CmmUtil.nvl(String.valueOf(current.get("repeat"))); // 반복횟수
			int repeat = Integer.parseInt(StringRepeat);

			nDTO.setRepeat(repeat);
			nDTO.setTitle(title);

			nList.add(nDTO); // List에 데이터 저장

			nDTO = null;

		}

		log.info(this.getClass().getName() + ".getTitle End!");

		return nList;
	}

	// SortedTitle에서 탑 50 가져오기
	@Override
	public List<NewsTitleDTO> getTop50(String colNm2) throws Exception {

		log.info(this.getClass().getName() + ".getTitle Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm2);

		// 컬렉션으로부터 repeat이 높은 순서대로 60개만 가져옴
		Iterator<DBObject> cursor = rCol.find().sort(new BasicDBObject("repeat", -1)).limit(150);

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<NewsTitleDTO> nList = new ArrayList<NewsTitleDTO>();

		NewsTitleDTO nDTO = null;

		while (cursor.hasNext()) {

			nDTO = new NewsTitleDTO();

			final DBObject current = cursor.next();

			String title = CmmUtil.nvl((String) current.get("title")); // 제목
			String StringRepeat = CmmUtil.nvl(String.valueOf(current.get("repeat"))); // 반복횟수
			int repeat = Integer.parseInt(StringRepeat);

			nDTO.setRepeat(repeat);
			nDTO.setTitle(title);

			nList.add(nDTO); // List에 데이터 저장

			nDTO = null;

		}

		log.info(this.getClass().getName() + ".getTitle End!");

		return nList;
	}

	@Override
	public int titleDelete(NewsTitleDTO nDTO, String colNm2) throws Exception {

		int res = 0;

		String title = CmmUtil.nvl(nDTO.getTitle());	
		
		DBObject query = new BasicDBObject()
				.append("title", title);
		log.info("query:" + query);
		
		DBObject queryRes = mongodb.getCollection(colNm2).find(query).one();
		
		mongodb.getCollection(colNm2).remove(queryRes);

		res = 1;

		return res;
	}

}
