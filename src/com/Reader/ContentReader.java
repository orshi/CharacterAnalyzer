package com.Reader;

import com.Analyzer.CharacterAnalyzer;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * ��ȡ�ı����ݣ�����ɺ��ʵ����ݽṹ
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
     * ÿһ�е�������Ϊset������ڶ�ȡͣ�ô�
     * @param filePath �ļ�·��
     * @return �����
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
