package com.wuming.vm.classload;

import java.io.InputStream;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.classload
 * @Class ClassLoaderTest
 * @Author: WuMing
 * @CreateDate: 2018/9/25 18:58
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class ClassLoaderTest {
	
	public static void main(String[] args) throws Exception {
		ClassLoader myLoader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				try {
					String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
					InputStream resourceAsStream = getClass().getResourceAsStream(fileName);
					if (resourceAsStream == null) {
						return super.loadClass(name);
					}
					byte[] bytes = new byte[resourceAsStream.available()];
					resourceAsStream.read(bytes);
					return defineClass(name, bytes, 0, bytes.length);
				} catch (Exception e) {
					throw new ClassNotFoundException(name);
				}
			}
			
		};
		Object aClass = myLoader.loadClass("com.wuming.vm.classload.ClassLoaderTest").newInstance();
		System.out.println(aClass.getClass().getName());
		System.out.println(aClass instanceof ClassLoaderTest);
	}
}
