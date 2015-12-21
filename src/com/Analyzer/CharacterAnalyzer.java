package com.Analyzer;

import net.paoding.analysis.analyzer.PaodingAnalyzer;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

import java.io.StringReader;
import java.util.*;

/**
 * Created by Shulei on 2015/12/20.
 */
public class CharacterAnalyzer {

    private static Logger logger = Logger.getLogger(CharacterAnalyzer.class);

    /**
     * 对文本进行分词
     * @param fileContent 需要分词的文本内容
     * @return 分词结果，词与词之间用'/'分隔
     */
    public String ContentCutter(String fileContent) {
        logger.info("进行分词");
        PaodingAnalyzer analyzer = new PaodingAnalyzer();
        try {
            StringBuilder sb = new StringBuilder();
            TokenStream ts = analyzer.tokenStream("", new StringReader(fileContent));
            Token token;
            sb.setLength(0);
            while ((token = ts.next()) != null) {
                sb.append(token.termText()).append("/");
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }

            System.out.print(sb.toString());
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return "error";
        }
    }

    /**
     * 去除英文和标点
     * @param content 文本内容
     * @return 处理后的文本
     */
    public String RemoveIllegalCharacter(String content) {
        logger.info("去除非法字符");
        try {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < content.length(); i++) {
                if ((content.charAt(i) + "").getBytes().length > 1) {
                    sb.append(content.charAt(i));
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 统计文本词频
     * @param cuttedContentWithSlash 分词之后的文本，关键词之间以'/'间隔
     * @return 关键词和词频结果的MAP结构
     */
    public Map<String, Integer> GetWordsFrequency(String cuttedContentWithSlash) {

        logger.info("统计词频");
        try {

            Map<String, Integer> wordsFrequencyList = new HashMap<String, Integer>();

            String[] wordsList = cuttedContentWithSlash.split("/");

            for (String item : wordsList) {

                if (wordsFrequencyList.keySet().contains(item)) {

                    wordsFrequencyList.put(item, wordsFrequencyList.get(item) + 1);
                } else {

                    wordsFrequencyList.put(item, 1);
                }
            }

            return wordsFrequencyList;

        } catch (Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 去停用词
     * @param stopWords 停用词集合
     * @param freqList 词频统计后后的文本内容
     * @return
     */
    public Map<String, Integer> RemoveStopWords(Set<String> stopWords, Map<String, Integer> freqList) {
        logger.info("去停用词");
        try {
            Iterator<Map.Entry<String, Integer>> entries = freqList.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Integer> item = entries.next();
                if (stopWords.contains(item.getKey())) {

                    entries.remove();
                }
            }

            freqList = MapSort(freqList);

            return freqList;
        } catch (Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 对Map安value的大小进行排序
     * @param oldMap 排序前的Map
     * @return 排序后的Map
     */
    public Map MapSort(Map oldMap) {
        try {
            ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

                @Override
                public int compare(Map.Entry<String, Integer> arg0,
                                   Map.Entry<String, Integer> arg1) {
                    return arg1.getValue() - arg0.getValue();
                }
            });
            Map newMap = new LinkedHashMap();
            for (int i = 0; i < list.size(); i++) {
                newMap.put(list.get(i).getKey(), list.get(i).getValue());
            }
            return newMap;
        } catch (Exception e) {

            e.printStackTrace();
            logger.info(e.getMessage());
            return null;
        }
    }
}
