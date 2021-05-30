package lu.die.slimhooktest;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.swift.sandhook.SandHookConfig;
import com.swift.sandhook.xposedcompat.XposedCompat;
import com.swift.sandhook.xposedcompat.classloaders.ProxyClassLoader;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.File;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import lu.die.slimhooktest.databinding.ActivityMainBinding;
import lu.die.xposedcompat.HookMode;

public class MainActivity extends AppCompatActivity {
    private static String getHookModeStr()
    {
        if(HookMode.currentHookMode.equals(HookMode.HOOKMODE_SANDHOOK))
            return "SandHook";
        if(HookMode.currentHookMode.equals(HookMode.HOOKMODE_SLIMHOOK))
            return "SlimHook";
        if(HookMode.currentHookMode.equals(HookMode.HOOKMODE_EPIC))
            return "Epic";
        if(HookMode.currentHookMode.equals(HookMode.HOOKMODE_PINE))
            return "Pine";
        return "";
    }

    private static String testHookMethod()
    {
        return "尚未挂钩本函数，当前Hook模式是 "+getHookModeStr();
    }

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private void testHookEnforce()
    {
        try {
            SandHookConfig.DEBUG = BuildConfig.DEBUG;
            SandHookConfig.delayHook = false;
            XposedCompat.cacheDir = new File(getCacheDir(), "xxx");
            XposedCompat.classLoader = new ProxyClassLoader(XposedBridge.class.getClassLoader(), this.getClassLoader());
            XposedCompat.context = this;

            XposedHelpers.findAndHookMethod(this.getClass(), "testHookMethod",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            // you can change method here
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            param.setResult("成功挂钩方法，当前Hook模式是 "+getHookModeStr());
                        }
                    });
        }catch (Throwable t)
        {
            t.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        testHookEnforce();

        TextView textView = findViewById(R.id.textView_Main);
        textView.setText(testHookMethod());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return false;
    }
}