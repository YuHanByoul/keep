package com.kbrainc.plum.rte.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.AbstractView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ComponentScan(basePackages = "com.kbrainc.plum.rte.util")
@Slf4j
public class QRCodeView extends AbstractView {

    public QRCodeView() {
        //뷰의 컨텐츠 타입 설정
        //이미지로 뿌려야 함.
        setContentType("image/png; charset=UTF-8");
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());

        String userAgent = request.getHeader("User-Agent");
        boolean ie = userAgent.indexOf("MSIE") > -1;

        //String fileName = "QRCode.png";
        //
        //if(ie){
        //    fileName = URLEncoder.encode(fileName, "UTF-8");
        //}else{
        //    fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        //}

        //res.setHeader("Content-Disposition", "attachment; filename=\""+fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        OutputStream out = response.getOutputStream();

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String text = (String)model.get("qrText");
        text = new String(text.getBytes("UTF-8"), "ISO-8859-1");
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 250, 250);

        //zxing에서 스트림에 파일을 뿌릴수있도록 메소드를 지원함.
        MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
        out.flush();
    }
   
    
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            Base64.Encoder encoder = Base64.getEncoder();
            imageString = encoder.encodeToString(imageBytes);

            bos.close();
        } catch (IOException e) {
            log.error("encodeToString.IOException");
        }
        return imageString;
    }
    
    
    
    
    
}
