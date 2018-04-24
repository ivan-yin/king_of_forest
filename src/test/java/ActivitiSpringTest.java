import cn.hutool.core.collection.CollUtil;
import com.ifzer.Application;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//SpringJUnit4ClassRunner
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ActivitiSpringTest {


    private final static Logger LOGGER = LoggerFactory.getLogger(ActivitiSpringTest.class);

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService historyService;
    @Autowired
    private TaskService managementService;

    @Test
    public void deploy() {
        String id = "leaveBill";
        Deployment deployment = repositoryService.createDeployment()
                .name(id)//设置部署的名称
                .addClasspathResource("1.bpmn")//从类路径中添加资源,一次只能添加一个资源
//                .addClasspathResource("/activiti_process/1.png")
                .category("oa")//设置部署的类别
                .deploy();
        LOGGER.info("deployid {}", deployment.getId());
        LOGGER.info("deploy name {}", deployment.getName());

        ProcessInstance pi = runtimeService.startProcessInstanceByKey(id);
        System.out.println("流程实例" + pi.getId());

        List<Task> list = taskService.createTaskQuery().taskAssignee("sen").list();
        System.out.println("任务个数" + list.size());
        if (CollUtil.isNotEmpty(list)) {
            for (Task t : list) {
                System.out.print(t.getId() + ",");
                System.out.print(t.getName() + ",");
                System.out.print(t.getAssignee() + ",");
                System.out.println(t.getProcessInstanceId());
            }
        }

        final List<ProcessDefinition> leaveBillList = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(id).latestVersion()
                .orderByProcessDefinitionVersion().desc().list();
        //遍历结果
        if (CollUtil.isNotEmpty(leaveBillList)) {
            for (ProcessDefinition temp : leaveBillList) {
                System.out.print(" 流程定义的id: " + temp.getId());
                System.out.print(" ,流程定义的key: " + temp.getKey());
                System.out.print(" ,流程定义的版本: " + temp.getVersion());
                System.out.print(" ,流程定义部署的id: " + temp.getDeploymentId());
                System.out.println(" ,流程定义的名称: " + temp.getName());
            }
        }

        // delete deployment
        final List<ProcessDefinition> toRemoveList = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(id).orderByDeploymentId().asc().listPage(0, 1);
        if (CollUtil.isNotEmpty(toRemoveList)){
            repositoryService.deleteDeployment(toRemoveList.get(0).getDeploymentId());
//            repositoryService.deleteDeployment(toRemoveList.get(0).getDeploymentId(), true);
        }
    }

}

