package com.jni_text.jyl.ailock.uitls;

import javax.xml.transform.sax.SAXTransformerFactory;

public class BLEUtils {
    public static byte[] getHeadBytes(byte head, byte package_count, byte packages_index, byte packages_length) {
        byte[] bytes = new byte[4];
        bytes[0] = head;
        bytes[1] = package_count;
        bytes[2] = packages_index;
        bytes[3] = packages_length;

        return bytes;
    }

    public static byte[] getFingerPrintData(String user_id, String taskType) {
        //s001+user_id
        byte[] user_id_bytes = user_id.getBytes();
        byte[] taskType_bytes = taskType.getBytes();
        byte[] data_byte = byteMerger(taskType_bytes, user_id_bytes);
        return data_byte;
    }

    //System.arraycopy()方法
    public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
        byte[] bt3 = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }

    //16进制byte数组转字符串
    public static String bytes2HexString(byte[] b) {
        String r = "";

        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r += hex.toUpperCase();
        }

        return r;
    }

    public static String oneByte2HexString(byte b) {
        String r = "";
        String hex = Integer.toHexString(b & 0xFF);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        r = hex.toUpperCase();

        return r;
    }

    //获取校验位
    public static byte getXor(byte[] data) {
        byte temp = data[0];
        for (int i = 1; i < data.length; i++) {
            temp ^= data[i];
        }
        return temp;
    }


    //long转byte数组
    public static byte[] longToByteArray(long num) {
        byte[] byteNum = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

}
