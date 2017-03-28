package com.example.administrator.rxjavaandretrofitsimple.util.xmlparse;

import android.text.TextUtils;
import android.util.Xml;

import com.example.administrator.rxjavaandretrofitsimple.bean.SplashAdvEntity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/23
 *
 * 类描述：启动页广告XML解析
 */
public class PullSplashAdvParser implements SplashAdvParser {

    @Override
    public List<SplashAdvEntity> parse(InputStream is) throws Exception {
        List<SplashAdvEntity> splashAdvList = null;
        SplashAdvEntity book = null;
        XmlPullParser parser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例
        parser.setInput(is, "UTF-8");               //设置输入流 并指明编码方式

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    splashAdvList = new ArrayList<SplashAdvEntity>();
                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("splashadv")) {
                        book = new SplashAdvEntity();
                    } else if (parser.getName().equals("content")) {
                        eventType = parser.next();
                        book.content = parser.getText();
                    }else if (parser.getName().equals("link")) {
                        eventType = parser.next();
                        book.link = parser.getText();
                    }else if (parser.getName().equals("filePath")) {
                        eventType = parser.next();
                        book.filePath = parser.getText();
                    }else if (parser.getName().equals("isAlive")) {
                        eventType = parser.next();
                        book.isAlive = parser.getText();
                    }else if (parser.getName().equals("version")) {
                        eventType = parser.next();
                        book.version = parser.getText();
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("splashadv")) {
                        splashAdvList.add(book);
                        book = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
        return splashAdvList;
    }

    @Override
    public String serialize(List<SplashAdvEntity> books) throws Exception {
//      XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//      XmlSerializer serializer = factory.newSerializer();

        XmlSerializer serializer = Xml.newSerializer(); //由android.util.Xml创建一个XmlSerializer实例
        StringWriter writer = new StringWriter();
        serializer.setOutput(writer);   //设置输出方向为writer
        serializer.startDocument("UTF-8", true);
        serializer.startTag("", "splashadvs");
        for (SplashAdvEntity splashAdvEntity : books) {
            serializer.startTag("", "splashadv");

            serializer.startTag("", "content");
            serializer.text(splashAdvEntity.content);
            serializer.endTag("", "content");

            serializer.startTag("", "isAlive");
            serializer.text(splashAdvEntity.isAlive);
            serializer.endTag("", "isAlive");

            serializer.startTag("", "filePath");
            if(TextUtils.isEmpty(splashAdvEntity.filePath)){
                serializer.text("");
            }else{
                serializer.text(splashAdvEntity.filePath);
            }
            serializer.endTag("", "filePath");

            serializer.startTag("", "isAlive");
            serializer.text(splashAdvEntity.isAlive);
            serializer.endTag("", "isAlive");

            serializer.endTag("", "splashadv");
        }
        serializer.endTag("", "splashadvs");
        serializer.endDocument();
        return writer.toString();
    }
}

