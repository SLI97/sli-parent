package com.sli.security.code.image;

import com.sli.security.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码
 * @author jared
 */
public class ImageCode extends ValidateCode {

    private static final long serialVersionUID = -8961976676579903385L;
    private BufferedImage image;

    /**
     * Instantiates a new Image code.
     *
     * @param image 图片
     * @param code 验证码
     * @param expireIn 过期时间
     */
    ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    /**
     * Instantiates a new Image code.
     *
     * @param image the image
     * @param code the code
     * @param expireTime the expire time
     */
    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
