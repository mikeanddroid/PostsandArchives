package com.mike.givemewingzz.postsandarchives.core;

import android.app.Application;
import android.content.Context;

import com.mike.givemewingzz.postsandarchives.utils.DBHelper;
import com.mike.givemewingzz.postsandarchives.utils.EventBusSingleton;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.L;
import com.squareup.otto.Bus;

import io.realm.Realm;

/**
 * Created by GiveMeWingzz on 4/3/2017.
 */

public class PostsApplication extends Application {

    public Context context;
    public static PostsApplication application;

    // Hold reference to event bus.
    Bus bus = EventBusSingleton.getBus();

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        context = this.getApplicationContext();

        // Setup Realm for database interaction.
        Realm.setDefaultConfiguration(DBHelper.getRealmConfig(this));

        initImageLoader(context);

    }

    // Inititalizing Imageloader
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.MAX_PRIORITY)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .memoryCacheSizePercentage(25).threadPoolSize(5)
                .build();

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
        L.writeLogs(false);
    }

    public static PostsApplication getInstance() {
        return application;
    }
}
