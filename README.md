# ProjectHelper
Android工具库，节省开发效率,包含view、bitmap、File、App、等集中的处理方法


# How to use
# step1

<code>

    allprojects {
     	
     	repositories {
     			...
     			maven { url 'https://www.jitpack.io' }
     		}
     	}
	
</code>	
<code>
	
	 dependencies {
		...
		compile 'com.github.beiyun:ProjectHelper:1.0.4'
	    }
</code>	

# step2

    public class MyApplication extends Application {

        @Override
        public void onCreate() {
            super.onCreate();
            ProjectHelper.init(this);
        }
     }

	
	
