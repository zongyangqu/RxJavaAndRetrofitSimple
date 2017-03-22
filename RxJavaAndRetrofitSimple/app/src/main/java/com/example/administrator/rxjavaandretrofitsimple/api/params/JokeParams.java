package com.example.administrator.rxjavaandretrofitsimple.api.params;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/20
 *
 * 类描述：
 */

public class JokeParams {
    private String page;
    private String pagesize;
    private String key;

    public static class Builder {

        private final JokeParams params;

        public Builder() {
            params = new JokeParams();
        }

        public Builder page(int value) {
            params.page = value+"";
            return this;
        }

        public Builder pagesize(int value) {
            params.pagesize = value+"";
            return this;
        }

        public Builder key(String value) {
            params.key = value;
            return this;
        }

        public JokeParams build() {
            return params;
        }

    }
}
