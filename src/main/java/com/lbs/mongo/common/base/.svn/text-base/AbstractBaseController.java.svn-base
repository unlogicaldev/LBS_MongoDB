package com.lbs.mongo.common.base;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.google.code.morphia.Morphia;
import com.mongodb.DBObject;

public abstract class AbstractBaseController<T> {

	@SuppressWarnings("unchecked")
	final protected Logger log = LoggerFactory.getLogger(((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]));

	@Autowired protected ObjectMapper jkMapper;
	
	@Autowired protected Morphia morphia;
	
	
	protected void setSessionAttr(String key, Object value, HttpServletRequest request){
		request.getSession().setAttribute(key, value);
		request.getSession().setMaxInactiveInterval(-1);
	}
	
	protected Object getSessionAttr(String key, HttpServletRequest request){
		return request.getSession().getAttribute(key);
	}
		
	protected void resetSession(String key, HttpServletRequest request){
		request.getSession().removeAttribute(key);
	}
	
	protected String getJSONString(Object o){
		String json = "";
		try {
			json = jkMapper.writeValueAsString(o);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	protected DBObject convertURIDecoder(Object obj){
		DBObject o = morphia.toDBObject(obj);
		Set<String> set = o.keySet();
		Iterator<String> it = set.iterator();
		String key;
		while(it.hasNext()){
			key = it.next();
			System.out.println(o.get(key).getClass().toString());
			if(o.get(key).getClass().equals(String.class)){
				if(((String)o.get(key)).indexOf("%") > -1){
					try {
						o.put(key, URLDecoder.decode((String)o.get(key), "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return o;
		
	}
	
	/**
	 * 모든 에러케이스에 전부 적용시키는 에러헨들러 이다.
	 * Exception을 다시 던지는 이유 --> 모든 ajax요청에 Error처리가 되어있음.
	 * WAS에 접속하기 전 Error가 발생하면 WS에서 처리하도록 되어있다.
	 * @param e
	 * @param request
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	protected void handleException(Exception e, HttpServletRequest request) throws Exception{
		StackTraceElement[] st = e.getStackTrace();
		StringBuffer sb = new StringBuffer();
		sb.append(new Date()).append("\r\n");
		sb.append(log.getName()).append("\r\n");
		for(StackTraceElement s: st){
			sb.append(s.toString()).append("\r\n");
		}
		System.out.println(sb.toString());
		//TODO 에러를 관리자 메일로 발송한다. 발송에 실패할 경우 DM 날린다.
		throw new Exception();
	}
}
