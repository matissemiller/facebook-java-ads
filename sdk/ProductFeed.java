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
public class ProductFeed extends APINode {
  @SerializedName("country")
  private String mCountry = null;
  @SerializedName("created_time")
  private String mCreatedTime = null;
  @SerializedName("default_currency")
  private String mDefaultCurrency = null;
  @SerializedName("deletion_enabled")
  private Boolean mDeletionEnabled = null;
  @SerializedName("delimiter")
  private EnumDelimiter mDelimiter = null;
  @SerializedName("encoding")
  private String mEncoding = null;
  @SerializedName("file_name")
  private String mFileName = null;
  @SerializedName("id")
  private String mId = null;
  @SerializedName("latest_upload")
  private ProductFeedUpload mLatestUpload = null;
  @SerializedName("name")
  private String mName = null;
  @SerializedName("product_count")
  private Long mProductCount = null;
  @SerializedName("quoted_fields_mode")
  private EnumQuotedFieldsMode mQuotedFieldsMode = null;
  @SerializedName("schedule")
  private ProductFeedSchedule mSchedule = null;
  protected static Gson gson = null;

  ProductFeed() {
  }

  public ProductFeed(Long id, APIContext context) {
    this(id.toString(), context);
  }

  public ProductFeed(String id, APIContext context) {
    this.mId = id;
    this.context = context;
  }

  public ProductFeed fetch() throws APIException{
    ProductFeed newInstance = fetchById(this.getPrefixedId().toString(), this.context);
    this.copyFrom(newInstance);
    return this;
  }

  public static ProductFeed fetchById(Long id, APIContext context) throws APIException {
    return fetchById(id.toString(), context);
  }

  public static ProductFeed fetchById(String id, APIContext context) throws APIException {
    ProductFeed productFeed =
      new APIRequestGet(id, context)
      .requestAllFields()
      .execute();
    return productFeed;
  }

  public static APINodeList<ProductFeed> fetchByIds(List<String> ids, List<String> fields, APIContext context) throws APIException {
    return (APINodeList<ProductFeed>)(
      new APIRequest<ProductFeed>(context, "", "/", "GET", ProductFeed.getParser())
        .setParam("ids", APIRequest.joinStringList(ids))
        .requestFields(fields)
        .execute()
    );
  }

  private String getPrefixedId() {
    return getId();
  }

