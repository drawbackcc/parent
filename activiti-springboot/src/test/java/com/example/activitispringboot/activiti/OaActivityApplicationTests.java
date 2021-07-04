package com.example.activitispringboot.activiti;


import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OaActivityApplicationTests {

    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;
    @Autowired
    HistoryService historyService;
    private String processInstanceKey = "test";

    /**
     * 部署流程定义
     * 执行完这步之后，act_re_deployment表会新增一条部署记录
     * 也会新增act_re_procdef流程定义表（processDefine）
     * 新增资源文件表act_ge_bytearray
     * 更新系统配置表 act_ge_property
     */
    @Test
    public void createLiucheng() {
        String processResourceFile = "diagram/qingjia.bpmn";
        Deployment deploy = repositoryService.createDeployment()// 创建部署
                .addClasspathResource(processResourceFile)// 加载资源文件
                .name(processInstanceKey)//流程名称
                .deploy(); //部署
        System.out.println("部署ID：" + deploy.getId()); //1
        System.out.println("部署名称：" + deploy.getName());//test
    }

    /**
     * 启动流程实例
     */
    @Test
    public void firstLiucheng() {
//        Map<String,Object> property = new HashMap<>();
//        property.put("user", "1");
//        ProcessInstance pi = runtimeService.startProcessInstanceByKey("myProcess",property);//运行时Service
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("myProcess");//运行时Service
        System.out.println("流程实例的ProcessInstanceId: "+pi.getId());//2501,这里的id是系统配置表 act_ge_property中id生成的
        System.out.println("流程实例的ProcessDefinitionKey: "+pi.getProcessDefinitionKey());//myProcess
        System.out.println("流程实例的ProcessDefinitionId: "+pi.getProcessDefinitionId());//myProcess:1:4
        System.out.println("流程实例的ProcessDefinitionName: "+pi.getProcessDefinitionName());//null
        System.out.println("流程实例的ProcessDefinitionVersion: "+pi.getProcessDefinitionVersion());//null

    }

    /**
     * 查看流程实例的待办任务
     */
    @Test
    public void getDaiban() {
        System.out.println("--得到待办--");
        String assignnee = "1";
        String processInstanceId = "10001";
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        //List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).taskAssignee(assignnee).list();
        //List<Task> tasks = taskService.createTaskQuery().taskAssignee("shyroke").list();根据任务委派人和
        for(Task task:tasks){
            System.out.println("任务ID:"+task.getId());
            System.out.println("任务名称:"+task.getName());
            System.out.println("任务创建时间:"+task.getCreateTime());
            System.out.println("任务委派人:"+task.getAssignee());
            System.out.println("流程实例ID:"+task.getProcessInstanceId());
        }
    }

    /**
     * 完成某个任务
     */
    @Test
    public void test_completeTask() {
        taskService.complete("12502");
    }

    @Test
    public void test_history(){
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().list();
//        historyService.createHistoricProcessInstanceQuery().finished();
        for (HistoricProcessInstance process : list) {
            System.out.println("流程实例id：" + process.getId());
            System.out.println("流程实例name：" + process.getName());
            HistoricTaskInstanceQuery taskInstanceQuery =  historyService.createHistoricTaskInstanceQuery().processInstanceId(process.getId());
            List<HistoricTaskInstance> tasks = taskInstanceQuery.list();
            for(HistoricTaskInstance task:tasks){
                System.out.println("    任务ID:"+task.getId());
                System.out.println("    任务名称:"+task.getName());
                System.out.println("    任务创建时间:"+task.getCreateTime());
                System.out.println("    任务委派人:"+task.getAssignee());
                System.out.println("    流程实例ID:"+task.getProcessInstanceId());
                System.out.println();
            }
            System.out.println();
        }


    }

}