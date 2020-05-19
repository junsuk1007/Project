package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.service.INewsService;

@Controller
public class NewsController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "NewsService")
	private INewsService newsService;
	
	//메인화면 request
	@RequestMapping(value="Main")
	public String Main() {
		log.info(this.getClass());
		
		return "/Project/Main";
	}
	
	@RequestMapping(value="WordCloudTest")
	public String WordCloudTest() {
		log.info(this.getClass());
		
		return "/Project/WordCloudTest";
	}
	
	@RequestMapping(value = "project/collectNews")
	@ResponseBody
	public String collectNews(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		log.info(this.getClass().getName() + ".collectNews start!");
		
		newsService.CollectNews();
		
		log.info(this.getClass().getName()+ ".collectNews end!");
		
		return "success";
	}

}
