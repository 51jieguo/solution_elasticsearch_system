package cn.yangtengfei.repository.employee;

import cn.yangtengfei.model.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeRepository extends ElasticsearchRepository<Employee,String> {
      
    Employee queryEmployeeById(String id);

    List<Employee> queryEmployeeByFirstName(String firstname);
  
}  