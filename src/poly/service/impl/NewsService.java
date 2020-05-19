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
		
		Document doc = Jsoup.connect(url).header("Accept", "text/html, application/xhtml+xml,*/*").header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
				.header("Accept-Encoding", "gzip,delate").header("Accept-Language", "ko").header("Connection", "Keep-Alive").get();
		
		//System.out.println(doc);
		Elements head = doc.select("a.title span");
		
		/* for(int i = 0; i < head.size(); i++) */
			for(int i = 0; i < 10; i++){
			Element item = head.get(i);
			System.out.println(item.text());
			
			NewsDTO pDTO = new NewsDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			int seq = i+1;
			pDTO.setSeq(seq);
			pDTO.setTitle(item.text());
			
			pList.add(pDTO);
						
		}
		
		String colNm = "NewsCrol" + DateUtil.getDateTime("yyyyMMdd"); //생성할 컬렉션명
		
		newsMapper.createCollection(colNm);
		
		newsMapper.insertTitle(pList, colNm);
		
		log.info(this.getClass().getName() + ".collectNews end!");
		
		return res;
		
	}

}
