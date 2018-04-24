package com.ifzer.config;


import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by nelson on 2018-04-19.
 */
@Configuration
public class SpringActivitiConfig {


    private final static Logger LOGGER = LoggerFactory.getLogger(SpringActivitiConfig.class);

    @Bean
    public ProcessEngineFactoryBean processEngineFactoryBean(DataSource dataSource,
                                                             DataSourceTransactionManager transactionManager){
        ProcessEngineFactoryBean pefb = new ProcessEngineFactoryBean();
        final SpringProcessEngineConfiguration spec = new SpringProcessEngineConfiguration();
        spec.setDataSource(dataSource);
        spec.setTransactionManager(transactionManager);
        spec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        pefb.setProcessEngineConfiguration(spec);
        LOGGER.info("processEngineFactoryBean done");
        return pefb;
    }

    @Bean
    public ProcessEngine processEngine(ProcessEngineFactoryBean pefb) throws Exception {
        LOGGER.info("repositoryService done");
        final ProcessEngine processEngine = pefb.getObject();
        return processEngine;
    }

    @Bean
    public RepositoryService repositoryService(ProcessEngineFactoryBean pefb) throws Exception {
        LOGGER.info("repositoryService done");
        final RepositoryService repositoryService = pefb.getObject().getRepositoryService();
        return repositoryService;
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngineFactoryBean pefb) throws Exception {
        LOGGER.info("runtimeService done");
        final RuntimeService runtimeService = pefb.getObject().getRuntimeService();
        return runtimeService;
    }

    @Bean
    public TaskService taskService(ProcessEngineFactoryBean pefb) throws Exception {
        LOGGER.info("taskService done");
        final TaskService taskService = pefb.getObject().getTaskService();
        return taskService;
    }

    @Bean
    public HistoryService historyService(ProcessEngineFactoryBean pefb) throws Exception {
        LOGGER.info("historyService done");
        final HistoryService historyService = pefb.getObject().getHistoryService();
        return historyService;
    }

    @Bean
    public ManagementService managementService(ProcessEngineFactoryBean pefb) throws Exception {
        LOGGER.info("managementService done");
        final ManagementService managementService = pefb.getObject().getManagementService();
        return managementService;
    }


}
