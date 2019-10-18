package com.wuming;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @ProjectName: LotterAdmin
 * @Package: com.secoo.cre.admin.util
 * @Class SystemUtil
 * @Author: WuMing
 * @CreateDate: 2018/9/6 11:15
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class SystemUtil {
	
	public static String getServerIp() {
		
		// 获取操作系统类型
		String sysType = System.getProperties().getProperty("os.name");
		String ip;
		if (sysType.toLowerCase().startsWith("win")) {  // 如果是Windows系统，获取本地IP地址
			String localIP = null;
			try {
				localIP = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			if (localIP != null) {
				return localIP;
			}
		} else {
			// 兼容Linux
			ip = getIpByEthNum("eth0");
			if (ip != null) {
				return ip;
			}
		}
		return "127.0.0.1";
	}
	
	private static String getIpByEthNum(String ethNum) {
		try {
			Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				if (ethNum.equals(netInterface.getName())) {
					Enumeration addresses = netInterface.getInetAddresses();
					while (addresses.hasMoreElements()) {
						ip = (InetAddress) addresses.nextElement();
						if (ip != null && ip instanceof Inet4Address) {
							return ip.getHostAddress();
						}
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return "获取服务器IP错误";
	}
}

