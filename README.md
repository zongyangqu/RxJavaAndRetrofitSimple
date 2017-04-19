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

关于MVP的概念要我说就是解耦、易扩展，各司其职自己专注做自己的事符合软件设计的开闭原则。关于MVP在架构中如何运用，我觉得是一个仁者见仁智者见智的问题，没有最好的只有最适合自己。最早之前开发的时候没有一个MVP的规范写法。而在本项目里我也写了3种不同的MVP调用方式（一个项目写多种架构，我自己写都觉得恶心）。

好吧，先介绍下这3种不同的MVP写法的区别。

**第一种**

这种是我最早接触MVP模式的写法。其实我觉得也挺好的简单易用，没有太多的额外接口。

OK，看下包结构。

![](https://github.com/43081438/RxJavaAndRetrofitSimple/blob/master/RxJavaAndRetrofitSimple/screenshot/mvp01.png?raw=true)

- View视图层，是一个接口由Activity或Fragment来实现，其接口中方法负责更新UI。
- Presenter逻辑层，处理数据，是View层和Model层的连接者。
- Model数据层，本地持久化的一些操作放到这里（其实我只是把获得网络请求封装到Model层了，因此显得Model的更能很轻，惭愧啊~~）

**第二种**

这种MVP结构实现的并不完全，因为没有写Model层，其实还是上边我提到的问题，我实现的Model层功能很轻，只是实现了网络请求的封装，因此这种架构中我把网络请求这部分也一并放到Presenter层处理。

看下包结构：

![](https://github.com/43081438/RxJavaAndRetrofitSimple/blob/master/RxJavaAndRetrofitSimple/screenshot/mvp02.png?raw=true)

View层和第一种没有什么区别不在赘述了。

Presenter层我做了底层的网络请求返回数据的状态码处理，你也可以拿来自己在扩展你需要的状态码（我们在实际开发中返回的数据一般都会有一个数据的状态码，根据这个状态码的不同我们要做不同的处理）。

如果项目不是很重这种模式的MVP也是挺好用的。

**第三种**

一直以来关于MVP的分层如何编写都没有统一标准，直到谷歌爸爸出了一个官方Demo
[https://github.com/googlesamples/android-architecture](https://github.com/googlesamples/android-architecture)

这下大家都满意了吧。不用再讨论谁的MVP是山寨的了（大家都TM是山寨的）。
虽说谷歌给出了MVP的推荐写法，但是我觉得也不一定非要按照这个去写，还是看自己的需要把。这种MVP的写法我在项目中也用到了，老规矩看下这种MVP下的包结构：

![](https://github.com/43081438/RxJavaAndRetrofitSimple/blob/master/RxJavaAndRetrofitSimple/screenshot/mvp03.png?raw=true)

看到了多出来一个contract的包，好奇怪有木有这是什么鬼？而且View包里为什么只有一个基类缺没有实现接口。这就是官方给出MVP架构的不同处了。

多出的contract包：里面放的是契约接口。更能直接明了的看到View和Presenter之间的方法。
看代码

    public interface WeatherContract {

    interface Model extends BaseStandardModel {
        //请求获取图片
        Observable<WeatherResponse.ResultBean> getWeatherData(String city, String key);
    }

    interface View extends BaseStandardView {
        //返回获取的图片
        void returnWeatherRequestData(WeatherResponse.ResultBean resultBean);
    }
    abstract static class Presenter extends BaseStandardPresenter<WeatherContract.View, WeatherContract.Model> {
        //发起获取图片请求
        public abstract void getWeatherRequest(String city, String key);
    	}
	}

然后Presenter和Model分别实现契约类里边的基类即可。

PS：其实这个锲约类接口也可以理解成一个加强版的View层，只是把mvp各层间的关系做了约束，方便调用者查看。

看过一遍文章，觉得这位仁兄使用MVP的历程和我比较像推荐下他的文章[MVP google官方demo比较分析](http://www.jianshu.com/p/14283d8d3a60)

**在P层基类中我们要注意一件事**

当View销毁时（这里的View指的是Activity）网络请求也要取消否则P层持有View层，而View又持有Activity的引用可能会引起内存泄漏。
如果没有使用Rxjava，那么Retrofit返回的是一个Call，而这个Call对象有一个cancel方法可以用来取消Http请求。那么用了Rxjava之后，如何来取消一个请求呢？因为返回值是一个Observable。我们能做的似乎只有解除对Observable对象的订阅，其他的什么也做不了。好在Retrofit已经帮我们考虑到了这一点。

RxJava提供给我们的方式就是将网络请求返回值Observable通过订阅（调用subscribe方法）得到的对象Subscription，在View销毁时（View指的Activity或者Fragment）调用Subscription的unsubscribe方法取消订阅来解除Http请求以及相关绑定。是不是这样大家就都满意了~~~

**注：（此种调用方式没有使用Model层具体实现可以看项目中NewsDetailsFragment代码）**

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

**BaseStandardMVPActivity** 谷歌推荐MVP的Avtivity基类

**BaseStandardMVPFragment**  谷歌推荐MVP的Fragment基类

注：在基类销毁时要取消订阅网络请求，具体方式上面已经说过，详细实现请看代码。



**感谢以下大神的开源项目分享**

[1.Android AutoLayout全新的适配方式 堪称适配终结者](http://blog.csdn.net/lmj623565791/article/details/49990941)

[2.Android-ConvenientBanner](https://github.com/saiwu-bigkoo/Android-ConvenientBanner)

[3.FlycoTabLayout](https://github.com/H07000223/FlycoTabLayout)

[4.StatusBarUtil沉浸式状态栏](https://github.com/laobie/StatusBarUtil)

[5.viewpagertransform转场动画库ViewPager](https://github.com/ToxicBakery/ViewPagerTransforms)



> 我会坚持在这个项目上更新更多内容，期望各位关注！ 
> 
> 如果你觉得项目中有东西帮到了你或者让你有一点收获欢迎点个star
> 
> 有问题请邮件email(43081438@qq.com)











