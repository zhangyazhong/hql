package com.github.zhangyazhong.hql.query;

import com.github.zhangyazhong.hql.HqlStatement;

import java.util.List;
import java.util.Map;

/**
 * @author zhangyazhong
 * @version 1.0
 */
public interface HqlQueryStatement extends HqlStatement {
    enum Order {
        DESC, ASC
    }
    enum Operator {
    }
    
    String DESC = "desc";
    String ASC = "asc";
    
    HqlQueryStatement select(String... selects);
    HqlQueryStatement from(String... classes);
    HqlQueryStatement where(String... wheres);
    HqlQueryStatement where(List<String> where);
    HqlQueryStatement where(Map<String, String> wheres);
    HqlQueryStatement orderBy(String order, String... attributes);
    HqlQueryStatement groupBy(String... attributes);
    
    Map<Integer, String> getQueryParameters() throws Exception;
}
