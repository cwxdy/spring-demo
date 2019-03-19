package com.example.demo.utils.http.useragent;

import java.util.regex.Pattern;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.Engine;
import cn.hutool.http.useragent.OS;
import cn.hutool.http.useragent.Platform;
import cn.hutool.http.useragent.UserAgent;

/**
 * User-Agent解析器
 * 
 * @author looly
 * @since 4.2.1
 */
public class UserAgentParser {

	/**
	 * 解析User-Agent
	 * 
	 * @param userAgentString User-Agent字符串
	 * @return {@link cn.hutool.http.useragent.UserAgent}
	 */
	public static cn.hutool.http.useragent.UserAgent parse(String userAgentString) {
		final cn.hutool.http.useragent.UserAgent userAgent = new UserAgent();
		
		final cn.hutool.http.useragent.Browser browser = parseBrowser(userAgentString);
		userAgent.setBrowser(parseBrowser(userAgentString));
		userAgent.setVersion(browser.getVersion(userAgentString));
		
		final cn.hutool.http.useragent.Engine engine = parseEngine(userAgentString);
		userAgent.setEngine(engine);
		if (false == engine.isUnknown()) {
			userAgent.setEngineVersion(parseEngineVersion(engine, userAgentString));
		}
		userAgent.setOs(parseOS(userAgentString));
		final cn.hutool.http.useragent.Platform platform = parsePlatform(userAgentString);
		userAgent.setPlatform(platform);
		userAgent.setMobile(platform.isMobile() || browser.isMobile());
		

		return userAgent;
	}

	/**
	 * 解析浏览器类型
	 * 
	 * @param userAgentString User-Agent字符串
	 * @return 浏览器类型
	 */
	private static cn.hutool.http.useragent.Browser parseBrowser(String userAgentString) {
		for (cn.hutool.http.useragent.Browser brower : cn.hutool.http.useragent.Browser.browers) {
			if (brower.isMatch(userAgentString)) {
				return brower;
			}
		}
		return Browser.Unknown;
	}
	
	/**
	 * 解析引擎类型
	 * 
	 * @param userAgentString User-Agent字符串
	 * @return 引擎类型
	 */
	private static cn.hutool.http.useragent.Engine parseEngine(String userAgentString) {
		for (cn.hutool.http.useragent.Engine engine : cn.hutool.http.useragent.Engine.engines) {
			if (engine.isMatch(userAgentString)) {
				return engine;
			}
		}
		return cn.hutool.http.useragent.Engine.Unknown;
	}

	/**
	 * 解析引擎版本
	 * 
	 * @param engine 引擎
	 * @param userAgentString User-Agent字符串
	 * @return 引擎版本
	 */
	private static String parseEngineVersion(Engine engine, String userAgentString) {
		final String regexp = engine.getName() + "[\\/\\- ]([\\d\\w\\.\\-]+)";
		final Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
		return ReUtil.getGroup1(pattern, userAgentString);
	}

	/**
	 * 解析系统类型
	 * 
	 * @param userAgentString User-Agent字符串
	 * @return 系统类型
	 */
	private static cn.hutool.http.useragent.OS parseOS(String userAgentString) {
		for (cn.hutool.http.useragent.OS os : cn.hutool.http.useragent.OS.oses) {
			if (os.isMatch(userAgentString)) {
				return os;
			}
		}
		return OS.Unknown;
	}

	/**
	 * 解析平台类型
	 * 
	 * @param userAgentString User-Agent字符串
	 * @return 平台类型
	 */
	private static cn.hutool.http.useragent.Platform parsePlatform(String userAgentString) {
		for (cn.hutool.http.useragent.Platform platform : cn.hutool.http.useragent.Platform.platforms) {
			if (platform.isMatch(userAgentString)) {
				return platform;
			}
		}
		return Platform.Unknown;
	}
}
