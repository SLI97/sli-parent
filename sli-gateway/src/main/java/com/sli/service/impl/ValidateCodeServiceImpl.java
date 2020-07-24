package com.sli.service.impl;

import com.google.code.kaptcha.Producer;
import com.sli.common.core.constant.Constants;
import com.sli.common.core.domain.AjaxResult;
import com.sli.common.core.exception.CaptchaException;
import com.sli.common.core.utils.Base64Utils;
import com.sli.common.core.utils.IdUtils;
import com.sli.common.core.utils.RedisUtils;
import com.sli.service.ValidateCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码实现处理
 *
 * @author ruoyi
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService
{
    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 生成验证码
     */
    @Override
    public AjaxResult createCapcha() throws IOException
    {
        // 生成验证码
        String capText = producer.createText();
        String capStr = capText.substring(0, capText.lastIndexOf("@"));
        String verifyCode = capText.substring(capText.lastIndexOf("@") + 1);
        BufferedImage image = producer.createImage(capStr);
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        redisUtils.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return AjaxResult.error(e.getMessage());
        }

        AjaxResult ajax = AjaxResult.success();
        ajax.put("uuid", uuid);
        ajax.put("img", Base64Utils.encode(os.toByteArray()));
        return ajax;
    }

    /**
     * 校验验证码
     */
    @Override
    public void checkCapcha(String code, String uuid) throws CaptchaException
    {
        if (StringUtils.isEmpty(code))
        {
            throw new CaptchaException("验证码不能为空");
        }
        if (StringUtils.isEmpty(uuid))
        {
            throw new CaptchaException("验证码已失效");
        }
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
//        String captcha = redisService.getCacheObject(verifyKey);
//        redisService.deleteObject(verifyKey);

//        if (!code.equalsIgnoreCase(captcha))
//        {
//            throw new CaptchaException("验证码错误");
//        }
    }
}
