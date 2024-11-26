package org.example.delivery.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.delivery.Entity.Employee;
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
