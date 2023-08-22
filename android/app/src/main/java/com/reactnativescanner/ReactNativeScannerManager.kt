package com.reactnativescanner;
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager

class ReactNativeScannerManager<ThemedReactContext>(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    private lateinit var scanView: ReactNativeScannerView
    private lateinit var context: ThemedReactContext

    fun createViewInstance(reactContext: ThemedReactContext): ReactNativeScannerView {
        context = reactContext
        scanView = ReactNativeScannerView(reactContext, null)
        scanView.scanViewListener = this
        return scanView
    }
    
    override fun getName(): String {
        return "RNScanner";
    }

}

class ReactNativeScannerPackage : ReactPackage {
    override fun createNativeModules(reactContext: ReactApplicationContext): MutableList<NativeModule> {
        return mutableListOf()
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): MutableList<ViewManager<out View, out ReactShadowNode<*>>> {
        return mutableListOf(ReactNativeScannerManager(reactContext));
    }
}

class ReactNativeScannerView(context: Context?, attributeSet: AttributeSet?) : RelativeLayout(context, attributeSet) {}