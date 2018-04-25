package com.beiyun.library.util;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.File;

/**
 * Created by beiyun on 2018/4/12.
 * ProjectHelper
 */
public class Videos {


    /**
     * 获取视频文件时长
     * @param file
     * @return
     */
    public static int getDuration(File file) {
        try {
            return MediaPlayer.create(Apps.getCurrentActivity(), Uri.fromFile(file)).getDuration();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取视频文件缩略图
     * @param videoPath
     * @return
     */
    public static Bitmap getVideoThumbnail(String videoPath) {
        MediaMetadataRetriever media =new MediaMetadataRetriever();
        media.setDataSource(videoPath);
        return media.getFrameAtTime();
    }


}
