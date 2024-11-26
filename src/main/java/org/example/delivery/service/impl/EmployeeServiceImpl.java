package org.example.delivery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.delivery.Entity.Employee;
import org.example.delivery.mapper.EmployeeMapper;
import org.example.delivery.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
