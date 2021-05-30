![SlimHook]()
##SlimHook Xposed Compat是什么?
Xposed compatibility for SlimHook and SandHook

-----

Both SlimHook and SandHook can use this Xposed Compat framework for method hooking. Even more!

-----

* You can use Epic or Pine for method hooking, just import this project and change 
     
     lu.die.xposedcompat.HookMode
     

##SlimHook Xposed Compat有哪些功能？

* Xposed API Compatibility
* Android 5.0 - 12 Support
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

##有问题反馈
在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流

* 邮件(1#die.lu, 把#换成@)
* Web: [Click Here](http://www.die.lu)