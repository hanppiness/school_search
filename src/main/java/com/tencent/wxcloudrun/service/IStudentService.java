package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.domain.dto.CreatedDto;
import com.tencent.wxcloudrun.domain.vo.StudentInfo;

public interface IStudentService extends IService<StudentInfo> {


    String create(CreatedDto createdDto);

    StudentInfo getStudent(String id);
}
