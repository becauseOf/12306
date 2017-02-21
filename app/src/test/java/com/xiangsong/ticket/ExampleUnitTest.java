package com.xiangsong.ticket;

import com.google.gson.reflect.TypeToken;
import com.xiangsong.ticket.model.OrderBean;
import com.xiangsong.ticket.model.QueryTicketItem;
import com.xiangsong.ticket.model.UserLogin;
import com.xiangsong.ticket.presenter.util.GsonUtil;
import com.xiangsong.ticket.presenter.util.HttpUtil;

import org.json.JSONObject;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testQueryTest() throws Exception{
        QueryTicketItem queryTicketItem = new QueryTicketItem("广州","武汉","2016-09-21",false,true);
        HttpURLConnection conn =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/QueryTicket");
        String parameter = "content="+ GsonUtil.toJson(queryTicketItem,QueryTicketItem.class);
        HttpUtil.setBodyParameter(conn,parameter);
        String content = HttpUtil.readInputStream(conn);
        System.out.print(content);

        JSONObject j = new JSONObject(content);
        String datas = j.getString("content");


    }
    @Test
    public void testQueryOrderTest()throws Exception{
        HttpURLConnection conn =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/QueryOrder");
        String parameter = "userid=1&idcard=450421199506084511";
        HttpUtil.setBodyParameter(conn,parameter);
        String content = HttpUtil.readInputStream(conn);

        System.out.println("content:"+content);
        JSONObject j = new JSONObject(content);
        String s = j.getString("issuccess");
        List list = (List) GsonUtil.fromJson(s,new TypeToken<List>(){}.getType());
    }

    @Test
    public void testDeleteOrder()throws Exception{
        HttpURLConnection conn =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/DeleteOrder");


        //错误原因在于获取到的orderBean一直都是最后一个
        String parameter = "orderid=3";
        HttpUtil.setBodyParameter(conn,parameter);
        String content = HttpUtil.readInputStream(conn);
        System.out.print(content);
    }

}