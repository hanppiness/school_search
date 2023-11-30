package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.domain.dto.CreatedDto;
import com.tencent.wxcloudrun.domain.dto.GenDto;
import com.tencent.wxcloudrun.service.IStudentService;
import com.tencent.wxcloudrun.util.ResultVo;
import com.tencent.wxcloudrun.util.Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/best")
public class BestController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/gen/V1")
    public ResultVo generateQrCode(GenDto genDto, HttpServletResponse response){
        //将照片信息存储位base64
        try {
            Utils.createCodeToOutPutStream(genDto.getId(),response.getOutputStream(),genDto.getWith(),genDto.getHeight());
        } catch (Exception e) {
            System.out.println(e.getCause());
            return ResultVo.fail(e.getMessage());
        }
        return ResultVo.success();
    }

    @ApiOperation("获取学历信息")
    @GetMapping(value = "/info",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultVo getStudentInfo(String content){
        if(null == content){
            return ResultVo.fail("错误的参数信息");
        }else{
            return ResultVo.success(studentService.getStudent(content));
        }
    }
    @PostMapping("/create")
    @ResponseBody
    public ResultVo create(CreatedDto createdDto){
        return ResultVo.success(studentService.create(createdDto));
    }
}
