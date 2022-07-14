package com.baomidou.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: meizhaowei
 * @Create: 2022/7/8 23:17
 * @Description:
 */
public class MysqlGenerator {

    //数据库信息
    private static String url = "jdbc:mysql://14.215.45.210:3306/sm_ems?useUnicode=true&characterEncoding=UTF-8";
    private static String username = "root";
    private static String password = "Gbl12345";

    //目前使用的orm框架
    public static String nowOrm = "Hand";

    //包路径
    private static String pkgPath = System.getProperty("user.dir")+"/src/main/java";
    private static String pkgXml = System.getProperty("user.dir")+"/src/main/resources";

    //忽略的字段，这是hand框架必须在数据中携带的
    private static List<String> ignoreColumns = Arrays.asList("REQUEST_ID","PROGRAM_ID","OBJECT_VERSION_NUMBER","CREATED_BY","CREATION_DATE","LAST_UPDATED_BY","LAST_UPDATE_DATE","LAST_UPDATE_LOGIN"
                                                            ,"ATTRIBUTE_CATEGORY","ATTRIBUTE1","ATTRIBUTE2","ATTRIBUTE3","ATTRIBUTE4","ATTRIBUTE5","ATTRIBUTE6","ATTRIBUTE7","ATTRIBUTE8","ATTRIBUTE9"
                                                            ,"ATTRIBUTE10","ATTRIBUTE11","ATTRIBUTE12","ATTRIBUTE13","ATTRIBUTE14","ATTRIBUTE15");

    public static void main(String[] args) {
        FastAutoGenerator.create(url,username,password)
            .globalConfig(builder -> {
                builder.outputDir(pkgPath) // 指定输出目录
                    .disableOpenDir()
                    .author("meizhaowei")  //默认为无
                    .commentDate("yyyy-MM-dd HH:mm:ss");
            })
            .packageConfig(builder -> {
                builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名，开发用这个
                    .pathInfo(new HashMap<OutputFile,String>(){{
                        put(OutputFile.entity,pkgPath+"/com/baomidou/mybatisplus/samples/generator/dto");//正式上线用这个
                        put(OutputFile.xml,pkgXml);
                    }});
            })
            .strategyConfig(builder -> {
                builder.addInclude("tbl_assessment") // 设置需要生成的表名
                    .addTablePrefix("tbl_") // 设置过滤表前缀
                    .entityBuilder()        // 实体配置
                        .superClass("com.hand.hap.system.dto.BaseDTO")
                        .disableSerialVersionUID()
//                        .enableTableFieldAnnotation()     //在Mybatis-Plus模式下启用
                        .addIgnoreColumns(ignoreColumns)    //忽略字段
                        .convertFileName((String entityName) -> { return entityName + "Dto"; })  //更改entity的名字，只有 Hand 框架才需要
                    .mapperBuilder()        // mapper配置
                        .superClass("com.hand.hap.mybatis.common.Mapper")
                        .enableBaseColumnList()
                        .enableBaseResultMap()
                    .serviceBuilder()      // service配置
                        .superServiceClass("com.hand.hap.system.service.IBaseService")
                        .superServiceImplClass("com.hand.hap.system.service.impl.BaseServiceImpl")
                    .controllerBuilder()    // controller配置
                        .superClass("com.hand.hap.system.controllers.BaseController")
                        .enableRestStyle();
            })
            .injectionConfig(builder -> {
                builder.customMap(new HashMap<String,Object>(){{
                    put("ormType",MysqlGenerator.nowOrm);
                }});
            })
            .execute();
    }

}
