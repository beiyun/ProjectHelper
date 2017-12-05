# ProjectHelper
Android工具库，节省开发效率,包含view、bitmap、File、App、等集中的处理方法


# use before
step 1: config gradle
    
    allprojects {
     	repositories {
     			...
     			maven { url 'https://www.jitpack.io' }
     		     }
     	        }
<code>	

    dependencies {
		...
		compile 'com.github.beiyun:ProjectHelper:1.1'
	         }

step 2: init ProjectHelper in Application

    public class MyApplication extends Application {

        @Override
        public void onCreate() {
            super.onCreate();
            ProjectHelper.init(this);
        }
     }

# Now all is ready
begin to use...
	
	
