package com.base.xtgl.factory;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.base.xtgl.util.StringUtil;;

/**
 * ���񹤳������ڱ�����EJB������ʹ�á�
 * 
 * @author Administrator
 * 
 */
public class ServiceFactory {
	private ServiceFactory() {

	}

	public static ApplicationContext springContext = null;

	
	synchronized private static void setApplicationContext() {

//		if (springContext == null) {
//			springContext = new ClassPathXmlApplicationContext(new String[] {
//					"com/zfsoft/config/applicationContext*.xml",
//					"com/zfsoft/config/*/applicationContext*.xml",
//					"com/zfsoft/config/comm/*/applicationContext*.xml",
//					"com/zfsoft/config/comp/*/applicationContext*.xml" });
//		}

		
		if (springContext == null) {
			springContext = new ClassPathXmlApplicationContext(new String[] {
					"classpath*:/conf/spring/applicationContext*.xml",
					"classpath*:/conf/spring/common.xml",
					"classpath*:/conf/spring/config*.xml" });
		}

	}
	
	/**
	 * ������֪�����õķ�����ȡ����
	 * 
	 * @param svrCode
	 * @return
	 */
	public static Object getService(String svrCode) {
		if (StringUtil.isEmpty(svrCode)) {
			return null;
		}

		if (springContext == null) {
			setApplicationContext();
		}
		//
		if (springContext != null) {

			return springContext.getBean(svrCode);
		}
		return null;

	}


	/**
	 * ���ڰ��淶�����ķ���ͨ���������ȡ����
	 * 
	 * @param svrCode
	 * @return
	 */
	public static Object getService(Class cls) {
		if (cls == null) {
			return null;
		}
		String svrCode = cls.getSimpleName();
		if (svrCode.length() <= 1 || StringUtil.isEmpty(svrCode)) {
			return null;
		}

		byte bytes[] = svrCode.getBytes();
		bytes[1] = new String(bytes, 1, 1).toLowerCase().getBytes()[0];
		return getService(new String(bytes, 1, bytes.length - 1));

	}

	/**
	 * ȡ����Դ
	 * 
	 * @return
	 */
	public static DataSource getDataSource(String svrCode) {
		return (DataSource) getService(svrCode);

	}

}
