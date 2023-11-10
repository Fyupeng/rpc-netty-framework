package cn.fyupeng.protocol;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @Auther: fyp
 * @Date: 2022/3/22
 * @Description: 请求体
 * @Package: cn.fyupeng.protocol
 * @Version: 1.0
 */
public class RpcRequest implements Serializable {

    /**
     *  请求号，主要用来做 多 客户端 请求包 和 响应包的 过滤
     */
    private String requestId;
    // 调用方法 所属的 接口名
    private String interfaceName;
    // 调用方法名
    private String methodName;
    // 调用方法的参数
    private Object[] parameters;
    // 调用方法的 参数类型
    private String[] paramTypes;
    // 调用方法的 返回值类型
    private String returnType;
    /**
     * 重发标志位 默认为 false 表示非 重发包
     */
    private Boolean reSend = false;

    private String group;

    /**
     * 指定 是否为 心跳包，区分数据包和心跳包的关键，需要配合 IdleStateHandler 使用
     * IdleStateHandler 作用 做 读空闲检测、写检测、读写检测
     * 客户端 设置 写 状态超时时，超时时间内 未 向 服务端 写操作，即 触发 读超时时间
     * 理解：表示 客户端 虽然 没有 向 服务端 写 数据了，但 还是 要向 服务端 “表示我还活着” -> 发送 心跳包
     * 注意：这里 JSON 对 boolean 反序列 有时会出现 异常（Reference Chain），最好 使用 引用类型的 Boolean，推荐 KRYO
     */
    private Boolean heartBeat = false;

    /**
     * 没有空 构造方法 会导致 反序列化 失败
     * Exception: no delegate- or property-based Creator
     */
    public RpcRequest() {
        super();
    }

    public RpcRequest(Builder builder) {
        this.requestId = builder.requestId;
        this.interfaceName = builder.interfaceName;
        this.methodName = builder.methodName;
        this.parameters = builder.parameters;
        this.paramTypes = builder.paramTypes;
        this.returnType = builder.returnType;
        this.heartBeat = builder.heartBeat;
        this.reSend = builder.reSend;
    }

    public static final class Builder {

        private String requestId;
        private String interfaceName;
        private String methodName;
        private Object[] parameters;
        private String[] paramTypes;
        private String returnType;
        private Boolean heartBeat;
        private Boolean reSend;

        public Builder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder interfaceName(String interfaceName) {
            this.interfaceName = interfaceName;
            return this;
        }

        public Builder methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public Builder parameters(Object[] parameters) {
            this.parameters = parameters;
            return this;
        }

        public Builder paramTypes(String[] paramTypes) {
            this.paramTypes = paramTypes;
            return this;
        }

        public Builder returnType(String returnType) {
            this.returnType = returnType;
            return this;
        }

        public Builder heartBeat(Boolean heartBeat) {
            this.heartBeat = heartBeat;
            return this;
        }

        public Builder reSend(Boolean reSend) {
            this.reSend = reSend;
            return this;
        }

        public RpcRequest build() {
            return new RpcRequest(this);
        }

    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(String[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Boolean getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(Boolean heartBeat) {
        this.heartBeat = heartBeat;
    }

    public Boolean getReSend() {
        return reSend;
    }

    public void setReSend(Boolean reSend) {
        this.reSend = reSend;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "requestId='" + requestId + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                ", paramTypes=" + Arrays.toString(paramTypes) +
                ", returnType=" + returnType +
                ", reSend=" + reSend +
                ", group='" + group + '\'' +
                ", heartBeat=" + heartBeat +
                '}';
    }
}
