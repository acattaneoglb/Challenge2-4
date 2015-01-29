package co.mobilemakers.virtuozoo;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimalDescriptionFragment extends Fragment {

    TextView mAnimalHabitat;
    TextView mAnimalDescription;

    public void setHabitat(String habitat) {
        if (mAnimalHabitat != null)
            mAnimalHabitat.setText(habitat);
    }

    public void setDescription(String description) {
        if (mAnimalDescription != null)
            mAnimalDescription.setText(description);
    }

    public AnimalDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_animal_description, container, false);

        mAnimalHabitat = (TextView)view.findViewById(R.id.text_animal_habitat);
        mAnimalDescription = (TextView)view.findViewById(R.id.text_animal_description);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MainActivity)getActivity()).showAnimalDescription();
    }
}
