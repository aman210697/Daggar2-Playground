package com.aman.dagger2playground.component;


import com.aman.dagger2playground.interfaces.RandomUserApplicationScope;
import com.aman.dagger2playground.interfaces.RandomUsersApi;
import com.aman.dagger2playground.module.PicassoModule;
import com.aman.dagger2playground.module.RandomUsersModule;
import com.squareup.picasso.Picasso;

import dagger.Component;


@RandomUserApplicationScope
@Component(modules = {RandomUsersModule.class, PicassoModule.class})
public interface RandomUserComponent {

    RandomUsersApi getRandomUserService();

    Picasso getPicasso();
}
