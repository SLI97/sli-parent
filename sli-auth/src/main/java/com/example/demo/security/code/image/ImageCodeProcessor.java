package com.example.demo.security.code.image;

import com.example.demo.security.ValidateCodeGenerator;
import com.example.demo.security.ValidateCodeRepository;
import com.example.demo.security.impl.AbstractValidateCodeProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 图片验证码处理器
 *
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Resource
    private ObjectMapper objectMapper;

    /**
     * Instantiates a new Abstract validate code processor.
     *
     * @param validateCodeGenerators the validate code generators
     * @param validateCodeRepository the validate code repository
     */
    public ImageCodeProcessor(Map<String, ValidateCodeGenerator> validateCodeGenerators,
                              ValidateCodeRepository validateCodeRepository) {
        super(validateCodeGenerators, validateCodeRepository);
    }

    /**
     * 发送图形验证码，将其写到响应中
     *
     * @param request the request
     * @param imageCode the image code
     *
     * @throws Exception the exception
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        HttpServletResponse response = request.getResponse();
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(imageCode.getImage(), "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}