# RxJavaAndRetrofitSimple


**介绍**

项目整体采用MVP+Retrofit+RxJava模式（以后会考虑加入Dagger2解耦），接口API基本来自聚合数据。整合了一些开源项目。事件的传递采用基于RxJava实现的RxBus。

写这个项目的初衷就是把自己开发以来用到的知识做一个整理。没有专业的设计因此项目界面有些粗糙只看功能实现即可。

下面分几个模块来介绍下这个项目：

# 网络请求部分

网络请求部分使用Retrofit实现，不了解Retrofit可以参照下[RetrofitDemoMaster](https://github.com/43081438/RetrofitDemoMaster).

项目中支持访问不同的基地址

关于Retrofit有有几点单独拿出来说下。

**一.添加自定义的header**

Retrofit提供了两个方式定义Http请求头参数：静态方法和动态方法，静态方法不能随不同的请求进行变化，头部信息在初始化的时候就固定了。而动态方法则必须为每个请求都要单独设置。

- 静态方法

在项目中采用的是静态方法。

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


关于获取Retrofit的获取贴出封装Retrofit类核心代码以及调用方式

**ApiManager**

    //构造方法私有
    private ApiManager(int hostType) {
        //开启Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File cacheFile = new File(BaseApplication.getInstance().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
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

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(mRewriteCacheControlInterceptor)
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ApiConstants.getHost(hostType))
                .build();
        movieService = retrofit.create(ApiService.class);
    }

    /**
     * 获取Retrofit
     * @param hostType  基地址类型
     * @return
     */
    public static ApiService getDefault(int hostType) {
        ApiManager retrofitManager = sRetrofitManager.get(hostType);
        if (retrofitManager == null) {
            retrofitManager = new ApiManager(hostType);
            sRetrofitManager.put(hostType, retrofitManager);
        }
        return retrofitManager.movieService;
    }

**RequestClient中调用的部分代码**

    /**
     * 获取新闻分类信息
     * @param requestType
     * @param cacheControl
     * @return
     */
    public static Observable<NewsResponse> getNewsClassify(String requestType, String cacheControl) {
        return ApiManager.getDefault(HostType.JUHE_DATE_NET_INTERFACE).getNewsClassify(requestType,cacheControl)
                .compose(RxTransformer.<NewsResponse>ioToUI());
    }

# 架构模式（MVP）

关于MVP的概念要我说就是解耦、易扩展，各司其职自己专注做自己的事符合软件设计的开闭原则。不说太多了，相信大家也都知道就不班门弄斧了。

**1.MVP分层**

其实在本项目里我使用了2种不同MVP的模式（一种有Model层，一种没有Model，区别其实也就是在于有Model层的将获取Retrofit请求实例放到了Model层，没有Model层的将获取Retrofit实例放到了Presenter层）。


**View层**

基类View层没有什么不同就是接口类。公用的方法比如加载中、网络错误这些状态的返回方法可以都在基类中声明，子类特有的方法在其实现类中自己声明。

    public interface NewsDetailsView extends BaseStatusView{
    	void provideNewsInfo(NewsResponse.ResultBean response);
	}


**Presenter层**

在P层基类中我们要注意一件事，当View销毁时（这里的View指的是Activity）网络请求也要取消否则P层持有View层，而View又持有Activity的引用可能会引起内存泄漏。
如果没有使用Rxjava，那么Retrofit返回的是一个Call，而这个Call对象有一个cancel方法可以用来取消Http请求。那么用了Rxjava之后，如何来取消一个请求呢？因为返回值是一个Observable。我们能做的似乎只有解除对Observable对象的订阅，其他的什么也做不了。好在Retrofit已经帮我们考虑到了这一点。

RxJava提供给我们的方式就是将网络请求返回值Observable通过订阅（调用subscribe方法）得到的对象Subscription，在View销毁时（View指的Activity或者Fragment）调用Subscription的unsubscribe方法取消订阅来解除Http请求以及相关绑定。是不是这样大家就都满意了~~~

注：（此种调用方式没有使用Model层具体实现可以看项目中NewsDetailsFragment代码）

看下相关代码：



**（1）P层的代码**

    public class NewsDetailsPresenter extends BaseStatusPresenter<NewsDetailsView> {
    /**
     * 获取新闻信息
     * @param requestType   请求分类
     * @param cacheControl
     */
    public void getNewsInfo( String requestType,String cacheControl) {
        getView().processingDialog();
        addSubscription(RequestClient.getNewsClassify(requestType,cacheControl)
                .subscribe(new SimpleResponseObserver<NewsResponse>() {
                    @Override
                    public void onBeforeResponseOperation() {
                        super.onBeforeResponseOperation();
                        getView().dismissProcessingDialog();
                    }

                    @Override
                    public void onResponse(NewsResponse response) {
                        getView().provideNewsInfo(response.result);
                    }

                    @Override
                    public void onResponseStatusFail(String s, String s1) {
                        super.onResponseStatusFail(s,s1);
                        getView().dismissProcessingDialog();
                    }
                }));
    	}
	}

**（2）P层基类BaseStatusPresenter代码**

	public abstract class BaseStatusPresenter<V extends BaseStatusView> extends StatusPresenter<V> {

    private CompositeSubscription subscriptions;

    @Override
    public void attachView(V view) {
        super.attachView(view);
        registerObservers();
    }

    protected void registerObservers() {
    }

    public void addSubscription(Subscription subscription) {
        if (subscriptions == null) {
            subscriptions = new CompositeSubscription();
        }
        subscriptions.add(subscription);
    }

    @Override
    public void detachView() {
        if (subscriptions != null && !subscriptions.isUnsubscribed()) {
            subscriptions.unsubscribe();
        }
        super.detachView();
    	}
	}



如果你选择使用Model层，那么获取网络请求Retrofit操作就要放到Model层来执行了（具体实现可以看项目中WeChatFragment的代码）。


#关于Activity和Fragment基类的封装#

由于项目中有的没有使用Model因此Activity和Fragment我同样是封装了几个不同版本

**BaseModelFragment**使用Model层

**BaseMvpLazyFragment** 未使用Model层

**BaseStatusMvpStatusActivity**未使用Model层

**BaseNoNetworkActivity** 无网络请求的基类

注：在基类销毁时要取消订阅网络请求，具体方式上面已经说过，详细实现请看代码。








