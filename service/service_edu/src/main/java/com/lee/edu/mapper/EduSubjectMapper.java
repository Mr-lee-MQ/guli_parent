package com.lee.edu.mapper;

import com.lee.edu.entity.EduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2021-05-19
 */
@Repository
public interface EduSubjectMapper extends BaseMapper<EduSubject> {

}
