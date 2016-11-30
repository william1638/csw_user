package com.std.sms.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties props = null;

    public static void init(String configFile) {
        props = new Properties();
        try {
            InputStream in = PropertiesUtil.class
                .getResourceAsStream(configFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            props.load(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if (props == null) {
            // throw new Exception("配置文件初始化失败");
        }
        return props.getProperty(key);
    }

    public static final class Config {
        public static String APNS_PRODUCTION = props
            .getProperty("APNS_PRODUCTION");

        public static String TEMPLATE_ID = props.getProperty("TEMPLATE_ID");

        public static String URL = props.getProperty("URL");

        public static String COLOR1 = props.getProperty("COLOR1");

        public static String COLOR2 = props.getProperty("COLOR2");

        public static String BJ_ROLE_CODE = props.getProperty("BJ_ROLE_CODE");

        public static String USER_URL = props.getProperty("USER_URL");
    }
}
