package com.ebay.test.managersystem.task;

import com.ebay.test.managersystem.service.ResourcePermissionService;
import com.ebay.test.managersystem.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ScheduledResourcePermissionUpdater implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledResourcePermissionUpdater.class);

    @Value("${datafile.path}")
    private String dataFilePath;

    @Autowired
    private ResourcePermissionService resourcePermissionService;

//    @Scheduled(cron = "0 0/10 * * * ?")
    @Scheduled(cron = "0/10 * * * * ?")
    public void updateResourcePermission() {
        try {
            FileUtil.writeFile(resourcePermissionService.getUserResourcePermissionInfo(), dataFilePath);
        } catch (IOException e) {
            LOGGER.error("更新数据到文件失败", e);
        }
    }

    @Override
    public void run(ApplicationArguments args) {
        File file = new File(dataFilePath);
        if (!file.exists() || !file.isFile()) {
            LOGGER.info("数据文件不存在，即将新建{}", dataFilePath);
            return;
        }
        String text = null;
        try {
            text = FileUtil.readFile(dataFilePath);
        } catch (IOException e) {
            LOGGER.error("读取数据文件失败", e);
        }
        if (text != null && !text.isEmpty()) {
            resourcePermissionService.setUserResourcePermissionInfo(text);
        }
    }
}
