package com.alibaba.json.bvt;

import org.junit.Assert;
import junit.framework.TestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class LongFieldTest extends TestCase {

    public void test_codec() throws Exception {
        V0 v = new V0();
        v.setValue(1001L);

        String text = JSON.toJSONString(v);
        System.out.println(text);

        V0 v1 = JSON.parseObject(text, V0.class);

        Assert.assertEquals(v1.getValue(), v.getValue());
    }

    public void test_codec_null() throws Exception {
        V0 v = new V0();

        SerializeConfig mapping = new SerializeConfig();
        

        String text = JSON.toJSONString(v, mapping, SerializerFeature.WriteMapNullValue);
        Assert.assertEquals("{\"value\":null}", text);

        V0 v1 = JSON.parseObject(text, V0.class);

        Assert.assertEquals(v1.getValue(), v.getValue());
    }
    
    public void test_codec_null_asm() throws Exception {
        V0 v = new V0();

        SerializeConfig mapping = new SerializeConfig();
        

        String text = JSON.toJSONString(v, mapping, SerializerFeature.WriteMapNullValue);
        Assert.assertEquals("{\"value\":null}", text);

        ParserConfig config = new ParserConfig();
        
        
        V0 v1 = JSON.parseObject(text, V0.class, config, JSON.DEFAULT_PARSER_FEATURE);

        Assert.assertEquals(v1.getValue(), v.getValue());
    }

    public void test_codec_null_1() throws Exception {
        V0 v = new V0();

        SerializeConfig mapping = new SerializeConfig();
        

        String text = JSON.toJSONString(v, mapping, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullNumberAsZero);
        Assert.assertEquals("{\"value\":0}", text);

        V0 v1 = JSON.parseObject(text, V0.class);

        Assert.assertEquals(Long.valueOf(0), v1.getValue());
    }

    public static class V0 {

        private Long value;

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

    }
}
