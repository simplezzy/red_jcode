package com.redcode.demo.qrcode;

import java.io.IOException;

/**
 * Created by zhiyu.zhou on 2017/8/30.
 */
public class QRTools {

    /*public static boolean generateCode(String productId) throws WriterException, IOException {

        // 这里是URL，扫描之后就跳转到这个界面
        String text = "https://www.baidu.com/?uudi=" + productId;

        String path = "E:/"; // 图片生成的位置
        int width = 400;
        int height = 400;
        // 二维码图片格式
        String format = "jpg";
        // 设置编码，防止中文乱码
        Hashtable<EncodeHintType, Object> ht = new Hashtable<EncodeHintType, Object>();
        ht.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 设置二维码参数(编码内容，编码类型，图片宽度，图片高度,格式)
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, ht);
        // 生成二维码(定义二维码输出服务器路径)
        File outputFile = new File(path);
        if (!outputFile.exists()) {
            // 创建文件夹
            outputFile.mkdir();
        }

        int b_width = bitMatrix.getWidth();
        int b_height = bitMatrix.getHeight();
        // 建立图像缓冲器
        BufferedImage image = new BufferedImage(b_width, b_height, BufferedImage.TYPE_3BYTE_BGR);
        for (int x = 0; x < b_width; x++) {
            for (int y = 0; y < b_height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
            }
        }
        // 生成二维码
        ImageIO.write(image, format, new File(path + "/code." + format));
        // 二维码的名称
        // code.jpg

        return true;

    }
*/
}
