package com.sdx.mobile.tucao;

import com.sdx.mobile.tucao.model.TopicModel;
import com.sdx.mobile.tucao.util.DebugLog;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        //assertEquals(4, 2 + 2);

        String s = "[{\"id\":\"145\",\"sid\":\"137\",\"title\":\"Sterling\\u7535\\u8d1d\\u53f8Ray4\\u56db\\u5f26Ray5\\u4e94\\u5f26\\u5370\\u5c3c\\u8fdb\\u53e3\\u7535\\u8d1d\\u65af\",\"uid\":\"47\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/e3\\/2e\\/e32e3e81d44cb38ba79bde6ec1d1552c.jpg\",\"nick_name\":\"\\u60c5\\u8bdd\\u90fd\\u817b\\u4e86\\u7231\\u8fd8\\u600e\\u4e48\\u633d\",\"up_count\":\"17\",\"text\":\"\\u8bdd\\u8bf4\\u5f88\\u591a\\u5730\\u65b9\\u8c8c\\u4f3c\\u5531\\u7684\\u8282\\u594f\\u4e0d\\u5bf9\\u554a\",\"imgs\":[\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/6f\\/44\\/6f44e865d0fcc97bae4bb9f61c87f098.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/ff\\/47\\/ff47fda801b53a6835981b131fddca08.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/17\\/68\\/17689850231316b551ee8050c09eb410.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/9d\\/96\\/9d96fb3f3b0f10d2f2ddd254114d5d11.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/8e\\/f2\\/8ef2cc42fe798d17162d226b777edf66.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/84\\/98\\/8498de9c94b5c788e1efc2c1b49623ae.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/8f\\/d0\\/8fd0cd94ab1c76f152ae578228a052fb.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/6b\\/67\\/6b673334a16023253f6ba6e2e34610c9.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/ae\\/74\\/ae74c64eb35a852753689dc036e25099.jpg\"],\"add_time\":\"2016-03-08 13:15:03\",\"comment_count\":\"18\",\"comment_list\":[{\"id\":\"7305\",\"text\":\"\\u6211\\u5b66\\u8fc7~\\u6709\\u96be\\u5ea6\\u8c31\\u5b50..\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/f0\\/d4\\/f0d4a6e8c455f9f2005029e56012cbb0.jpg\",\"nick_name\":\"\\u65e0\\u804a\\u5c4e\\u4e86\"},{\"id\":\"7135\",\"text\":\"\\u8fd9\\u5bb6\\u4f19\\u2026\\u2026\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/01\\/53\\/01531c00ddc033ba8d2cdfc3c1fc5a56.jpg\",\"nick_name\":\"\\u5564\\u9152\\u5473\\u725b\\u5976\"}],\"comment_max_id\":\"7135\"},{\"id\":\"173\",\"sid\":\"73\",\"title\":\"IBANEZ\\u4f9d\\u73ed\\u5a1cGRG150\\u7535\\u5409\\u4ed6\",\"uid\":\"88\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/30\\/81\\/3081d8a5be8972cb320a939b17e6ccd8.jpg\",\"nick_name\":\"\\u4e00\\u70b9\\u6731\\u7802\",\"up_count\":\"13\",\"text\":\"\\u55ef\\u55ef\\uff0c\\u8c22\\u8c22\\u6307\\u5bfc\\uff0c\\u4ee5\\u540e\\u591a\\u591a\\u6307\\u6559\\u54c8\\uff01\",\"imgs\":[\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/45\\/8e\\/458e14c786dd8daaaf9a4697da4c2983.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/eb\\/5a\\/eb5ac399902209f24d874cbc85f4aa66.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/33\\/e5\\/33e50c6aa427d935ab7ec45491fb2647.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/63\\/1f\\/631f7e04bb9e8bd5c8684da7c5b09b92.jpg\"],\"add_time\":\"2016-03-08 13:15:03\",\"comment_count\":\"20\",\"comment_list\":[{\"id\":\"7267\",\"text\":\"\\u5475\\u5475....\\u4e0d\\u9519\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/16\\/bb\\/16bb5ab079b7f85dd2a06b27f669bac7.jpg\",\"nick_name\":\"\\u5b64\\u72ec\\u60a3\\u8005\"},{\"id\":\"7120\",\"text\":\"\\u63d0\\u793a:\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/92\\/38\\/9238a8936e15168a0dc74caa657a52c6.jpg\",\"nick_name\":\"\\u7b11\\u886c\\u5fc3\\u9178 .\"}],\"comment_max_id\":\"7120\"},{\"id\":\"174\",\"sid\":\"112\",\"title\":\"\\u4f0a\\u65af\\u7279\\u66fcEastman AR810CE \\u624b\\u5de5\\u7235\\u58eb\\u5409\\u4ed6\",\"uid\":\"148\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/f1\\/b3\\/f1b358209662d94aa2ae9512a2469636.jpg\",\"nick_name\":\"\\u4eba\\u8d70\\u8336\\u81ea\\u51c9\",\"up_count\":\"13\",\"text\":\"\\u6709\\u4e9b\\u4e1c\\u897f\\u4e0d\\u662f\\u7edd\\u5bf9\\u6027\\uff0c\\u4fdd\\u6301\\u826f\\u597d\\u7684\\u7ec3\\u4e60\\u4e60\\u60ef\\u5c31\\u884c\\u3002\",\"imgs\":[\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/54\\/2f\\/542fbde4371e109f42c931ec444f4158.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/5c\\/94\\/5c9400d7cb1d8fae8ed89c3ea2795152.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/27\\/74\\/27741f3b32957f51d90ebc207f5c3584.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/ea\\/c7\\/eac782e6aabec121ca8825d7a19b47ac.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/b2\\/76\\/b276aeea06915b64fe324274bf24e4ef.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/08\\/a2\\/08a2498010aa8dfc8500a944fdf1e8b9.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/08\\/1b\\/081baa716229bbbb684177f4f51c12cc.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/95\\/da\\/95dae3d1b3780ea3a1c70c7920aa789c.jpg\"],\"add_time\":\"2016-03-08 13:15:03\",\"comment_count\":\"23\",\"comment_list\":[{\"id\":\"7485\",\"text\":\"\\u4e09\\u4f4d\\u5c0f\\u670b\\u53cb\\u5927\\u54e5\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/8f\\/21\\/8f21f18ec02f7c59c905b593247333d8.jpg\",\"nick_name\":\"\\u53ea\\u662f\\u6c89\\u9ed8.\"},{\"id\":\"7338\",\"text\":\"\\u53ef\\u80fd\\u538b\\u7f29\\u52a0\\u7684\\u6709\\u70b9\\u5927\\u4e86\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/f9\\/c4\\/f9c403c93338a0544caf8d5eb85f3861.jpg\",\"nick_name\":\"\\u628a\\u9152\\u5171\\u5929\\u6daf\"}],\"comment_max_id\":\"7338\"},{\"id\":\"167\",\"sid\":\"107\",\"title\":\"\\u76db\\u4e50\\u82b1\\u68a8\\u767d\\u9aa8\\u8f74\\u76f8\\u7435\\u7436sy-5516-3\",\"uid\":\"196\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/c2\\/e7\\/c2e762c2491a5b9d01edc7f5ef758c15.jpg\",\"nick_name\":\"\\u6e05\\u98ce\\u633d\\u5fc6\\u3002\",\"up_count\":\"13\",\"text\":\"\\u5723\\u9a6c\\u53ef\\uff0c120\\uff0c126\\u90fd\\u53ef\\u4ee5\\u3002\",\"imgs\":[\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/70\\/06\\/700653ce7cdbd9061b4ec1c4f3f5147b.jpg\"],\"add_time\":\"2016-03-08 13:15:03\",\"comment_count\":\"26\",\"comment_list\":[{\"id\":\"7283\",\"text\":\"\\u6c57\\uff0e\\uff0e\\u8bf7\\u95ee\\u4ec0\\u4e48\\u53ebska\\u66f2\\u98ce\\uff0e\\uff0e\\u6709\\u4ec0\\u4e48\\u63a8\\u8350\\u7684\\u4f5c\\u54c1\\u5417\\uff1f\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/49\\/f0\\/49f05ceebc4e1502a212e461becb5084.jpg\",\"nick_name\":\"\\u7e41\\u82b1\\u843d\\u96e8\\u5915\\u62fe\\u672b\"},{\"id\":\"7247\",\"text\":\"  \\u4e0d\\u9519~\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/66\\/d8\\/66d80b56e845711a1a6b0811032d0bc8.jpg\",\"nick_name\":\"\\u4e0e\\u9152\\u4e3a\\u4f34\"}],\"comment_max_id\":\"7247\"},{\"id\":\"170\",\"sid\":\"118\",\"title\":\"MES\\u9650\\u91cf\\u6b3e\\u67b6\\u5b50\\u9f13\\u7235\\u58eb\\u9f13Crush\\u5957\\u88c5\\u9f13\\u6210\\u4eba\\u67b6\\u5b50\\u9f135\\u9f133\\u9572\\u7247\",\"uid\":\"1\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/00\\/b6\\/00b673a9a9e0495a0dc9eff7313bacc1.jpg\",\"nick_name\":\"\\u660e\\u660e\\u5148\\u68ee\",\"up_count\":\"10\",\"text\":\"\\u8fd9\\u3002\\u3002\\u3002\\u4e24\\u79cd\\u7ebf\\u662f\\u4e00\\u6837\\u7684\\u4e1c\\u897f\\u3002\\u3002\\u3002\",\"imgs\":[\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/8f\\/e4\\/8fe49aa8bf40ef053f9259675a41ef9d.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/23\\/d3\\/23d3469ea06cf61e7d60f273ff328207.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/53\\/2d\\/532d7a81078e5309c6996c48c683e815.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/cd\\/02\\/cd02450ca04d5f206400337d45e1f298.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/4c\\/5d\\/4c5daec996f09ee9ddb03a9fcce82276.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/05\\/a8\\/05a87574ad08e6e42b87ed2e7fcd847f.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/fb\\/99\\/fb995850da53fb1b4e46801f1fbbf085.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/08\\/78\\/0878c57cf2352e16e0981d15724deb23.jpg\"],\"add_time\":\"2016-03-08 13:15:03\",\"comment_count\":\"26\",\"comment_list\":[{\"id\":\"6863\",\"text\":\"\\u6211\\u559c\\u6b22\\u8fd9\\u6837\\u7684\\u611f\\u89c9\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/f0\\/d4\\/f0d4a6e8c455f9f2005029e56012cbb0.jpg\",\"nick_name\":\"\\u65e0\\u804a\\u5c4e\\u4e86\"},{\"id\":\"6842\",\"text\":\"\\u81ea\\u5df1\\u5077\\u5077\\u9876\\u4e00\\u4e0b~~\\u554a\\u54c8\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/2e\\/37\\/2e3774e9ec55f3b07dbab9f14bc1ceda.jpg\",\"nick_name\":\"\\u6545\\u4e8b\\u914d\\u9152\\u4f60\\u914d\\u72d7\"}],\"comment_max_id\":\"6842\"},{\"id\":\"164\",\"sid\":\"23\",\"title\":\"VOX MINI3-G2\\u4fbf\\u643a\\u5f0f\\u591a\\u529f\\u80fd\\u5409\\u4ed6\\u97f3\\u7bb1\",\"uid\":\"209\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/16\\/bb\\/16bb5ab079b7f85dd2a06b27f669bac7.jpg\",\"nick_name\":\"\\u5b64\\u72ec\\u60a3\\u8005\",\"up_count\":\"10\",\"text\":\"\\u5f52\\u540c\\u5b66\\u4e86\\uff0c\\u8981\\u662f\\u6211\\u518d\\u597d\\u7684\\u5144\\u5f1f\\u90fd\\u6ca1\\u5546\\u91cf\",\"imgs\":[\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/ae\\/32\\/ae325d9bb8fb73e5c1c09ef7592ae787.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/85\\/35\\/8535a601ec152a1a793650ab3471dfdf.jpg\"],\"add_time\":\"2016-03-08 13:15:03\",\"comment_count\":\"24\",\"comment_list\":[{\"id\":\"6969\",\"text\":\"\\u5f39\\u5531\\u7434\\u8fd8\\u5f0f\\u9a6c\\u4e01\\u723d\\u554a\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/00\\/b6\\/00b673a9a9e0495a0dc9eff7313bacc1.jpg\",\"nick_name\":\"dasdsad\"},{\"id\":\"6915\",\"text\":\"\\u6211\\u89c9\\u5f97\\u7b2c\\u4e00\\u4e2a\\u5f39\\u7684\\u4e0d\\u9519\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/7b\\/3b\\/7b3b69438cedfc7e19eeec47255379d6.jpg\",\"nick_name\":\"\\u6211 \\u597d\\u50cf\\u559c\\u6b22\\u4e0a\\u4f60\\u4e86\"}],\"comment_max_id\":\"6915\"},{\"id\":\"156\",\"sid\":\"38\",\"title\":\"\\u597d\\u58f0\\u97f3\\u5468\\u6770\\u4f26\\u63a8\\u8350\\u53f0\\u6e7eUMA\\u5c24\\u514b\\u91cc\\u91cc\\u5c0f\\u5409\\u4ed6\",\"uid\":\"165\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/e5\\/5a\\/e55ae01bf969b7e8ffb7d83f95f14d04.jpg\",\"nick_name\":\"\\u505c\\u7559\\u98ce\\u6c99\",\"up_count\":\"10\",\"text\":\"\\u8a79\\u7237\\u3002\\u3002\\u5176\\u4ed6\\u4e0d\\u8bf4\\u4e86\\uff0c\\u7ed9\\u540e\\u6765\\u4eba\\u673a\\u4f1a\",\"imgs\":[\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/6b\\/76\\/6b762a90865523e719dd45bf5530685b.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/e8\\/46\\/e846fb220512fe1ce57b2b7d099fb260.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/22\\/a0\\/22a0312c2471c742c96624585fb0c3dd.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/a3\\/b8\\/a3b8af0aa5665441215435cd3650eafb.jpg\"],\"add_time\":\"2016-03-08 13:15:03\",\"comment_count\":\"21\",\"comment_list\":[{\"id\":\"6780\",\"text\":\"\\u63d0\\u793a:\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/af\\/96\\/af969d63213707fe5bf1e06e7e1a91b1.jpg\",\"nick_name\":\"\\u9152\\u5165\\u6101\\u80a0.\"},{\"id\":\"6550\",\"text\":\"\\u5efa\\u8bae\\u5728\\u672c\\u5730\\u7434\\u884c\\u627e\\u4e2a\\u8d1f\\u8d23\\u7684\\u8001\\u5e08\\u8ba4\\u771f\\u5b66\\uff0c\\u4e5f\\u987a\\u4fbf\\u7ed3\\u8bc6\\u4e00\\u4e9b\\u5174\\u8da3\\u76f8\\u540c\\u7684\\u670b\\u53cb\\u4e00\\u8d77\\u4ea4\\u6d41\\uff0c\\u8fdb\\u6b65\\u66f4\\u5feb\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/29\\/fd\\/29fd7569225655cc21ecc97ee387f8c4.jpg\",\"nick_name\":\"\\u5c0f\\u4e03\\u6211\\u4f34\\u4f60\\u8d70\"}],\"comment_max_id\":\"6550\"},{\"id\":\"171\",\"sid\":\"119\",\"title\":\"\\u641c\\u72d7\\u7cd6\\u732b\\u513f\\u7ae5\\u7535\\u8bdd\\u624b\\u8868T2\\u667a\\u80fd\\u5b9a\\u4f4d\\u624b\\u8868\\u9632\\u6c34GPS\\u9632\\u4e22\\u8fd0\\u52a8\\u624b\\u73af\\u624b\\u673a\",\"uid\":\"34\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/02\\/22\\/0222b4fb83de3b144606d65e04d7c29e.jpg\",\"nick_name\":\"\\u9053\\u4e0d\\u540c\\u4e0d\\u76f8\\u4e3a\\u8c0b\",\"up_count\":\"10\",\"text\":\"\\u8fd9\\u4e24\\u79cd\\u7ebf\\u6709\\u533a\\u522b\\u5417\\uff1f\\u53ea\\u4e0d\\u8fc7\\u4e00\\u4e2a\\u957f\\u70b9\\u4e00\\u4e2a\\u77ed\\u70b9\\u7684\\u95ee\\u9898\\uff0c\\u770b\\u4f60\\u600e\\u4e48\\u65b9\\u4fbf\\u600e\\u4e48\\u63a5\\u5c31\\u5f97\\u4e86\\u5457\\uff0c\\u597d\\u50bb\\u7684\\u95ee\\u9898\\u554a\\u3002\\u3002\\u3002\\u53ea\\u8981\\u4f60\\u4e0d\\u7528\\u97f3\\u7bb1\\u7ebf\\u5c31\\u884c\\u4e86\\uff0c\",\"imgs\":[\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/e5\\/5a\\/e55ae01bf969b7e8ffb7d83f95f14d04.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/63\\/97\\/63972fb53aba0e48a5516823d1865cea.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/bc\\/fb\\/bcfbeb62c2901ea63f9547ae75b22296.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/26\\/d4\\/26d4ff26b8f9b568bf1705835e011e65.jpg\"],\"add_time\":\"2016-03-08 13:15:03\",\"comment_count\":\"25\",\"comment_list\":[{\"id\":\"7334\",\"text\":\"\\u652f\\u6301\\u4e0b~~~~~~~\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/9d\\/c8\\/9dc81b5cbcf471862332758501566c7d.jpg\",\"nick_name\":\"\\u66a7\\u6627\\u5165\\u620f\"},{\"id\":\"7233\",\"text\":\"\\u652f\\u6301\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/58\\/99\\/5899e3c3a8a0f4047360c865065d38c2.jpg\",\"nick_name\":\"\\u52c9\\u5f3a\\u505a\\u670b\\u53cb\"}],\"comment_max_id\":\"7233\"},{\"id\":\"115\",\"sid\":\"87\",\"title\":\"\\u6b63\\u97f3\\u5802\\u5c0f\\u53f6\\u7d2b\\u6a80\\u4e8c\\u80e1\",\"uid\":\"183\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/81\\/13\\/8113fbe39905fd321abaca9d79a0e13d.jpg\",\"nick_name\":\"\\u8001\\u8857\\u65e7\\u53cb\",\"up_count\":\"10\",\"text\":\"\\u770b\\u6210\\u8272\\u5427 \\u4e0d\\u662f95\\u65b0 \\u7a0d\\u8d35\",\"imgs\":[\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/d8\\/19\\/d81931826cc9467610177cfeacc734ef.jpg\",\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/64\\/fd\\/64fd3b8f67466d79c181196c61a6caa4.jpg\"],\"add_time\":\"2016-03-08 13:15:02\",\"comment_count\":\"23\",\"comment_list\":[{\"id\":\"7148\",\"text\":\"\\u4e2a\\u4eba\\u559c\\u6b22314\\uff01\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/b8\\/9f\\/b89f36880a1bcd84b912eb728c6bd0eb.jpg\",\"nick_name\":\"\\u5b64\\u4e45\\u5219\\u5b89@\"},{\"id\":\"7126\",\"text\":\"\\u53d8\\u8c03\\u5939^12 ^12\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/83\\/46\\/8346833d7ab967ff394bafd60ec614bd.jpg\",\"nick_name\":\"\\u4e0e\\u8896\\u76f8\\u6620\"}],\"comment_max_id\":\"7126\"},{\"id\":\"118\",\"sid\":\"149\",\"title\":\"Roland\\u7f57\\u5170F-20\\u7535\\u94a2\\u7434\",\"uid\":\"22\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/66\\/96\\/669623c2a4866f625075c8eb3cb460f8.jpg\",\"nick_name\":\"\\u6307\\u5c16\\u89e6\\u78b0\\u7684\\u5239\\u90a3\\u5fc3\\u52a8\",\"up_count\":\"9\",\"text\":\"\\u554a\\uff1f\\u8fd8\\u80fd\\u78b0\\u74f7\\u5417\\uff1f\\u4e3a\\u5565\\u4e0d\\u80fd\\u6478\\u554a\\uff1f\",\"imgs\":[\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/7b\\/59\\/7b5956da70a9814931efdd76b186df53.jpg\"],\"add_time\":\"2016-03-08 13:15:02\",\"comment_count\":\"18\",\"comment_list\":[{\"id\":\"7456\",\"text\":\"\\u624b\\u6cd5 \\u597d \\u7279\\u522b\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/b5\\/f8\\/b5f88f27a76accad17c1799494d76b42.jpg\",\"nick_name\":\"\\u7c73\\u8c37\\u5427\\u5e05\\u54e5\\u8fd8\\u662f\\u602a\\u5496\"},{\"id\":\"7452\",\"text\":\"\\u8c03\\u8c03\\u7434\\u554a\\uff0c\\u90fd\\u4e0d\\u51c6\",\"user_face\":\"http:\\/\\/img.sdxapp.com\\/face\\/source\\/cb\\/03\\/cb0317b6618b76884db18b766c50f1fb.jpg\",\"nick_name\":\"\\u6211\\u60f3\\u4f60\\u4e86i\"}],\"comment_max_id\":\"7452\"}]";

        DebugLog.d("***********" + JSON.parseObject(s, List.class));
    }
}