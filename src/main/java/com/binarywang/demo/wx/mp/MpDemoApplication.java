package com.binarywang.demo.wx.mp;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author binary wang
 */
@RestController
@RequestMapping("/")
@SpringBootApplication
public class MpDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpDemoApplication.class, args);
    }

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/test")
    public String test() throws WxErrorException {
        // this.mpService.getWxMpConfigStorage().getAppId();
        return  this.wxMpService.getAccessToken();
    }

    @GetMapping("/send")
    public String send() throws WxErrorException {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("oD7tU6tidlm647pWSMwdKhZSHbJw")
                .templateId("HRK3-MOlRwtTa14OlqoIJvp_S0UdHl5q5mxQknLXiC0")
                .url("www.baidu.com")
                .build();

        templateMessage.addData(new WxMpTemplateData("test", "1111", null));
        return this.wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
    }
    @GetMapping("/send")
    public String user() throws WxErrorException {
        String url = "";
        wxMpService.getOAuth2Service().buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
//        return this.wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        return null;
    }


} 
