package poly.service;

import java.util.List;

import poly.dto.NewsDTO;
import poly.dto.NewsTitleDTO;

public interface INewsService {

	// 뉴스 기사 크롤링
	public int CollectNews() throws Exception;

	//제목 분류 컬렉션 만들기
	public int createTitleCollection() throws Exception;

	// MongoDB 뉴스기사 데이터 가져오기
	public List<NewsDTO> getNews() throws Exception;
	
	public List<NewsTitleDTO> getTop50() throws Exception;
	
	public void makeCsv() throws Exception;

	public int titleDelete(NewsTitleDTO nDTO) throws Exception;	
		
}
