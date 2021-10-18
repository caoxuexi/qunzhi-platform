package com.xidian.qunzhi.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

public class ImageUtil {
    /**
     * 将图片二进制数据进行 base64 编码
     *
     * @param bufImg
     * @return base64 编码后的字符串
     */
    public static String encodeImg2Base64(BufferedImage bufImg, String formatName) {
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufImg, formatName, outputStream);
        } catch (IOException e) {
            throw new RuntimeException("Base64 编码失败！" + e.getMessage());
        }
        Base64.Encoder encoder=Base64.getEncoder();
        return Arrays.toString(encoder.encode(outputStream.toByteArray()));
    }

    private ImageUtil() {
    } // 工具类私有化构造方法是常见的做法
}