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
import poly.dto.NlpDTO;
import poly.service.IAdminService;
import poly.service.INewsService;
import poly.service.INlpService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Controller
public class NewsController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "NewsService")
	private INewsService newsService;

	@Resource(name = "AdminService")
	private IAdminService adminService;

	@Resource(name = "NlpService")
	private INlpService nlpService;

	// 메인화면 request
	@RequestMapping(value = "Main")
	public String Main(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		log.info(this.getClass());

		if (session.getAttribute("SS_ADMIN_CODE") != null) {
			List<NewsTitleDTO> nList = new ArrayList<NewsTitleDTO>();

			nList = newsService.getTop50();
			model.addAttribute("nList", nList);
		}
		List<NewsDTO> rList = newsService.getNews();

		if (rList == null) {
			rList = new ArrayList<NewsDTO>();
		}

		model.addAttribute("rList", rList);

		return "/Project/Main";
	}

	// 오피니언 마이닝
	@RequestMapping(value = "project/getOpinion")
	@ResponseBody
	public List<Integer> getOpinion(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + " .getOpinion Start!");

		List<NewsDTO> rList = newsService.getNews();

		if (rList == null) {
			rList = new ArrayList<NewsDTO>();
		}

		List<Integer> pList = new ArrayList<>();

		for (int i = 0; i < rList.size(); i++) {
			String text_message = CmmUtil.nvl(rList.get(i).getTitle());

			log.info("test_Message : " + text_message);

			NlpDTO pDTO = new NlpDTO();

			pDTO.setWord(text_message);

			int point = nlpService.preProcessWordAnalysisForMind(pDTO);

			// point 가 -4보다 작은경우
			if (point <= -4) {
				point = 1;
				// point 가 -4부터 -2사이인 경우
			} else if (point > -4 && point < -2) {
				point = 15;
				// point 가 -2부터 0사이인 경우
			} else if (point >= -2 && point < 0) {
				point = 40;
				// point가 0인경우
			} else if (point == 0) {
				point = 50;
				// 0 ~2미만
			} else if (point > 0 && point <= 3) {
				point = 60;
				// 2~4미만
			} else if (point > 3 && point <= 4) {
				point = 75;
				// 4이상
			} else {
				point = 95;
			}

			pList.add(point);
		}

		return pList;
	}

	@RequestMapping(value = "WordCloudTest")
	public String WordCloudTest() {
		log.info(this.getClass());

		return "/Project/WordCloudTest";
	}

	@RequestMapping(value = "ModalTest")
	public String ModalTest() {
		log.info(this.getClass());

		return "/Project/ModalTest";
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
	public String insertUserInfo(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {

		log.info(this.getClass().getName() + ".AdminCheck start!");

		AdminDTO pDTO = null;

		try {
			String admin_code = CmmUtil.nvl(request.getParameter("admin_code"));
			String admin_name = CmmUtil.nvl(request.getParameter("admin_code"));

			log.info("admin_code확인 : " + admin_code);

			pDTO = new AdminDTO();
			
			pDTO.setName(admin_name);

			pDTO.setCode(EncryptUtil.encHashSHA256(admin_code));

			int res = adminService.AdminCheck(pDTO);

			if (res == 1) {
				model.addAttribute("msg", "인증 되었습니다.");
				model.addAttribute("url", "/Main.do");
				session.setAttribute("SS_ADMIN_CODE", pDTO.getCode());

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

	@RequestMapping(value = "project/titleDelete")
	public String titleDelete(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		log.info(this.getClass().getName() + ".titleDelete start");

		String title = request.getParameter("title");

		NewsTitleDTO nDTO = new NewsTitleDTO();

		nDTO.setTitle(title);

		int res = newsService.titleDelete(nDTO);

		if (res == 1) {
			model.addAttribute("msg", "삭제 되었습니다.");
			model.addAttribute("url", "/Main.do");
			newsService.makeCsv();

		} else {
			model.addAttribute("msg", "실패하였습니다.");
			model.addAttribute("url", "/Main.do");
		}

		return "redirect";
	}

}
