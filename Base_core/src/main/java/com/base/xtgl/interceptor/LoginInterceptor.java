package com.base.xtgl.interceptor;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;
import com.base.xtgl.entity.User;
import com.base.xtgl.factory.ServiceFactory;
import com.base.xtgl.factory.SessionFactory;
import com.base.xtgl.util.StringUtil;;

public class LoginInterceptor extends AbstractInterceptor {

private static final long serialVersionUID = 1L;
	
	private static ResourceBundle excludeurlBundle = null;
	
	private static String PARAM = "[Param]";
	/**
	 * �ж��Ƿ��ǲ����˵�url ���ƥ���򷵻�true
	 * @param actionInvocation
	 * @return
	 */
	public static boolean getCheckUrl(ActionInvocation actionInvocation){
		String path=ServletActionContext.getRequest().getServletPath();
		
		if(excludeurlBundle==null){
			try{
				excludeurlBundle = ResourceBundle.getBundle("excludeurl");
			}catch(Exception e){
				return false;
			}
		}
		for(String key:excludeurlBundle.keySet()){
			String urlSegment=excludeurlBundle.getString(key);
			if(path.indexOf(urlSegment)>-1){
				return true;
			}
			int index=urlSegment.indexOf(PARAM);
			if(index>-1){
				if(path.indexOf(urlSegment.substring(0, index))>-1){
					String[] paramlist=urlSegment.substring(index+PARAM.length()).split(",");
					for(String p:paramlist){
						String[] attr = p.split(":");
						if(attr.length<2)
						{
							return false;
						}
						String value = attr[1];
						if(attr[1].startsWith("$")){
							value= String.valueOf(ServletActionContext.
									getRequest().getSession().getAttribute(attr[1].substring(1)));
						}
						
						if(!value.equals(String.valueOf(ServletActionContext
								.getRequest().getAttribute(attr[0])))){
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		ValueStack vs = ServletActionContext.getValueStack(request);
		String path=ServletActionContext.getRequest().getServletPath();
		
		if(getCheckUrl(actionInvocation)){
			return actionInvocation.invoke();
		}
		//User myuser = new User();
		//request.getSession().setAttribute("user", myuser);
		User user = SessionFactory.getUser();
		
		String queryString = request.getQueryString();
		if(!StringUtil.isEmpty(queryString)){
			path = path +"?"+queryString;
		}
		if (null == user) {
		    if(!path.startsWith("/xtgl/index_sessionOut.html")){
		        vs.set("sessionout", "yes");
		        return "sessionOut";
		    }else{
		        return Action.LOGIN;
		    }
		}
		
		//�˵�Ȩ�޴���
		
		/*if(((IIndexService)ServiceFactory.getService("indexService")).yzQx(path)){
			IAncdService service = (IAncdService) ServiceFactory.getService("ancdService");
			List<AncdModel> ancdModelList = service.cxAncd(user, path);
			vs.set("ancdModelList", ancdModelList);
		}*/
		
		return actionInvocation.invoke();

	}

}
