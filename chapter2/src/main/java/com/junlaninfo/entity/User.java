package com.junlaninfo.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by 辉 on 2020/2/21.
 */
@Entity//@Entity注释指名这是一个实体Bean，@Table注释指定了Entity所要映射带数据库表
public class User {
    /*
    DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` VARCHAR(64) NOT NULL COMMENT '名称',
  `sex` TINYINT(2) UNSIGNED NOT NULL DEFAULT '0' COMMENT '性别',
  `birthday` DATE NOT NULL DEFAULT '0000-00-00' COMMENT '出生日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';
     */
   /* @Id注释指定表的主键，它可以有多种生成方式：

            1）TABLE：容器指定用底层的数据表确保唯一；
            2）SEQUENCE：使用数据库德SEQUENCE列莱保证唯一（Oracle数据库通过序列来生成唯一ID）；
            3）IDENTITY：使用数据库的IDENTITY列莱保证唯一；
            4）AUTO：由容器挑选一个合适的方式来保证唯一；
            5）NONE：容器不负责主键的生成，由程序来完成。*/
   @Id
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
   //@GeneratedValue注释定义了标识字段生成方式
    private   Long  id;
    private  String  name;
    private   int  sex;
    //规定从前台传来的格式
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date  birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
