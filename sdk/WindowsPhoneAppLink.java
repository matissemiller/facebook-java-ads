/**
 * Copyright (c) 2015-present, Facebook, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 *
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

package com.facebook.ads.sdk;

import java.io.File;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.facebook.ads.sdk.APIException.MalformedResponseException;

/**
 * This class is auto-genereated.
 *
 * For any issues or feature requests related to this class, please let us know
 * on github and we'll fix in our codegen framework. We'll not be able to accept
 * pull request for this class.
 *
 */
public class WindowsPhoneAppLink extends APINode {
  @SerializedName("app_id")
  private String mAppId = null;
  @SerializedName("app_name")
  private String mAppName = null;
  @SerializedName("url")
  private String mUrl = null;
  protected static Gson gson = null;

  public WindowsPhoneAppLink() {
  }

  public String getId() {
    return null;
  }
  public static WindowsPhoneAppLink loadJSON(String json, APIContext context) {
    WindowsPhoneAppLink windowsPhoneAppLink = getGson().fromJson(json, WindowsPhoneAppLink.class);
    if (context.isDebug()) {
      JsonParser parser = new JsonParser();
      JsonElement o1 = parser.parse(json);
      JsonElement o2 = parser.parse(windowsPhoneAppLink.toString());
      if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
        o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
      }
      if (!o1.equals(o2)) {
        context.log("[Warning] When parsing response, object is not consistent with JSON:");
        context.log("[JSON]" + o1);
        context.log("[Object]" + o2);
      };
    }
    windowsPhoneAppLink.context = context;
    windowsPhoneAppLink.rawValue = json;
    return windowsPhoneAppLink;
  }

  public static APINodeList<WindowsPhoneAppLink> parseResponse(String json, APIContext context, APIRequest request) throws MalformedResponseException {
    APINodeList<WindowsPhoneAppLink> windowsPhoneAppLinks = new APINodeList<WindowsPhoneAppLink>(request, json);
    JsonArray arr;
    JsonObject obj;
    JsonParser parser = new JsonParser();
    Exception exception = null;
    try{
      JsonElement result = parser.parse(json);
      if (result.isJsonArray()) {
        // First, check if it's a pure JSON Array
        arr = result.getAsJsonArray();
        for (int i = 0; i < arr.size(); i++) {
          windowsPhoneAppLinks.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context));
        };
        return windowsPhoneAppLinks;
      } else if (result.isJsonObject()) {
        obj = result.getAsJsonObject();
        if (obj.has("data")) {
          if (obj.has("paging")) {
            JsonObject paging = obj.get("paging").getAsJsonObject().get("cursors").getAsJsonObject();
            String before = paging.has("before") ? paging.get("before").getAsString() : null;
            String after = paging.has("after") ? paging.get("after").getAsString() : null;
            windowsPhoneAppLinks.setPaging(before, after);
          }
          if (obj.get("data").isJsonArray()) {
            // Second, check if it's a JSON array with "data"
            arr = obj.get("data").getAsJsonArray();
            for (int i = 0; i < arr.size(); i++) {
              windowsPhoneAppLinks.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context));
            };
          } else if (obj.get("data").isJsonObject()) {
            // Third, check if it's a JSON object with "data"
            obj = obj.get("data").getAsJsonObject();
            boolean isRedownload = false;
            for (String s : new String[]{"campaigns", "adsets", "ads"}) {
              if (obj.has(s)) {
                isRedownload = true;
                obj = obj.getAsJsonObject(s);
                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                  windowsPhoneAppLinks.add(loadJSON(entry.getValue().toString(), context));
                }
                break;
              }
            }
            if (!isRedownload) {
              windowsPhoneAppLinks.add(loadJSON(obj.toString(), context));
            }
          }
          return windowsPhoneAppLinks;
        } else if (obj.has("images")) {
          // Fourth, check if it's a map of image objects
          obj = obj.get("images").getAsJsonObject();
          for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
              windowsPhoneAppLinks.add(loadJSON(entry.getValue().toString(), context));
          }
          return windowsPhoneAppLinks;
        } else {
          // Fifth, check if it's an array of objects indexed by id
          boolean isIdIndexedArray = true;
          for (Map.Entry entry : obj.entrySet()) {
            String key = (String) entry.getKey();
            if (key.equals("__fb_trace_id__")) {
              continue;
            }
            JsonElement value = (JsonElement) entry.getValue();
            if (
              value != null &&
              value.isJsonObject() &&
              value.getAsJsonObject().has("id") &&
              value.getAsJsonObject().get("id") != null &&
              value.getAsJsonObject().get("id").getAsString().equals(key)
            ) {
              windowsPhoneAppLinks.add(loadJSON(value.toString(), context));
            } else {
              isIdIndexedArray = false;
              break;
            }
          }
          if (isIdIndexedArray) {
            return windowsPhoneAppLinks;
          }

          // Sixth, check if it's pure JsonObject
          windowsPhoneAppLinks.clear();
          windowsPhoneAppLinks.add(loadJSON(json, context));
          return windowsPhoneAppLinks;
        }
      }
    } catch (Exception e) {
      exception = e;
    }
    throw new MalformedResponseException(
      "Invalid response string: " + json,
      exception
    );
  }

  @Override
  public APIContext getContext() {
    return context;
  }

  @Override
  public void setContext(APIContext context) {
    this.context = context;
  }

  @Override
  public String toString() {
    return getGson().toJson(this);
  }


  public String getFieldAppId() {
    return mAppId;
  }

  public WindowsPhoneAppLink setFieldAppId(String value) {
    this.mAppId = value;
    return this;
  }

  public String getFieldAppName() {
    return mAppName;
  }

  public WindowsPhoneAppLink setFieldAppName(String value) {
    this.mAppName = value;
    return this;
  }

  public String getFieldUrl() {
    return mUrl;
  }

  public WindowsPhoneAppLink setFieldUrl(String value) {
    this.mUrl = value;
    return this;
  }




  synchronized /*package*/ static Gson getGson() {
    if (gson != null) {
      return gson;
    } else {
      gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.STATIC)
        .excludeFieldsWithModifiers(Modifier.PROTECTED)
        .disableHtmlEscaping()
        .create();
    }
    return gson;
  }

  public WindowsPhoneAppLink copyFrom(WindowsPhoneAppLink instance) {
    this.mAppId = instance.mAppId;
    this.mAppName = instance.mAppName;
    this.mUrl = instance.mUrl;
    this.context = instance.context;
    this.rawValue = instance.rawValue;
    return this;
  }

  public static APIRequest.ResponseParser<WindowsPhoneAppLink> getParser() {
    return new APIRequest.ResponseParser<WindowsPhoneAppLink>() {
      public APINodeList<WindowsPhoneAppLink> parseResponse(String response, APIContext context, APIRequest<WindowsPhoneAppLink> request) throws MalformedResponseException {
        return WindowsPhoneAppLink.parseResponse(response, context, request);
      }
    };
  }
}
