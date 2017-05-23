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

import com.facebook.ads.sdk.*;
import java.io.File;
import java.util.Arrays;

public class SAMPLE_CODE {
  public static void main (String args[]) throws APIException {

    String access_token = "EAAWN0SaCUWoBALlBLOk15X6XjVOvZCXZCAyUNLJ6QSteOP5HmPOp1167gCeaXmTvyv2NLZAZCFZCyJWTYJpTwcda7olTSKgFewgGR2A4HNZCMhyJZA1MrEJ2PRsxvqOshwZAcF9GKWGat0nznN7B7xn1p0hy5HIl4jUqqrddrFZCAN49uHHYh3EnXzqZCB6FgcezMIo8BXvHRNbwZDZD";
    String ad_account_id = "100298480550407";
    APIContext context = new APIContext(access_token).enableDebug(true);

    new AdAccount(ad_account_id, context).getInsights()
      .setTimeRange("{\"since\":\"2017-04-13\",\"until\":\"2017-05-13\"}")
      .setFiltering("[{\"field\":\"impressions\",\"operator\":\"GREATER_THAN\",\"value\":\"0\"}]")
      .setLevel(AdsInsights.EnumLevel.VALUE_ADSET)
      .setBreakdowns("[\"days_1\",\"publisher_platform\",\"platform_position\",\"device_platform\"]")
      .requestField("campaign_group_name")
      .requestField("campaign_name")
      .requestField("objective")
      .requestField("spend")
      .requestField("actions:post_reaction")
      .requestField("actions:post")
      .requestField("actions:comment")
      .requestField("actions:like")
      .requestField("video_p100_watched_actions:video_view")
      .requestField("cpp")
      .requestField("cpm")
      .requestField("actions:link_click")
      .requestField("unique_actions:link_click")
      .requestField("cost_per_action_type:link_click")
      .execute();
  }
}
