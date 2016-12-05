package com.github.zhangyazhong.hql.query;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyazhong
 * @version 1.0
 */
public class HqlQuerySafe implements HqlQueryStatement {

    private class WhereClause {
        private String identifier;
        private String operator;
        private String value;
        
        private final List<String> OPERATOR_SET = ImmutableList.of("<=", "<", ">=", ">", "=", "<>", " LIKE ", " like ");
        
        private WhereClause(String where) {
            this.operator = OPERATOR_SET.stream()
                    .filter((operator) -> (where.contains(operator)))
                    .findFirst()
                    .orElse(null);
            int operatorPosition = where.indexOf(operator);
            this.identifier = where.substring(0, operatorPosition).trim();
            this.value = where.substring(operatorPosition + operator.length(), where.length()).trim();
        }
    
        private String toHql() {
            return String.format("%s%s?", identifier, operator);
        }
        private String toValue() {
            return value;
        }
    }
    
    private boolean CONSTRUCTED = false;
    
    private List<String> selectList;
    private List<String> fromList;
    private List<WhereClause> whereList;
    private List<String> orderByList;
    private List<String> groupByList;
    private Map<Integer, String> parameterMap;

    public HqlQuerySafe() {
        selectList = Lists.newArrayList();
        fromList = Lists.newArrayList();
        whereList = Lists.newArrayList();
        orderByList = Lists.newArrayList();
        groupByList = Lists.newArrayList();
        parameterMap = Maps.newHashMap();
    }

    public HqlQuerySafe(String clazz) {
        this();
        fromList.add(clazz);
    }

    public HqlQuerySafe select(String... selects) {
        Collections.addAll(selectList, selects);
        return this;
    }

    public HqlQuerySafe from(String... classes) {
        for (String clazz: classes) {
            fromList.add(String.format("%s %s",
                    clazz, clazz.substring(0, 1).toLowerCase() + clazz.substring(1)));
        }
        return this;
    }

    public HqlQuerySafe where(String... wheres) {
        for (String where: wheres) {
            whereList.add(new WhereClause(where));
        }
        return this;
    }
    public HqlQuerySafe where(List<String> wheres) {
        if (wheres != null) {
            for (String where: wheres) {
                whereList.add(new WhereClause(where));
            }
        }
        return this;
    }
    public HqlQuerySafe where(Map<String, String> wheres) {
        if (wheres != null) {
            wheres.forEach((key, value) -> whereList.add(new WhereClause(String.format("%s=%s", key, value))));
        }
        return this;
    }
    
    public HqlQuerySafe orderBy(String order, String... attributes) {
        for (String attribute: attributes) {
            orderByList.add(String.format("%s %s", attribute, order));
        }
        return this;
    }
    
    public HqlQuerySafe groupBy(String... attributes) {
        for (String attribute: attributes) {
            groupByList.add(String.format("%s", attribute));
        }
        return this;
    }
    
    @Override
    public String createStatement() throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append(!selectList.isEmpty() ? String.format("select %s ", StringUtils.join(selectList, ", ")) : "");
        hql.append(String.format("from %s ", StringUtils.join(fromList, ", ")));
        if (!whereList.isEmpty()) {
            StringBuilder hqlWhere = new StringBuilder();
            for (int i = 0; i < whereList.size(); i++) {
                hqlWhere.append(whereList.get(i).toHql()).append(" and ");
                parameterMap.put(i, whereList.get(i).toValue());
            }
            hql.append(String.format("where %s ", hqlWhere.substring(0, hqlWhere.lastIndexOf(" and"))));
        }
        hql.append(!groupByList.isEmpty() ? String.format("group by %s ", StringUtils.join(groupByList, ", ")) : "");
        hql.append(!orderByList.isEmpty() ? String.format("order by %s ", StringUtils.join(orderByList, ", ")) : "");
        CONSTRUCTED = true;
        return hql.toString();
    }
    
    public Map<Integer, String> getQueryParameters() throws Exception {
        if (!CONSTRUCTED) {
            createStatement();
        }
        return parameterMap;
    }
}
