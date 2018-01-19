package com.myself.festec.example;

import com.myself.latte.activities.ProxyActivity;
import com.myself.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
