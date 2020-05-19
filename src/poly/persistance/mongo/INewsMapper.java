package poly.persistance.mongo;

import java.util.List;

import poly.dto.NewsDTO;

public interface INewsMapper {

	/*
	 * MongoDB 컬렉션 생성하기
	 * 
	 * @param colNm 생성되는 컬렉션 이름
	 */
	public boolean createCollection(String colNm) throws Exception;
	
	/*
	 * MongoDB 데이터 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	
	
	public int insertTitle(List<NewsDTO> pList, String colNm) throws Exception;
}
