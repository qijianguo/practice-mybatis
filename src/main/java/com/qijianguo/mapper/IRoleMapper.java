package com.qijianguo.mapper;

import com.qijianguo.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleMapper {

    @Select(value = "select * from role r left join user_role ur on r.id = ur.rid where ur.uid = #{uid  }")
    List<Role> findRoleByUid(Integer uid);
}
