package poly.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.NlpDTO;
import poly.persistance.mapper.INlpMapper;
import poly.service.INlpService;
import poly.util.CmmUtil;
import poly.util.StringUtil;

@Service("NlpService")
public class NlpService implements INlpService {

	@Resource(name = "NlpMapper")
	private INlpMapper nlpMapper;

	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	// NLP_DIC를 ㄱㄴㄷㄹ 순으로 나눠서 저장한 이유는 전체 약 몇만건의 데이터를 조회하는 것보다
	// 일정한 범위를 지정하여 데이터 조회 횟수를 감소하기 위해서 나눔
	// 가나다 순으로 저장될 데이터 사전들(가~하까지)
	private Map<String, List<NlpDTO>> NLP_DIC = new HashMap<String, List<NlpDTO>>();

	/**
	 * PostConstruct은 처음 한번만 실행되도록 하는 어노테이션
	 * 
	 * 데이터 사전 변수 생성을 위한 함수 최초 스프링 호출이 발생할때 함수가 실행 실행 이후 더이상 실행은 하지 않는다.
	 * 
	 * NLP_DIC 변수에 값을 저장함
	 */
	@Override
	@PostConstruct
	public void getWord() throws Exception {

		log.info(this.getClass().getName() + ".getWord start!");

		NlpDTO pDTO = new NlpDTO();

		List<NlpDTO> rList = null;

		pDTO.setWord("ㄱ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㄱ", rList);

		pDTO.setWord("ㄴ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㄴ", rList);

		pDTO.setWord("ㄷ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㄷ", rList);

		pDTO.setWord("ㄹ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㄹ", rList);

		pDTO.setWord("ㅁ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅁ", rList);

		pDTO.setWord("ㅂ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅂ", rList);

		pDTO.setWord("ㅅ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅅ", rList);

		pDTO.setWord("ㅇ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅇ", rList);

		pDTO.setWord("ㅈ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅈ", rList);

		pDTO.setWord("ㅊ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅊ", rList);

		pDTO.setWord("ㅋ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅋ", rList);

		pDTO.setWord("ㅌ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅌ", rList);

		pDTO.setWord("ㅍ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅍ", rList);

		pDTO.setWord("ㅎ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅎ", rList);

		pDTO.setWord("ㄲ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㄲ", rList);

		pDTO.setWord("ㄸ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㄸ", rList);

		pDTO.setWord("ㅃ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅃ", rList);

		pDTO.setWord("ㅆ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅆ", rList);

		pDTO.setWord("ㅉ");
		rList = nlpMapper.getWord(pDTO);

		if (rList == null) {
			rList = new ArrayList<NlpDTO>();
		}

		NLP_DIC.put("ㅉ", rList);

		log.info(this.getClass().getName() + ".getWord End!");
	}

	/**
	 * 감정 분석을 위한 문장 나누는 전처리 단계
	 * 
	 * @param 분석할 문장 정보
	 * @return 감정 분석 평가 결과
	 */
	@Override
	public int preProcessWordAnalysisForMind(NlpDTO pDTO) throws Exception {

		log.info(this.getClass().getName() + ".WordAnalysisForMind start!");

		int res = 0;

		String text = CmmUtil.nvl(pDTO.getWord()).replaceAll("[^\\uAC00-\\uD7A3xfe0-9a-zA-Z\\\\s]", " ");

		text = text.replaceAll("\\s{2,}", " ");

		log.info("text : " + text);

		String[] textArr = text.split(" ");		

		log.info("textArr.length : " + textArr.length);

		int maxCnt = textArr.length;

		log.info("###textArr.length : " + textArr.length);

		log.info("###maxCnt : " + maxCnt);

		for (int i = 0; i < maxCnt; i++) {

			String firstWord2 = textArr[i].substring(0, 1);
			log.info("firstWord2 : " + firstWord2);
			String text2 = "";
			log.info("###반복 횟수 : " + i);

			if (i == maxCnt - 1) {
				text2 = textArr[i];
			} else if (i == maxCnt - 2) {
				text2 = textArr[i] + " " + textArr[i + 1];
			} else {
				text2 = textArr[i] + " " + textArr[i + 1] + " " + textArr[i + 2];
			}

			res += WordAnalysisForMind(firstWord2, text2);
		}

		log.info("Res : " + res);

		log.info(this.getClass().getName() + ".WordAnalysisForMind End!");

		return res;
	}

	/**
	 * 감정 분석 처리 함수
	 * 
	 * @param 분석할 문장의 첫글자
	 * @param 분석할 문장
	 * @return 감정 분석 평가 결과
	 */
	@Override
    public int WordAnalysisForMind(String firstWord, String text) throws Exception {
        int res = 0;

        log.info("###############WordAnalysisForMind 서비스");
        log.info("firstWord : " + firstWord);
        log.info("text : " + text);

        String dicType = StringUtil.getFirstWord(firstWord);

        log.info("DIC type : " + dicType);

        if (dicType.length() > 0) {

            List<NlpDTO> rList = NLP_DIC.get(StringUtil.getFirstWord(firstWord));
            log.info(rList.size());

            if (rList == null) {
                rList = new ArrayList<NlpDTO>();
            }

            Iterator<NlpDTO> it = rList.iterator();

            while (it.hasNext()) {
                NlpDTO rDTO = it.next();

                if (rDTO == null) {
                    rDTO = new NlpDTO();
                }

                if (text.indexOf(CmmUtil.nvl(rDTO.getWord())) > -1) {
                    log.info("DIC-word : " + CmmUtil.nvl(rDTO.getWord()));
                    log.info("DIC-word getPolarity : " + CmmUtil.nvl(rDTO.getPolarity()));
                    log.info("text : " + text);
                    res += Integer.parseInt(CmmUtil.nvl(rDTO.getPolarity(), "0"));

                    break;
                }
            }
        }

        return res;
    }

}
