package com.github.zhangyazhong.hql.query;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;

import java.util.List;
import java.util.Map;

/**
 * @author zhangyazhong
 * @version 1.0
 */
public class HqlQueryCount implements HqlQueryStatement {
    
    private List<String> countList;
    private HqlQueryStatement hqlQuerySafe;
    
    public HqlQueryCount() {
        hqlQuerySafe = new HqlQuerySafe();
        countList = Lists.newArrayList();
    }
    
    public HqlQueryCount(String clazz) {
        this();
        hqlQuerySafe.from(clazz);
    }
    
    @Override
    public HqlQueryStatement select(String... selects) {
        return null;
    }
    
    @Override
    public HqlQueryStatement from(String... classes) {
        return null;
    }
    
    @Override
    public HqlQueryStatement where(String... wheres) {
        return null;
    }
    
    @Override
    public HqlQueryStatement where(List<String> wheres) {
        return null;
    }
    
    @Override
    public HqlQueryStatement where(Map<String, String> wheres) {
        return null;
    }
    
    @Override
    public HqlQueryStatement where(Table<String, Operator, String> wheres) {
        return null;
    }
    
    @Override
    public HqlQueryStatement orderBy(Order order, String... attributes) {
        return null;
    }
    
    @Override
    public HqlQueryStatement groupBy(String... attributes) {
        return null;
    }
    
    @Override
    public String createStatement() throws Exception {
        return null;
    }
    
    @Override
    public Map<Integer, String> getQueryParameters() throws Exception {
        return null;
    }
}
