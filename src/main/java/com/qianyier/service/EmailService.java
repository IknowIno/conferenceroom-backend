package com.qianyier.service;

import com.qianyier.common.exception.SystemException;

import java.util.Map;

/**
 * @author qianyier
 * @Description
 */
public interface EmailService {

    boolean sendStartMail(String to, String subject, Map<String,Object> content) throws SystemException;

    boolean sendAuditMail(String to, String subject, Map<String,Object> content) throws SystemException;
}
