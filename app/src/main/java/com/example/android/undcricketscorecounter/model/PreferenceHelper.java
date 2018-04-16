/*
 * The following license applies and this license notice
 * must be included in all works derived from this project.
 *
 * Copyright (c) 2017 Nishant Patel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Last modified 4/16/18 6:12 PM
 */

package com.example.android.undcricketscorecounter.model;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {
    private static PreferenceHelper sPreferenceHelper;
    private static SharedPreferences sSharedPreferences;
    private static SharedPreferences.Editor sEditor;

    public static PreferenceHelper getInstance(Context context) {
        if (sPreferenceHelper == null) {
            sPreferenceHelper = new PreferenceHelper();
            sSharedPreferences = context.getSharedPreferences("PERFORMANCE_PREFERENCE", Context.MODE_PRIVATE);
            sEditor = sSharedPreferences.edit();
        }
        return sPreferenceHelper;
    }

    public void writeData(String key, String value) {
        sEditor.putString(key, value).commit();
    }

    public String readData(String key) {
        return sSharedPreferences.getString(key, "0");
    }

    public void clearData() {
        sEditor.clear().commit();
    }
}