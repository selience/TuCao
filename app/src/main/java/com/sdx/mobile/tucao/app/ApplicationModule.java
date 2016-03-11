package com.sdx.mobile.tucao.app;

import com.sdx.mobile.tucao.constant.Config;
import com.sdx.mobile.tucao.retrofit.FastjsonConverterFactory;
import com.sdx.mobile.tucao.retrofit.LoggingInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Name: ApplicationModule
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2015/12/15 13:46
 * Desc:
 */
public class ApplicationModule {

    private Retrofit mRetrofit;
    private OkHttpClient okHttpClient;

    private static class SingtonInstance {
        private static ApplicationModule instance = new ApplicationModule();
    }

    public static ApplicationModule getInstance() {
        return SingtonInstance.instance;
    }

    private ApplicationModule() {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
        mRetrofit = buildRetrofit();
    }

    private Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(FastjsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Config.BASE_URL)
                .build();
    }

    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }
}
