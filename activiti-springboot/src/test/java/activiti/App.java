package activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.junit.Test;

public class App {
    //创建activiti表,activiti 5.19.0.2版本的共生成了25张表，6.0.0好像会生成28张表。
    @Test
    public void testCreateTable() {
        //1.创建ProcessEngineConfiguration对象
        ProcessEngineConfiguration pec=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //2.创建ProcessEngine对象
        ProcessEngine pe=pec.buildProcessEngine();

        //默认是classpath下的activiti.cfg.xml文件
//        ProcessEngineConfiguration
//                .createProcessEngineConfigurationFromResourceDefault()
//                .setDatabaseSchemaUpdate(ProcessEngineConfigurationImpl.DB_SCHEMA_UPDATE_CREATE)
//                .buildProcessEngine();
    }
}
