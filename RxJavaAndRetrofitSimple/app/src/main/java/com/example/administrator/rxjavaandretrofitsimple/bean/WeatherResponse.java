package com.example.administrator.rxjavaandretrofitsimple.bean;

import com.example.administrator.rxjavaandretrofitsimple.bean.base.BaseResponse;

import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/19
 *
 * 类描述：天气实体类
 */

public class WeatherResponse extends BaseResponse{


    /**
     * result : {"data":{"realtime":{"city_code":"101210701","city_name":"温州","date":"2017-04-19","time":"13:00:00","week":3,"moon":"三月廿三","dataUptime":1492580103,"weather":{"temperature":"26","humidity":"49","info":"阴","img":"2"},"wind":{"direct":"东南风","power":"2级","offset":null,"windspeed":null}},"life":{"date":"2017-4-19","info":{"chuanyi":["舒适","建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"],"ganmao":["易发","相对于今天将会出现大幅度降温，空气湿度较大，易发生感冒，请注意适当增加衣服。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"xiche":["不宜","不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"],"yundong":["较不宜","有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"],"ziwaixian":["弱","紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"]}},"weather":[{"date":"2017-04-19","info":{"day":["3","阵雨","25","东北风","微风","05:29","出门记得带伞，行走驾驶做好防滑准备"],"night":["8","中雨","19","东北风","微风","18:25","出门记得带伞，行走驾驶做好防滑准备"]},"week":"三","nongli":"三月廿三"},{"date":"2017-04-20","info":{"dawn":["8","中雨","19","东北风","微风","18:25"],"day":["3","阵雨","26","东北风","微风","05:28"],"night":["3","阵雨","17","东北风","微风","18:26"]},"week":"四","nongli":"三月廿四"},{"date":"2017-04-21","info":{"dawn":["3","阵雨","17","东北风","微风","18:26"],"day":["8","中雨","21","东北风","微风","05:27"],"night":["2","阴","16","东北风","微风","18:27"]},"week":"五","nongli":"三月廿五"},{"date":"2017-04-22","info":{"dawn":["2","阴","16","东北风","微风","18:27"],"day":["1","多云","21","东北风","微风","05:26"],"night":["1","多云","15","东北风","微风","18:27"]},"week":"六","nongli":"三月廿六"},{"date":"2017-04-23","info":{"dawn":["1","多云","15","东北风","微风","18:27"],"day":["1","多云","22","东北风","微风","05:25"],"night":["1","多云","14","东北风","微风","18:28"]},"week":"日","nongli":"三月廿七"}],"f3h":{"temperature":[{"jg":"20170419110000","jb":"26"},{"jg":"20170419140000","jb":"24"},{"jg":"20170419170000","jb":"22"},{"jg":"20170419200000","jb":"20"},{"jg":"20170419230000","jb":"20"},{"jg":"20170420020000","jb":"19"},{"jg":"20170420050000","jb":"19"},{"jg":"20170420080000","jb":"20"},{"jg":"20170420110000","jb":"23"}],"precipitation":[{"jg":"20170419110000","jf":"0"},{"jg":"20170419140000","jf":"0"},{"jg":"20170419170000","jf":"1.2"},{"jg":"20170419200000","jf":"5.8"},{"jg":"20170419230000","jf":"1.8"},{"jg":"20170420020000","jf":"2.3"},{"jg":"20170420050000","jf":"4.8"},{"jg":"20170420080000","jf":"0.9"},{"jg":"20170420110000","jf":"0"}]},"pm25":{"key":"Wenzhou","show_desc":0,"pm25":{"curPm":"90","pm25":"44","pm10":"118","level":2,"quality":"良","des":"可以正常在户外活动，易敏感人群应减少外出"},"dateTime":"2017年04月19日13时","cityName":"温州"},"jingqu":"","jingqutq":"","date":"","isForeign":"0"}}
     * error_code : 0
     */

    public ResultBean result;

