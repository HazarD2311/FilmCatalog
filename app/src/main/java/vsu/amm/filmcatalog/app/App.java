package vsu.amm.filmcatalog.app;

import android.app.Application;

import vsu.amm.filmcatalog.app.module.ContextModule;
import vsu.amm.filmcatalog.app.module.HttpModule;

public class App extends Application {

    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    public void initAppComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .contextModule(new ContextModule(this))
                .httpModule(new HttpModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
