package com.service.user.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.service.user.aspects.LoggingAspect;
import com.service.user.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RefreshScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;



    @Value("${app.name}")
    private String appName;

    @GetMapping(value = "/all")
    public Collection<String> getUsers(){
        logger.info(appName);
        return userRepository.findAll().stream().map(user -> user.getName()+ appName).collect(Collectors.toList());
    }
}
