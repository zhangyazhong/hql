package com.github.zhangyazhong.hql.query;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import junit.framework.TestCase;

/**
 * @author zhangyazhong
 * @version 1.0
 */
public class HqlQueryTest extends TestCase {
    public void testCreateStatement() throws Exception {
        HqlQueryStatement hqlQueryStatement = new HqlQuery("Event")
                .where("isChoice=1")
                .where(ImmutableList.of("name like %复旦大学%"))
                .where(ImmutableMap.of("date", "2016-12-05", "type", "4"))
                .where(ImmutableTable.of("eventId", HqlQueryStatement.Operator.LESS, "46"))
                .where("usable=1")
                .orderBy(HqlQueryStatement.Order.ASC, "eventId");
        System.out.println(hqlQueryStatement.createStatement());
    }
    
    public void testGetQueryParameters() throws Exception {
        HqlQueryStatement hqlQueryStatement = new HqlQuery("Event")
                .where("isChoice=1")
                .where(ImmutableList.of("name like %复旦大学%"))
                .where(ImmutableMap.of("date", "2016-12-05", "type", "4"))
                .where(ImmutableTable.of("eventId", HqlQueryStatement.Operator.LESS, "46"))
                .where("usable=1")
                .orderBy(HqlQueryStatement.Order.ASC, "eventId");
        System.out.println(hqlQueryStatement.getQueryParameters());
    }
    
}