package csmz.storm.zhu.utils;

import android.graphics.Color;
import android.view.View;

import com.balysv.materialripple.MaterialRippleLayout;

public class ClickEffectUtil {
	public static void set(View v) {
		MaterialRippleLayout.on(v).rippleColor(Color.parseColor("#777777"))
				.rippleAlpha(0.2f).rippleHover(true).rippleOverlay(true)
				.create();
	}
}
