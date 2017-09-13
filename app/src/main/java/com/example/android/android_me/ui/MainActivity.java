package com.example.android.android_me.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

// This activity is responsible for displaying the master list of all images
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {


    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean mTwoPane;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPane = true;
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

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


        } else {
            mTwoPane = false;
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Posicao: " + position, Toast.LENGTH_SHORT).show();

        int bodyPartNumber = position / 12;

        int listIndex = position - 12 * bodyPartNumber;

        if (mTwoPane) {
            BodyPartFragments newFragment = new BodyPartFragments();

            switch (bodyPartNumber) {
                case 0:
                    newFragment.setmImageIds((AndroidImageAssets.getHeads()));
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.head_container, newFragment).commit();
                    break;
                case 1:
                    newFragment.setmImageIds((AndroidImageAssets.getBodies()));
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_container, newFragment).commit();
                    break;
                case 2:
                    newFragment.setmImageIds((AndroidImageAssets.getLegs()));
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.legs_container, newFragment).commit();
                    break;
            }
        } else {
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;

            }
        }


        Bundle b = new Bundle();
        b.putInt("headIndex",headIndex);
        b.putInt("bodyIndex",bodyIndex);
        b.putInt("legIndex",legIndex);

        // Attach the Bundle to an intent
        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        // The "Next" button launches a new AndroidMeActivity
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                startActivity(intent);
            }
        });
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
