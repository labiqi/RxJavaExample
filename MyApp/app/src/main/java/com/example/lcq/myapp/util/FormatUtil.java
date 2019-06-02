package com.example.lcq.myapp.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

public class FormatUtil {

    public static  int getPointNum(String str) {
        int res = 0;
        if(str.isEmpty() || str.indexOf('.') == -1) {
            return res;
        }
        String endStr = str.substring(str.indexOf('.'));
        if(endStr.length() <= 1) {
            return  res;
        }
        res = ignoreEndZero(endStr) - 1;
        return res;
    }

    public static int ignoreEndZero(String str) {
        int res = str.length();
        for(int j = str.length() -1; j>=0; j--) {
            if(str.charAt(j) == '0') {
                res--;
            } else {
                break;
            }
        }
        return res;
    }

    public  String formatDouble3(String str) {
        if(str == null || "".equals(str)) {
            return "";
        }
        double num = parsePrice(str);
        DecimalFormat df = new DecimalFormat("0.000");

        return df.format(num);
    }


    public static double doubleMul(double a,double b) {
        BigDecimal biga = new BigDecimal(String.valueOf(a));
        BigDecimal bigb = new BigDecimal(String.valueOf(b));
        BigDecimal mul = biga.multiply(bigb);
        return Double.parseDouble(mul.toString());
    }

    public static String formatDouble4(double d) {
        DecimalFormat df = new DecimalFormat("#.000");
        return df.format(d);
    }



    public static double parsePrice(String priceStr) {
        if (TextUtils.isEmpty(priceStr)) {
            return 0.0F;
        }
        try {
            return Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            return 0.0F;
        }
    }

    public static double formatDouble2(double d) {
        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(3, RoundingMode.HALF_DOWN);
        return bg.doubleValue();
    }


    public static String getName(Context context, int arrayId, String id) {
        if (TextUtils.isEmpty(id)) {
            return "";
        }
        String[] array = context.getResources().getStringArray(arrayId);
        for (String item : array) {
            String[] itemArray = item.split(":", -1);
            if (itemArray.length >= 2 && itemArray[0].equals(id)) {
                return itemArray[1];
            }
        }
        return "";
    }

    //获取mac地址
    public static String getMac(Context context) {
        String res = "";
        return res;
    }

    public static String getMacDefault(Context context) {
        String mac = "";
        if (context == null) {
            return mac;
        }
        WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = null;
        try {
            info = wifi.getConnectionInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (info == null) {
            return null;
        }
        mac = info.getMacAddress();
        if (!TextUtils.isEmpty(mac)) {
            mac = mac.toUpperCase(Locale.ENGLISH);
        }
        return mac;
    }


    /**
     * Android 6.0-Android 7.0 获取mac地址
     */
    public static String getMacAddress() {
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec("cat/sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            while (null != str) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();//去空格
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }

        return macSerial;
    }



    public static String getMacFromHardware() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) {
                    continue;
                }

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }





}
