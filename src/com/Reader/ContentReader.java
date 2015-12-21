package com.Reader;

import com.Analyzer.CharacterAnalyzer;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 读取文本内容，整理成合适的数据结构
 */
public class ContentReader {

    private static Logger logger = Logger.getLogger(CharacterAnalyzer.class);

    /**
     * Read file content.
     *
     * @param filePath file path.
     * @return file content.
     */
    public String ReadContentFromFile(String filePath) {

        StringBuilder fileContent = new StringBuilder();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String lineString = null;
            while ((lineString = br.readLine()) != null) {
                fileContent.append(lineString);
            }

            return fileContent.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        } finally {
            try {
                if (null != br) {
                    br.close();
                }
            } catch (Exception brEx) {
                brEx.printStackTrace();
                logger.error(brEx.getMessage());
            }
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception frEx) {
                frEx.printStackTrace();
                logger.error(frEx.getMessage());
            }
        }
    }

    /**
     * 每一行的内容作为set的项，用于读取停用词
     * @param filePath 文件路径
     * @return 结果集
     */
    public Set<String> ReadLineIntoSet(String filePath) {
        Set stopWordsSet = new HashSet();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String lineConent = null;
            while (null != (lineConent = br.readLine())) {

                stopWordsSet.add(lineConent);
            }

            return stopWordsSet;

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            return null;
        } finally {

            try {
                if (null != br) {
                    br.close();
                }
            } catch (Exception brEx) {
                brEx.printStackTrace();
                logger.error(brEx.getMessage());
            }
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception frEx) {
                frEx.printStackTrace();
                logger.error(frEx.getMessage());
            }
        }
    }
}
