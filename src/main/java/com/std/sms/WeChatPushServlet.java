package com.std.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.std.sms.ao.IReceiverAO;
import com.std.sms.common.PropertiesUtil;
import com.std.sms.sent.wechat.ReceiveXml;
import com.std.sms.sent.wechat.XmlToDomain;
import com.std.sms.spring.SpringContextHolder;
import com.std.sms.util.SignUtil;

/**
 * @author: xieyj 
 * @since: 2016年12月2日 上午11:42:44 
 * @history:
 */
public class WeChatPushServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(WeChatPushServlet.class);

    private IReceiverAO receiverAO = SpringContextHolder
        .getBean(IReceiverAO.class);

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 6175432226630152841L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out
            .println("***************************验证签名begin***************************");
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
        System.out
            .println("***************************验证签名end***************************");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("*************请求开始*************");
        try {
            // 解析请求xml报文
            StringBuffer sb = new StringBuffer();
            InputStreamReader isr = new InputStreamReader(
                request.getInputStream(), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            XmlToDomain xmlToDomain = new XmlToDomain();
            ReceiveXml receiveXml = xmlToDomain.convertXml(sb.toString());
            receiverAO.importWxReceiver(receiveXml.getContent(),
                PropertiesUtil.Config.SYSTEM_CODE,
                receiveXml.getFromUserName(), "接收微信消息");
        } finally {
            PrintWriter out = response.getWriter();
            out.print("success");
            out.close();
            out = null;
        }
        System.out.println("*************请求结束*************");
    }
}
