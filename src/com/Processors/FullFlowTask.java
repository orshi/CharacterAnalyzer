package com.Processors;

import com.Analyzer.CharacterAnalyzer;
import com.Context.WorkingContext;
import com.Writer.ResultWriter;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Set;

/**
 * 文本处理工作流类
 */
public class FullFlowTask implements Runnable {
 public  static Logger logger=Logger.getLogger(FullFlowTask.class);

    private String fileContent;
    private String fileName;

    private FullFlowTask(){

    }
    public FullFlowTask(String fileContent,String fileName){

        logger.info("FullFlowTask初始化");
        this.fileContent=fileContent;
        this.fileName=fileName;
    }
    @Override
    public void run() {
        process();
    }

    public String getFileContent() {
        return fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    /**
     * 对文本的处理流程
     */
    public void process(){
        try {

            logger.info("处理文本...");
            WorkingContext context = WorkingContext.getWorkingContext();

            CharacterAnalyzer analyzer = new CharacterAnalyzer();
            fileContent = analyzer.RemoveIllegalCharacter(fileContent);
            fileContent = analyzer.ContentCutter(fileContent);
            Map<String, Integer> freqList = analyzer.GetWordsFrequency(fileContent);
            Set<String> stopWords = context.getStopWords();
            Map<String, Integer> updatedFreqList = analyzer.RemoveStopWords(stopWords, freqList);

            ResultWriter writer = new ResultWriter();
            boolean isFinished = writer.WriteContentToFile(updatedFreqList, context.getOutPutDirecory() + "Result_" + fileName);
        }catch (Exception e){

            e.printStackTrace();
            logger.error("文件："+fileName+"处理异常。Error:"+e.getMessage());
        }
    }
}
