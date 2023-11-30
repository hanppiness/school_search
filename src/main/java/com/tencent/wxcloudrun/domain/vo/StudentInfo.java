package com.tencent.wxcloudrun.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@TableName(value = "student_info")
@ApiModel(description = "学生毕业信息")
public class StudentInfo {
    @ApiModelProperty("主键ID")
    private String id;
    @TableField(value = "name")
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("性别")
    @TableField(value = "sex")
    private char sex;
    @TableField(value = "born_date")
    @ApiModelProperty("出生时间")
    private String bornDate;
    //入学时间
    @TableField(value = "enrollment_time")
    @ApiModelProperty("入学时间")
    private String enrollmentTime;

    //毕业时间
    @ApiModelProperty("毕业时间")
    @TableField(value = "graduation_time")
    private String graduationTime;

    //学校名称
    @TableField(value = "school_name")
    @ApiModelProperty("学校名称")
    private String schoolName;

    //专业
    @ApiModelProperty("专业")
    @TableField(value = "specialized")
    private String specialized;

    //学制(4年)
    @ApiModelProperty("学制")
    @TableField(value = "academic")
    private String academic;

    //层次(本科)
    @TableField(value = "level")
    @ApiModelProperty("层次")
    private String level;

    //学历类别(普通高等教育)
    @TableField(value = "category")
    @ApiModelProperty("学历类别")
    private String category;

    //学习形式(全日制)
    @TableField(value = "format_study")
    @ApiModelProperty("学习形式")
    private String formatStudy;

    //结业:毕业
    @TableField(value = "graduation")
    @ApiModelProperty("结业")
    private String graduation;

    //证书编号
    @TableField(value = "certificate_number")
    @ApiModelProperty("证书编号")
    private String certificateNumber;

    //校长名称
    @TableField(value = "principal_name")
    @ApiModelProperty("校长名称")
    private String principalName;


    //在线验证码(12位)
    @TableField(value = "random_code")
    @ApiModelProperty("在线验证码")
    private String randomCode;

    //base64头像信息
    @TableField(value = "image")
    @ApiModelProperty("base64头像信息")
    private String image;

    @TableField(value = "update_time")
    @ApiModelProperty("更新日期")
    private String updateTime;
}
