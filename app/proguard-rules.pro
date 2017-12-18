# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\luweibin\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5                                                                   #指定代码压缩级别
-dontusemixedcaseclassnames                                                             #指定是否大小写混合
-dontskipnonpubliclibraryclasses                                                        #是否混淆第三方jar
-dontpreverify                                                                          #混淆时是否预检验
-ignorewarnings                                                                         #忽略警告，避免打包时某些警告出现
-verbose                                                                                #混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*                #混淆采用的算法


-keep public class * extends android.app.Activity                                       #保持哪些类不被混淆
-keep public class * extends android.app.Application                                    #保持哪些类不被混淆
-keep public class * extends android.app.Service                                        #保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver                          #保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider                            #保持哪些类不被混淆
-keep public class com.android.vending.licensing.ILicensingService                      #保持哪些类不被混淆
-keep public class * extends android.support.v4.app.Fragment
-keep public class * implements java.lang.Cloneable

-keepclasseswithmembernames class * {                                                   #保持native方法不被混淆
    native <methods>;
}
-keepclasseswithmembernames class * {                                                   #保持自定义控件不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembernames class * {                                                   #保持自定义控件不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers enum * {                                                              #保持menu类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {                                        #保持Parcelable不被混淆
  public static final android.os.Parcelable$Creator *;
}




-keep class freemarker.**{*;}
-keep class in.srain.cube.views.ptr.**{*;}
-keep class android.support.annotation.**{*;}
-keep class com.squareup.picasso.**{*;}

-keep class de.greenrobot.dao.**{*;}
-keep class de.greenrobot.daogenerator.**{*;}
-keep class com.google.gson.**{*;}
-keep class com.squareup.picasso.**{*;}

-dontwarn  freemarker.**
-dontwarn  com.squareup.picasso.**

-keep class org.eclipse.mat.** { *; }
-keep class com.squareup.leakcanary.** { *; }

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# Gson specific classes
-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }
##---------------End: proguard configuration for Gson  ----------


-keep class rx.**{*;}
-keep class rx.android.**{*;}
-keep class butterknife.**{*;}
-keep class okhttp3.**{*;}
-keep class okio.**{*;}
-keep class retrofit2.**{*;}
-keep class retrofit2.adapter.rxjava.**{*;}
-keep class retrofit2.converter.gson.**{*;}
-keep class com.jcodecraeer.xrecyclerview.**{*;}
-keep class android.net.**{*;}
-keep class com.android.internal.http.multipart.**{*;}
-keep class org.apache.**{*;}
-keep class org.apache.http.conn.**{*;}
-keep class retrofit2.** {*;}

-dontwarn okhttp3**
-dontwarn retrofit2.**
-dontwarn rx.internal.util.**
-dontwarn okio.**
-dontwarn org.apache.**
-dontwarn com.alipay.android.phone.mrpc.core.**


-keepattributes Signature
-keepattributes Exceptions
-keepattributes InnerClasses

-keepattributes EnclosingMetho

-keep class cn..galleryfinal.widget.zoonview.*{*;}

# 百度地图
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**

#Novate 网络框架
-keep class com.tamic.novate.** {*;}