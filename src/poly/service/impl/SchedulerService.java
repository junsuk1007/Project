package poly.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import poly.service.INewsService;

@Component
@Service("SchedulerService")
public class SchedulerService implements ISchedulerService {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "NewsService")
	private INewsService newsService;

	@Scheduled(cron = "0 0 0/6 * * ?")
	@Override
	public void sTest() throws Exception {

		log.info(this.getClass().getName() + "크롤링 스케쥴러 작동");
		newsService.CollectNews();

		log.info(this.getClass().getName() + "제목 분류 스케쥴러 작동");
		newsService.createTitleCollection();
	}

	@Scheduled(cron = "0 0 12 ? * WED")
	@Override
	public void sTest2() throws Exception {
		
		log.info(this.getClass().getName() + "csv파일 생성 스케쥴러 작동");
		newsService.makeCsv();
		
	}
	

}
