package cn.edu.gdaib.fulongnews.utils;

import android.content.Context;
import android.os.Environment;

/**
 * 文件操作类
 * author: ZYongY.
 * date: 2016/12/14.
 */

public class FileUtil {
    //创建根缓存目录
    @SuppressWarnings("ConstantConditions")
    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }
}
