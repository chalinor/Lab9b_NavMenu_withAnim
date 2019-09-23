package com.example.lab9b_navmenu_withanim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;


import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Step 5 -
    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        //Add a list of menu items
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("News", R.mipmap.ic_launcher));
        menuItems.add(new MenuItem("Chat", R.mipmap.ic_launcher));
        menuItems.add(new MenuItem("Messages", R.mipmap.ic_launcher));
        menuItems.add(new MenuItem("Profile", R.mipmap.ic_launcher));
        menuItems.add(new MenuItem("Music", R.mipmap.ic_launcher));

        sNavigationDrawer.setMenuItemList(menuItems);

        fragmentClass = NewsFragment.class; //then create this fragment and its layout

        try {
            fragment = (Fragment) fragmentClass.newInstance(); //add this line after creating the news class and layout
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


        //after completing the above, add this
        if (fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                    android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }

        //then write this
        sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
            @Override
            public void onDrawerOpening() {

            }

            @Override
            public void onDrawerClosing() {
                //modify this code
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

                //after the above code, copy the code below from the if condition
                if (fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                            android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
                }

            }

            @Override
            public void onDrawerOpened() {
                  //no need to put anything here as we won't be using this method in this lab
            }

            @Override
            public void onDrawerClosed() {
                 //no need to put anything here as we won't be using this method in this lab
            }

            @Override
            public void onDrawerStateChanged(int newState) {
               //no need to put anything here as we won't be using this method in this lab      
            }
        });
    }
}
