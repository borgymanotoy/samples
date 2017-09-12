package com.samples.rest.springboot.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;

public class ObjectToJsonString {


    public static String serializeToJson(Object obj) {
        return serializeToJson(obj, null);
    }

    public static String serializeToJson(Object obj, String callback) {
        StringBuilder sb = new StringBuilder();
        String theJsonString = null;

        try {
            ObjectMapper theObjectMapper = new ObjectMapper();
            ByteArrayOutputStream theJsonOutputStream = new ByteArrayOutputStream();
            theObjectMapper.writeValue(theJsonOutputStream, obj);
            theJsonString = theJsonOutputStream.toString("UTF-8");
        } catch (Exception theException) {
            theException.printStackTrace();
        }

        if(null != callback){
            sb.append(callback);
            sb.append("(");
            sb.append(theJsonString);
            sb.append(")");
        }
        else
            sb.append(theJsonString);

        return sb.toString();
    }

}
