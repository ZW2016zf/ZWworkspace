package com.base.xtgl.factory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.base.xtgl.entity.User;



/**
 * �Ự������ֻ�����ڵ�ǰ�̣߳�EJB�˲�֧��ʹ�ã��Ժ����֧�֣�
 * 
 * @author Administrator
 * 
 */
public class SessionFactory {
	private SessionFactory() {

	}
	/**
	 * �����������ȡ�Ự
	 * 
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();

	}

	/**
	 * �����������ȡ������Ϣ
	 * 
	 * @param svrCode
	 * @return
	 */
	public static HttpServletRequest getHttpRequest() {
		return ServletActionContext.getRequest();

	}

	/**
	 * ȡ������Ϣ��ip,port,name��
	 * 
	 * @return
	 */
	public static String[] getHostInfo() {
		HttpServletRequest request = null;
		try {
			request = getHttpRequest();
		} catch (NullPointerException npEx) {
			request=null;
		}
		if (request == null) {
			return new String[] { "", "", "" };
		}
		return new String[] { request.getRemoteAddr(),
				request.getRemotePort() + "", request.getRemoteHost()};
	}

	/**
	 * ȡ�û�Ȩ����Ϣ[�û����,��λ����,����]
	 * 
	 * @return
	 */
	@Deprecated
	public static String[] getUserInfo() {
		HttpServletRequest request = getHttpRequest();
		if (request == null) {
			return new String[] { "", "", "" };
		}
		return new String[] { "1", "1", "1" };
	}
	
	
	/**
	 * ȡ��ǰ�Ự�û���Ϣ
	 * @return User
	 */
	public static User getUser() {
		HttpSession session = getSession();
		return (User) session.getAttribute("user");
	}

	

	/**
	 * ȡ����������·��
	 * 
	 * @return
	 */
	public static String getContextPath() {
		HttpServletRequest request = getHttpRequest();
		if (request == null) {
			return "xsgzgl";
		}
		return request.getContextPath();
	}

}
