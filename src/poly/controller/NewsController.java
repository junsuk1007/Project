package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.AdminDTO;
import poly.dto.NewsDTO;
import poly.dto.NewsTitleDTO;
import poly.service.IAdminService;
import poly.service.INewsService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Controller
public class NewsController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "NewsService")
	private INewsService newsService;
	
	@Resource(name = "AdminService")
	private IAdminService adminService;

	// 메인화면 request
	@RequestMapping(value = "Main")
	public String Main(HttpSession session,HttpServletRequest request,  Model model) {
		log.info(this.getClass());
		
		if(session.getAttribute("SS_ADMIN_CODE")!=null) {
			List<NewsTitleDTO> nList = new ArrayList<NewsTitleDTO>();
			try {
				nList = newsService.getTop50();
				model.addAttribute("nList", nList);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}		

		return "/Project/Main";
	}

	@RequestMapping(value = "WordCloudTest")
	public String WordCloudTest() {
		log.info(this.getClass());

		return "/Project/WordCloudTest";
	}
	@RequestMapping(value = "WordCloud2")
	public String WordCloud2() {
		log.info(this.getClass());

		return "/Project/WordCloud2";
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
		@RequestMapping(value = "makecsv")
		@ResponseBody
		public String makecsv(HttpServletRequest request, HttpServletResponse response) throws Exception {

			log.info(this.getClass().getName() + ".makecsv start!");

			newsService.makeCsv();
			
			log.info(this.getClass().getName() + ".makecsv end!");		
			

			return "success";
		}
		
		@RequestMapping("/AdminCheck")
		public String insertUserInfo(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model)
				throws Exception {

			log.info(this.getClass().getName() + ".AdminCheck start!");
			

			AdminDTO pDTO = null;

			try {
				String admin_code = CmmUtil.nvl(request.getParameter("admin_code"));
				
				log.info("admin_code확인 : " + admin_code);

				pDTO = new AdminDTO();
				
				pDTO.setCode(EncryptUtil.encHashSHA256(admin_code));				

				int res = adminService.AdminCheck(pDTO);

				if (res == 1) {
					model.addAttribute("msg", "인증 되었습니다.");
					model.addAttribute("url", "/Main.do");
					session.setAttribute("SS_ADMIN_CODE",pDTO.getCode());
					
					
				} else {
					model.addAttribute("msg", "권한이 없습니다.");
					model.addAttribute("url", "/Main.do");
				}
			} catch (Exception e) {				
				log.info(e.toString());
				e.printStackTrace();

			} 

			return "redirect";
		}
		
		@RequestMapping(value = "logOut")
		public String logOutTry(HttpSession session, HttpServletRequest request, Model model) throws Exception {
			request.getSession().removeAttribute("SS_ADMIN_CODE");			
			log.info("세션 꺼짐");
			model.addAttribute("msg", "로그아웃 되었습니다.");
			model.addAttribute("url", "/Main.do");

			return "redirect";
		}
		
		@RequestMapping(value ="project/titleDelete")
		public String titleDelete(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
			log.info(this.getClass().getName()+".titleDelete start");
			
			String title = request.getParameter("title");
			
			NewsTitleDTO nDTO = new NewsTitleDTO();
			
			nDTO.setTitle(title);			
			
			
			int res = newsService.titleDelete(nDTO);
			
			if(res == 1) {
				model.addAttribute("msg", "삭제 되었습니다.");
				model.addAttribute("url", "/Main.do");				
				newsService.makeCsv();
				
			}else {
				model.addAttribute("msg", "실패하였습니다.");
				model.addAttribute("url", "/Main.do");
			}
			
			return "redirect";
		}

}