    public static class ResultBean {
        /**
         * data : {"realtime":{"city_code":"101210701","city_name":"温州","date":"2017-04-19","time":"13:00:00","week":3,"moon":"三月廿三","dataUptime":1492580103,"weather":{"temperature":"26","humidity":"49","info":"阴","img":"2"},"wind":{"direct":"东南风","power":"2级","offset":null,"windspeed":null}},"life":{"date":"2017-4-19","info":{"chuanyi":["舒适","建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"],"ganmao":["易发","相对于今天将会出现大幅度降温，空气湿度较大，易发生感冒，请注意适当增加衣服。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"xiche":["不宜","不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"],"yundong":["较不宜","有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"],"ziwaixian":["弱","紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"]}},"weather":[{"date":"2017-04-19","info":{"day":["3","阵雨","25","东北风","微风","05:29","出门记得带伞，行走驾驶做好防滑准备"],"night":["8","中雨","19","东北风","微风","18:25","出门记得带伞，行走驾驶做好防滑准备"]},"week":"三","nongli":"三月廿三"},{"date":"2017-04-20","info":{"dawn":["8","中雨","19","东北风","微风","18:25"],"day":["3","阵雨","26","东北风","微风","05:28"],"night":["3","阵雨","17","东北风","微风","18:26"]},"week":"四","nongli":"三月廿四"},{"date":"2017-04-21","info":{"dawn":["3","阵雨","17","东北风","微风","18:26"],"day":["8","中雨","21","东北风","微风","05:27"],"night":["2","阴","16","东北风","微风","18:27"]},"week":"五","nongli":"三月廿五"},{"date":"2017-04-22","info":{"dawn":["2","阴","16","东北风","微风","18:27"],"day":["1","多云","21","东北风","微风","05:26"],"night":["1","多云","15","东北风","微风","18:27"]},"week":"六","nongli":"三月廿六"},{"date":"2017-04-23","info":{"dawn":["1","多云","15","东北风","微风","18:27"],"day":["1","多云","22","东北风","微风","05:25"],"night":["1","多云","14","东北风","微风","18:28"]},"week":"日","nongli":"三月廿七"}],"f3h":{"temperature":[{"jg":"20170419110000","jb":"26"},{"jg":"20170419140000","jb":"24"},{"jg":"20170419170000","jb":"22"},{"jg":"20170419200000","jb":"20"},{"jg":"20170419230000","jb":"20"},{"jg":"20170420020000","jb":"19"},{"jg":"20170420050000","jb":"19"},{"jg":"20170420080000","jb":"20"},{"jg":"20170420110000","jb":"23"}],"precipitation":[{"jg":"20170419110000","jf":"0"},{"jg":"20170419140000","jf":"0"},{"jg":"20170419170000","jf":"1.2"},{"jg":"20170419200000","jf":"5.8"},{"jg":"20170419230000","jf":"1.8"},{"jg":"20170420020000","jf":"2.3"},{"jg":"20170420050000","jf":"4.8"},{"jg":"20170420080000","jf":"0.9"},{"jg":"20170420110000","jf":"0"}]},"pm25":{"key":"Wenzhou","show_desc":0,"pm25":{"curPm":"90","pm25":"44","pm10":"118","level":2,"quality":"良","des":"可以正常在户外活动，易敏感人群应减少外出"},"dateTime":"2017年04月19日13时","cityName":"温州"},"jingqu":"","jingqutq":"","date":"","isForeign":"0"}
         */

        public DataBean data;

        public static class DataBean {
            /**
             * realtime : {"city_code":"101210701","city_name":"温州","date":"2017-04-19","time":"13:00:00","week":3,"moon":"三月廿三","dataUptime":1492580103,"weather":{"temperature":"26","humidity":"49","info":"阴","img":"2"},"wind":{"direct":"东南风","power":"2级","offset":null,"windspeed":null}}
             * life : {"date":"2017-4-19","info":{"chuanyi":["舒适","建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"],"ganmao":["易发","相对于今天将会出现大幅度降温，空气湿度较大，易发生感冒，请注意适当增加衣服。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"xiche":["不宜","不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"],"yundong":["较不宜","有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"],"ziwaixian":["弱","紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"]}}
             * weather : [{"date":"2017-04-19","info":{"day":["3","阵雨","25","东北风","微风","05:29","出门记得带伞，行走驾驶做好防滑准备"],"night":["8","中雨","19","东北风","微风","18:25","出门记得带伞，行走驾驶做好防滑准备"]},"week":"三","nongli":"三月廿三"},{"date":"2017-04-20","info":{"dawn":["8","中雨","19","东北风","微风","18:25"],"day":["3","阵雨","26","东北风","微风","05:28"],"night":["3","阵雨","17","东北风","微风","18:26"]},"week":"四","nongli":"三月廿四"},{"date":"2017-04-21","info":{"dawn":["3","阵雨","17","东北风","微风","18:26"],"day":["8","中雨","21","东北风","微风","05:27"],"night":["2","阴","16","东北风","微风","18:27"]},"week":"五","nongli":"三月廿五"},{"date":"2017-04-22","info":{"dawn":["2","阴","16","东北风","微风","18:27"],"day":["1","多云","21","东北风","微风","05:26"],"night":["1","多云","15","东北风","微风","18:27"]},"week":"六","nongli":"三月廿六"},{"date":"2017-04-23","info":{"dawn":["1","多云","15","东北风","微风","18:27"],"day":["1","多云","22","东北风","微风","05:25"],"night":["1","多云","14","东北风","微风","18:28"]},"week":"日","nongli":"三月廿七"}]
             * f3h : {"temperature":[{"jg":"20170419110000","jb":"26"},{"jg":"20170419140000","jb":"24"},{"jg":"20170419170000","jb":"22"},{"jg":"20170419200000","jb":"20"},{"jg":"20170419230000","jb":"20"},{"jg":"20170420020000","jb":"19"},{"jg":"20170420050000","jb":"19"},{"jg":"20170420080000","jb":"20"},{"jg":"20170420110000","jb":"23"}],"precipitation":[{"jg":"20170419110000","jf":"0"},{"jg":"20170419140000","jf":"0"},{"jg":"20170419170000","jf":"1.2"},{"jg":"20170419200000","jf":"5.8"},{"jg":"20170419230000","jf":"1.8"},{"jg":"20170420020000","jf":"2.3"},{"jg":"20170420050000","jf":"4.8"},{"jg":"20170420080000","jf":"0.9"},{"jg":"20170420110000","jf":"0"}]}
             * pm25 : {"key":"Wenzhou","show_desc":0,"pm25":{"curPm":"90","pm25":"44","pm10":"118","level":2,"quality":"良","des":"可以正常在户外活动，易敏感人群应减少外出"},"dateTime":"2017年04月19日13时","cityName":"温州"}
             * jingqu :
             * jingqutq :
             * date :
             * isForeign : 0
             */

