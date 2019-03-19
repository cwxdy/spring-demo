package com.example.demo.utils.http.useragent;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentParser;

/**
 * User-Agent工具类
 * 
 * @author looly
 *
 */
public class UserAgentUtil {

	/**
	 * 解析User-Agent
	 * 
	 * @param userAgentString User-Agent字符串
	 * @return {@link cn.hutool.http.useragent.UserAgent}
	 */
	public static UserAgent parse(String userAgentString) {
		return UserAgentParser.parse(userAgentString);
	}
}
