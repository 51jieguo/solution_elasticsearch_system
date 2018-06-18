package cn.yangtengfei.model;


import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;

/**
 * Created by admin on 2018/6/13.
 */

@Document(indexName = "employee",type = "employee", shards = 5,replicas = 1, refreshInterval = "-1")
//@Mapping(mappingPath = "employee_mapping.json")
public class Employee {


    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String firstName;

    private String lastName;

    private int age;

    private String about;

    private String message;

    //@Field(type = FieldType.Date, index = false, store = true,format = DateFormat.date_optional_time)
    private Date myDate = new Date();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMyDate() {
        return myDate;
    }

    public void setMyDate(Date myDate) {
        this.myDate = myDate;
    }
}
