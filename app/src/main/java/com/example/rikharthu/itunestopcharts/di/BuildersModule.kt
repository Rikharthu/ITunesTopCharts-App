package com.example.rikharthu.itunestopcharts.di

import com.example.rikharthu.itunestopcharts.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Defines bindings for sub-components so that Dagger can inject them
 */
@Module
abstract class BuildersModule {
    /*
    Note that @ContributesAndroidInjector annotation (introduced in 2.11) frees us
    from having to create separate components annotated with @Subcomponent
     */

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    /*
    @ContributesAndroidInjector(modules = arrayOf(LobbyFragmentModule::class))
    internal abstract// or gain access to lobby activity dependencies from fragment via
            // @ContributesAndroidInjector(modules = {LobbyFragmentModule.class, LobbyActivityModule.class})
    fun bindLobbyFragment(): LobbyFragment
    */
    // Add bindings for other sub-components here
}