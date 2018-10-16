package com.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.util.YexUtil;
import com.google.openrtb.OpenRtb;
import com.google.openrtb.json.OpenRtbJsonFactory;
import com.google.openrtb.youdao.OpenRtbYDExtForDsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import yex.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author yechao111987@126.com
 * @date 2018/10/16 10:06
 */
@Controller
public class YexController {

    private final static String YEX = "/yex";
    private final static Logger logger = LoggerFactory.getLogger(YexController.class);

    @GetMapping(value = YEX + "/yex")
    @ResponseBody
    public String toYex(ModelMap map, HttpServletRequest request, HttpServletResponse response, @RequestBody String json) throws IOException {
        logger.info("json:{}", json);
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(request.getInputStream().toString());
        OpenRtb.BidResponse.Builder bidResponse = OpenRtb.BidResponse.newBuilder();
        bidResponse.setId("1");
        bidResponse.setNbr(OpenRtb.BidResponse.NoBidReason.UNSUPPORTED_DEVICE);
        String res = YexUtil.writeResponse(bidResponse.build());
        return res;

    }


}
