package com.xxh.listviewcommonadapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


/**
 * Created by 解晓辉 on 2017/2/15.
 * 作用：图片加载的工具类
 */

public class MyImageLoader {
    /**
     * 显示图片
     *
     * @param context   上下文对象
     * @param path      加载的路径
     * @param imageView 需要显示图片的控件
     * @param errorId   记载错误显示的图片
     * @param placeId   加载中的占位图
     */
    public void displayImage(Context context, String path, ImageView imageView, int errorId, int placeId) {
        Glide.with(context)                             //配置上下文
                .load(path)      //设置图片路径(文件名包含%符号 无法识别和显示)
                .error(errorId)           //设置错误图片
                .placeholder(placeId)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);

    }


    /**
     * 重载的方法 显示图片，但是不显示错误图片和占位图
     *
     * @param context
     * @param url
     * @param imageView
     */
    public void displayImage(Context context, String url, ImageView imageView) {
        Glide.with(context)                             //配置上下文
                .load(url)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .centerCrop()
                .into(imageView);
    }

    public void displayIamgeNoScale(Context context, String url, ImageView imageView) {
        Glide.with(context)                             //配置上下文
                .load(url)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }


    /**
     * @param context
     * @param url
     * @param imageView
     * @param width     设置显示的宽
     * @param height    设置显示的高
     */
    public void displayImageOverride(Context context, String url, ImageView imageView, int width, int height) {
        Glide.with(context)                             //配置上下文
                .load(url)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .override(width, height)
                .into(imageView);
    }

    //单例模式
    private static MyImageLoader instance;


    public static MyImageLoader getInstance() {
        if (instance == null) {
            instance = new MyImageLoader();
        }
        return instance;
    }

    private MyImageLoader() {

    }
    
}
