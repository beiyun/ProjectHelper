package com.beiyun.library.util;

import java.io.File;
import java.io.IOException;

/**
 * Created by beiyun on 2017/11/6.
 */
public class Files {


    public static boolean exists(File file){
        return file.exists();
    }


    public static File createNewFile(String path){
        File file = new File(path);
        if(!file.exists()){
            try {
                boolean newFile = file.createNewFile();
                if(newFile){
                    return file;
                }else{
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }



}
