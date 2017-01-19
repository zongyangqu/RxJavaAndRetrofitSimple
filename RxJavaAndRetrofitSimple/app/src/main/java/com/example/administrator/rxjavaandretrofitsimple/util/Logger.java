package com.example.administrator.rxjavaandretrofitsimple.util;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：
 */

public interface Logger {

    void v(@NonNull String tag, @NonNull String pattern, @NonNull Object... params);

    void d(@NonNull String tag, @NonNull String pattern, @NonNull Object... params);

    void i(@NonNull String tag, @NonNull String pattern, @NonNull Object... params);

    void w(@NonNull String tag, @NonNull String pattern, @NonNull Object... params);

    void e(@NonNull String tag, @NonNull String pattern, @NonNull Object... params);

    void a(@NonNull String tag, @NonNull String pattern, @NonNull Object... params);

    void exception(Throwable throwable);

    class BnhLogger implements Logger {
        /**
         * Priority constant for the println method; use Log.v.
         */
        public static final int VERBOSE = 2;

        /**
         * Priority constant for the println method; use Log.d.
         */
        public static final int DEBUG = 3;

        /**
         * Priority constant for the println method; use Log.i.
         */
        public static final int INFO = 4;

        /**
         * Priority constant for the println method; use Log.w.
         */
        public static final int WARN = 5;

        /**
         * Priority constant for the println method; use Log.e.
         */
        public static final int ERROR = 6;

        /**
         * Priority constant for the println method.
         */
        public static final int ASSERT = 7;

        /**
         * Priority constant for the println method; use {@link Throwable#printStackTrace()}
         */
        public static final int EXCEPTION = 8;

        /**
         * Priority constant for the println method; use Log.e
         */
        public static final int ENCRYPT = 9;

        /**
         * Priority constant for stop printing log.
         */
        public static final int NONE = 10;

        static final String TAG_PREFIX = "BNH.";

        public static int LEVEL = VERBOSE;

        public static void setLevel(int level) {
            LEVEL = level;
        }

        public static final Logger DEFAULT = new BnhLogger();

        public static Logger logger = DEFAULT;

        public static void setLogger(Logger logger) {
            BnhLogger.logger = logger;
        }

        final int LOG_MAX_OUTPUT_LENGTH = 4000;

        @Override
        public void v(@NonNull String tag, @NonNull String pattern, @NonNull Object... params) {
            if (LEVEL <= VERBOSE) {
                final String msg = finalMessage(pattern, params);
                try {
                    if (msg.length() > LOG_MAX_OUTPUT_LENGTH) {
                        for (int i = 0; i < msg.length(); i += LOG_MAX_OUTPUT_LENGTH) {
                            if (i + LOG_MAX_OUTPUT_LENGTH < msg.length())
                                Log.v(tag, msg.substring(i, i + LOG_MAX_OUTPUT_LENGTH));
                            else {
                                Log.v(tag, msg.substring(i, msg.length()));
                            }
                        }
                    } else {
                        Log.v(tag, msg);
                    }
                } catch (Exception e) {

                }
            }

        }

        final String finalMessage(String pattern, Object... params) {
            return params == null ? pattern : String.format(pattern, params);
        }

        final String makeTag(String tag) {
            return tag == null ? TAG_PREFIX :
                    String.format("%s%s", TAG_PREFIX, tag);
        }

        @Override
        public void d(@NonNull String tag, @NonNull String pattern, @NonNull Object... params) {
            if (LEVEL <= DEBUG) {
                final String msg = finalMessage(pattern, params);
                try {
                    if (msg.length() > LOG_MAX_OUTPUT_LENGTH) {
                        for (int i = 0; i < msg.length(); i += LOG_MAX_OUTPUT_LENGTH) {
                            if (i + LOG_MAX_OUTPUT_LENGTH < msg.length())
                                Log.d(tag, msg.substring(i, i + LOG_MAX_OUTPUT_LENGTH));
                            else {
                                Log.d(tag, msg.substring(i, msg.length()));
                            }
                        }
                    } else {
                        Log.d(tag, msg);
                    }
                } catch (Exception e) {

                }
            }
        }

        @Override
        public void i(@NonNull String tag, @NonNull String pattern, @NonNull Object... params) {
            if (LEVEL <= INFO) {
                final String msg = finalMessage(pattern, params);
                try {
                    if (msg.length() > LOG_MAX_OUTPUT_LENGTH) {
                        for (int i = 0; i < msg.length(); i += LOG_MAX_OUTPUT_LENGTH) {
                            if (i + LOG_MAX_OUTPUT_LENGTH < msg.length())
                                Log.i(tag, msg.substring(i, i + LOG_MAX_OUTPUT_LENGTH));
                            else {
                                Log.i(tag, msg.substring(i, msg.length()));
                            }
                        }
                    } else {
                        Log.i(tag, msg);
                    }
                } catch (Exception e) {

                }
            }
        }

        @Override
        public void w(@NonNull String tag, @NonNull String pattern, @NonNull Object... params) {
            if (LEVEL <= WARN) {
                final String msg = finalMessage(pattern, params);
                try {
                    if (msg.length() > LOG_MAX_OUTPUT_LENGTH) {
                        for (int i = 0; i < msg.length(); i += LOG_MAX_OUTPUT_LENGTH) {
                            if (i + LOG_MAX_OUTPUT_LENGTH < msg.length())
                                Log.w(tag, msg.substring(i, i + LOG_MAX_OUTPUT_LENGTH));
                            else {
                                Log.w(tag, msg.substring(i, msg.length()));
                            }
                        }
                    } else {
                        Log.w(tag, msg);
                    }
                } catch (Exception e) {

                }
            }
        }

        @Override
        public void e(@NonNull String tag, @NonNull String pattern, @NonNull Object... params) {
            if (LEVEL <= ERROR) {
                final String msg = finalMessage(pattern, params);
                try {
                    if (msg.length() > LOG_MAX_OUTPUT_LENGTH) {
                        for (int i = 0; i < msg.length(); i += LOG_MAX_OUTPUT_LENGTH) {
                            if (i + LOG_MAX_OUTPUT_LENGTH < msg.length())
                                Log.e(tag, msg.substring(i, i + LOG_MAX_OUTPUT_LENGTH));
                            else {
                                Log.e(tag, msg.substring(i, msg.length()));
                            }
                        }
                    } else {
                        Log.e(tag, msg);
                    }
                } catch (Exception e) {

                }
            }
        }

        @Override
        public void a(@NonNull String tag, @NonNull String pattern, @NonNull Object... params) {
            if (LEVEL <= ASSERT) {
                try {
                    System.err.println(String.format("%s->%s", makeTag(tag), params == null ? pattern : String.format(pattern, params)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void exception(Throwable throwable) {
            if (LEVEL <= EXCEPTION) {
                if (throwable != null) {
                    throwable.printStackTrace();
                }
            }
        }
    }
}

