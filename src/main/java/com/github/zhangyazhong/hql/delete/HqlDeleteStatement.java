package com.github.zhangyazhong.hql.delete;

import com.github.zhangyazhong.hql.HqlStatement;
import com.github.zhangyazhong.hql.query.HqlQueryStatement;
import com.google.common.collect.Table;

import java.util.List;
import java.util.Map;

/**
 * @author zhangyazhong
 * @version 1.0
 */
public interface HqlDeleteStatement extends HqlStatement {
    enum Operator {
        GREATER(">"),
        GREATER_OR_EQUAL(">="),
        LESS("<"),
        LESS_OR_EQUAL("<="),
        EQUAL("="),
        NOT_EQUAL("<>"),
        LIKE("like");
        
        private String operator;
        
        Operator(String operator) {
            this.operator = operator;
        }
        @Override
        public String toString() {
            return operator;
        }
    }
    
    HqlQueryStatement from(String... classes);
    HqlQueryStatement where(String... wheres);
    HqlQueryStatement where(List<String> wheres);
    HqlQueryStatement where(Map<String, String> wheres);
    HqlQueryStatement where(Table<String, HqlQueryStatement.Operator, String> wheres);
    
    Map<Integer, String> getQueryParameters() throws Exception;
}
