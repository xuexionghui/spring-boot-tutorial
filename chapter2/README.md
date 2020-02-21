第二章：集成SpringBootJPA完成CURD

一、日志配置
直接用springboot内置的logback，使用方式是（在application.properties配置）
logging.level.root=info
#logging.path=D:/logs
logging.file=my.log
（日志文件放到D:/logs文件夹不会创建，直接放到项目里面，应该是没有权限创建，才在D:/logs没有）

在代码里（使用org.apache.commons.logging接口实现日志）
private Log  log= LogFactory.getLog(UserController.class);

    /*
    增加用户
     */
    @PostMapping(value = "/addUser")
    public   String  addUser(@RequestBody User user){
        userService.addUser(user);
        log.info("增加成功");
        return   "success";
    }
 二、依赖信息
 （使用jpa进行简单的curd）
 1、引入springboot的起始依赖
   <parent>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-parent</artifactId>
         <version>2.0.5.RELEASE</version>
     </parent>
 2、引入jpa的依赖
         <dependency>
                     <groupId>org.springframework.boot</groupId>
                     <artifactId>spring-boot-starter-data-jpa</artifactId>
         </dependency>
 3、引入mysql的依赖
         <dependency>
             <groupId>mysql</groupId>
             <artifactId>mysql-connector-java</artifactId>
         </dependency>
 4、引入springboot-web的依赖
        <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-web</artifactId>
         </dependency>
 三、使用JpaRepository就可以进行简单的curd工作
 四、相关知识链接：
 https://www.cnblogs.com/softidea/p/6216722.html
 https://www.cnblogs.com/mracale/p/9828346.html
