package com.yacer.unilearn.utils;

import com.yacer.unilearn.auth.services.AuthenticationService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class ApplicationUtils {
    public static Logger appLogger = LoggerFactory.getLogger(ApplicationUtils.class);
}
