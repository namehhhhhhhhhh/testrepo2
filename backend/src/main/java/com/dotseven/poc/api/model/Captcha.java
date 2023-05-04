package com.dotseven.poc.api.model;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Base64;
import java.io.ByteArrayOutputStream;

public class Captcha {
    private String base64Url;
    private String ans1;
    private String ans2;

    public Captcha(BufferedImage img, String ans1, String ans2) {
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "png", baos);
            this.base64Url = Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            System.err.println("Couldn't interpretate image from File");
            e.printStackTrace();
            System.exit(1);
        }

        this.ans1 = ans1;
        this.ans2 = ans2;
    }

    public String getBase64Url() {
        return base64Url;
    }

    public void setBase64Url(String url) {
        this.base64Url = url;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

}
