package poly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.dto.NewsDTO;
import poly.dto.NewsTitleDTO;
import poly.persistance.mongo.INewsMapper;
import poly.service.INewsService;
import poly.util.DateUtil;

@Service("NewsService")
public class NewsService implements INewsService {

	@Resource(name = "NewsMapper")
	private INewsMapper newsMapper;

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public int CollectNews() throws Exception {

		log.info(this.getClass().getName() + ".collectNews start!");

		int res = 0;

		List<NewsDTO> pList = new ArrayList<NewsDTO>();

		String url = "https://sports.news.naver.com/basketball/news/index.nhn?isphoto=N";

		Document doc = Jsoup.connect(url).header("Accept", "text/html, application/xhtml+xml,*/*")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
				.header("Accept-Encoding", "gzip,delate").header("Accept-Language", "ko")
				.header("Connection", "Keep-Alive").get();

		// System.out.println(doc);
		Elements head = doc.select("a.title span");

		/* for(int i = 0; i < head.size(); i++) */
		for (int i = 0; i < 10; i++) {
			Element item = head.get(i);
			System.out.println(item.text());

			NewsDTO pDTO = new NewsDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			int seq = i + 1;
			pDTO.setSeq(seq);
			pDTO.setTitle(item.text());

			pList.add(pDTO);

		}

		String colNm = "NewsCrol"; // 생성할 컬렉션명

		newsMapper.createCollection(colNm);

		newsMapper.insertTitle(pList, colNm);

		log.info(this.getClass().getName() + ".collectNews end!");

		return res;

	}

	// 제목 분류 컬렉션 생성
	@Override
	public int createTitleCollection() throws Exception {

		log.info(this.getClass().getName() + ".createTitleCollection start!");

		List<NewsTitleDTO> pList = new ArrayList<NewsTitleDTO>();

		int res = 0;

		// 87~96줄 까지 NewsCrol 에서 title 가져오기

		// 조회할 컬렉션 이름
		String colNm = "NewsCrol";

		String colNm2 = "SortedTitle"; // repeat조회 할 컬렉션명

		List<NewsDTO> rList = newsMapper.getTitle(colNm);

		if (rList == null) {
			rList = new ArrayList<NewsDTO>();
		}

		int rSize = rList.size();
		for (int i = 0; i < rSize; i++) {

			// NewsCrol에서 가져온 title값을 SortedTitle로 넣어줌
			String title = rList.get(i).getTitle();

			NewsTitleDTO pDTO = new NewsTitleDTO();

			pDTO.setTitle(title);
			pDTO.setRepeat(1);

			pList.add(pDTO);

		}
		// 컬렉션을 없애지 않을거기 때문에 주석
		/* newsMapper.createTitleCollection(colNm2); */

		newsMapper.insertSortedTitle(pList, colNm2);

		log.info(this.getClass().getName() + ".createTitleCollection end!");

		return res;

	}

	@Override
	public List<NewsDTO> getNews() throws Exception {

		log.info(this.getClass().getName() + ".getNews Start!");

		// 조회할 컬렉션 이름
		String colNm = "NewsCrol";

		List<NewsDTO> rList = newsMapper.getNews(colNm);

		if (rList == null) {
			rList = new ArrayList<NewsDTO>();
		}

		log.info(this.getClass().getName() + ".getNews End!");

		return rList;
	}

}
