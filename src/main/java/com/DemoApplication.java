package com;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.service.TestSystemService;
import com.google.openrtb.OpenRtb;
import com.google.openrtb.youdao.OpenRtbYDExtForDsp;
import com.google.protobuf.ExtensionRegistry;
import com.reposity.mysql.apitest.TestSystem;
import com.reposity.mysql.apitest.TestSystemDao;
import com.reposity.mysql.dao.UserDao;
import com.yechao.springboot.demo.Bookbean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import yex.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@SpringBootApplication   //整个Spring Boot的核心注解，它的目的就是开启Spring Boot的自动配置
//@RestController   //使之变为一个Controller，然后里边提供一个地址转换方法
@Controller
@EnableCaching    //开启缓存注解
@EnableScheduling   //开启定时任务注解
public class DemoApplication {

    @Value("${config.name}")
    private String appname;
    @Value(value = "${config.age}")
    private String age;

    //@Autowired
    @Resource
    private Bookbean bookbean;

    @Autowired
    private UserDao userDao;
    @Autowired
    private TestSystemDao testSystemDao;
    @Autowired
    private TestSystemService testSystemService;

    private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @RequestMapping(value = "/app/house", produces = "text/plain;charset=UTF-8")
    String house() {
        String str = "My Name is " + appname + "," + "age is " + age + " !";
        return "app/house";
    }


    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
        new SpringApplicationBuilder(DemoApplication.class).web(true).run(args);
    }

    @RequestMapping(value = "/test1", produces = "text/plain;charset=UTF-8")
    String indextest() {
        String str = "My Name is " + appname + "," + "age is " + age + " !";
        return "test1";
    }

    @RequestMapping(value = "/sdktest", produces = "text/plain;charset=UTF-8")
    String sdktest(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("yechao", "yechaovalue");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
        return "sdktest";
    }


//    private static ExtensionRegistry extensionRegistry = ExtensionRegistry.newInstance();

