package com.myself.festec.example.generator;

import com.myself.example.annotations.PayEntryGenerator;
import com.myself.latte.wechat.template.WXPayEntryTemplate;

/**
 * Created by Kamh on 2018/2/23.
 */
@PayEntryGenerator(
        packageName = "com.myself.festec.example",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {


}
