package com.didi.hummer;

import android.content.Context;

import com.didi.hummer.context.HummerContext;
import com.didi.hummer.context.HummerContextFactory;
import com.didi.hummer.core.util.HMLog;
import com.didi.hummer.register.HummerRegister$$hummer_component;
import com.didi.hummer.render.style.HummerLayout;
import com.didi.hummer.tools.EventTracer;

/**
 * Created by XiaoFeng on 2019-11-05.
 */
public class Hummer {

    public static boolean isSupport(Context context, @HummerSDK.JsEngine int engine) {
        return HummerSDK.isSupport(context, engine);
    }

    public static void setJsEngine(@HummerSDK.JsEngine int jsEngine) {
        HummerSDK.setJsEngine(jsEngine);
    }

    public static void init(Context context) {
        init(context, null);
    }

    public static void init(Context context, HummerConfig config) {
        HummerSDK.init(context, config);
    }

    public static void release() {
        HummerSDK.release();
    }

    public static HummerContext createContext(HummerLayout container) {
        return createContext(container, null);
    }

    public static HummerContext createContext(HummerLayout container, String namespace) {
        HMLog.d("HummerNative", "HummerContext.createContext");
        long startTime = System.currentTimeMillis();

        HummerContext context = HummerContextFactory.createContext(container, namespace);
        HummerRegister$$hummer_component.init(context);

        EventTracer.tracePerformance(namespace, "HummerContext.createContext", startTime);
        return context;
    }
}
