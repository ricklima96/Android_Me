package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.util.Log;
import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taqtile on 11/09/17.
 */

public class BodyPartFragments extends Fragment {


    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";
    private static final String TAG = "BodyPartFragment";

    private List<Integer> mImageIds;
    private int mListIndex;


    //construtor obrigatório
    public BodyPartFragments(){

    }

    //quando o fragment é criado
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){

        if(savedInstaceState != null){
            mImageIds = savedInstaceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstaceState.getInt(LIST_INDEX);
        }



        //fragmento inflado
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        //imagem que vai aparecer neste fragmento
        // (linkando com o arquivo XML de um ImageVIew com o ID body_part_image_view)
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);
        if(mImageIds != null){
            imageView.setImageResource(mImageIds.get(mListIndex));
            imageView.setOnClickListener(new View.OnClickListener(){
             public void onClick(View view){
                if(mListIndex < mImageIds.size()-1){
                    mListIndex++;
                }else{
                    mListIndex = 0;
                }
                 imageView.setImageResource(mImageIds.get(mListIndex));
             }
         });




        }else{
            Log.v(TAG, "This fragment has a null list of image id");
        }

        //retorna a view
        return rootView;
    }



    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setListIndex(int index) {
        mListIndex = index;
    }
    //Bundle para salvar a combinacao atual
    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);
    }

}
