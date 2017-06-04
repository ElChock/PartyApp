package com.example.enriquebecerram.partyapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by Ayrton on 30/04/2017.
 */

public class fragmetnFacebook  extends FragmentActivity {
    CallbackManager callbackManager;
    LoginButton loginButton ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
      //  LoginButton loginButton = (LoginButton) view.findViewById(R.id.usersettings_fragment_login_button);
     //   loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
       //     ...
       // };
    }

}