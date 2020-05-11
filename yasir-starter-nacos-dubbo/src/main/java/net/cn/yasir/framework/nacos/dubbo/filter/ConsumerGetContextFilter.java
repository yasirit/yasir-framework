package net.cn.yasir.framework.nacos.dubbo.filter;

import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

/**
 * 获取context
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/5/11
 */
public class ConsumerGetContextFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return null;
    }
}
