package cn.allcheer.springcloud.eureka_zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lihui
 */
@Slf4j
public class CustomTokenFilter extends ZuulFilter {
    /**
     *  返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型
     *  pre：路由之前
     *  routing：路由之时
     *  post： 路由之后
     *  error：发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext rCtx = RequestContext.getCurrentContext();
        HttpServletRequest request=rCtx.getRequest();
        log.info("--->>> CustomTokenFilter >>> {} >>> {} >>> {}",request.getMethod(),request.getRequestURL(),request.getParameterMap());
        String token=request.getParameter("token");
        if(StringUtils.isNotBlank(token)){
//            对请求进行路由
            rCtx.setSendZuulResponse(true);
            rCtx.setResponseStatusCode(HttpStatus.SC_OK);
            rCtx.set("isSC",true);
        }else{
//            不对请求进行路由
            rCtx.setSendZuulResponse(false);
            rCtx.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            rCtx.set("isSC",false);
        }
        return null;
    }
}
