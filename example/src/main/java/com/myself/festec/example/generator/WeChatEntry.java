package com.myself.festec.example.generator;

import com.myself.example.annotations.EntryGenerator;
import com.myself.latte.wechat.template.WXEntryTemplate;

/**
 * Created by Kamh on 2018/2/23.
 */
@EntryGenerator(
        packageName = "com.myself.festec.example",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}
