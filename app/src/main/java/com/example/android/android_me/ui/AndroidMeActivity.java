
package com.example.android.android_me.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);
        if (savedInstanceState == null) {
            //Instancia de BodyPartFragement para o fragmento da cabeca
            BodyPartFragments headFragment = new BodyPartFragments();
            BodyPartFragments bodyFragment = new BodyPartFragments();
            BodyPartFragments legsFragment = new BodyPartFragments();

            int headIndex = getIntent().getIntExtra("headIndex", 0);
            headFragment.setListIndex(headIndex);

            int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
            bodyFragment.setListIndex(bodyIndex);

            int legIndex = getIntent().getIntExtra("legIndex", 0);
            legsFragment.setListIndex(legIndex);


            headFragment.setmImageIds(AndroidImageAssets.getHeads());
            headFragment.setListIndex(headIndex);

            bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setListIndex(bodyIndex);

            legsFragment.setmImageIds(AndroidImageAssets.getLegs());
            legsFragment.setListIndex(legIndex);


            //FragmentManager adiciona o fragmento a tela
            FragmentManager fragmentManager = getSupportFragmentManager();
            //Codigo de transacao
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .add(R.id.body_container, bodyFragment)
                    .add(R.id.legs_container, legsFragment)
                    .commit();
        }
    }

}
