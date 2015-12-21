package com.Loader;

import com.Context.WorkingContext;
import com.Processors.FullFlowTask;
import com.Reader.ContentReader;
import com.WorkController.ProcessingQueue;

import java.io.File;

/**
 * Created by Shulei on 2015/12/21.
 */
public class StartUp {


    //对指定路径下的所有文本进行分析
    public static void main(String[] argues) {

        WorkingContext context=WorkingContext.getWorkingContext();
        File directory=new File(context.getRawFileDirectory());
        File[]files= directory.listFiles();
        ContentReader reader= new ContentReader();
        for(File file :files){

            String fileContent=reader.ReadContentFromFile(file.getPath());
            String fileName=file.getName();
            FullFlowTask task=new FullFlowTask(fileContent,fileName);
            ProcessingQueue.getQueueContext().Submit(task);
        }

    }
}
