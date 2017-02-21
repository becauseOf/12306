package com.xiangsong.ticket;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiangsong.ticket.model.Order;
import com.xiangsong.ticket.model.QueryTicketItem;
import com.xiangsong.ticket.model.RemainTicket;
import com.xiangsong.ticket.model.Ticket;
import com.xiangsong.ticket.presenter.util.GsonUtil;
import com.xiangsong.ticket.presenter.util.HttpUtil;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    public void testQueryTest() throws Exception{
        QueryTicketItem queryTicketItem = new QueryTicketItem("长沙","武汉","2016-09-18",false,true);
        HttpURLConnection conn =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/QueryTicket");
        String parameter = "content="+ GsonUtil.toJson(queryTicketItem,QueryTicketItem.class);
        HttpUtil.setBodyParameter(conn,parameter);
        String content = HttpUtil.readInputStream(conn);
        System.out.print(content);

        //使用juit会存在问题
        JSONObject j = new JSONObject(content);
        String datas = j.getString("content");

        List<RemainTicket> list = (List<RemainTicket>) GsonUtil.fromJson(datas,new TypeToken<List<RemainTicket>>(){}.getType());


    }
    public void testQueryOrderTest()throws Exception{
        HttpURLConnection conn =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/QueryOrder");
        String parameter = "userid=1&idcard=450421199506084511";
        HttpUtil.setBodyParameter(conn,parameter);
        String content = HttpUtil.readInputStream(conn);

        JSONObject j = new JSONObject(content);
//        String s = j.getString("content");


        String ticket  = j.getString("ticket");
        String order  = j.getString("order");
        List<Ticket> tickets = (List) GsonUtil.fromJson(ticket,new TypeToken<List<Ticket>>(){}.getType());
        List<Order> orders = (List) GsonUtil.fromJson(order,new TypeToken<List<Order>>(){}.getType());

        List list = new ArrayList();
        list.add(tickets);
        list.add(orders);
    }
}