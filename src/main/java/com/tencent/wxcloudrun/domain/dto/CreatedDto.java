package com.tencent.wxcloudrun.domain.dto;


import com.tencent.wxcloudrun.domain.vo.StudentInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@ApiModel(description = "创建学生信息")
@Data
public class CreatedDto  extends StudentInfo {
    @ApiModelProperty("头像")
    private MultipartFile headImage;
    @ApiModelProperty("是否生成随机码")
    private boolean flag;
}
