package com.example.administrator.rxjavaandretrofitsimple.bean;

import com.example.administrator.rxjavaandretrofitsimple.bean.base.BaseEntity;

import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/20
 *
 * 类描述：笑话
 */

public class JokeResponse extends BaseEntity{

    /**
     * error_code : 0
     * result : {"data":[{"content":"上午开车出去办事， 等红绿灯，我前面就一辆车后面贴着实习，！ 从前车后视镜看到是一女司机正在玩手机， 我看红灯还30多秒，就拿抹布擦下方向盘的灰尘， 不小心按到了喇叭！ 谁知前面那女司机加油门就跑了， 我默默的看着红灯还有10来秒！ 只能在心里说，对不起了，我真不是在催你！","hashId":"51a94ed31c78cccc9e86657582d4d4af","unixtime":1490015630,"updatetime":"2017-03-20 21:13:50"},{"content":"一个姓周的称姓陈的为\"东家\"，姓陈的不知其意。一天终于知道了原因，于是再见到姓周的，就称他为\"吉先生\"，姓周的说:\"我姓周，不姓吉。\"姓陈的说:\"我姓陈，不姓东，你割了我耳朵，我不能剥你的皮吗？\"","hashId":"761b03867f9b25be75da3f28aa6a5760","unixtime":1490006030,"updatetime":"2017-03-20 18:33:50"},{"content":"你好，我是一名医学生。如果你中午12点吃饭了，那1点之前就不要再吃饭，从医学角度分析，如果你吃了，你会很撑得慌。。。","hashId":"98be3deb78a23f9c6b41ff2b98365cfc","unixtime":1489999430,"updatetime":"2017-03-20 16:43:50"},{"content":"领导穿了件偏绿色的冲锋衣来上班，别人都说颜色亮啊什么的，只有我嘴贱，说款式和颜色不协调，问我哪里不协调，我说绿色的款式怎么可以有帽子？","hashId":"b342426177ab6e78b58ba74e57fbbc41","unixtime":1489997030,"updatetime":"2017-03-20 16:03:50"},{"content":"姐姐来电抱怨：\u201c朋友来做客，我把家里一个毛绒娃娃送给了她女儿。结果你姐夫下班回来发脾气，说那个毛绒娃娃是他送给我的礼物，倾注了感情不该乱送人。现在好啦，我厚着脸皮讨回来了。\u201d  我开玩笑说：\u201c哈哈，也许他把私房钱藏娃娃里了。\u201d  过了一会儿，居然收到老姐两百块的红包，留言里写着：小妹好样的！","hashId":"d4540d99339c7694d28e6fcbae0edb0c","unixtime":1489995830,"updatetime":"2017-03-20 15:43:50"},{"content":"带女朋友去吃饭遇到流氓向女朋友吹口哨该怎么办。你若是年轻就应该提刀砍他，若是过了那个年纪，就应该努力挣钱，带她去高档点的餐厅吃饭，那里没有流氓。","hashId":"ac5396878ee47498fe70663b8759510e","unixtime":1489994030,"updatetime":"2017-03-20 15:13:50"},{"content":"今一大早上班，同事大哥说：\u201c你看我儿子给我新买的腰带！\u201d我说：\u201c你孩子真孝顺。\u201d\u201c孝顺个屁，他是让老子勒紧裤腰带，给他省钱买房结婚。\u201d","hashId":"c1f4c4a3dc42659d7d835448d23d11ca","unixtime":1489992830,"updatetime":"2017-03-20 14:53:50"},{"content":"刚刚跟男朋友吵架，因为他打游戏没接我电话！ 我生气的说：\u201c在你心里我不如游戏重要吗？＂ 男朋友说：\u201c没有人会把游戏看的比人重要，就好像我拉屎的时候不能同时亲你，那就说明我把屎看得比你还重要吗？ \u201d突然觉得很有道理的样子啊！","hashId":"316c8d4b3bd99df71c01c12269ea41cc","unixtime":1489992830,"updatetime":"2017-03-20 14:53:50"},{"content":"一天同学聚会，都说自己工资有多高多高，问道我时我弱弱的很少的可怜就3000块。大家都笑话我说确实少的可怜，最后我补了句：\u201c老婆发的。\u201d全场瞬间沉默，然后惊呼：这才叫土豪啊。。。","hashId":"e2470dde2a4e3809ec960862188096eb","unixtime":1489992830,"updatetime":"2017-03-20 14:53:50"},{"content":"拿到诊断报告：真菌弥漫性感染多细胞综合症。天啊！都弥漫了，还综合症！这还有活路吗？我哭着打电话告诉老婆，老婆顿时眼泪哗哗地说：老公，你想吃点啥？我给你买去......现在我哪吃得下呀！于是赶紧给当医生的同学去电话，问问还能活多久。同学听完我描述说：嗨，什么鸟毛病，通俗讲就是脚气。啊！","hashId":"d4cb98210521aa91dde91f2532d5cc61","unixtime":1489992230,"updatetime":"2017-03-20 14:43:50"}]}
     */
    public ResultBean result;

    public static class ResultBean {
        public List<DataBean> data;

        public static class DataBean {
            /**
             * content : 上午开车出去办事， 等红绿灯，我前面就一辆车后面贴着实习，！ 从前车后视镜看到是一女司机正在玩手机， 我看红灯还30多秒，就拿抹布擦下方向盘的灰尘， 不小心按到了喇叭！ 谁知前面那女司机加油门就跑了， 我默默的看着红灯还有10来秒！ 只能在心里说，对不起了，我真不是在催你！
             * hashId : 51a94ed31c78cccc9e86657582d4d4af
             * unixtime : 1490015630
             * updatetime : 2017-03-20 21:13:50
             */

            public String content;
            public String hashId;
            public int unixtime;
            public String updatetime;
        }
    }
}
