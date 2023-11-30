package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.domain.dto.CreatedDto;
import com.tencent.wxcloudrun.domain.vo.StudentInfo;
import com.tencent.wxcloudrun.mapper.StudentInfoMapper;
import com.tencent.wxcloudrun.service.IStudentService;
import com.tencent.wxcloudrun.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements IStudentService {
    @Override
    public String create(CreatedDto createdDto) {
        StudentInfo studentInfo=new StudentInfo();
        BeanUtils.copyProperties(createdDto,studentInfo);
        //设置主键ID
        studentInfo.setId(UUID.randomUUID().toString().replaceAll("-",""));
        if(createdDto.isFlag()){
            studentInfo.setRandomCode(Utils.genCode(12));
        }
        //获取头像信息
        MultipartFile headImage = createdDto.getHeadImage();
        try {
            studentInfo.setImage(Base64Utils.encodeToString(headImage.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        baseMapper.insert(studentInfo);
        return  studentInfo.getId();
    }
    @Override
    public StudentInfo getStudent(String id) {
        StudentInfo studentInfo = baseMapper.selectById(Utils.dec(id));
        studentInfo.setImage(" data:image/png;base64,".concat(studentInfo.getImage()));
        return studentInfo;
    }
}
