package com.zxwcbj.ccyx.acl.controller;
import com.zxwcbj.ccyx.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static jodd.system.SystemUtil.info;
 @Api(tags = "登录接口")
@RestController
 @CrossOrigin //跨域
@RequestMapping("/admin/acl/index" )
public class IndexController {

    //登录
     @ApiOperation("登录")
    @PostMapping("login")
    public Result login() throws UnsupportedEncodingException {
        Map<String,String> map=new HashMap<>();

        map.put("token", java.net.URLEncoder.encode("成功","UTF-8"));
         //map.put("token", "成功");
        return Result.ok(map);
    }

    @ApiOperation("获取信息")
    @GetMapping("info")
    public Result info(){
        Map<String,String> map=new HashMap<>();
        map.put("name","aa");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }
    /**
     * 退出登录
     *
     * @return com.zxwcbj.ccyx.common.result.Result
     **/
    @PostMapping("logout")
    @ApiOperation("退出 ")
    public Result  logout(){
        return Result.ok(null);
    }
}
