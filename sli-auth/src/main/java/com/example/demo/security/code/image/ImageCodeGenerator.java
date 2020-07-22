package com.example.demo.security.code.image;

import com.example.demo.security.ValidateCodeGenerator;
import com.google.code.kaptcha.Producer;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;

/**
 * 默认的图片验证码生成器
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

    //    private SecurityProperties securityProperties;
    private Producer captchaProducer;

    private static final Integer ExpireIn = 180;

    /**
     * 生成图片验证码.
     *
     * @param request request请求
     * @return 图片验证码
     */
    @Override
    public ImageCode generate(ServletWebRequest request) {
        String kaptchaCode = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(kaptchaCode);
        return new ImageCode(image, kaptchaCode, ExpireIn);
    }

    /**
     * 设置securityProperties的值
     *
     * @param securityProperties 权限属性文件
     */
//    public void setSecurityProperties(SecurityProperties securityProperties) {
//        this.securityProperties = securityProperties;
//    }

    /**
     * Sets captcha producer.
     *
     * @param captchaProducer the captcha producer
     */
    public void setCaptchaProducer(Producer captchaProducer) {
        this.captchaProducer = captchaProducer;
    }

}
