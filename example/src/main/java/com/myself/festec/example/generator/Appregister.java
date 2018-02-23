package com.myself.festec.example.generator;

import com.myself.example.annotations.AppRegisterGenerator;
import com.myself.latte.wechat.template.AppRegisterTemplate;

/**
 * Created by Kamh on 2018/2/23.
 */
@AppRegisterGenerator(
        packageName = "com.myself.festec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface Appregister {
}
