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
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import java.util.List;

import static com.example.administrator.rxjavaandretrofitsimple.util.permission.PermissionDispatcher.dispatchPermissionGranted;


public class PermissionRequester {

    private Object source;
    private String[] permissions;
    private int requestCode;

    private PermissionRequester() {

    }

    public static PermissionRequester build() {
        return new PermissionRequester();
    }

    public PermissionRequester attach(Activity source) {
        this.source = source;
        initCounter++;
        return this;
    }

    public PermissionRequester attach(Fragment source) {
        this.source = source;
        initCounter++;
        return this;
    }

    public PermissionRequester permissions(String... permissions) {
        this.permissions = permissions;
        initCounter++;
        return this;
    }

    public PermissionRequester requestCode(int requestCode) {
        this.requestCode = requestCode;
        initCounter++;
        return this;
    }

    int initCounter = 0;
    static final int TOTAL_NEEDED_INIT_AMOUNT = 3;

    public void request() {
        if (initCounter != TOTAL_NEEDED_INIT_AMOUNT) {
            throw new IllegalArgumentException("Dispatcher must be work on #attach,#permissions,#requestCode are invoked.");
        }
        if (!(source instanceof Activity || source instanceof Fragment)) {
            throw new UnSupportSourceTypeException(String.format("%s is not support.", source.getClass().getName()));
        }
        if (!Utils.isOverMarshmallow()) {
            dispatchPermissionGranted(source, requestCode);
            return;
        }

        List<String> deniedPermissions = Utils.findDeniedPermissions(source, permissions);
        if (deniedPermissions.size() > 0) {
            if (source instanceof Fragment) {
                ((Fragment) source).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            } else {
                ActivityCompat.requestPermissions(Utils.toActivity(source), deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            }
        } else {
            dispatchPermissionGranted(source, requestCode);
        }

        initCounter = 0;
    }

}
