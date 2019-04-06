package com.aman.dagger2playground.MainActivityFeature;


import com.aman.dagger2playground.MainActivity;
import com.aman.dagger2playground.component.RandomUserComponent;

import dagger.Component;

@Component(modules = MainActivityModule.class, dependencies = RandomUserComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);

}
