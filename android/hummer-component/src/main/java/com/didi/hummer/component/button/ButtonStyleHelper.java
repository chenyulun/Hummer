package com.didi.hummer.component.button;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import com.didi.hummer.render.component.view.BackgroundDrawable;
import com.didi.hummer.render.style.HummerStyleUtils;
import com.didi.hummer.render.utility.YogaAttrUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 按钮样式帮助类
 *
 * Created by XiaoFeng on 2019-09-15.
 */
public class ButtonStyleHelper {

    public final static String KEY_BACKGROUND_COLOR = "backgroundColor";
    public final static String KEY_BORDER_WIDTH = "borderWidth";
    public final static String KEY_BORDER_COLOR = "borderColor";
    public final static String KEY_BORDER_RADIUS = "borderRadius";
    public final static String KEY_BORDER_STYLE = "borderStyle";
    public final static String KEY_TEXT_COLOR = "color";

    public final static int KEY_ON_NORMAL = 0;
    public final static int KEY_ON_PRESS = 1;
    public final static int KEY_ON_DISABLE = 2;

    public static void fillButtonBackgroundBorderStyle(Map<String, Object> fullStyle, Map<String, Object> subStyle) {
        if (fullStyle.containsKey(KEY_BORDER_WIDTH) && !subStyle.containsKey(KEY_BORDER_WIDTH)) {
            subStyle.put(KEY_BORDER_WIDTH, fullStyle.get(KEY_BORDER_WIDTH));
        }
        if (fullStyle.containsKey(KEY_BORDER_COLOR) && !subStyle.containsKey(KEY_BORDER_COLOR)) {
            subStyle.put(KEY_BORDER_COLOR, fullStyle.get(KEY_BORDER_COLOR));
        }
        if (fullStyle.containsKey(KEY_BORDER_RADIUS) && !subStyle.containsKey(KEY_BORDER_RADIUS)) {
            subStyle.put(KEY_BORDER_RADIUS, fullStyle.get(KEY_BORDER_RADIUS));
        }
        if (fullStyle.containsKey(KEY_BORDER_STYLE) && !subStyle.containsKey(KEY_BORDER_STYLE)) {
            subStyle.put(KEY_BORDER_STYLE, fullStyle.get(KEY_BORDER_STYLE));
        }
    }

    public static Drawable pickButtonBackgroundDrawable(Map style) {
        Object bgColor = null;
        if (style.containsKey(KEY_BACKGROUND_COLOR)) {
            bgColor = style.get(KEY_BACKGROUND_COLOR);
            bgColor = HummerStyleUtils.convertColor(bgColor);
        }
        BackgroundDrawable drawable = new BackgroundDrawable();
        drawable.setColor(bgColor);
        if (style.containsKey(KEY_BORDER_WIDTH)) {
            Object width = style.get(KEY_BORDER_WIDTH);
            if (width != null) {
                drawable.setBorderWidth(HummerStyleUtils.convertNumber(width));
            }
        }
        if (style.containsKey(KEY_BORDER_COLOR)) {
            Object color = style.get(KEY_BORDER_COLOR);
            color = HummerStyleUtils.convertColor(color);
            if (color != null) {
                drawable.setBorderColor((Integer) color);
            }
        }
        if (style.containsKey(KEY_BORDER_RADIUS)) {
            Object radius = style.get(KEY_BORDER_RADIUS);
            if (radius != null) {
                drawable.setBorderRadius(HummerStyleUtils.convertNumber(radius));
            }
        }
        if (style.containsKey(KEY_BORDER_STYLE)) {
            Object stl = style.get(KEY_BORDER_STYLE);
            if (stl != null) {
                drawable.setBorderStyle((String) stl);
            }
        }
        return drawable;
    }

    public static StateListDrawable makeButtonBackgroundColorStateList(Map<Integer, Drawable> bgDrawableMap) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (Integer key : bgDrawableMap.keySet()) {
            switch (key) {
                case KEY_ON_NORMAL: {
                    int[] state = new int[]{-android.R.attr.state_pressed, android.R.attr.state_enabled};
                    Drawable drawable = bgDrawableMap.get(ButtonStyleHelper.KEY_ON_NORMAL);
                    stateListDrawable.addState(state, drawable);
                    break;
                }
                case KEY_ON_PRESS: {
                    int[] state = new int[]{android.R.attr.state_pressed};
                    Drawable drawable = bgDrawableMap.get(ButtonStyleHelper.KEY_ON_PRESS);
                    stateListDrawable.addState(state, drawable);
                    break;
                }
                case KEY_ON_DISABLE: {
                    int[] state = new int[]{-android.R.attr.state_enabled};
                    Drawable drawable = bgDrawableMap.get(ButtonStyleHelper.KEY_ON_DISABLE);
                    stateListDrawable.addState(state, drawable);
                    break;
                }
                default:
                    break;
            }
        }
        return stateListDrawable;
    }

    public static int pickButtonTextColor(Map<String, Object> style) {
        int color = Color.BLACK;
        if (style.containsKey(KEY_TEXT_COLOR)) {
            Object object = style.get(KEY_TEXT_COLOR);
            if (object instanceof String) {
                color = YogaAttrUtils.parseColor((String) object);
            }
        }
        return color;
    }

    public static ColorStateList makeButtonTextColorStateList(Map<Integer, Integer> textColorMap) {
        List<int[]> states = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        for (Integer key : textColorMap.keySet()) {
            switch (key) {
                case KEY_ON_NORMAL:
                    states.add(new int[]{-android.R.attr.state_pressed, android.R.attr.state_enabled});
                    colors.add(textColorMap.get(key));
                    break;
                case KEY_ON_PRESS:
                    states.add(new int[]{android.R.attr.state_pressed});
                    colors.add(textColorMap.get(key));
                    break;
                case KEY_ON_DISABLE:
                    states.add(new int[]{-android.R.attr.state_enabled});
                    colors.add(textColorMap.get(key));
                    break;
                default:
                    break;
            }
        }

        if (states.size() <= 0) {
            return null;
        }

        int[][] stateArray = new int[states.size()][];
        int[] colorArray = new int[colors.size()];
        for (int i = 0; i < colors.size(); i++) {
            colorArray[i] = colors.get(i);
        }

        return new ColorStateList(states.toArray(stateArray), colorArray);
    }
}
