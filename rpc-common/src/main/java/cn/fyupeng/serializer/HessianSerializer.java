package cn.fyupeng.serializer;

import cn.fyupeng.enums.SerializerCode;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @Auther: fyp
 * @Date: 2022/12/6
 * @Description: Hessian 序列化
 * @Package: cn.fyupeng.serializer
 * @Version: 1.0
 */
public class HessianSerializer implements CommonSerializer {

    @Override
    public byte[] serialize(Object obj) {
        byte[] data = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Hessian2Output output = new Hessian2Output(os);
            output.writeObject(obj);
            output.getBytesOutputStream().flush();
            output.completeMessage();
            output.close();
            data = os.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;

    }

    @Override
    public Object deserialize(byte[] data, Class<?> clazz) {
        if(data == null){
            return null;
        }
        Object obj = null;
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            Hessian2Input input = new Hessian2Input(is);
            obj = input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public int getCode() {
        return SerializerCode.valueOf("HESSIAN").getCode();
    }
}