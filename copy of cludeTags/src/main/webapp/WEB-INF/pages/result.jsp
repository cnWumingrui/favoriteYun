<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="com.google.gson.Gson,com.yc.bean.JsonModel" %>
<%

  Gson gson=new Gson();
  String jsonString=gson.toJson(  (JsonModel) session.getAttribute("jsonModel") ); 
  
 %>
 <%=jsonString %>