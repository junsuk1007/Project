package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.NewsDTO;
import poly.dto.NewsTitleDTO;
import poly.service.INewsService;

@Controller
public class NewsController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "NewsService")
	private INewsService newsService;

	// 메인화면 request
	@RequestMapping(value = "Main")
	public String Main() {
		log.info(this.getClass());

		return "/Project/Main";
	}

	@RequestMapping(value = "WordCloudTest")
	public String WordCloudTest() {
		log.info(this.getClass());

		return "/Project/WordCloudTest";
	}

	@RequestMapping(value = "project/collectNews")
	@ResponseBody
	public String collectNews(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".collectNews start!");

		newsService.CollectNews();

		log.info(this.getClass().getName() + ".collectNews end!");

		return "success";
	}
	
	@RequestMapping(value = "project/TestTitleCollection")
	@ResponseBody
	public String TestTitleCollection(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".collectNews start!");

		newsService.createTitleCollection();

		log.info(this.getClass().getName() + ".collectNews end!");

		return "success";
	}

	@RequestMapping(value = "getNewsTest")
	public String getNewsTest() {
		log.info(this.getClass());

		return "/Project/getNewsTest";
	}

	// 뉴스 데이터 가져오기
	@RequestMapping(value = "project/getNews")
	@ResponseBody
	public List<NewsDTO> getNews(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + " .TestGetNews Start!");

		List<NewsDTO> rList = newsService.getNews();

		if (rList == null) {
			rList = new ArrayList<NewsDTO>();
		}
		log.info(this.getClass().getName() + " .TestGetNews End!");

		return rList;
	}
	
	// 뉴스 데이터 가져오기
		@RequestMapping(value = "project/getTop50")
		@ResponseBody
		public List<NewsTitleDTO> getTop50(HttpServletRequest request, HttpServletResponse response) throws Exception {

			log.info(this.getClass().getName() + " .TestGetNews Start!");

			List<NewsTitleDTO> nList = newsService.getTop50();

			if (nList == null) {
				nList = new ArrayList<NewsTitleDTO>();
			}
			log.info(this.getClass().getName() + " .TestGetNews End!");

			return nList;
		}

}
