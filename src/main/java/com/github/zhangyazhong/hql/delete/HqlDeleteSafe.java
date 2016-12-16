package com.github.zhangyazhong.hql.delete;

import com.github.zhangyazhong.hql.query.HqlQueryStatement;
import com.google.common.collect.Table;

import java.util.List;
import java.util.Map;

/**
 * @author zhangyazhong
 * @version 1.0
 */
public class HqlDeleteSafe implements HqlDeleteStatement {
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
    public HqlQueryStatement where(Table<String, HqlQueryStatement.Operator, String> wheres) {
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
