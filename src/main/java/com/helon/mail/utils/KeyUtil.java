package com.helon.mail.utils;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * @Author: Helon
 * @Description: 唯一键生成工具
 * @Data: 2018/1/21 12:37
 * @Modified By:
 */
public class KeyUtil {

	/**
	 * @Author: Helon
	 * @Description: 主键生成策略
	 * @Data: 2018/1/21 12:38
	 * @Modified By:
	 */
	public static String generatorUUID(){
		TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
		return timeBasedGenerator.generate().toString();
	}
}
