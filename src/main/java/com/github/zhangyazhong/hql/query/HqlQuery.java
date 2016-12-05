package com.github.zhangyazhong.hql.query;

import com.google.common.collect.Lists;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangyazhong
 * @version 1.0
 */
public class HqlQuery implements HqlQueryStatement {

    private List<String> selectList, fromList, asList, whereList, orderByList, groupByList;

    public HqlQuery() {
        selectList = Lists.newArrayList();
        fromList = Lists.newArrayList();
        asList = Lists.newArrayList();
        whereList = Lists.newArrayList();
        orderByList = Lists.newArrayList();
        groupByList = Lists.newArrayList();
    }

    public HqlQuery(String className) {
        this();
        fromList.add(className); asList.add("");
    }

    public HqlQuery select(String... selectNames) {
        Collections.addAll(selectList, selectNames);
        return this;
    }

    public HqlQuery from(String... classNames) {
        for (String className: classNames) {
            fromList.add(String.format("%s %s",
                    className, className.substring(0, 1).toLowerCase() + className.substring(1)));
            asList.add("");
        }
        return this;
    }

    public HqlQuery where(String... wheres) {
        Collections.addAll(whereList, wheres);
        return this;
    }
    public HqlQuery where(List<String> wheres) {
        if (wheres != null && !wheres.isEmpty()) {
            whereList.addAll(wheres);
        }
        return this;
    }
    public HqlQuery where(Map<String, String> wheres) {
        if (wheres != null && !wheres.isEmpty()) {
            whereList.addAll(wheres.entrySet().stream().
                    map(entry -> String.format("%s='%s'", entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList()));
        }
        return this;
    }

    public HqlQuery orderBy(String order, String... attributes) {
        for (String attribute: attributes) {
            orderByList.add(String.format("%s %s", attribute, order));
        }
        return this;
    }

    public HqlQuery groupBy(String... attributes) {
        for (String attribute: attributes) {
            groupByList.add(String.format("%s", attribute));
        }
        return this;
    }

    @Override
    public String createStatement() {
        String hql = "";
        hql += selectList.size() > 0 ? String.format("select %s ", StringUtils.join(selectList, ", ")) : "";
        hql += String.format("from %s ", StringUtils.join(fromList, ", "));
        hql += whereList.size() > 0 ? String.format("where %s ", StringUtils.join(whereList, " and ")) : "";
        hql += groupByList.size() > 0 ? String.format("group by %s ", StringUtils.join(groupByList, ", ")) : "";
        hql += orderByList.size() > 0 ? String.format("order by %s ", StringUtils.join(orderByList, ", ")) : "";
        return hql;
    }
    
    @Override
    public Map<Integer, String> getQueryParameters() throws Exception {
        return Maps.newHashMap();
    }
}
