package com.example.bituls.sms_trial;

/**
 * Created by bituls on 4/6/18.
 */

import  android.content.Context ;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import  com.android.volley.Request ;
import  com.android.volley.RequestQueue ;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.ImageLoader;
import  com.android.volley.toolbox.Volley ;
import org.apache.http.impl.client.DefaultHttpClient;

public class Erpconnect {
    private DefaultHttpClient mHttpClient;
    private static Erpconnect mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;

    private Erpconnect(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized Erpconnect getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Erpconnect(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mHttpClient = new DefaultHttpClient();
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext(), new HttpClientStack(mHttpClient));
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }


}
