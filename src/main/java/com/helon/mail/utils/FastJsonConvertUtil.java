package com.helon.mail.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @Author: Helon
 * @Description: json转换工具类
 * @Data: 2018/1/21 12:41
 * @Modified By:
 */
public class FastJsonConvertUtil {

	private static final SerializerFeature[] featuresWithNullValue = { SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullBooleanAsFalse,
	        SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty };

	/**
	 * @Author: Helon
	 * @Description: 将JSON字符串转换为实体对象
	 * @param data - 字符串
	 * @param clazz - 实体类的Class对象
	 * @Data: 2018/1/21 12:44
	 * @Modified By:
	 */
	public static <T> T convertJSONToObject(String data, Class<T> clazz) {
		try {
			T t = JSON.parseObject(data, clazz);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Author: Helon
	 * @Description: 将JSONObject对象转化成实体类对象
	 * @param data
	 * @param clazz
	 * @Data: 2018/1/21 12:47
	 * @Modified By:
	 */
	public static <T> T convertJSONToObject(JSONObject data, Class<T> clazz) {
		try {
			T t = JSONObject.toJavaObject(data, clazz);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Author: Helon
	 * @Description: 将JSON字符串数组转为List集合对象
	 * @param data
	 * @param clazz
	 * @Data: 2018/1/21 12:50
	 * @Modified By:
	 */
	public static <T> List<T> convertJSONToArray(String data, Class<T> clazz) {
		try {
			List<T> t = JSON.parseArray(data, clazz);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Author: Helon
	 * @Description: 将List<JSONObject>转为List集合对象
	 * @param data
	 * @param clazz
	 * @Data: 2018/1/21 12:53
	 * @Modified By:
	 */
	public static <T> List<T> convertJSONToArray(List<JSONObject> data, Class<T> clazz) {
		try {
			List<T> t = new ArrayList<T>();
			for (JSONObject jsonObject : data) {
				t.add(convertJSONToObject(jsonObject, clazz));
			}
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Author: Helon
	 * @Description: 将对象转为JSON字符串
	 * @param obj
	 * @Data: 2018/1/21 12:54
	 * @Modified By:
	 */
	public static String convertObjectToJSON(Object obj) {
		try {
			String text = JSON.toJSONString(obj);
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Author: Helon
	 * @Description: 将对象转为JSONObject对象
	 * @param obj
	 * @Data: 2018/1/21 12:55
	 * @Modified By:
	 */
	public static JSONObject convertObjectToJSONObject(Object obj){
		try {
			JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}


	public static String convertObjectToJSONWithNullValue(Object obj) {
		try {
			String text = JSON.toJSONString(obj, featuresWithNullValue);
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
