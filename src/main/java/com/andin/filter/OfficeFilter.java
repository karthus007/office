package com.andin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.andin.utils.ConstantUtil;
import com.andin.utils.StringUtil;

@Component
@WebFilter(filterName = "filter")
public class OfficeFilter implements Filter{
	
	public static boolean licenseStatus = false;
	
	static {
		licenseStatus = StringUtil.getLicenseStatus();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		if(uri.startsWith("/license")) {
			chain.doFilter(request, response);			
		}else {
			if(licenseStatus) {
				chain.doFilter(request, response);			
			}else {
				writeAjaxResponse(resp);				
			}
		}
		
	}
	
	
	/**
	 * 返回错误响应
	 * @param resp
	 * @param description
	 * @throws IOException
	 */
	private void writeAjaxResponse(HttpServletResponse resp) throws IOException {
        JSONObject result = new JSONObject();
        result.put(ConstantUtil.RESULT_CODE, ConstantUtil.LICENSE_NOAUTH_ERROR_CODE);
        result.put(ConstantUtil.RESULT_MSG, ConstantUtil.LICENSE_NOAUTH_ERROR_MSG);
        resp.setStatus(200);
        resp.setContentType(ConstantUtil.APPLICATION_JSON_UTF_8);
        resp.getWriter().append(result.toJSONString());
	}

}
