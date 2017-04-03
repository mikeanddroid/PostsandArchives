package com.mike.givemewingzz.postsandarchives.apiservice;

import com.mike.givemewingzz.postsandarchives.appmodel.Token;
import com.mike.givemewingzz.postsandarchives.core.PostsApplication;

public class RequestInterceptor {

    public static final String TAG = RequestInterceptor.class.getSimpleName();
    public static final retrofit.RequestInterceptor mIntercepter = new BaseUrlInterceptor();

    private static class BaseUrlInterceptor extends BaseRequestInterceptor {

        @Override
        public void intercept(retrofit.RequestInterceptor.RequestFacade request) {

            Token token = new Token();
            token.load(PostsApplication.getInstance().getApplicationContext());

            request.addEncodedQueryParam("access_token", token.getAccessToken());
        }
    }

}
