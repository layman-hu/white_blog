package com.white.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LoggerInfo {
    //设置记录器名称
    private String loggerName;

    //记录内容
    private List<String> messages = new ArrayList<>();

    public LoggerInfo() {
    }

    public static LoggerInfo initialization() {
        LoggerInfo loggerInfo = new LoggerInfo();
        return loggerInfo;
    }

    public LoggerInfo loggerName(String loggerName){
        this.setLoggerName(loggerName);
        return this;
    }

    public LoggerInfo messages(String msg){
        this.getMessages().add(msg);
        return this;
    }

    public LoggerInfo messages(String msgName,Object msg) {
        this.getMessages().add(msgName+":\t");
        this.getMessages().add(msg.toString());
        return this;
    }

    public LoggerInfo output(){
        Logger logger = Logger.getLogger(loggerName);
        for(String msg:messages){
            logger.info(msg);
        }
        logger.info("");
        return this;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
