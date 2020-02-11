package cn.settile.sblog.listener;

import cn.settile.sblog.model.entity.Permission;
import cn.settile.sblog.service.PermissionService;
import cn.settile.sblog.service.RoleService;
import cn.settile.sblog.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : lzjyz
 * @date : 2020-02-10 17:54
 */
@Component
@Slf4j
public class ApplicationInitialListener implements ApplicationListener<ContextRefreshedEvent> {

    private static boolean isStart=true;

    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

    @Value("${sblog.data.permission}")
    List<String> permStr;
    @Value("${sblog.data.role}")
    String roleStr;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("ContextRefreshedEvent...等待数据库初始化");
        if(isStart){
            initData();
            isStart=false;
        }else{

        }
    }

    /**
     *
     */
    public void initData(){
        log.info("开始初始化数据库数据...");
        permStr.forEach(s -> {
            if(!permissionService.existsPermissionByPermissionsName(s)){
                permissionService.createPermission(s);
            }
        });
        try {
            Map<String,List<String>> roleMap = CommonUtil.parseRole(roleStr);
            roleMap.forEach((k,v)->{
                if (!roleService.existsRoleByRoleName(k)){
                    List<Permission> permissions = new ArrayList<>();
                    v.forEach(s -> {
                        permissions.add(permissionService.getPermissionByName(s));
                    });
                    roleService.createRole(k,permissions);
                }
            });
            log.info("结束初始化数据库数据...");
        } catch (IOException e) {
            log.info("初始化数据库数据异常...");
            e.printStackTrace();
        }
    }

}
