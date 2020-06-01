package poly.persistance.mongo;

import java.util.List;

import poly.dto.NewsDTO;
import poly.dto.NewsTitleDTO;

public interface INewsMapper {

	/*
	 * MongoDB 컬렉션 생성하기
	 * 
	 * @param colNm 생성되는 컬렉션 이름
	 */
	public boolean createCollection(String colNm) throws Exception;
	
	//제목 컬렉션
	public boolean createTitleCollection(String colNm) throws Exception;
	
	/*
	 * MongoDB 데이터 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */	
	public int insertTitle(List<NewsDTO> pList, String colNm) throws Exception;
	
	public int insertSortedTitle(List<NewsTitleDTO> pList, String colNm) throws Exception;
	
	public int updateSortedTitle(List<NewsTitleDTO> uList, String colNm) throws Exception;
	
	
	/*
	 * MOngoDB 데이터 가져오기
	 * 
	 * @param colNm 가져올 컬렉션 이름
	 */	
	public List<NewsDTO> getNews(String colNm) throws Exception;
	
	/*
	 * MOngoDB 데이터 가져오기
	 * 
	 * @param colNm 가져올 컬렉션 이름
	 */	
	public List<NewsDTO> getTitle(String colNm) throws Exception;

	public List<NewsTitleDTO> getRepeat(String colNm2) throws Exception;
	
	public List<NewsTitleDTO> getTop50(String colNm2) throws Exception;
	
	
}
