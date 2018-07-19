package android_serialport_api;

import android.util.Log;
import java.util.Random;

/**
 * Created by X6ti-M2 on 2017/12/12.
 * 数据转换工具 小栗
 */

public class MyFunc {

    // 判断奇数或偶数，位运算，最后一位是1则为奇数，为0是偶数
    static public int isOdd(int num)
    {
        return num & 0x1;
    }


    //Hex字符串转int
    static public int HexToInt(String inHex)
    {
        return Integer.parseInt(inHex, 16);
    }


    //Hex字符串转byte
    static public byte HexToByte(String inHex)
    {
        return (byte)Integer.parseInt(inHex,16);
    }


    //1字节转2个Hex字符
    static public String Byte2Hex(Byte inByte)
    {
        return String.format("%02x", inByte).toUpperCase();
    }


    //字节数组转转hex字符串
    static public String ByteArrToHex(byte[] inBytArr)
    {
        StringBuilder strBuilder=new StringBuilder();
        int j=inBytArr.length;
        for (int i = 0; i < j; i++)
        {
            strBuilder.append(Byte2Hex(inBytArr[i]));
            strBuilder.append(" ");
        }
        return strBuilder.toString();
    }


    //字节数组转转hex字符串，可选长度
    static public String ByteArrToHex(byte[] inBytArr,int offset,int byteCount)
    {
        StringBuilder strBuilder=new StringBuilder();
        int j=byteCount;
        for (int i = offset; i < j; i++)
        {
            strBuilder.append(Byte2Hex(inBytArr[i]));
        }
        return strBuilder.toString();
    }


    //转hex字符串转字节数组
    static public byte[] HexToByteArr(String inHex)//hex字符串转字节数组
    {
        int hexlen = inHex.length();
        byte[] result;
        if (isOdd(hexlen)==1)
        {//奇数
            hexlen++;
            result = new byte[(hexlen/2)];
            inHex="0"+inHex;
        }else {//偶数
            result = new byte[(hexlen/2)];
        }
        int j=0;
        for (int i = 0; i < hexlen; i+=2)
        {
            result[j]=HexToByte(inHex.substring(i,i+2));
            j++;
        }
        return result;
    }



    //字節數組轉字符
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    // byte转int
    public final static int getInt( byte ...buf) {
        boolean asc =true;
        int len =1;
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (len > 4) {
            throw new IllegalArgumentException("byte array size > 4 !");
        }
        int r = 0;
        if (asc)
            for (int i = len - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        else
            for (int i = 0; i < len; i++) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        return r;
    }

    //补位16进制转2进制
    public static byte[] getBooleanArray(byte b){
        byte[] array =new byte[8];
        for (int i =0; i<array.length;i++){
            array[i] = (byte)(b & 1);
            b =(byte)(b>>1);
        }
        return  array;
    }

    //计算校验和
    public static byte CalBlock( byte[] ptr) {
        int len =getInt( ptr[3]);
        int j = 0;
        for (int i = 3; i < 3+len; i++) {//获取校验码
            j += getInt( ptr[i]);
        }
        return (byte)j;
    }


    //16int转10byte
    public static byte Tobyte(String key) {
        int j =Integer.parseInt(key, 16);
        return (byte)j;
    }

    //高低位
    public static byte getHeight( int data){//获取高四位
        byte height [];
        if(data > 0xffff) {
            return 0x00;
        }
        String b=Integer.toHexString(data /256);
        height = toBytes(b);
        return height[0];
    }
    public static byte getLow( int data){//获取低四位
        byte low[];
        if(data > 0xffff) {
            return 0x00;
        }
        int a =data % 256;
        String b=Integer.toHexString(a);
        low =  toBytes(b);
        return low[0];

    }

    //16进制String转成16byte
    public static byte[] toBytes(String str) {

        if(str.length()==1){
            StringBuilder sb = new StringBuilder(str);//构造一个StringBuilder对象
            sb.insert(0, "0");//在指定的位置1，插入指定的字符串
            str = sb.toString();
        }
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes;
    }

}
