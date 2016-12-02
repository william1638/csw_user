package com.std.sms.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.std.sms.common.JsonUtil;
import com.std.sms.sent.wechat.UserData;
import com.std.sms.sent.wechat.WeChatClientSend;

/** 
 * @author: haiqingzheng 
 * @since: 2016年11月26日 下午2:03:54 
 * @history:
 */
public class HttpsUtil {
    private static class TrustAnyTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }

    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * post方式请求服务器(https协议)
     * 
     * @param url
     *            请求地址
     * @param content
     *            参数
     * @param charset
     *            编码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public static byte[] post(String url, String content, String charset)
            throws NoSuchAlgorithmException, KeyManagementException,
            IOException {
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
            new java.security.SecureRandom());

        URL console = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
        conn.setSSLSocketFactory(sc.getSocketFactory());
        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        conn.setDoOutput(true);
        conn.connect();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(content.getBytes(charset));
        // 刷新、关闭
        out.flush();
        out.close();
        InputStream is = conn.getInputStream();
        if (is != null) {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            is.close();
            return outStream.toByteArray();
        }
        return null;
    }

    private static void getUserList() {
        String accessToken = WeChatClientSend.getAccessToken(
            "wxef7fda595f81f6d6", "057815f636178d3a81c3b065f395a209");
        String postUrl = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token="
                + accessToken;
        // String[] openIds = { "ojdtdv8a8-q0zXinOXQjA1DKNMKg",
        // "ojdtdv5k5eIIwa6mViWLpO34fRA4", "ojdtdv5fmsOwDeikUy11LbHkU6i8",
        // "ojdtdv9JNbYQqukXvesUsGXRA13E", "ojdtdvyrZkB-NNmXEfiJSAglyZ9Y",
        // "ojdtdv7NK4g9WplkZN7MMSpjmhT4", "ojdtdv2G2NBeEMEQCinbbIDp0-j8",
        // "ojdtdv5dunW9h4IPi_g5QmCOXrk8", "ojdtdv71yky2o8yofg3TkSyog93E",
        // "ojdtdv-YX-hZH5rvM6ZEKpufjtdM", "ojdtdv7cSlzH5bgretl7z6KRy1kg",
        // "ojdtdv25Hi2EVmSx261xwS-hkimI", "ojdtdvzvkrN7dkuNQX_j7KqQFt0s",
        // "ojdtdv8avxBXl-Y125fCXuX3XkoE", "ojdtdv4qiejFkei1sHOwPQkKCBBc",
        // "ojdtdvxU9zYBR7eKKs9JXxmhnhSg", "ojdtdv3_I1-kLBtgQCAtreEwbj6Q",
        // "ojdtdvwWAVFl4beZQ9EsPtDXGZfM", "ojdtdv_SFd8auuyg6nMcRglR8fwk",
        // "ojdtdv7QXxYPRGsnwqlbiPbl4nc0", "ojdtdv_3dNhHDJNiV0BJt7wW-N5s",
        // "ojdtdv6UmYhpTxcJIhB7Wx-rsw-A", "ojdtdvxIJmm3QLmSmQ0A_-burblU",
        // "ojdtdv5RfSzodwzJc9q21RV25ntU", "ojdtdvzuWuKZ4Kt4R7wUDICIwgjk",
        // "ojdtdvxY6L0n8hi4dgJZJIU-UGC8", "ojdtdv8Ol12rFoADbdL_bwBnoudk",
        // "ojdtdv471JqvTvr_ey8HSRnavyvE", "ojdtdv4Wg1N5C78_bKZZeWatmwfQ",
        // "ojdtdv44r_FgijS6a1dMZiCC1lQ8", "ojdtdvzRpxBvichsFNs1GdecSFbU",
        // "ojdtdv2iiCSLGAXV9QdlP_cDzxCk", "ojdtdv7Fn9KIVivvBXekdksPIyPI",
        // "ojdtdv4Zan2hXG5JTM9lZ-a4rtug", "ojdtdv7DvvXRzlopcx8ZqwfLMqkY",
        // "ojdtdvzzIkfxLmTRTsDqkLHCE7O4", "ojdtdv5A4_dwNdjC7nt3HopPc5t0",
        // "ojdtdv46MxYob5z5GXy3udPlPZcw", "ojdtdv2VWmsrwLGTdJOb6zuyNfg4",
        // "ojdtdv9g1syNVzWjFSLjBmUipMb4", "ojdtdv1Z60PW9fswU7NXAwPmX7kQ",
        // "ojdtdv8nM7Zo3w32b9fkcLAoxVrY", "ojdtdv_gQAkrWa2-zFu794ZxoqfA",
        // "ojdtdvyMB31Bkc0GIMVFi1HBqQ9c", "ojdtdv0M_x8ujW02Qb-pkmhig2dk",
        // "ojdtdv0vbD0JgUxiqo83NobNxYuQ", "ojdtdv3vlUmuwfUww7sju36mzjFU" };
        //
        String[] openIds = { "omRftwTwHQe7jCRo_y40p8aAEe90",
                "omRftwY5RTvN5nIIA-xO0COWvGE8", "omRftwVA_Si13z3mQXFAYdiUFe0I",
                "omRftwUtx7oIvFNb-DwmHdZ5oSXE", "omRftwdwx2yR529I1bFYynYe_9W8" };

        // result:{"user_info_list":[{"subscribe":1,"openid":"ojdtdv8a8-q0zXinOXQjA1DKNMKg","nickname":"孟江凯","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/PiajxSqBRaELEDovx0IwDQROb4PsPXFV8DaGbwRGOg7cuhcWtT4FzvIQCzibNEOVicqL7rCmlGY0Rclkh5F35iaj0w\/0","subscribe_time":1477534469,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv5k5eIIwa6mViWLpO34fRA4","nickname":"Zz","sex":1,"language":"zh_CN","city":"台州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/Q3auHgzwzM42GTiaFx1j91TB7fONsVVX8VMblJKHFbzQF5HJ3gLAdWAkEibuoOOSQIdrf9nWl29fsWBoWsBBosqQ\/0","subscribe_time":1476413264,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv5fmsOwDeikUy11LbHkU6i8","nickname":"云伟","sex":1,"language":"zh_CN","city":"海淀","province":"北京","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/ajNVdqHZLLDjrPVcFKVvCibmJCzlYzfzvDhwbx7iashprNhHice0NhaUtj20XpNPYxF7iaiaA7vDYA7Kx5K3RtQiaoiaQ\/0","subscribe_time":1476345424,"remark":"李云伟","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv9JNbYQqukXvesUsGXRA13E","nickname":"葛","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/PiajxSqBRaELjEXx8FoMQoYx4zHFJiaPN8qgeqgbH1ynYTk8la5iaibeneK3kQ2Ar5WDbSI2UYHVxaxc9P3CXqowTQ\/0","subscribe_time":1476411602,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdvyrZkB-NNmXEfiJSAglyZ9Y","nickname":"cxd","sex":1,"language":"zh_HK","city":"温州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE3tpkSUXKIlo5kfnrY40YiaicoyGlRof97W0MxKUKB2kAgahf6DBPMva9c6cmzPSQeibppekuJ5Zj8IQ\/0","subscribe_time":1476413367,"remark":"陈显东","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv7NK4g9WplkZN7MMSpjmhT4","nickname":"阿迟","sex":1,"language":"zh_CN","city":"温州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/PiajxSqBRaEIFNC0qnNsu71eGh7Mu0kHcyssFKc7eTJgiaYQcXcnEUqQFEgJ7cicnTnRYEUicKjz7PDDPKz1SrSoxw\/0","subscribe_time":1477534436,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv2G2NBeEMEQCinbbIDp0-j8","nickname":"吴联请","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/ajNVdqHZLLCgmQKCoYiaz04XxcqYVRkFU6fEehlVW4FZWOibbypB5HuB79eBnCCXUXTmRnDcnuica5qwj7m5tbfxA\/0","subscribe_time":1476345513,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv5dunW9h4IPi_g5QmCOXrk8","nickname":"杨奎-哲力知产（JILY）","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/BxUSCQs4DMr4fh4nZGEoA1KcLoT9LXFPGict4lM1USFTA3XXan0aArG3O8y5FF8EKe1IicBdk6s5sTgV0QPgBEcKHdVSCUJLvG\/0","subscribe_time":1476420491,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv71yky2o8yofg3TkSyog93E","nickname":"小海哥","sex":1,"language":"zh_CN","city":"宁波","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/jj4e65x0Px0xEjWKe4wV9eV4ADOJ1dMvVNnshF8cnZuCw8NQvqndHUZFdNmpqVtCsrHEb3g0rKyShJbZaM7PmA\/0","subscribe_time":1476346341,"remark":"郑海清","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv-YX-hZH5rvM6ZEKpufjtdM","nickname":"圣","sex":0,"language":"zh_CN","city":"","province":"","country":"阿尔巴尼亚","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/yHGvrwBbicre1za26qjNzyYFJqSNYPTwlbciaf8hE25WCE1V8CywSNjooFZuf36ic78au4EVsUr1VWsH7mfjKA0XwsHictQRicun4\/0","subscribe_time":1479433623,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv7cSlzH5bgretl7z6KRy1kg","nickname":"丫丫","sex":2,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/jj4e65x0Px15iav9c8NsArwolwXMkAibJC32SRl9Jov1ibRrLNDqDTtq2j2iah3YYP4cFnWa4jqfWJ7TBxzMSc7VHA\/0","subscribe_time":1476345345,"remark":"赵琳","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv25Hi2EVmSx261xwS-hkimI","nickname":"刘大锟","sex":1,"language":"zh_CN","city":"南平","province":"福建","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE3FkDFrcqr9SQW5NJnFMnwmtke1UemFl5hOwJ00VQ7C7jNzVuK9icqtsVSeiaMwJHSLibWGVOJxCXuPw\/0","subscribe_time":1479433627,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdvzvkrN7dkuNQX_j7KqQFt0s","nickname":"1234567","sex":1,"language":"zh_CN","city":"福州","province":"福建","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/ajNVdqHZLLDryadT1rUubuXE9d2DjCXO9s6B0G2pCAEZopj47vjXf5uneFbcw8Tt69tZ5MznangnQGW4MGxy3Q\/0","subscribe_time":1479434335,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv8avxBXl-Y125fCXuX3XkoE","nickname":"泡泡","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/PiajxSqBRaEJRKs5gkiasUzFicKEZJiaz2piclXSXPq7e34IDibGPz6ibBL6boBwg16zTzdicZ0Wtib8stEItqqOaMzhsYg\/0","subscribe_time":1476417879,"remark":"刁晨钰","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv4qiejFkei1sHOwPQkKCBBc","nickname":"逗逼煌","sex":2,"language":"zh_CN","city":"三明","province":"福建","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/Q3auHgzwzM5icq96Ux8HFtBWDGw0FIcHBG1DuT8ic1RfTYAgTrdX9VSwucxMV5q5l36UYiaK6SItia89tUtNGzdsgsmWibBS1O3ibeHw5fCUt8Izo\/0","subscribe_time":1479450116,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdvxU9zYBR7eKKs9JXxmhnhSg","nickname":"风子","sex":1,"language":"zh_CN","city":"安庆","province":"安徽","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/yHGvrwBbicre3gTdibStWEaSxRmIYD44IZKxSDibsBgbR7mDLUW4ia3IxrH66oXb43OD2GcrW2Y037xpW32Fw7ibbzazEzRqhzMFF\/0","subscribe_time":1479798395,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv3_I1-kLBtgQCAtreEwbj6Q","nickname":"LvQS","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE2l31vrRcybicAGNvervuzjtG5jJ3cDI3jRH1akxo4icjmXiaEPOqPK7Hqu8nfmAX17Rl6sz4GutU9ANcfcPAfWBD5\/0","subscribe_time":1479447127,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdvwWAVFl4beZQ9EsPtDXGZfM","nickname":"夏-洛忆","sex":2,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/aFmT4hWphanX3BZPE84sTDp7TsNYtXIelrmqNocM4x3A9J0moarwbyoFMJsZdnCrLibfkHibNJPtojcTqUzgiaRruNBf20wKNGZ\/0","subscribe_time":1479431814,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv_SFd8auuyg6nMcRglR8fwk","nickname":"Chen","sex":2,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE2l31vrRcybicPOGnCUdbmRbXA5xm3JxDLGIMj0T5T7ic9O6vxHMFWBU5iajcgviccEYdvzKGVLTMLeqy1ZMcvDLw69\/0","subscribe_time":1479168255,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv7QXxYPRGsnwqlbiPbl4nc0","nickname":"sun","sex":1,"language":"zh_CN","city":"南阳","province":"河南","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/yHGvrwBbicrfkiay1LAZzBRuegPVs5Yu5cwSjawOvfM2ldZVbDB2au7kCAcG8wjJRDFJ4aQibK95Q6riaPMB1pTMXuBd0ek8a6zD\/0","subscribe_time":1479787653,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv_3dNhHDJNiV0BJt7wW-N5s","nickname":"光辉岁月","sex":0,"language":"zh_CN","city":"","province":"","country":"","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/LqmKdbAsb8CLqDt7bia3vhgsNlof2T9It4z3ibmMUqaR4CfDUJIduXxOaY99Eszt4CLeXJAsKddcPzxkC5icoOaaEllNYJNS0XR\/0","subscribe_time":1479434337,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv6UmYhpTxcJIhB7Wx-rsw-A","nickname":"宓永宝-Koala","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/LqmKdbAsb8Dq13wSGwRwKxlFJoppVH5fn38g4JHcl0O7D11oD0oJXiavo5FmShCmYE4K0IQBsw9EMapP7vSrff4rHD0pibNI4N\/0","subscribe_time":1479425520,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdvxIJmm3QLmSmQ0A_-burblU","nickname":"🙄","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE2l31vrRcybicP9r8SfDyGick3VSSRFsSE2DRY7jqGkDicNth78PtlB5Ff5IMplLP15ZsEr73B29ppXjaPluF83wvg\/0","subscribe_time":1476416651,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv5RfSzodwzJc9q21RV25ntU","nickname":"z","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE2l31vrRcybicFU3346d0w2tZurXz7uibEFC2XpLzxB1F6LgzicMpOn9ibYNiaqKdxWnuuysickf2FmpryFHhHfXH1KCp\/0","subscribe_time":1476347956,"remark":"罗启","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdvzuWuKZ4Kt4R7wUDICIwgjk","nickname":"陈涛","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE2l31vrRcybicDJtxg1YJLQPyPibGanIRbTRiaImuiaPwoM6k4ic5ulicKMKGsbg20WiaUS9Rnt77C8OtBTyh1JEVL3dAv\/0","subscribe_time":1479441297,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdvxY6L0n8hi4dgJZJIU-UGC8","nickname":"Dreaming","sex":2,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/jj4e65x0Px1AibhLVywIG6BuTOcDYNRAvOB2w2IFZ5s5bvn8wibM1h2OpgklXJa5APuQUaZ9Ub9rmTMEzIeibE4aVMzYZUDBgtN\/0","subscribe_time":1479444952,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv8Ol12rFoADbdL_bwBnoudk","nickname":"一到晚上就发疯的木山家的芒果大帝","sex":1,"language":"zh_CN","city":"台州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/yHGvrwBbicrfMrKSBMF97H1rNRQs7FTgmczQfxl7picIrJDwGZBSlV8mXrOhC4f9BsWFww9uaLszpvWo1MkKMGq7ky5lPnRTNc\/0","subscribe_time":1479439325,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv471JqvTvr_ey8HSRnavyvE","nickname":"倪可军Nike
        // 汇联金融区块链","sex":1,"language":"zh_CN","city":"金华","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/yHGvrwBbicrfMrKSBMF97H80VTL5uY29mOyWmP2ct59WMvJdZFsGgYWxofD7yvmQb2A7AqEkgxicXP14lnn3LUn7JAls2SGIOH\/0","subscribe_time":1478490567,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv4Wg1N5C78_bKZZeWatmwfQ","nickname":"A杭州百度。李国豪","sex":1,"language":"zh_CN","city":"温州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE2l31vrRcybicO7Gb2OMpgUBR34xNp3jDf9cKU46ylv9icTuBWIeHJpKX3jKe0WuOQIZJconlxRb9TAqCianZLBjWx\/0","subscribe_time":1478735032,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv44r_FgijS6a1dMZiCC1lQ8","nickname":"四叶草","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/LqmKdbAsb8BCwTO0wXrGbGh2iaTqxacUicBvJL6sUkwfnenv1pQIYJflM9QOGwXImTWup0ukelSEDdlYMl0GwRdibUUAtT0M5co\/0","subscribe_time":1476409059,"remark":"谢延径","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdvzRpxBvichsFNs1GdecSFbU","nickname":"任憔悴","sex":2,"language":"zh_CN","city":"荆州","province":"湖北","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/Q3auHgzwzM4QnT0Msf3vSSWg5cN8GbuPuTicibbmjYsAh1WjnZTwY6z8ewc5TMxBHTFlUuKLYlvpFIhN7apC1E6mhBeVP3PCXswISNhiaW6gy4\/0","subscribe_time":1479481519,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv2iiCSLGAXV9QdlP_cDzxCk","nickname":"叶蜀黍","sex":2,"language":"zh_CN","city":"福州","province":"福建","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/yHGvrwBbicrfMrKSBMF97H7eBqSo61R8VdtrWjK1ibcQRAd0rRGymEahLXo0ibVeE9J1xLPQ17WhbBEqIc9uqzoY4BFfgIZgau1\/0","subscribe_time":1479435151,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv7Fn9KIVivvBXekdksPIyPI","nickname":"宓锦涛","sex":1,"language":"zh_CN","city":"宁波","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE3x2Wd1p8iapSv59cB5XDmhkBjKhy5Kev0u7WENiboAniaKtWBlxqKicib63QFmAmp9LTzaff2ockEKGLLrAbTEiboUDf\/0","subscribe_time":1478705309,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv4Zan2hXG5JTM9lZ-a4rtug","nickname":"~zZ","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE2l31vrRcybicJd8alo3oJtykSqPuaFXsQLqLBKdbLrMVZkkMCJ2RctSv6q4micCIbicC6ArSU9aT2pZQia1pnftl2S\/0","subscribe_time":1479444879,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv7DvvXRzlopcx8ZqwfLMqkY","nickname":"学辉","sex":1,"language":"zh_CN","city":"福州","province":"福建","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/Q3auHgzwzM4QnT0Msf3vSSWg5cN8GbuPRF40SJolRCTEun0tOkJOI4XxRnkp4ibB6ibfhF6O4dvWCGDCqGTvYF4cEaCHjDEy0F01x1Ps7D4Es\/0","subscribe_time":1479434021,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdvzzIkfxLmTRTsDqkLHCE7O4","nickname":"馳
        // ㍿","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE2l31vrRcybicGaic4tmpZOTFxA0dsicL3z4VEWP5N1yb1wCmwLvXtiaCMYgnVosrDnEHcaD13IlNh2jOR6l7wicycfic\/0","subscribe_time":1479151565,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv5A4_dwNdjC7nt3HopPc5t0","nickname":"蔡苗苗","sex":2,"language":"zh_CN","city":"温州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE2l31vrRcybicESnXS1KwF6ZXTUbicPs66Sqw8cUkghGXPxngLkApPvnYWJasLicV3pHufl9DWff4dlKEICe9pFQK1\/0","subscribe_time":1476345787,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv46MxYob5z5GXy3udPlPZcw","nickname":"lordcyc","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE2l31vrRcybicKxF6IrBlfXOT15tuw6Yex4WC87QpkuB0v3k1ytNd40p1P7ErFjNibfRC6gygy4Iko4XgUUQHO0FS\/0","subscribe_time":1476414554,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv2VWmsrwLGTdJOb6zuyNfg4","nickname":"傅晨明","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/yHGvrwBbicrfMrKSBMF97H5JFGcuviaBicfibAQbhlrQLtCpwdBScPs1V7sOIYadKQvAfluhrYz6NBgHjdiakGia43ZVubZhricEpJk\/0","subscribe_time":1479435269,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv9g1syNVzWjFSLjBmUipMb4","nickname":"柯","sex":2,"language":"zh_CN","city":"","province":"","country":"丹麦","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/aFmT4hWphanX3BZPE84sTBY19jtVRLKOpvwDsNIuMrgjcTby5xv5X0Ko2cDMflibd0WRibvfy189z87EXW9B5tK4Jz1brbic2hr\/0","subscribe_time":1479125621,"remark":"亦柯","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv1Z60PW9fswU7NXAwPmX7kQ","nickname":"往事随风","sex":1,"language":"zh_CN","city":"荆州","province":"湖北","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/aFmT4hWphanX3BZPE84sTEML9UA6B6V4uNYEhLy11wDFskkCuNWIApPna8vjlswwEnGicTZicfhuHhribfzAicWXpOc3vjpvoorS\/0","subscribe_time":1479432101,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv8nM7Zo3w32b9fkcLAoxVrY","nickname":"田lei","sex":1,"language":"zh_CN","city":"","province":"都柏林","country":"爱尔兰","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE2l31vrRcybicLbRnHHCNNWVX3zH5lMUbw7dntuL20ooWLWjDSxiasTeN0iaHMpAZub4zcUTyQovpvnKpk1NTdDhQU\/0","subscribe_time":1476411240,"remark":"田磊","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv_gQAkrWa2-zFu794ZxoqfA","nickname":"Pulitzer莉莉","sex":2,"language":"zh_CN","city":"商丘","province":"河南","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE2l31vrRcybicM0WRVHIKJKa3SkibsBfSrCgwbaEZG3QSiadcGox7YtyFZHszxVofibZzQbBP4lVtDuK3XzDEqYbGSq\/0","subscribe_time":1479444102,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdvyMB31Bkc0GIMVFi1HBqQ9c","nickname":"钱栋栋","sex":1,"language":"zh_CN","city":"宁波","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/LqmKdbAsb8BCwTO0wXrGbM6xFxcYKJciaRtGibCgUv3YBEIAJ6uVvibT63IroscrHniaNo5fd1PgyjzkCUib0Yby0ZibRlSURTXcmm\/0","subscribe_time":1476413458,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv0M_x8ujW02Qb-pkmhig2dk","nickname":"LY8","sex":1,"language":"zh_CN","city":"福州","province":"福建","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/Q3auHgzwzM5L94Psq2VzRzWtAHicpQZxTkw5m6ibxP2zx1P4ibIhcgHYNRBTiadia6nW5JiaRK2QTTahRKYRNgGK33E0WfwnmKRrcUhb2v9zFBLUs\/0","subscribe_time":1479434343,"remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv0vbD0JgUxiqo83NobNxYuQ","nickname":"蒲絮与柳公英","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/icP22aU12SE21jAw4RZe49y4xGszG55ZicYEKBfmrRpk0FMKcYH3Kk1nK3m8Inb1FGfchMAmGp39icRzcHthOgjibN3uibEv6QRwx\/0","subscribe_time":1476345607,"remark":"贺建琼","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"ojdtdv3vlUmuwfUww7sju36mzjFU","nickname":"tingting","sex":2,"language":"zh_CN","city":"金华","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/yHGvrwBbicrfMrKSBMF97H67dzwzr4GBmKDHEyjBBRqNdgCX3YujBa8FiaHLiasAMc1YkAEYCpFkgWm7NPK1Y6JcStsDZZPlp7U\/0","subscribe_time":1476345415,"remark":"陈婷婷","groupid":0,"tagid_list":[]}]}

        // result:{"user_info_list":[{"subscribe":1,"openid":"omRftwTwHQe7jCRo_y40p8aAEe90","nickname":"宓永宝-Koala","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/iaAUDe9mia9K8tIlM9HtNTFxxiazib5nm6vIn4Aqte099cAMPaXOfZzggJs6U9zyicclK7zquBpBsq8vySoyYVxlp43SiaImXZMnvD\/0","subscribe_time":1479711594,"unionid":"oJCjlwVEhGFzr4EIfy1m1ylnTQ5I","remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"omRftwY5RTvN5nIIA-xO0COWvGE8","nickname":"z","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/SAegktAYa5M5icqLAicAQO4QzRllI1SYO3CMpEUDttOl3tbECKQn39zq1UuBpmUBGmu1EQSUsYujGYY2sGGyBpw4Z6PMQbbTBM\/0","subscribe_time":1479711490,"unionid":"oJCjlwZFMenFp5GWUsaHP9QuUSYg","remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"omRftwVA_Si13z3mQXFAYdiUFe0I","nickname":"四叶草","sex":1,"language":"zh_CN","city":"杭州","province":"浙江","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/iaAUDe9mia9K97a8ViaoR11RYZicflV0fAqt0sQBWzehc8ORpA6hox6kFWZpc9iajv5AoiceAGoRicuwFXk24tIl6mLzajiaqK0S0e2H\/0","subscribe_time":1480651129,"unionid":"oJCjlwecRJmft8W48MtYidEmR58c","remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"omRftwUtx7oIvFNb-DwmHdZ5oSXE","nickname":"柯","sex":2,"language":"zh_CN","city":"","province":"","country":"丹麦","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/VqApBoqIb5Via488miaAsJkFR721VeHgGeqNt0GPboJQtSLUVAEZlR99f3YTdrAO5DDHf2GrpiabkcQx7dlFucbbHzvibZlzG502\/0","subscribe_time":1480644577,"unionid":"oJCjlwdL0z38A2K3IXRLF0epTalA","remark":"","groupid":0,"tagid_list":[]},{"subscribe":1,"openid":"omRftwdwx2yR529I1bFYynYe_9W8","nickname":"田lei","sex":1,"language":"zh_CN","city":"","province":"都柏林","country":"爱尔兰","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/SAegktAYa5M5icqLAicAQO4bNH6fguPPFhfTkEQib6j3OFIZYZRLFfXCFydyKicuiaUficEsBzKCqmoX7DdrQVCm54q2NzIP7uEaicia\/0","subscribe_time":1479723543,"unionid":"oJCjlwaNhiLtQ94b2FivdAM2xdOc","remark":"","groupid":0,"tagid_list":[]}]}
        List<UserData> user_list = new ArrayList<UserData>();
        for (int i = 0; i < openIds.length; i++) {
            user_list.add(new UserData(openIds[i], null));
        }
        String content = "{\"user_list\":" + JsonUtil.Object2Json(user_list)
                + "}";
        System.out.println(content);
        try {
            String result = new String(post(postUrl, content, "UTF-8"));
            System.out.println("result:" + result);
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void getOpenIdList() {
        String ACCESS_TOKEN = "";
    }

    public static void main(String[] args) {
        getUserList();
    }
}
