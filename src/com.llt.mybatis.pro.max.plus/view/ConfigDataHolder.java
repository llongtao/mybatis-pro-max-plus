package com.llt.mybatis.pro.max.plus.view;

import com.alibaba.fastjson.JSON;
import com.intellij.ide.util.PropertiesComponent;
import com.llt.mybatishelper.core.data.DataSourceHolder;
import com.llt.mybatishelper.core.model.BuildConfig;
import com.llt.mybatishelper.core.model.Config;
import com.llt.mybatishelper.core.model.EntityField;
import com.llt.mybatishelper.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author LILONGTAO
 */
public class ConfigDataHolder  {
    private static Logger log = LoggerFactory.getLogger(DataSourceHolder.class);
    private static Config config ;

    static {
        try {
            String configStr = PropertiesComponent.getInstance().getValue("config", StringUtils.EMPTY);
            if (!StringUtils.isEmpty(configStr)) {
                ConfigDataHolder.config  = JSON.parseObject(configStr, Config.class);
            }
        } catch (Exception e) {
            log.warn("找不到配置文件", e);
        }

        if (config == null) {
            config = new Config();
            config.setUseDb(true);
            config.setBaseDbUsername("root");
            config.setDbType("mysql");
            config.setBaseDbUrl("127.0.0.1:3306");
            config.setBaseEntityFieldList(Collections.singletonList(new EntityField("id","id","Integer",null,null,null,null,null,false,"主键")));
            config.setBuildConfigList(Collections.singletonList(new BuildConfig(StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,true)));
        }

        log.info("loadData:{}", config);
    }

    public static void updateBuildConfig(Vector<Vector> dataVector) {

        List<BuildConfig> buildConfigList = new ArrayList<>();
        for (Vector vector : dataVector) {
            BuildConfig buildConfig =
                    new BuildConfig(
                            (String) vector.get(0),
                            (String) vector.get(1),
                            (String) vector.get(2),
                            null,
                            (String) vector.get(4),
                            null,
                            null,
                            !(Boolean) vector.get(3)
                    );

            buildConfigList.add(buildConfig);
        }
        config.setBuildConfigList(buildConfigList);
        save();
    }

    public static void updateModelConfig(Vector<Vector> dataVector) {

        List<EntityField> entityFieldList = new ArrayList<>();
        for (Vector vector : dataVector) {
            EntityField entityField =
                    new EntityField(
                            (String) vector.get(0),
                            (String) vector.get(1),
                            (String) vector.get(2),
                            null,
                            null,
                            null,
                            (String) vector.get(3),
                            (String) vector.get(4),
                            !(Boolean) vector.get(5),
                            (String) vector.get(6)
                    );

            entityFieldList.add(entityField);
        }
        config.setBaseEntityFieldList(entityFieldList);
        save();
    }

    public static void updateBaseConfig(String dbType,String baseDbUrl,String baseDbUsername,String baseDbPassword,Boolean useDb){
        config.setDbType(dbType);
        config.setBaseDbUrl(baseDbUrl);
        config.setBaseDbUsername(baseDbUsername);
        config.setBaseDbPassword(baseDbPassword);
        config.setUseDb(useDb);
        save();
    }



    public static Config save() {

        String s = JSON.toJSONString(config);
        log.info("saveConfig:{}", s);
        PropertiesComponent.getInstance().setValue("config",s);
        System.out.println(s);
        return config;
    }

    public static Config getData() {
        return config;
    }
}
