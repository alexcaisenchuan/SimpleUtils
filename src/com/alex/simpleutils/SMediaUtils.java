package com.alex.simpleutils;

import java.io.IOException;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;

/**
 * 媒体工具类
 * @author caisenchuan
 */
public class SMediaUtils {

    private static final String TAG = SMediaUtils.class.getSimpleName();

    /**
     * 播放器
     */
    public static class Player implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener {

        public MediaPlayer mediaPlayer; // 媒体播放器
        private Context mContext;
        // 初始化播放器
        public Player(Context context) {
            super();
            
            mContext = context;
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
                mediaPlayer.setOnBufferingUpdateListener(this);
                mediaPlayer.setOnPreparedListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void play() {
            mediaPlayer.start();
        }

        /**
         * 
         * @param url
         *            url地址
         */
        public void playUrl(String url) {
            SLog.d(TAG, "play : %s", url);
            try {
                mediaPlayer.reset();
                Uri uri = Uri.parse(url);
                mediaPlayer.setDataSource(mContext, uri); // 设置数据源
                mediaPlayer.prepare(); // prepare自动播放
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 暂停
        public void pause() {
            mediaPlayer.pause();
        }

        // 停止
        public void stop() {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }

        // 播放准备
        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.start();
            SLog.d(TAG, "onPrepared");
        }

        // 播放完成
        @Override
        public void onCompletion(MediaPlayer mp) {
            SLog.d(TAG, "onCompletion");
        }

        /**
         * 缓冲更新
         */
        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            SLog.d(TAG, "onBufferingUpdate, " + percent + " buffer");
        }
    }
    
    /**
     * 播放音乐
     */
    public static Player playMusicByUrl(Context context, final String url) {
        final Player musicPlayer = new Player(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                musicPlayer.playUrl(url);
            }
        }).start();
        return musicPlayer;
    }
}