            public RealtimeBean realtime;
            public LifeBean life;
            public F3hBean f3h;
            public Pm25BeanX pm25;
            public String jingqu;
            public String jingqutq;
            public String date;
            public String isForeign;
            public List<WeatherBeanX> weather;

            public static class RealtimeBean {
                /**
                 * city_code : 101210701
                 * city_name : 温州
                 * date : 2017-04-19
                 * time : 13:00:00
                 * week : 3
                 * moon : 三月廿三
                 * dataUptime : 1492580103
                 * weather : {"temperature":"26","humidity":"49","info":"阴","img":"2"}
                 * wind : {"direct":"东南风","power":"2级","offset":null,"windspeed":null}
                 */

                public String city_code;
                public String city_name;
                public String date;
                public String time;
                public int week;
                public String moon;
                public int dataUptime;
                public WeatherBean weather;
                public WindBean wind;

                public static class WeatherBean {
                    /**
                     * temperature : 26
                     * humidity : 49
                     * info : 阴
                     * img : 2
                     */

                    public String temperature;
                    public String humidity;
                    public String info;
                    public String img;
                }

                public static class WindBean {
                    /**
                     * direct : 东南风
                     * power : 2级
                     * offset : null
                     * windspeed : null
                     */

                    public String direct;
                    public String power;
                    public Object offset;
                    public Object windspeed;
                }
            }

            public static class LifeBean {
                /**
                 * date : 2017-4-19
                 * info : {"chuanyi":["舒适","建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"],"ganmao":["易发","相对于今天将会出现大幅度降温，空气湿度较大，易发生感冒，请注意适当增加衣服。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"xiche":["不宜","不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"],"yundong":["较不宜","有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"],"ziwaixian":["弱","紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"]}
                 */

                public String date;
                public InfoBean info;

                public static class InfoBean {
                    public List<String> chuanyi;
                    public List<String> ganmao;
                    public List<String> kongtiao;
                    public List<String> xiche;
                    public List<String> yundong;
                    public List<String> ziwaixian;
                }
            }

            public static class F3hBean {
                public List<TemperatureBean> temperature;
                public List<PrecipitationBean> precipitation;

                public static class TemperatureBean {
                    /**
                     * jg : 20170419110000
                     * jb : 26
                     */

                    public String jg;
                    public String jb;
                }

                public static class PrecipitationBean {
                    /**
                     * jg : 20170419110000
                     * jf : 0
                     */

                    public String jg;
                    public String jf;
                }
            }

            public static class Pm25BeanX {
                /**
                 * key : Wenzhou
                 * show_desc : 0
                 * pm25 : {"curPm":"90","pm25":"44","pm10":"118","level":2,"quality":"良","des":"可以正常在户外活动，易敏感人群应减少外出"}
                 * dateTime : 2017年04月19日13时
                 * cityName : 温州
                 */

                public String key;
                public int show_desc;
                public Pm25Bean pm25;
                public String dateTime;
                public String cityName;

                public static class Pm25Bean {
                    /**
                     * curPm : 90
                     * pm25 : 44
                     * pm10 : 118
                     * level : 2
                     * quality : 良
                     * des : 可以正常在户外活动，易敏感人群应减少外出
                     */

                    public String curPm;
                    public String pm25;
                    public String pm10;
                    public int level;
                    public String quality;
                    public String des;
                }
            }

            public static class WeatherBeanX {
                /**
                 * date : 2017-04-19
                 * info : {"day":["3","阵雨","25","东北风","微风","05:29","出门记得带伞，行走驾驶做好防滑准备"],"night":["8","中雨","19","东北风","微风","18:25","出门记得带伞，行走驾驶做好防滑准备"]}
                 * week : 三
                 * nongli : 三月廿三
                 */

                public String date;
                public InfoBeanX info;
                public String week;
                public String nongli;

                public static class InfoBeanX {
                    public List<String> day;
                    public List<String> night;
                }
            }
        }
    }
}

