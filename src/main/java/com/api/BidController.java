package com.api;

import com.google.openrtb.OpenRtb;
import com.google.openrtb.OpenRtb.BidRequest;
import com.google.openrtb.OpenRtb.BidResponse;
import com.google.openrtb.youdao.OpenRtbYDExtForDsp;
import com.google.protobuf.ExtensionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author yechao111987@126.com
 * @date 2018/6/8 17:41
 */

@Controller
public class BidController {

    @PostMapping(value = "/api/bid")
    public void index(HttpServletRequest request, HttpServletResponse response) {
        try {
            InputStream in = request.getInputStream();
            ExtensionRegistry registry = ExtensionRegistry.newInstance();
            OpenRtbYDExtForDsp.registerAllExtensions(registry);
            BidRequest bidRequest = BidRequest.parseFrom(in, registry);
            System.out.println("bidrequest id:" + bidRequest.getId());


            OpenRtb.BidResponse.Builder builder = OpenRtb.BidResponse.newBuilder();
            builder.setId("1");
            builder.setNbr(OpenRtb.BidResponse.NoBidReason.UNSUPPORTED_DEVICE);


            byte[] data = builder.build().toByteArray();


            response.setStatus(200);
            response.setContentType("application/x-protobuf");

            OutputStream outputStream = response.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();

        }


    }
}
