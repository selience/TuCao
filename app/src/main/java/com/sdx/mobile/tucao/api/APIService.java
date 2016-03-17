package com.sdx.mobile.tucao.api;

import com.sdx.mobile.tucao.model.CommentModel;
import com.sdx.mobile.tucao.model.HttpResult;
import com.sdx.mobile.tucao.model.TopicDetail;
import com.sdx.mobile.tucao.model.TopicModel;
import com.sdx.mobile.tucao.model.TopicWord;
import com.sdx.mobile.tucao.model.UserModel;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Name: APIService
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/7 15:37
 * Desc:
 */
public interface APIService {

    /**
     * 图片上传接口 :参数 type：图片类型
     *
     * @param query
     * @return
     */
    @Multipart
    @POST("common/uploadImg.html")
    Observable<HttpResult<String>> upload(@Part("image\"; filename=\"image.png\"")
                                          RequestBody file,
                                          @QueryMap Map<String, String> query);

    /**
     * 注册
     */
    @GET("user/register.html")
    Observable<HttpResult<UserModel>> userRegister(@QueryMap Map<String, String> query);

    /**
     * 首页数据
     */
    @GET("common/indexData.html")
    Observable<HttpResult<List<TopicModel>>> obtainIndexData(@QueryMap Map<String, String> query);

    /**
     * 评论列表
     */
    @GET("comment/list.html")
    Observable<HttpResult<List<CommentModel>>> obtainCommentList(@QueryMap Map<String, String> query);

    /**
     * 赞某个吐槽
     */
    @GET("tucao/up.html")
    Observable<HttpResult<String>> handleUpTopic(@QueryMap Map<String, String> query);

    /**
     * 踩某个吐槽
     */
    @GET("tucao/down.html")
    Observable<HttpResult<String>> handleDownTopic(@QueryMap Map<String, String> query);

    /**
     * 发表吐槽
     */
    @FormUrlEncoded
    @POST("tucao/publish.html")
    Observable<HttpResult<String>> publishTopic(@FieldMap Map<String, String> field,
                                                @QueryMap Map<String, String> query);

    /**
     * 主题详情
     *
     * @param query
     * @return
     */
    @GET("subject/detail.html")
    Observable<HttpResult<TopicDetail>> obtainTopicDetail(@QueryMap Map<String, String> query);

    /**
     * 发表评论
     *
     * @param query
     * @return
     */
    @FormUrlEncoded
    @POST("comment/publish.html")
    Observable<HttpResult<String>> publishComment(@FieldMap Map<String, String> field,
                                                  @QueryMap Map<String, String> query);

    /**
     * 搜索主题
     *
     * @param field
     * @param query
     * @return
     */
    @FormUrlEncoded
    @POST("subject/search.html")
    Observable<HttpResult<List<TopicWord>>> searchTopicList(@FieldMap Map<String, String> field,
                                                            @QueryMap Map<String, String> query);
}
