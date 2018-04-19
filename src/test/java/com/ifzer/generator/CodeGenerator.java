package com.ifzer.generator;


import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

/**
 * Created by nelson on 2018-04-19.
 */
public class CodeGenerator {

    @Test
    public void generateCode(){
        String pkgName = "com.ifzer.modules";
        genByTables("users", pkgName, "users");
    }

    private void genByTables(String moduleName, String pkg, String... tableNames){
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("nelson").setFileOverride(true).setOutputDir("C:\\mybatis_code_gen");
        gc.setActiveRecord(false).setIdType(IdType.ID_WORKER_STR);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL).setUrl("jdbc:mysql://localhost:3306/forest_db").
                setUsername("root").setPassword("sa");
        dsc.setDriverName("com.mysql.jdbc.Driver");

        StrategyConfig sc = new StrategyConfig();
        sc.setDbColumnUnderline(true);
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setCapitalMode(true).setInclude(tableNames);
//        sc.setColumnNaming(NamingStrategy.underline_to_camel);
//        sc.setSuperMapperClass("");

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(moduleName);
        packageConfig.setXml(moduleName+"_mapper");
        packageConfig.setParent(pkg).setController("controller").setEntity("entity");

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("/code_gen_template_ftl/entity.java");
        templateConfig.setService("/code_gen_template_ftl/service.java");
        templateConfig.setServiceImpl("/code_gen_template_ftl/serviceImpl.java");
        templateConfig.setXml("/code_gen_template_ftl/mapper.xml");
        templateConfig.setMapper("/code_gen_template_ftl/mapper.java");
        templateConfig.setController("/code_gen_template_ftl/controller.java");

        new AutoGenerator().setTemplateEngine(new FreemarkerTemplateEngine())
                .setTemplate(templateConfig)
                .setGlobalConfig(gc).setDataSource(dsc).setStrategy(sc)
                .setPackageInfo(packageConfig).execute();
    }
}
