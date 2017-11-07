package com.beiyun.library.util;

import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beiyun on 2017/11/6.
 * File操作类
 */
public class Files {


    /**
     * 判断文件是否存在
     * @param filePath
     * @return true 存在  false不存在
     */
    public static boolean exists(String filePath){
        return exists(get(filePath));
    }

    /**
     * 判断文件是否存在
     * @param file
     * @return true 存在  false不存在
     */
    public static boolean exists(File file){
        return file != null && file.exists();
    }

    /**
     * 根据路径获取File对象
     * @param filePath 路径
     * @return File
     */
    public static File get(String filePath){
        if(filePath == null || filePath.length() == 0) return null;
        return new File(filePath);
    }

    /**
     * 根据路径获取File对象
     * @param parent 父路径
     * @param child 子路径
     * @return File
     */
    public static File get(String parent,String child){
        if(parent == null || child == null) return null;
        return new File(parent,child);
    }


    /**
     * 根据路径获取File对象
     * @param parent 父目录
     * @param child 子路径
     * @return File
     */
    public static File get(File parent,@NonNull String child){
        if(parent == null || !isDir(parent)) return null;
        return new File(parent,child);
    }


    /**
     * 根据uri创建文件
     * @param uri uri
     * @return File
     */
    public static File get(URI uri){
        if(uri == null) return null;
        return new File(uri);
    }



    /**
     * 判断file是否是文件
     * @param file file
     * @return true 是  false file==null or file不存在 or file不是文件
     */
    public static boolean isFile(File file) {
        return !(file == null || !exists(file)) && file.isFile();
    }


    /**
     * 判断file是否是目录
     * @param file file
     * @return true 是  false file==null or file 不存在 or file 不是目录
     */
    public static boolean isDir(File file){
        return exists(file) && file.isDirectory();
    }


    /**
     * 创建新文件除非该文件已经存在
     * @param file file
     * @return true 创建成功  false 创建失败
     */
    public static boolean createFileExceptExists(File file){
        if(file == null) return false;
        if(exists(file)) return isFile(file);
        if(!createDirExceptExists(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 判断文件是否存在，存在则在创建之前删除
     *
     * @param file 文件
     * @return {@code true}: 创建成功<br>{@code false}: 创建失败
     */
    public static boolean createFileByDeleteOldFile(final File file) {
        if (file == null) return false;
        // 文件存在并且删除失败返回false
        if (file.exists() && !file.delete()) return false;
        // 创建目录失败返回false
        if (!createDirExceptExists(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * 创建文件目录除非已经存在
     * @param dir dir
     * @return true创建成功  false 创建失败
     */
    public static boolean createDirExceptExists(File dir) {
        return !exists(dir) && isDir(dir) && dir.mkdirs();
    }


    /**
     * 删除文件
     * @param file
     * @return true 删除成功  false 删除失败
     */
    public static boolean deleteFile(File file){
        return exists(file) && file.isFile() && file.delete();
    }


    /**
     * 删除目录
     *
     * @param dir 目录
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteDir(File dir){
        if (dir == null) return false;
        // 目录不存在返回true
        if (!dir.exists()) return true;
        // 不是目录返回false
        if (!dir.isDirectory()) return false;
        // 现在文件存在且是文件夹
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!file.delete()) return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir(file)) return false;
                }
            }
        }
        return dir.delete();

    }


    /**
     * 删除目录下所有东西
     *
     * @param dirPath 目录路径
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteAllInDir(final String dirPath) {
        return deleteAllInDir(get(dirPath));
    }

    /**
     * 删除目录下所有东西
     *
     * @param dir 目录
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteAllInDir(final File dir) {
        return deleteFilesInDirWithFilter(dir, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        });
    }

    /**
     * 删除目录下所有文件
     *
     * @param dirPath 目录路径
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFilesInDir(final String dirPath) {
        return deleteFilesInDir(get(dirPath));
    }

    /**
     * 删除目录下所有文件
     *
     * @param dir 目录
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFilesInDir(final File dir) {
        return deleteFilesInDirWithFilter(dir, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
    }

    /**
     * 删除目录下所有过滤的文件
     *
     * @param dirPath 目录路径
     * @param filter  过滤器
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFilesInDirWithFilter(final String dirPath, final FileFilter filter) {
        return deleteFilesInDirWithFilter(get(dirPath), filter);
    }

    /**
     * 删除目录下所有过滤的文件
     *
     * @param dir    目录
     * @param filter 过滤器
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFilesInDirWithFilter(final File dir, final FileFilter filter) {
        if (dir == null) return false;
        // 目录不存在返回true
        if (!dir.exists()) return true;
        // 不是目录返回false
        if (!dir.isDirectory()) return false;
        // 现在文件存在且是文件夹
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (filter.accept(file)) {
                    if (file.isFile()) {
                        if (!file.delete()) return false;
                    } else if (file.isDirectory()) {
                        if (!deleteDir(file)) return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 获取目录下所有文件
     * <p>不递归进子目录</p>
     *
     * @param dirPath 目录路径
     * @return 文件链表
     */
    public static List<File> listFilesInDir(final String dirPath) {
        return listFilesInDir(dirPath, false);
    }

    /**
     * 获取目录下所有文件
     * <p>不递归进子目录</p>
     *
     * @param dir 目录
     * @return 文件链表
     */
    public static List<File> listFilesInDir(final File dir) {
        return listFilesInDir(dir, false);
    }

    /**
     * 获取目录下所有文件
     *
     * @param dirPath     目录路径
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDir(final String dirPath, final boolean isRecursive) {
        return listFilesInDir(get(dirPath), isRecursive);
    }

    /**
     * 获取目录下所有文件
     *
     * @param dir         目录
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDir(final File dir, final boolean isRecursive) {
        return listFilesInDirWithFilter(dir, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        }, isRecursive);
    }

    /**
     * 获取目录下所有过滤的文件
     *
     * @param dir         目录
     * @param filter      过滤器
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(final File dir,
                                                      final FileFilter filter,
                                                      final boolean isRecursive) {
        if (!isDir(dir)) return null;
        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (filter.accept(file)) {
                    list.add(file);
                }
                if (isRecursive && file.isDirectory()) {
                    //noinspection ConstantConditions
                    list.addAll(listFilesInDirWithFilter(file, filter, true));
                }
            }
        }
        return list;
    }



}
