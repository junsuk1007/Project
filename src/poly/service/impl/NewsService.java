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

		// 인서트 하기위해 저장할 리스트
		List<NewsTitleDTO> pList = new ArrayList<NewsTitleDTO>();

		// 업데이트 하기위해 저장할 리스트
		List<NewsTitleDTO> uList = new ArrayList<NewsTitleDTO>();

		int res = 0;

		// 87~96줄 까지 NewsCrol 에서 title 가져오기

		// 조회할 컬렉션 이름
		String colNm = "NewsCrol";

		String colNm2 = "SortedTitle"; // repeat조회 할 컬렉션명

		// NewsCrol 컬렉션에서 가져온것을 담을 리스트
		List<NewsDTO> rList = newsMapper.getTitle(colNm);

		if (rList == null) {
			rList = new ArrayList<NewsDTO>();
		}

		int rSize = rList.size();
		for (int i = 0; i < rSize; i++) {

			ArrayList<String> stackedTitle = new ArrayList<>();
			ArrayList<Integer> stackedRepeat = new ArrayList<>();

			// SortedTitle에서 가져온 것을 담을 리스트
			List<NewsTitleDTO> nList = newsMapper.getRepeat(colNm2);

			if (nList == null) {
				nList = new ArrayList<NewsTitleDTO>();
			} else {
				int nSize = nList.size();
				for (int j = 0; j < nSize; j++) {

					String sortedTitle = nList.get(j).getTitle();
					int repeat = nList.get(j).getRepeat();

					stackedTitle.add(sortedTitle);
					stackedRepeat.add(repeat);
				}
			}

			// NewsCrol에서 가져온 title값을 SortedTitle로 넣어줌
			String title = rList.get(i).getTitle();

			String[] strArr = sortTitle(title);

			for (int k = 0; k < strArr.length; k++) {

				NewsTitleDTO pDTO = new NewsTitleDTO();

				if (stackedTitle.contains(strArr[k])) {

					NewsTitleDTO uDTO = new NewsTitleDTO();
					for (int n = 0; n < nList.size(); n++) {
						String usedTitle = stackedTitle.get(n);
						int usedRepeat = stackedRepeat.get(n);
						if (usedTitle.equals(strArr[k])) {

							stackedTitle.set(n, strArr[k]);
							stackedRepeat.set(n, usedRepeat+1);
							uDTO.setTitle(strArr[k]);
							uDTO.setRepeat(usedRepeat + 1);
							uList.add(uDTO);
						}
					}

				} else {

					stackedTitle.add(strArr[k]);
					stackedRepeat.add(1);

					pDTO.setTitle(strArr[k]);
					pDTO.setRepeat(1);

					pList.add(pDTO);

				}
			}

		}
		// 컬렉션을 없애지 않을거기 때문에 주석
		/* newsMapper.createTitleCollection(colNm2); */
		newsMapper.updateSortedTitle(uList, colNm2);

		newsMapper.insertSortedTitle(pList, colNm2);

		log.info(this.getClass().getName() + ".createTitleCollection end!");

		return res;

	}

	public String[] sortTitle(String title) {

		System.out.println("original str :" + title);

		title = title.replaceAll("[^\\uAC00-\\uD7A3xfe0-9a-zA-Z\\\\s]", " ");

		/* System.out.println("1st str :" + title); */

		title = title.replaceAll("\\s{2,}", " ");

		/* System.out.println("2nd str :" + title); */

		title = title.trim();

		/* System.out.println("3nd str :" + title); */

		String[] strArr = title.split(" ");

		/* System.out.println("strArr.length :" + strArr.length); */

		// 마지막 글자 없애는 함수
		// System.out.println("네번째 배열 조사삭제:" + strArr[3].substring(0, strArr[3].length()
		// - 1));

		for (int i = 0; i < strArr.length; i++) {
			// System.out.println("strArr[" + i + "]번째 :" + strArr[i]);

			// 마지막 글자 추출
			String checkstr = strArr[i].substring(strArr[i].length() - 1);

			if ((checkstr.equals("은")) || (checkstr.equals("는")) || (checkstr.equals("이")) || (checkstr.equals("가"))
					|| (checkstr.equals("을")) || (checkstr.equals("를")) || (checkstr.equals("와"))
					|| (checkstr.equals("랑")) || (checkstr.equals("로")) || (checkstr.equals("의"))
					|| (checkstr.equals("에")) || (checkstr.equals("과"))) {

				strArr[i] = strArr[i].substring(0, strArr[i].length() - 1);

				// System.out.println("바뀐 strArr[" + i + "]번째 :" + strArr[i]);
			}

		}

		for (int k = 0; k < strArr.length; k++) {
			// System.out.println("최종 strArr[" + k + "]번째 :" + strArr[k]);
		}

		return strArr;
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
