package cn.yangtengfei.controller;

import cn.yangtengfei.model.Employee;
import cn.yangtengfei.repository.employee.EmployeeRepository;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/es")
public class ElasticSearchController {  
      
    @Autowired
    private EmployeeRepository employeeRepository;
      
    //增加  
    @RequestMapping("/add")  
    public String add(){

        String array[] = {
                "中华崛起炎黄儿女生百福，民族团结歌舞升平纳千祥！",
                "祖国之树屹立不倒",
                "，祖国之花永不凋谢",
                "，祖国之水万古长流。",
                "伟大的领袖毛泽东，领导我们向前，祖国要富强"
        };
        for(int i=1; i<=4; i++){
            Employee employee=new Employee();
            employee.setId(i+"");
            employee.setFirstName(array[i]);
            employee.setLastName("zh"+i);
            employee.setAge(i);
            employee.setAbout(i+ " am in peking");
            employee.setMessage("message"+i);
            employeeRepository.save(employee);
        }
        System.err.println("add a obj");
        return "success";  
    }  
      
        //删除  
    @RequestMapping("/delete")  
    public String delete(){  
          
        //er.delete("1");
          
        return "success";  
    }  
      
        //局部更新  
    @RequestMapping("/update")  
    public String update(){  
          
        Employee employee=employeeRepository.queryEmployeeById("1");
        employee.setFirstName("哈哈");
        employeeRepository.save(employee);
          
        System.err.println("update a obj");  
      
        return "success";  
    }  
      
        //查询  
    /*@RequestMapping("/query/{id}")
    public Employee query(@PathVariable("id") String id){
          
        Employee accountInfo=er.queryEmployeeById(id);
        //System.err.println(new Gson().toJson(accountInfo));
          
        return accountInfo;  
    }*/

    @RequestMapping("/query/{firstname}")
    public List<Employee> queryFirstName(@PathVariable("firstname") String firstname){

        //List<Employee> accountInfo=er.queryEmployeeByFirstName(firstname);
        //System.err.println(new Gson().toJson(accountInfo));
        //MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("firstName", "的");

        DisMaxQueryBuilder disMaxQueryBuilder = QueryBuilders.disMaxQuery().add(QueryBuilders.termQuery("firstName", firstname));
        Iterable<Employee> search = employeeRepository.search(disMaxQueryBuilder);
        List<Employee> accountInfo = new ArrayList<>();
        search.forEach(resource -> {
            System.out.println(resource.toString());
            accountInfo.add(resource);
        });

        return accountInfo;
    }

}