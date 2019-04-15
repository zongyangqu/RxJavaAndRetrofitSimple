/*
 * Copyright (c) 2016 congtaowang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.administrator.rxjavaandretrofitsimple.util.permission;

import android.Manifest;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by congtaowang 2016/10/19.
 */

public class PermissionChecker {

    public static final class Permissions {

        public static final String[] STORAGE_WRITE_READ = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };

        public static final String[] CAMERA = {
                Manifest.permission.CAMERA
        };

        public static final String[] MAIN_PERMISSIONS = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };

        public static final String[] LOCATION = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        public static final String[] CAMERA_STORAGE_WRITE_READ = {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };

        public static final String[] RECORD_STORAGE_WRITE_READ = {
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
    }

    public static final class PermissionRequestCode {
        public static final int STORAGE_WRITE_READ = 0x01;
        public static final int CAMERA = 0x02;
        public static final int LOCATION = 0x03;
        public static final int CAMERA_STORAGE_WRITE_READ = 0x04;
        public static final int RECORD_STORAGE_WRITE_READ = 0x05;
        public static final int MAIN_PERMISSIONS = 0x06;
    }

    final static Map<String, String> permissions = new HashMap<>();

    static {
        permissions.put(Manifest.permission.READ_EXTERNAL_STORAGE, "读取存储卡内容");
        permissions.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入文件到存储卡");
        permissions.put(Manifest.permission.CAMERA, "照相功能");
//        permissions.put(Manifest.permission.READ_CALL_LOG, "读取通话日志");
//        permissions.put(Manifest.permission.READ_PHONE_STATE, "获取电话状态");
//        permissions.put(Manifest.permission.CALL_PHONE, "拨打电话");
//        permissions.put(Manifest.permission.USE_SIP, "");
//        permissions.put(Manifest.permission.PROCESS_OUTGOING_CALLS, "");
//        permissions.put(Manifest.permission.ADD_VOICEMAIL, "");
//        permissions.put(Manifest.permission.READ_CALENDAR, "获取日历");
//        permissions.put(Manifest.permission.WRITE_CALENDAR, "修改日历");
//        permissions.put(Manifest.permission.BODY_SENSORS, "传感器");
//        permissions.put(Manifest.permission.ACCESS_FINE_LOCATION, "访问精确位置");
//        permissions.put(Manifest.permission.ACCESS_COARSE_LOCATION, "访问大概位置");
//        permissions.put(Manifest.permission.RECORD_AUDIO, "录音");
//        permissions.put(Manifest.permission.READ_SMS, "读取短信息");
//        permissions.put(Manifest.permission.RECEIVE_WAP_PUSH, "接收WAP推送");
//        permissions.put(Manifest.permission.RECEIVE_MMS, "接收彩信");
//        permissions.put(Manifest.permission.RECEIVE_SMS, "接收短信");
//        permissions.put(Manifest.permission.SEND_SMS, "发送短信");
    }

    static String getDenialPermissionDescribeInfo(List<String> denialPermissions) {
        if (denialPermissions == null || denialPermissions.size() == 0) {
            return null;
        }
        StringBuilder rationale = new StringBuilder();
        for (String denialPermission : denialPermissions) {
            if (permissions.containsKey(denialPermission)) {
                rationale.append(permissions.get(denialPermission)).append(",");
            }
        }
        if (!TextUtils.isEmpty(rationale.toString())) {
            rationale.deleteCharAt(rationale.length() - 1);
        }
        return rationale.toString();
    }
}
