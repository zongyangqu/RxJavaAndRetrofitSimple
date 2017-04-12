# RxJavaAndRetrofitSimple


**介绍**

项目整体采用MVP+Retrofit+RxJava模式（以后会考虑加入Dagger2解耦），接口API基本来自聚合数据。整合了一些开源项目。事件的传递采用基于RxJava实现的RxBus。

写这个项目的初衷就是把自己开发以来用到的知识做一个整理。没有专业的设计因此项目界面有些粗糙只看功能实现即可。

下面分几个模块来介绍下这个项目：

##网络请求

网络请求部分使用Retrofit实现，不了解Retrofit可以参照下[RetrofitDemoMaster](https://github.com/43081438/RetrofitDemoMaster).
关于Retrofit有有几点单独拿出来说下。

**一.添加自定义的header**

Retrofit提供了两个方式定义Http请求头参数：静态方法和动态方法，静态方法不能随不同的请求进行变化，头部信息在初始化的时候就固定了。而动态方法则必须为每个请求都要单独设置。

- 静态方法

我在项目中采用的是静态方法。

    	//增加头部信息
        Interceptor headerInterceptor =new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build();
                return chain.proceed(build);
            }
        };

- 动态方法															

	public interface BlueService {@GET("book/search")
    Call<BookSearchResponse> getSearchBooks(
    @Header("Content-Range") String contentRange, 
    @Query("q") String name, @Query("tag") String tag, 
    @Query("start") int start, @Query("count") int count);
	}

**二.网络请求日志**

调试网络请求的时候经常需要关注一下请求参数和返回值，以便判断和定位问题出在哪里，Retrofit官方提供了一个很方便查看日志的Interceptor，你可以控制你需要的打印信息类型，使用方法也很简单。

首先需要在build.gradle文件中引入logging-interceptor

    compile 'com.squareup.okhttp3:logging-interceptor:3.4.1'
添加到OkHttpClient创建处即可，完整的示例代码如下：

    	//开启Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(mRewriteCacheControlInterceptor)
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .cache(cache)
                .build();

HttpLoggingInterceptor提供了4中控制打印信息类型的等级，分别是NONE，BASIC，HEADERS，BODY，接下来分别来说一下相应的打印信息类型。

- NONE

没有任何日志信息

- Basic

打印请求类型，URL，请求体大小，返回值状态以及返回值的大小

    D/HttpLoggingInterceptor$Logger: --> POST /upload HTTP/1.1 (277-byte body)  
	D/HttpLoggingInterceptor$Logger: <-- HTTP/1.1 200 OK (543ms, -1-byte body)

- Headers

打印返回请求和返回值的头部信息，请求类型，URL以及返回值状态码
![](https://github.com/43081438/RxJavaAndRetrofitSimple/blob/master/RxJavaAndRetrofitSimple/screenshot/001.png)

- Body

打印请求和返回值的头部和body信息
开发中我们经常使用这个Body来打印接口给我们返回的数据信息。


**三.网络请求缓存**

目前官方Retrofit给出的缓存只能支持Get请求。
缓存的配置只要分以下几步

- 1.设置缓存时长，和缓存路径
- 2.根据网络不同（一般判断是否有网络连接）设置缓存策略

关于缓存代码比较多，不贴出来了，想看实现细节可以下载源码，关于缓存配置都在**ApiManager**类中。


##架构模式（MVP）

关于MVP的感念不想说太多了，相信大家都明白，要我说就是解耦、易扩展，各司其职自己专注做自己的事符合软件设计的开闭原则。



