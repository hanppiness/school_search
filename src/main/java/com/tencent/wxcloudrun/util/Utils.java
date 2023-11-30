package com.tencent.wxcloudrun.util;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson2.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    //二维码图片的宽度
    private static int DEFAULT_WIDTH = 500;

    //二维码图片的高度
    private static int DEFAULT_HEIGHT = 500;

    private static final   int FRONT_COLOR=0x000000;

    private static final int BACKGROUND_COLOR=0xFFFFFF;


    public static void createCodeToOutPutStream(String content,OutputStream outputStream,int width, int height) throws Exception {
        if(!StringUtils.hasText(content)) return;
        BufferedImage bufferedImage=getBufferedImage(content,width,height);
        ImageIO.write(bufferedImage,"png",outputStream);
    }

    private  static BufferedImage getBufferedImage(String content, int width, int height) throws Exception {
            if(width == 0) width=DEFAULT_WIDTH;
            if(height == 0) height=DEFAULT_HEIGHT;
            Map<EncodeHintType,Object> hints=new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.MARGIN,1);
            MultiFormatWriter formatWriter=new MultiFormatWriter();
            Rs rs=new Rs();
            rs.setId(enc(content));
            BitMatrix bitMatrix=formatWriter.encode(JSONObject.toJSONString(rs),BarcodeFormat.QR_CODE,width,height,hints);
            BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
            for (int x=0; x < width; x++){
                for(int y=0;y < height; y++){
                    bufferedImage.setRGB(x,y,bitMatrix.get(x,y)?FRONT_COLOR:BACKGROUND_COLOR);
                }
            }
            return bufferedImage;
    }

    private final static String KEY="1234567812345678";
    private final static String IV="1234567812345678";
    public static String enc(String text){
        AES des=new AES(Mode.CBC, Padding.PKCS5Padding,KEY.getBytes(),IV.getBytes());
        return des.encryptBase64(text);
    }
    public static String dec(String text){
        AES des=new AES(Mode.CBC, Padding.PKCS5Padding,KEY.getBytes(),IV.getBytes());
        return des.decryptStr(text);
    }
   private final static  String INT_NOT_ZERO="123456789";
    //生成14位随机码
    /**
     *      线程不安全(该方法的调用并发量不高,所以无需加锁)
     * @return
     */
    public  static  String  genCode(int num){
        char[] nonceChars=new char[num];
        for (int index = 0; index < nonceChars.length; index++){
            nonceChars[index]=INT_NOT_ZERO.charAt(RandomHolder.secRandom.nextInt(INT_NOT_ZERO.length()));
        }
        return new String(nonceChars);
    }
}
