package co.mobilemakers.virtuozoo;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimalPictureFragment extends Fragment {

    ImageView mAnimalPicture;
    TextView mAnimalName;

    public AnimalPictureFragment() {
        // Required empty public constructor
    }

    public void setPicture(Drawable picture) {
        if (mAnimalPicture != null)
            mAnimalPicture.setImageDrawable(picture);
    }

    public void setName(String name) {
        if (mAnimalName != null)
            mAnimalName.setText(name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_animal_picture, container, false);

        mAnimalPicture = (ImageView)view.findViewById(R.id.image_animal);
        mAnimalName = (TextView)view.findViewById(R.id.text_animal_name);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MainActivity)getActivity()).showAnimalImage();
    }
}
