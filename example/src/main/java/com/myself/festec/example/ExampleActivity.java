package com.myself.festec.example;

import com.myself.latte.activities.ProxyActivity;
import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.launcher.LauncherDelegate;
import com.myself.latte.ec.launcher.LauncherScrollDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherScrollDelegate();
    }
}
