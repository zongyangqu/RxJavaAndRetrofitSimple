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

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;


import com.example.administrator.rxjavaandretrofitsimple.util.permission.annotation.PermissionDenied;
import com.example.administrator.rxjavaandretrofitsimple.util.permission.annotation.PermissionGranted;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by congtaowang 2016/10/20.
 */

public class PermissionDispatcher {

    static void dispatchPermissionGranted(Object source, int requestCode) {
        Method grantedMethod = Utils.findMethod(source.getClass(), PermissionGranted.class, requestCode);
        invoke(source, grantedMethod);
    }

    static void dispatchPermissionDenied(Object source, int requestCode, Object... arguments) {
        Method deniedMethod = Utils.findMethod(source.getClass(), PermissionDenied.class, requestCode);
        invoke(source, deniedMethod, arguments);
    }

    private static void invoke(Object receiver, Method method, Object... arguments) {
        if (method != null) {
            method.setAccessible(true);
        }
        try {
            method.invoke(receiver, arguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onRequestPermissionsResult(Activity source,
                                                  int requestCode,
                                                  @NonNull String[] permissions,
                                                  @NonNull int[] grantResults) {
        parseRequestPermissionResult(source, requestCode, permissions, grantResults);
    }

    public static void onRequestPermissionsResult(Fragment source,
                                                  int requestCode,
                                                  @NonNull String[] permissions,
                                                  @NonNull int[] grantResults) {
        parseRequestPermissionResult(source, requestCode, permissions, grantResults);
    }

    private static void parseRequestPermissionResult(Object source,
                                                     int requestCode,
                                                     @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        List<String> deniedPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[i]);
            }
        }
        if (deniedPermissions.size() > 0) {
            dispatchPermissionDenied(source, requestCode, PermissionChecker.getDenialPermissionDescribeInfo(deniedPermissions));
        } else {
            dispatchPermissionGranted(source, requestCode);
        }
    }
}
