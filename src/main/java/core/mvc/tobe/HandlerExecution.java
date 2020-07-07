package core.mvc.tobe;

import core.mvc.Handler;
import core.mvc.ModelAndView;
import core.mvc.tobe.resolver.ArgumentResolverComposite;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class HandlerExecution implements Handler {

    private Method method;
    private Object instance;

    public HandlerExecution(Method method, Object instance) {
        this.method = method;
        this.instance = instance;
    }

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArgumentResolverComposite argumentResolverComposite = new ArgumentResolverComposite(method);
        Object[] args = argumentResolverComposite.getArguments(request);
        return (ModelAndView) method.invoke(instance, args);
    }
}
