package com.Context;

import com.Reader.ContentReader;

import java.util.Set;

/**
 * 配置类，保存一些基本的配置项
 */
public class WorkingContext {

    private Set<String> stopWords=null;

    //停用词字典路径
    private String stopWordsDicPath=null;

    //原始文本文件路径
    private String rawFileDirectory=null;

    //处理结果写入路径
    private String outPutDirecory=null;
    private static final WorkingContext workingContext = new WorkingContext();

    public static WorkingContext getWorkingContext() {
        return workingContext;
    }

    private WorkingContext(){
        this.stopWordsDicPath="Q:\\JavaWorkSpace\\CharacterAnalyzer\\Text\\StopWords.txt";
        this.rawFileDirectory="./RawFiles/";
        this.outPutDirecory="./OutPut/";
        ContentReader reader=new ContentReader();
        setStopWords(reader.ReadLineIntoSet(stopWordsDicPath));
    }

    public Set<String> getStopWords() {
        return stopWords;
    }

    private void setStopWords(Set<String> stopWords) {
        this.stopWords = stopWords;
    }

    public String getStopWordsDicPath() {
        return stopWordsDicPath;
    }

    public void setStopWordsDicPath(String stopWordsDicPath) {
        this.stopWordsDicPath = stopWordsDicPath;
    }

    public String getRawFileDirectory() {
        return rawFileDirectory;
    }

    public void setRawFileDirectory(String rawFileDirectory) {
        this.rawFileDirectory = rawFileDirectory;
    }

    public String getOutPutDirecory() {
        return outPutDirecory;
    }

    public void setOutPutDirecory(String outPutDirecory) {
        this.outPutDirecory = outPutDirecory;
    }
}