  public String getId() {
    return getFieldId().toString();
  }
  public static ProductFeed loadJSON(String json, APIContext context) {
    ProductFeed productFeed = getGson().fromJson(json, ProductFeed.class);
    if (context.isDebug()) {
      JsonParser parser = new JsonParser();
      JsonElement o1 = parser.parse(json);
      JsonElement o2 = parser.parse(productFeed.toString());
      if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
        o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
      }
      if (!o1.equals(o2)) {
        context.log("[Warning] When parsing response, object is not consistent with JSON:");
        context.log("[JSON]" + o1);
        context.log("[Object]" + o2);
      };
    }
    productFeed.context = context;
    productFeed.rawValue = json;
    return productFeed;
  }

  public static APINodeList<ProductFeed> parseResponse(String json, APIContext context, APIRequest request) throws MalformedResponseException {
    APINodeList<ProductFeed> productFeeds = new APINodeList<ProductFeed>(request, json);
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
          productFeeds.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context));
        };
        return productFeeds;
      } else if (result.isJsonObject()) {
        obj = result.getAsJsonObject();
        if (obj.has("data")) {
          if (obj.has("paging")) {
            JsonObject paging = obj.get("paging").getAsJsonObject().get("cursors").getAsJsonObject();
            String before = paging.has("before") ? paging.get("before").getAsString() : null;
            String after = paging.has("after") ? paging.get("after").getAsString() : null;
            productFeeds.setPaging(before, after);
          }
          if (obj.get("data").isJsonArray()) {
            // Second, check if it's a JSON array with "data"
            arr = obj.get("data").getAsJsonArray();
            for (int i = 0; i < arr.size(); i++) {
              productFeeds.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context));
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
                  productFeeds.add(loadJSON(entry.getValue().toString(), context));
                }
                break;
              }
            }
            if (!isRedownload) {
              productFeeds.add(loadJSON(obj.toString(), context));
            }
          }
          return productFeeds;
        } else if (obj.has("images")) {
          // Fourth, check if it's a map of image objects
          obj = obj.get("images").getAsJsonObject();
          for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
              productFeeds.add(loadJSON(entry.getValue().toString(), context));
          }
          return productFeeds;
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
              productFeeds.add(loadJSON(value.toString(), context));
            } else {
              isIdIndexedArray = false;
              break;
            }
          }
          if (isIdIndexedArray) {
            return productFeeds;
          }

          // Sixth, check if it's pure JsonObject
          productFeeds.clear();
          productFeeds.add(loadJSON(json, context));
          return productFeeds;
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

  public APIRequestGetProducts getProducts() {
    return new APIRequestGetProducts(this.getPrefixedId().toString(), context);
  }

  public APIRequestGetUploads getUploads() {
    return new APIRequestGetUploads(this.getPrefixedId().toString(), context);
  }

  public APIRequestCreateUpload createUpload() {
    return new APIRequestCreateUpload(this.getPrefixedId().toString(), context);
  }

  public APIRequestDelete delete() {
    return new APIRequestDelete(this.getPrefixedId().toString(), context);
  }

  public APIRequestGet get() {
    return new APIRequestGet(this.getPrefixedId().toString(), context);
  }

  public APIRequestUpdate update() {
    return new APIRequestUpdate(this.getPrefixedId().toString(), context);
  }


  public String getFieldCountry() {
    return mCountry;
  }

  public String getFieldCreatedTime() {
    return mCreatedTime;
  }

  public String getFieldDefaultCurrency() {
    return mDefaultCurrency;
  }

  public Boolean getFieldDeletionEnabled() {
    return mDeletionEnabled;
  }

  public EnumDelimiter getFieldDelimiter() {
    return mDelimiter;
  }

  public String getFieldEncoding() {
    return mEncoding;
  }

  public String getFieldFileName() {
    return mFileName;
  }

  public String getFieldId() {
    return mId;
  }

  public ProductFeedUpload getFieldLatestUpload() {
    if (mLatestUpload != null) {
      mLatestUpload.context = getContext();
    }
    return mLatestUpload;
  }

  public String getFieldName() {
    return mName;
  }

  public Long getFieldProductCount() {
    return mProductCount;
  }

  public EnumQuotedFieldsMode getFieldQuotedFieldsMode() {
    return mQuotedFieldsMode;
  }

  public ProductFeedSchedule getFieldSchedule() {
    return mSchedule;
  }



  public static class APIRequestGetProducts extends APIRequest<ProductItem> {

    APINodeList<ProductItem> lastResponse = null;
    @Override
    public APINodeList<ProductItem> getLastResponse() {
      return lastResponse;
    }
    public static final String[] PARAMS = {
      "bulk_pagination",
      "filter",
    };

    public static final String[] FIELDS = {
      "additional_image_urls",
      "age_group",
      "applinks",
      "availability",
      "brand",
      "category",
      "color",
      "commerce_insights",
      "condition",
      "currency",
      "custom_data",
      "custom_label_0",
      "custom_label_1",
      "custom_label_2",
      "custom_label_3",
      "custom_label_4",
      "description",
      "expiration_date",
      "gender",
      "gtin",
      "id",
      "image_url",
      "manufacturer_part_number",
      "material",
      "name",
      "ordering_index",
      "pattern",
      "price",
      "product_catalog",
      "product_feed",
      "product_group",
      "product_type",
      "retailer_id",
      "retailer_product_group_id",
      "review_rejection_reasons",
      "review_status",
      "sale_price",
      "sale_price_end_date",
      "sale_price_start_date",
      "shipping_weight_unit",
      "shipping_weight_value",
      "short_description",
      "size",
      "start_date",
      "url",
      "visibility",
    };

    @Override
    public APINodeList<ProductItem> parseResponse(String response) throws APIException {
      return ProductItem.parseResponse(response, getContext(), this);
    }

    @Override
    public APINodeList<ProductItem> execute() throws APIException {
      return execute(new HashMap<String, Object>());
    }

    @Override
    public APINodeList<ProductItem> execute(Map<String, Object> extraParams) throws APIException {
      lastResponse = parseResponse(executeInternal(extraParams));
      return lastResponse;
    }

    public APIRequestGetProducts(String nodeId, APIContext context) {
      super(context, nodeId, "/products", "GET", Arrays.asList(PARAMS));
    }

    @Override
    public APIRequestGetProducts setParam(String param, Object value) {
      setParamInternal(param, value);
      return this;
    }

    @Override
    public APIRequestGetProducts setParams(Map<String, Object> params) {
      setParamsInternal(params);
      return this;
    }


    public APIRequestGetProducts setBulkPagination (Boolean bulkPagination) {
      this.setParam("bulk_pagination", bulkPagination);
      return this;
    }
    public APIRequestGetProducts setBulkPagination (String bulkPagination) {
      this.setParam("bulk_pagination", bulkPagination);
      return this;
    }

    public APIRequestGetProducts setFilter (Object filter) {
      this.setParam("filter", filter);
      return this;
    }
    public APIRequestGetProducts setFilter (String filter) {
      this.setParam("filter", filter);
      return this;
    }

    public APIRequestGetProducts requestAllFields () {
      return this.requestAllFields(true);
    }

    public APIRequestGetProducts requestAllFields (boolean value) {
      for (String field : FIELDS) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestGetProducts requestFields (List<String> fields) {
      return this.requestFields(fields, true);
    }

    @Override
    public APIRequestGetProducts requestFields (List<String> fields, boolean value) {
      for (String field : fields) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestGetProducts requestField (String field) {
      this.requestField(field, true);
      return this;
    }

    @Override
    public APIRequestGetProducts requestField (String field, boolean value) {
      this.requestFieldInternal(field, value);
      return this;
    }

    public APIRequestGetProducts requestAdditionalImageUrlsField () {
      return this.requestAdditionalImageUrlsField(true);
    }
    public APIRequestGetProducts requestAdditionalImageUrlsField (boolean value) {
      this.requestField("additional_image_urls", value);
      return this;
    }
    public APIRequestGetProducts requestAgeGroupField () {
      return this.requestAgeGroupField(true);
    }
    public APIRequestGetProducts requestAgeGroupField (boolean value) {
      this.requestField("age_group", value);
      return this;
    }
    public APIRequestGetProducts requestApplinksField () {
      return this.requestApplinksField(true);
    }
    public APIRequestGetProducts requestApplinksField (boolean value) {
      this.requestField("applinks", value);
      return this;
    }
    public APIRequestGetProducts requestAvailabilityField () {
      return this.requestAvailabilityField(true);
    }
    public APIRequestGetProducts requestAvailabilityField (boolean value) {
      this.requestField("availability", value);
      return this;
    }
    public APIRequestGetProducts requestBrandField () {
      return this.requestBrandField(true);
    }
    public APIRequestGetProducts requestBrandField (boolean value) {
      this.requestField("brand", value);
      return this;
    }
    public APIRequestGetProducts requestCategoryField () {
      return this.requestCategoryField(true);
    }
    public APIRequestGetProducts requestCategoryField (boolean value) {
      this.requestField("category", value);
      return this;
    }
    public APIRequestGetProducts requestColorField () {
      return this.requestColorField(true);
    }
    public APIRequestGetProducts requestColorField (boolean value) {
      this.requestField("color", value);
      return this;
    }
    public APIRequestGetProducts requestCommerceInsightsField () {
      return this.requestCommerceInsightsField(true);
    }
    public APIRequestGetProducts requestCommerceInsightsField (boolean value) {
      this.requestField("commerce_insights", value);
      return this;
    }
    public APIRequestGetProducts requestConditionField () {
      return this.requestConditionField(true);
    }
    public APIRequestGetProducts requestConditionField (boolean value) {
      this.requestField("condition", value);
      return this;
    }
    public APIRequestGetProducts requestCurrencyField () {
      return this.requestCurrencyField(true);
    }
    public APIRequestGetProducts requestCurrencyField (boolean value) {
      this.requestField("currency", value);
      return this;
    }
    public APIRequestGetProducts requestCustomDataField () {
      return this.requestCustomDataField(true);
    }
    public APIRequestGetProducts requestCustomDataField (boolean value) {
      this.requestField("custom_data", value);
      return this;
    }
    public APIRequestGetProducts requestCustomLabel0Field () {
      return this.requestCustomLabel0Field(true);
    }
    public APIRequestGetProducts requestCustomLabel0Field (boolean value) {
      this.requestField("custom_label_0", value);
      return this;
    }
    public APIRequestGetProducts requestCustomLabel1Field () {
      return this.requestCustomLabel1Field(true);
    }
    public APIRequestGetProducts requestCustomLabel1Field (boolean value) {
      this.requestField("custom_label_1", value);
      return this;
    }
    public APIRequestGetProducts requestCustomLabel2Field () {
      return this.requestCustomLabel2Field(true);
    }
    public APIRequestGetProducts requestCustomLabel2Field (boolean value) {
      this.requestField("custom_label_2", value);
      return this;
    }
    public APIRequestGetProducts requestCustomLabel3Field () {
      return this.requestCustomLabel3Field(true);
    }
    public APIRequestGetProducts requestCustomLabel3Field (boolean value) {
      this.requestField("custom_label_3", value);
      return this;
    }
    public APIRequestGetProducts requestCustomLabel4Field () {
      return this.requestCustomLabel4Field(true);
    }
    public APIRequestGetProducts requestCustomLabel4Field (boolean value) {
      this.requestField("custom_label_4", value);
      return this;
    }
    public APIRequestGetProducts requestDescriptionField () {
      return this.requestDescriptionField(true);
    }
    public APIRequestGetProducts requestDescriptionField (boolean value) {
      this.requestField("description", value);
      return this;
    }
    public APIRequestGetProducts requestExpirationDateField () {
      return this.requestExpirationDateField(true);
    }
    public APIRequestGetProducts requestExpirationDateField (boolean value) {
      this.requestField("expiration_date", value);
      return this;
    }
    public APIRequestGetProducts requestGenderField () {
      return this.requestGenderField(true);
    }
    public APIRequestGetProducts requestGenderField (boolean value) {
      this.requestField("gender", value);
      return this;
    }
    public APIRequestGetProducts requestGtinField () {
      return this.requestGtinField(true);
    }
    public APIRequestGetProducts requestGtinField (boolean value) {
      this.requestField("gtin", value);
      return this;
    }
    public APIRequestGetProducts requestIdField () {
      return this.requestIdField(true);
    }
    public APIRequestGetProducts requestIdField (boolean value) {
      this.requestField("id", value);
      return this;
    }
    public APIRequestGetProducts requestImageUrlField () {
      return this.requestImageUrlField(true);
    }
    public APIRequestGetProducts requestImageUrlField (boolean value) {
      this.requestField("image_url", value);
      return this;
    }
    public APIRequestGetProducts requestManufacturerPartNumberField () {
      return this.requestManufacturerPartNumberField(true);
    }
    public APIRequestGetProducts requestManufacturerPartNumberField (boolean value) {
      this.requestField("manufacturer_part_number", value);
      return this;
    }
    public APIRequestGetProducts requestMaterialField () {
      return this.requestMaterialField(true);
    }
    public APIRequestGetProducts requestMaterialField (boolean value) {
      this.requestField("material", value);
      return this;
    }
    public APIRequestGetProducts requestNameField () {
      return this.requestNameField(true);
    }
    public APIRequestGetProducts requestNameField (boolean value) {
      this.requestField("name", value);
      return this;
    }
    public APIRequestGetProducts requestOrderingIndexField () {
      return this.requestOrderingIndexField(true);
    }
    public APIRequestGetProducts requestOrderingIndexField (boolean value) {
      this.requestField("ordering_index", value);
      return this;
    }
    public APIRequestGetProducts requestPatternField () {
      return this.requestPatternField(true);
    }
    public APIRequestGetProducts requestPatternField (boolean value) {
      this.requestField("pattern", value);
      return this;
    }
    public APIRequestGetProducts requestPriceField () {
      return this.requestPriceField(true);
    }
    public APIRequestGetProducts requestPriceField (boolean value) {
      this.requestField("price", value);
      return this;
    }
    public APIRequestGetProducts requestProductCatalogField () {
      return this.requestProductCatalogField(true);
    }
    public APIRequestGetProducts requestProductCatalogField (boolean value) {
      this.requestField("product_catalog", value);
      return this;
    }
    public APIRequestGetProducts requestProductFeedField () {
      return this.requestProductFeedField(true);
    }
    public APIRequestGetProducts requestProductFeedField (boolean value) {
      this.requestField("product_feed", value);
      return this;
    }
    public APIRequestGetProducts requestProductGroupField () {
      return this.requestProductGroupField(true);
    }
    public APIRequestGetProducts requestProductGroupField (boolean value) {
      this.requestField("product_group", value);
      return this;
    }
    public APIRequestGetProducts requestProductTypeField () {
      return this.requestProductTypeField(true);
    }
    public APIRequestGetProducts requestProductTypeField (boolean value) {
      this.requestField("product_type", value);
      return this;
    }
    public APIRequestGetProducts requestRetailerIdField () {
      return this.requestRetailerIdField(true);
    }
    public APIRequestGetProducts requestRetailerIdField (boolean value) {
      this.requestField("retailer_id", value);
      return this;
    }
    public APIRequestGetProducts requestRetailerProductGroupIdField () {
      return this.requestRetailerProductGroupIdField(true);
    }
    public APIRequestGetProducts requestRetailerProductGroupIdField (boolean value) {
      this.requestField("retailer_product_group_id", value);
      return this;
    }
    public APIRequestGetProducts requestReviewRejectionReasonsField () {
      return this.requestReviewRejectionReasonsField(true);
    }
    public APIRequestGetProducts requestReviewRejectionReasonsField (boolean value) {
      this.requestField("review_rejection_reasons", value);
      return this;
    }
    public APIRequestGetProducts requestReviewStatusField () {
      return this.requestReviewStatusField(true);
    }
    public APIRequestGetProducts requestReviewStatusField (boolean value) {
      this.requestField("review_status", value);
      return this;
    }
    public APIRequestGetProducts requestSalePriceField () {
      return this.requestSalePriceField(true);
    }
    public APIRequestGetProducts requestSalePriceField (boolean value) {
      this.requestField("sale_price", value);
      return this;
    }
    public APIRequestGetProducts requestSalePriceEndDateField () {
      return this.requestSalePriceEndDateField(true);
    }
    public APIRequestGetProducts requestSalePriceEndDateField (boolean value) {
      this.requestField("sale_price_end_date", value);
      return this;
    }
    public APIRequestGetProducts requestSalePriceStartDateField () {
      return this.requestSalePriceStartDateField(true);
    }
    public APIRequestGetProducts requestSalePriceStartDateField (boolean value) {
      this.requestField("sale_price_start_date", value);
      return this;
    }
    public APIRequestGetProducts requestShippingWeightUnitField () {
      return this.requestShippingWeightUnitField(true);
    }
    public APIRequestGetProducts requestShippingWeightUnitField (boolean value) {
      this.requestField("shipping_weight_unit", value);
      return this;
    }
    public APIRequestGetProducts requestShippingWeightValueField () {
      return this.requestShippingWeightValueField(true);
    }
    public APIRequestGetProducts requestShippingWeightValueField (boolean value) {
      this.requestField("shipping_weight_value", value);
      return this;
    }
    public APIRequestGetProducts requestShortDescriptionField () {
      return this.requestShortDescriptionField(true);
    }
    public APIRequestGetProducts requestShortDescriptionField (boolean value) {
      this.requestField("short_description", value);
      return this;
    }
    public APIRequestGetProducts requestSizeField () {
      return this.requestSizeField(true);
    }
    public APIRequestGetProducts requestSizeField (boolean value) {
      this.requestField("size", value);
      return this;
    }
    public APIRequestGetProducts requestStartDateField () {
      return this.requestStartDateField(true);
    }
    public APIRequestGetProducts requestStartDateField (boolean value) {
      this.requestField("start_date", value);
      return this;
    }
    public APIRequestGetProducts requestUrlField () {
      return this.requestUrlField(true);
    }
    public APIRequestGetProducts requestUrlField (boolean value) {
      this.requestField("url", value);
      return this;
    }
    public APIRequestGetProducts requestVisibilityField () {
      return this.requestVisibilityField(true);
    }
    public APIRequestGetProducts requestVisibilityField (boolean value) {
      this.requestField("visibility", value);
      return this;
    }
  }

  public static class APIRequestGetUploads extends APIRequest<ProductFeedUpload> {

    APINodeList<ProductFeedUpload> lastResponse = null;
    @Override
    public APINodeList<ProductFeedUpload> getLastResponse() {
      return lastResponse;
    }
    public static final String[] PARAMS = {
    };

    public static final String[] FIELDS = {
      "end_time",
      "id",
      "input_method",
      "start_time",
      "url",
    };

    @Override
    public APINodeList<ProductFeedUpload> parseResponse(String response) throws APIException {
      return ProductFeedUpload.parseResponse(response, getContext(), this);
    }

    @Override
    public APINodeList<ProductFeedUpload> execute() throws APIException {
      return execute(new HashMap<String, Object>());
    }

    @Override
    public APINodeList<ProductFeedUpload> execute(Map<String, Object> extraParams) throws APIException {
      lastResponse = parseResponse(executeInternal(extraParams));
      return lastResponse;
    }

    public APIRequestGetUploads(String nodeId, APIContext context) {
      super(context, nodeId, "/uploads", "GET", Arrays.asList(PARAMS));
    }

    @Override
    public APIRequestGetUploads setParam(String param, Object value) {
      setParamInternal(param, value);
      return this;
    }

    @Override
    public APIRequestGetUploads setParams(Map<String, Object> params) {
      setParamsInternal(params);
      return this;
    }


    public APIRequestGetUploads requestAllFields () {
      return this.requestAllFields(true);
    }

    public APIRequestGetUploads requestAllFields (boolean value) {
      for (String field : FIELDS) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestGetUploads requestFields (List<String> fields) {
      return this.requestFields(fields, true);
    }

    @Override
    public APIRequestGetUploads requestFields (List<String> fields, boolean value) {
      for (String field : fields) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestGetUploads requestField (String field) {
      this.requestField(field, true);
      return this;
    }

    @Override
    public APIRequestGetUploads requestField (String field, boolean value) {
      this.requestFieldInternal(field, value);
      return this;
    }

    public APIRequestGetUploads requestEndTimeField () {
      return this.requestEndTimeField(true);
    }
    public APIRequestGetUploads requestEndTimeField (boolean value) {
      this.requestField("end_time", value);
      return this;
    }
    public APIRequestGetUploads requestIdField () {
      return this.requestIdField(true);
    }
    public APIRequestGetUploads requestIdField (boolean value) {
      this.requestField("id", value);
      return this;
    }
    public APIRequestGetUploads requestInputMethodField () {
      return this.requestInputMethodField(true);
    }
    public APIRequestGetUploads requestInputMethodField (boolean value) {
      this.requestField("input_method", value);
      return this;
    }
    public APIRequestGetUploads requestStartTimeField () {
      return this.requestStartTimeField(true);
    }
    public APIRequestGetUploads requestStartTimeField (boolean value) {
      this.requestField("start_time", value);
      return this;
    }
    public APIRequestGetUploads requestUrlField () {
      return this.requestUrlField(true);
    }
    public APIRequestGetUploads requestUrlField (boolean value) {
      this.requestField("url", value);
      return this;
    }
  }

  public static class APIRequestCreateUpload extends APIRequest<ProductFeedUpload> {

    ProductFeedUpload lastResponse = null;
    @Override
    public ProductFeedUpload getLastResponse() {
      return lastResponse;
    }
    public static final String[] PARAMS = {
      "password",
      "update_only",
      "url",
      "username",
      "file",
    };

    public static final String[] FIELDS = {
    };

    @Override
    public ProductFeedUpload parseResponse(String response) throws APIException {
      return ProductFeedUpload.parseResponse(response, getContext(), this).head();
    }

    @Override
    public ProductFeedUpload execute() throws APIException {
      return execute(new HashMap<String, Object>());
    }

    @Override
    public ProductFeedUpload execute(Map<String, Object> extraParams) throws APIException {
      lastResponse = parseResponse(executeInternal(extraParams));
      return lastResponse;
    }

    public APIRequestCreateUpload(String nodeId, APIContext context) {
      super(context, nodeId, "/uploads", "POST", Arrays.asList(PARAMS));
    }

    @Override
    public APIRequestCreateUpload setParam(String param, Object value) {
      setParamInternal(param, value);
      return this;
    }

    @Override
    public APIRequestCreateUpload setParams(Map<String, Object> params) {
      setParamsInternal(params);
      return this;
    }

    public APIRequestCreateUpload addUploadFile (String uploadName, File file) {
      this.setParam(uploadName, file);
      return this;
    }

    public APIRequestCreateUpload setUseVideoEndpoint(boolean useVideoEndpoint) {
      this.useVideoEndpoint = useVideoEndpoint;
      return this;
    }

    public APIRequestCreateUpload setPassword (String password) {
      this.setParam("password", password);
      return this;
    }

    public APIRequestCreateUpload setUpdateOnly (Boolean updateOnly) {
      this.setParam("update_only", updateOnly);
      return this;
    }
    public APIRequestCreateUpload setUpdateOnly (String updateOnly) {
      this.setParam("update_only", updateOnly);
      return this;
    }

    public APIRequestCreateUpload setUrl (String url) {
      this.setParam("url", url);
      return this;
    }

    public APIRequestCreateUpload setUsername (String username) {
      this.setParam("username", username);
      return this;
    }

    public APIRequestCreateUpload requestAllFields () {
      return this.requestAllFields(true);
    }

    public APIRequestCreateUpload requestAllFields (boolean value) {
      for (String field : FIELDS) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestCreateUpload requestFields (List<String> fields) {
      return this.requestFields(fields, true);
    }

    @Override
    public APIRequestCreateUpload requestFields (List<String> fields, boolean value) {
      for (String field : fields) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestCreateUpload requestField (String field) {
      this.requestField(field, true);
      return this;
    }

    @Override
    public APIRequestCreateUpload requestField (String field, boolean value) {
      this.requestFieldInternal(field, value);
      return this;
    }

  }

  public static class APIRequestDelete extends APIRequest<APINode> {

    APINode lastResponse = null;
    @Override
    public APINode getLastResponse() {
      return lastResponse;
    }
    public static final String[] PARAMS = {
    };

    public static final String[] FIELDS = {
    };

    @Override
    public APINode parseResponse(String response) throws APIException {
      return APINode.parseResponse(response, getContext(), this).head();
    }

    @Override
    public APINode execute() throws APIException {
      return execute(new HashMap<String, Object>());
    }

    @Override
    public APINode execute(Map<String, Object> extraParams) throws APIException {
      lastResponse = parseResponse(executeInternal(extraParams));
      return lastResponse;
    }

    public APIRequestDelete(String nodeId, APIContext context) {
      super(context, nodeId, "/", "DELETE", Arrays.asList(PARAMS));
    }

    @Override
    public APIRequestDelete setParam(String param, Object value) {
      setParamInternal(param, value);
      return this;
    }

    @Override
    public APIRequestDelete setParams(Map<String, Object> params) {
      setParamsInternal(params);
      return this;
    }


    public APIRequestDelete requestAllFields () {
      return this.requestAllFields(true);
    }

    public APIRequestDelete requestAllFields (boolean value) {
      for (String field : FIELDS) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestDelete requestFields (List<String> fields) {
      return this.requestFields(fields, true);
    }

    @Override
    public APIRequestDelete requestFields (List<String> fields, boolean value) {
      for (String field : fields) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestDelete requestField (String field) {
      this.requestField(field, true);
      return this;
    }

    @Override
    public APIRequestDelete requestField (String field, boolean value) {
      this.requestFieldInternal(field, value);
      return this;
    }

  }

  public static class APIRequestGet extends APIRequest<ProductFeed> {

    ProductFeed lastResponse = null;
    @Override
    public ProductFeed getLastResponse() {
      return lastResponse;
    }
    public static final String[] PARAMS = {
    };

    public static final String[] FIELDS = {
      "country",
      "created_time",
      "default_currency",
      "deletion_enabled",
      "delimiter",
      "encoding",
      "file_name",
      "id",
      "latest_upload",
      "name",
      "product_count",
      "quoted_fields_mode",
      "schedule",
    };

    @Override
    public ProductFeed parseResponse(String response) throws APIException {
      return ProductFeed.parseResponse(response, getContext(), this).head();
    }

    @Override
    public ProductFeed execute() throws APIException {
      return execute(new HashMap<String, Object>());
    }

    @Override
    public ProductFeed execute(Map<String, Object> extraParams) throws APIException {
      lastResponse = parseResponse(executeInternal(extraParams));
      return lastResponse;
    }

    public APIRequestGet(String nodeId, APIContext context) {
      super(context, nodeId, "/", "GET", Arrays.asList(PARAMS));
    }

    @Override
    public APIRequestGet setParam(String param, Object value) {
      setParamInternal(param, value);
      return this;
    }

    @Override
    public APIRequestGet setParams(Map<String, Object> params) {
      setParamsInternal(params);
      return this;
    }


    public APIRequestGet requestAllFields () {
      return this.requestAllFields(true);
    }

    public APIRequestGet requestAllFields (boolean value) {
      for (String field : FIELDS) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestGet requestFields (List<String> fields) {
      return this.requestFields(fields, true);
    }

    @Override
    public APIRequestGet requestFields (List<String> fields, boolean value) {
      for (String field : fields) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestGet requestField (String field) {
      this.requestField(field, true);
      return this;
    }

    @Override
    public APIRequestGet requestField (String field, boolean value) {
      this.requestFieldInternal(field, value);
      return this;
    }

    public APIRequestGet requestCountryField () {
      return this.requestCountryField(true);
    }
    public APIRequestGet requestCountryField (boolean value) {
      this.requestField("country", value);
      return this;
    }
    public APIRequestGet requestCreatedTimeField () {
      return this.requestCreatedTimeField(true);
    }
    public APIRequestGet requestCreatedTimeField (boolean value) {
      this.requestField("created_time", value);
      return this;
    }
    public APIRequestGet requestDefaultCurrencyField () {
      return this.requestDefaultCurrencyField(true);
    }
    public APIRequestGet requestDefaultCurrencyField (boolean value) {
      this.requestField("default_currency", value);
      return this;
    }
    public APIRequestGet requestDeletionEnabledField () {
      return this.requestDeletionEnabledField(true);
    }
    public APIRequestGet requestDeletionEnabledField (boolean value) {
      this.requestField("deletion_enabled", value);
      return this;
    }
    public APIRequestGet requestDelimiterField () {
      return this.requestDelimiterField(true);
    }
    public APIRequestGet requestDelimiterField (boolean value) {
      this.requestField("delimiter", value);
      return this;
    }
    public APIRequestGet requestEncodingField () {
      return this.requestEncodingField(true);
    }
    public APIRequestGet requestEncodingField (boolean value) {
      this.requestField("encoding", value);
      return this;
    }
    public APIRequestGet requestFileNameField () {
      return this.requestFileNameField(true);
    }
    public APIRequestGet requestFileNameField (boolean value) {
      this.requestField("file_name", value);
      return this;
    }
    public APIRequestGet requestIdField () {
      return this.requestIdField(true);
    }
    public APIRequestGet requestIdField (boolean value) {
      this.requestField("id", value);
      return this;
    }
    public APIRequestGet requestLatestUploadField () {
      return this.requestLatestUploadField(true);
    }
    public APIRequestGet requestLatestUploadField (boolean value) {
      this.requestField("latest_upload", value);
      return this;
    }
    public APIRequestGet requestNameField () {
      return this.requestNameField(true);
    }
    public APIRequestGet requestNameField (boolean value) {
      this.requestField("name", value);
      return this;
    }
    public APIRequestGet requestProductCountField () {
      return this.requestProductCountField(true);
    }
    public APIRequestGet requestProductCountField (boolean value) {
      this.requestField("product_count", value);
      return this;
    }
    public APIRequestGet requestQuotedFieldsModeField () {
      return this.requestQuotedFieldsModeField(true);
    }
    public APIRequestGet requestQuotedFieldsModeField (boolean value) {
      this.requestField("quoted_fields_mode", value);
      return this;
    }
    public APIRequestGet requestScheduleField () {
      return this.requestScheduleField(true);
    }
    public APIRequestGet requestScheduleField (boolean value) {
      this.requestField("schedule", value);
      return this;
    }
  }

  public static class APIRequestUpdate extends APIRequest<ProductFeed> {

    ProductFeed lastResponse = null;
    @Override
    public ProductFeed getLastResponse() {
      return lastResponse;
    }
    public static final String[] PARAMS = {
      "default_currency",
      "deletion_enabled",
      "delimiter",
      "encoding",
      "name",
      "quoted_fields_mode",
      "schedule",
    };

    public static final String[] FIELDS = {
    };

    @Override
    public ProductFeed parseResponse(String response) throws APIException {
      return ProductFeed.parseResponse(response, getContext(), this).head();
    }

    @Override
    public ProductFeed execute() throws APIException {
      return execute(new HashMap<String, Object>());
    }

    @Override
    public ProductFeed execute(Map<String, Object> extraParams) throws APIException {
      lastResponse = parseResponse(executeInternal(extraParams));
      return lastResponse;
    }

    public APIRequestUpdate(String nodeId, APIContext context) {
      super(context, nodeId, "/", "POST", Arrays.asList(PARAMS));
    }

    @Override
    public APIRequestUpdate setParam(String param, Object value) {
      setParamInternal(param, value);
      return this;
    }

    @Override
    public APIRequestUpdate setParams(Map<String, Object> params) {
      setParamsInternal(params);
      return this;
    }


    public APIRequestUpdate setDefaultCurrency (String defaultCurrency) {
      this.setParam("default_currency", defaultCurrency);
      return this;
    }

    public APIRequestUpdate setDeletionEnabled (Boolean deletionEnabled) {
      this.setParam("deletion_enabled", deletionEnabled);
      return this;
    }
    public APIRequestUpdate setDeletionEnabled (String deletionEnabled) {
      this.setParam("deletion_enabled", deletionEnabled);
      return this;
    }

    public APIRequestUpdate setDelimiter (ProductFeed.EnumDelimiter delimiter) {
      this.setParam("delimiter", delimiter);
      return this;
    }
    public APIRequestUpdate setDelimiter (String delimiter) {
      this.setParam("delimiter", delimiter);
      return this;
    }

    public APIRequestUpdate setEncoding (ProductFeed.EnumEncoding encoding) {
      this.setParam("encoding", encoding);
      return this;
    }
    public APIRequestUpdate setEncoding (String encoding) {
      this.setParam("encoding", encoding);
      return this;
    }

    public APIRequestUpdate setName (String name) {
      this.setParam("name", name);
      return this;
    }

    public APIRequestUpdate setQuotedFieldsMode (ProductFeed.EnumQuotedFieldsMode quotedFieldsMode) {
      this.setParam("quoted_fields_mode", quotedFieldsMode);
      return this;
    }
    public APIRequestUpdate setQuotedFieldsMode (String quotedFieldsMode) {
      this.setParam("quoted_fields_mode", quotedFieldsMode);
      return this;
    }

    public APIRequestUpdate setSchedule (String schedule) {
      this.setParam("schedule", schedule);
      return this;
    }

    public APIRequestUpdate requestAllFields () {
      return this.requestAllFields(true);
    }

    public APIRequestUpdate requestAllFields (boolean value) {
      for (String field : FIELDS) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestUpdate requestFields (List<String> fields) {
      return this.requestFields(fields, true);
    }

    @Override
    public APIRequestUpdate requestFields (List<String> fields, boolean value) {
      for (String field : fields) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestUpdate requestField (String field) {
      this.requestField(field, true);
      return this;
    }

    @Override
    public APIRequestUpdate requestField (String field, boolean value) {
      this.requestFieldInternal(field, value);
      return this;
    }

  }

  public static enum EnumDelimiter {
      @SerializedName("AUTODETECT")
      VALUE_AUTODETECT("AUTODETECT"),
      @SerializedName("BAR")
      VALUE_BAR("BAR"),
      @SerializedName("COMMA")
      VALUE_COMMA("COMMA"),
      @SerializedName("TAB")
      VALUE_TAB("TAB"),
      @SerializedName("TILDE")
      VALUE_TILDE("TILDE"),
      @SerializedName("SEMICOLON")
      VALUE_SEMICOLON("SEMICOLON"),
      NULL(null);

      private String value;

      private EnumDelimiter(String value) {
        this.value = value;
      }

      @Override
      public String toString() {
        return value;
      }
  }

  public static enum EnumQuotedFieldsMode {
      @SerializedName("AUTODETECT")
      VALUE_AUTODETECT("AUTODETECT"),
      @SerializedName("ON")
      VALUE_ON("ON"),
      @SerializedName("OFF")
      VALUE_OFF("OFF"),
      NULL(null);

      private String value;

      private EnumQuotedFieldsMode(String value) {
        this.value = value;
      }

      @Override
      public String toString() {
        return value;
      }
  }

  public static enum EnumEncoding {
      @SerializedName("AUTODETECT")
      VALUE_AUTODETECT("AUTODETECT"),
      @SerializedName("LATIN1")
      VALUE_LATIN1("LATIN1"),
      @SerializedName("UTF8")
      VALUE_UTF8("UTF8"),
      @SerializedName("UTF16LE")
      VALUE_UTF16LE("UTF16LE"),
      @SerializedName("UTF16BE")
      VALUE_UTF16BE("UTF16BE"),
      @SerializedName("UTF32LE")
      VALUE_UTF32LE("UTF32LE"),
      @SerializedName("UTF32BE")
      VALUE_UTF32BE("UTF32BE"),
      NULL(null);

      private String value;

      private EnumEncoding(String value) {
        this.value = value;
      }

      @Override
      public String toString() {
        return value;
      }
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

  public ProductFeed copyFrom(ProductFeed instance) {
    this.mCountry = instance.mCountry;
    this.mCreatedTime = instance.mCreatedTime;
    this.mDefaultCurrency = instance.mDefaultCurrency;
    this.mDeletionEnabled = instance.mDeletionEnabled;
    this.mDelimiter = instance.mDelimiter;
    this.mEncoding = instance.mEncoding;
    this.mFileName = instance.mFileName;
    this.mId = instance.mId;
    this.mLatestUpload = instance.mLatestUpload;
    this.mName = instance.mName;
    this.mProductCount = instance.mProductCount;
    this.mQuotedFieldsMode = instance.mQuotedFieldsMode;
    this.mSchedule = instance.mSchedule;
    this.context = instance.context;
    this.rawValue = instance.rawValue;
    return this;
  }

  public static APIRequest.ResponseParser<ProductFeed> getParser() {
    return new APIRequest.ResponseParser<ProductFeed>() {
      public APINodeList<ProductFeed> parseResponse(String response, APIContext context, APIRequest<ProductFeed> request) throws MalformedResponseException {
        return ProductFeed.parseResponse(response, context, request);
      }
    };
  }
}