//    static {
//        OpenRtbYDExtForDsp.registerAllExtensions(extensionRegistry);
//    }

    private static String toString(HttpServletRequest request) {
        String valueStr = "";
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            valueStr = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            valueStr = "";
        }
        return valueStr;
    }


    @RequestMapping(value = "/yex", produces = "application/json")
    @ResponseBody
    public String toYex(ModelMap map, HttpServletRequest request, HttpServletResponse response, @RequestBody String json) throws IOException {
//        logger.info(JSON.toJSONString(bidRequest.getId()));
//        System.out.println(request.getInputStream().isReady());
        System.out.println(json);
        JSONObject jsonObject = JSON.parseObject(json);
//        System.out.println(getId());

//        OpenRtb.BidRequest bidRequest = yexOpenRtbJsonFactory.newReader().readBidRequest(par);
//        yexOpenRtbJsonFactory.newReader().readBidRequest();
//        System.out.println(bidRequest.getId());
        System.out.println(request.getInputStream().toString());
//        System.out.println(toString(request));

//        if (null==bidRequest){
//            System.out.println("null");
//        }
        OpenRtb.BidResponse.Builder bidResponse = OpenRtb.BidResponse.newBuilder();
        bidResponse.setId("1");
        bidResponse.setNbr(OpenRtb.BidResponse.NoBidReason.UNSUPPORTED_DEVICE);
//        bid.setAdmNative()
//        System.out.println(JSON.toJSONString(bidResponse));


//        response.getOutputStream().write();
//        String res = yexOpenRtbJsonFactory.newWriter().writeBidResponse(bidResponse.build());

        return "wrong";
    }


    @RequestMapping(value = "/index")
    public String toIndex(ModelMap map) {
        map.addAttribute("host", "121212");
        String str = "My Name is " + appname + "," + "age is " + age + " !";
        return "index";
    }

    @RequestMapping(value = "/book", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    String book() {
        String str = "The name of the book is " + bookbean.getName() + "," + "The price of the book is " + bookbean.getPrice() + "," + ",the book's author is " + bookbean.getAuthor() + " !";
        return str;
    }

    @GetMapping("/login")
    public String login(String name, String password) {
        logger.info("/user/login接收参数name={},password={}", name, password);
//        return userDao.countByNameAndPassword(name, password);
        return "login";
    }

    @GetMapping("/tables")
    public String toTables(String name, String password, Model model) {
        logger.info("/user/login接收参数name={},password={}", name, password);
//        return userDao.countByNameAndPassword(name, password);
        Page<TestSystem> pages = testSystemService.queryByPage(0, 100);
        model.addAttribute("data", pages.getContent());
        return "tables";
    }

    @GetMapping("/sdk.js")
    public String toSdkJs(Model model, HttpServletResponse response) {
        model.addAttribute("tenantCode", "yechao");
//        logger.info("/user/login接收参数name={},password={}", name, password);
//        return userDao.countByNameAndPassword(name, password);
        //设置响应信息，不设置无法调用js内容
        response.setHeader("Content-Type", "application/javascript;charset=UTF-8");
        return "sdk";
    }


//    private static OpenRtbJsonFactory openRtbJsonFactory = OpenRtbJsonFactory.create()
//            .register(new YDExtBattriReader(), OpenRtb.BidRequest.Imp.Native.Builder.class)
//            .register(new YDExtAttriReader(), OpenRtb.BidResponse.SeatBid.Bid.Builder.class)
//            .register(new YDExtStandardSchemaIdReader(), OpenRtb.BidRequest.Imp.Builder.class)
//            .register(new YDExtStandardAssetReader<>(OpenRtbYDExtForDsp.sasset, Constants.EXTEND_STANDARD_ASSET_FIELD_NAME), OpenRtb.NativeRequest.Asset.Builder.class)
//            .register(new YDExtBattriWriter(), Integer.class, OpenRtb.BidRequest.Imp.Native.class, Constants.EXTEND_BATTRI_FIELD_NAME)
//            .register(new YDExtDataAssetTypeWriter(), Integer.class, OpenRtb.NativeRequest.Asset.Data.class, Constants.EXTEND_DATA_ASSET_TYPE_FIELD_NAME)
//            .register(new YDExtStandardSchemaIdWriter(), Integer.class, OpenRtb.BidRequest.Imp.class, Constants.EXTEND_STANDARD_SCHEMA_ID_FIELD_NAME)
//            .register(new YDExtStandardAssetWriter(), OpenRtb.NativeRequest.Asset.class, OpenRtb.NativeRequest.Asset.class, Constants.EXTEND_STANDARD_ASSET_FIELD_NAME);
//
//    private static YexOpenRtbJsonFactory yexOpenRtbJsonFactory = YexOpenRtbJsonFactory.create()
//            .yexRegister(new YDExtBattriReader(), OpenRtb.BidRequest.Imp.Native.Builder.class)
//            .yexRegister(new YDExtAttriReader(), OpenRtb.BidResponse.SeatBid.Bid.Builder.class)
//            .yexRegister(new YDExtStandardSchemaIdReader(), OpenRtb.BidRequest.Imp.Builder.class)
//            .yexRegister(new YDExtStandardAssetReader<>(OpenRtbYDExtForDsp.sasset, Constants.EXTEND_STANDARD_ASSET_FIELD_NAME), OpenRtb.NativeRequest.Asset.Builder.class)
//            .yexRegister(new YDExtBattriWriter(), Integer.class, OpenRtb.BidRequest.Imp.Native.class, Constants.EXTEND_BATTRI_FIELD_NAME)
//            .yexRegister(new YDExtDataAssetTypeWriter(), Integer.class, OpenRtb.NativeRequest.Asset.Data.class, Constants.EXTEND_DATA_ASSET_TYPE_FIELD_NAME)
//            .yexRegister(new YDExtStandardSchemaIdWriter(), Integer.class, OpenRtb.BidRequest.Imp.class, Constants.EXTEND_STANDARD_SCHEMA_ID_FIELD_NAME)
//            .yexRegister(new YDExtStandardAssetWriter(), OpenRtb.NativeRequest.Asset.class, OpenRtb.NativeRequest.Asset.class, Constants.EXTEND_STANDARD_ASSET_FIELD_NAME);


}
