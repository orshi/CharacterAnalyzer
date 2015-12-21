package com.Writer;

import org.apache.log4j.Logger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Shulei on 2015/12/21.
 */
public class ResultWriter {

    public static Logger logger= Logger.getLogger(ResultWriter.class);

    /**
     * 文本处理结果写入文件
     * @param content 词频统计过后的最终结果
     * @param filePath 结果文本内容
     * @return 是否处理成功
     */
    public boolean WriteContentToFile(Map<String,Integer> content,String filePath){

        logger.info("处理结果写入文件");
        FileWriter fr=null;
        BufferedWriter br=null;
        try{
            fr=new FileWriter(filePath,false);
            br=new BufferedWriter(fr);

            Iterator<Map.Entry<String,Integer>> it =content.entrySet().iterator();
            while(it.hasNext()){

                Map.Entry<String,Integer> entry=it.next();
                br.write(entry.getKey()+" "+entry.getValue()+"\r\n");
            }
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }
        finally {

            if(null!=br){
                try{
                    br.close();
                }catch ( Exception exbr){
                    exbr.printStackTrace();
                    logger.error(exbr.getMessage());
                }
            }
            if(null!=fr){
                try{
                    fr.close();
                }catch (Exception exfr){
                    exfr.printStackTrace();
                    logger.error("处理出错"+exfr.getMessage());
                }
            }
        }
    }
}
