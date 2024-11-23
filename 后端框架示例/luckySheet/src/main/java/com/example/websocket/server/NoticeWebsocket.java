package com.example.websocket.server;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.common.ResponseDTO;
import com.example.model.NoticeWebsocketResp;
import com.example.utils.PakoGzipUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
 
/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/notice/{userId}/{gridKey}")

@Component
@Slf4j
public class NoticeWebsocket {
  private static NoticeWebsocket webSocketServer;
  //记录连接的客户端
  public static Map<String, Session> clients = new ConcurrentHashMap<>();
 
  /**
   * userId关联sid（解决同一用户id，在多个web端连接的问题）
   */
  public static Map<String, Set<String>> conns = new ConcurrentHashMap<>();
  /**
   * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
   */
  private static int onlineCount = 0;
  /**
   * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
   */
  private static ConcurrentHashMap<String, Map<String, NoticeWebsocket>> webSocketMap = new ConcurrentHashMap<>();
  private Session session;
  private String sid = null;
 
  private String userId;
  /**
   * 表格主键
   */
  private String gridKey = "";
//  @Autowired
//  private IMessageProcess messageProcess;


  @OnOpen
  public void onOpen(Session session, @PathParam("userId") String userId, @PathParam("gridKey") String gridKey) {
    String tempSid = session.getId();
    this.sid = tempSid;
    this.userId = userId;
    clients.put(tempSid, session);
    this.gridKey = gridKey;
    if (webSocketMap.containsKey(gridKey)) {
      webSocketMap.get(gridKey).put(userId, this);
    } else {
      Map<String, NoticeWebsocket> map = new HashMap<>();
      map.put(userId, this);
      webSocketMap.put(gridKey, map);
    }
    System.out.println("webSocketMap = " + webSocketMap);
    //addOnlineCount();
    log.info("用户连接:" + userId + ",打开的表格为：" + gridKey + ",当前在线人数为:" + "getOnlineCount()");

  }
 
  @OnClose
  public void onClose(Session session, @PathParam("userId") String userId) {
    String tempSid = session.getId();
    //log.info(this.sid + "连接断开！");
    closeSession(tempSid, userId);
  }
 
  public void closeSession(String sid, String userId){
    Session s  = clients.remove(sid);
    if (s!=null){
      try {
        s.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    Set<String> clientSet = conns.get(userId);
    if (clientSet!=null){
      clientSet.remove(sid);
    }
    int currentConnectCount = clientSet.size();
    if(clientSet!=null && clientSet.size()==0){
      conns.remove(userId);
      currentConnectCount = 0;
    }
    log.error("DeviceWebsocket---onClose===>id:--{}--{}--连接数：{}--在线数：{}--当前设备连接数：{}", userId, sid, clients.size(), conns.size(), currentConnectCount);
    //log.error("在线人数===="+clients.size());
  }
 
  public static void sendMessage(String noticeType){
    NoticeWebsocketResp noticeWebsocketResp = new NoticeWebsocketResp();
    noticeWebsocketResp.setType(noticeType);
    sendMessage(noticeWebsocketResp);
  }
  public  void sendMessage2(String noticeType){
    NoticeWebsocketResp noticeWebsocketResp = new NoticeWebsocketResp();
    noticeWebsocketResp.setType(noticeType);
    sendMessage(noticeWebsocketResp);
  }
  /**
   * 发送给所有用户
   * @param noticeWebsocketResp
   */
  public static void sendMessage(NoticeWebsocketResp noticeWebsocketResp){
    String message = JSONObject.toJSONString(noticeWebsocketResp);
    for (Session session1 : NoticeWebsocket.clients.values()) {
      try {
        session1.getBasicRemote().sendText(message);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  /**
   * 收到客户端消息后调用的方法
   * @param message
   * @throws IOException
   */
  @OnMessage
  public void onMessage(String message,Session session) throws IOException {
    //可以群发消息
    //消息保存到数据库、redis
    if (StrUtil.isNotBlank(message)) {
      try {
        if ("rub".equals(message)) {
          return;
        }
        String unMessage = PakoGzipUtils.unCompressURI(message);
        log.info("用户消息:" + userId + ",报文:" + unMessage);
        cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(unMessage);
        if (!"mv".equals(jsonObject.getStr("t"))) {
          System.out.println("55555");
          //webSocketServer.messageProcess.process(this.gridKey, jsonObject);
        }
        System.out.println("clients = " + clients);
        System.out.println("webSocketMap = " + webSocketMap);
        Map<String, NoticeWebsocket> sessionMap = webSocketMap.get(this.gridKey);
        System.out.println("this.userId = " + this.userId);
        System.out.println("sessionMap = " + sessionMap);
        if (StrUtil.isNotBlank(unMessage)) {
          sessionMap.forEach((key, value) -> {

            //广播到除了发送者外的其它连接端
            if (!key.equals(this.userId)) {
              try {
                //如果是mv,代表发送者的表格位置信息

                if ("mv".equals(jsonObject.getStr("t"))) {
                  sendMessage(JSONUtil.toJsonStr(ResponseDTO.mv(userId, userId+userId, unMessage)));
                  System.out.println("推送userId:"+userId);

                  //如果是切换sheet，则不发送信息
                } else if(!"shs".equals(jsonObject.getStr("t"))) {
                  sendMessage(JSONUtil.toJsonStr(ResponseDTO.update(userId, userId+userId, unMessage)));
                }
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          });
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }


  }


 
  /**
   * 根据用户id发送给某一个用户
   * **/
  public static void sendMessageByUserId(String userId, NoticeWebsocketResp noticeWebsocketResp) {
    if (!StringUtils.isEmpty(userId)) {
      String message = JSONObject.toJSONString(noticeWebsocketResp);
      Set<String> clientSet = conns.get(userId);
      if (clientSet != null) {
        Iterator<String> iterator = clientSet.iterator();
        while (iterator.hasNext()) {
          String sid = iterator.next();
          Session session = clients.get(sid);
          if (session != null) {
            try {
              session.getBasicRemote().sendText(message);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }else {
            iterator.remove();;
          }
        }
      }
    }
  }
 
  @OnError
  public void onError(Throwable error) {
    error.printStackTrace();
  }
 
}