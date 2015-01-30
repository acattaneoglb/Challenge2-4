package co.mobilemakers.virtuozoo;

import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.EnumMap;


public class MainActivity extends ActionBarActivity {

    final static private String KEY_SHOWING = "KEY_SHOWING";

    AnimalPictureFragment mAnimalPictureFragment;
    AnimalDescriptionFragment mAnimalDescriptionFragment;

    Button mNextButton;

    EnumMap<AnimalEnum, String> animalNames;
    //EnumMap<AnimalEnum, Drawable> animalPictures; // Replaced due to excessive memory use
    EnumMap<AnimalEnum, Integer> animalPictures;
    EnumMap<AnimalEnum, String> animalHabitats;
    EnumMap<AnimalEnum, String> animalDescriptions;

    public enum AnimalEnum {
        BROWN_BEAR,
        GRIZZLY_BEAR,
        WOMBAT,
        GECKO,
        CORAL_SNAKE,
        TORTOISE
    }

    AnimalEnum animalShowing;

    private void initFragments() {
        mAnimalPictureFragment = new AnimalPictureFragment();
        mAnimalDescriptionFragment = new AnimalDescriptionFragment();

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentById(R.id.picture_container) != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.picture_container, mAnimalPictureFragment)
                    .commit();
        }
        else {
            fragmentManager.beginTransaction()
                    .add(R.id.picture_container, mAnimalPictureFragment)
                    .commit();
        }
        if (fragmentManager.findFragmentById(R.id.description_container) != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.description_container, mAnimalDescriptionFragment)
                    .commit();
        }
        else {
            fragmentManager.beginTransaction()
                    .add(R.id.description_container, mAnimalDescriptionFragment)
                    .commit();
        }
    }

    private void initAnimals() {
        animalNames = new EnumMap<>(AnimalEnum.class);

        animalNames.put(AnimalEnum.BROWN_BEAR, getResources().getString(R.string.brown_bear_name));
        animalNames.put(AnimalEnum.GRIZZLY_BEAR, getResources().getString(R.string.grizzly_bear_name));
        animalNames.put(AnimalEnum.WOMBAT, getResources().getString(R.string.wombat_name));
        animalNames.put(AnimalEnum.GECKO, getResources().getString(R.string.gecko_name));
        animalNames.put(AnimalEnum.CORAL_SNAKE, getResources().getString(R.string.coral_snake_name));
        animalNames.put(AnimalEnum.TORTOISE, getResources().getString(R.string.tortoise_name));

        animalPictures = new EnumMap<>(AnimalEnum.class);

        animalPictures.put(AnimalEnum.BROWN_BEAR, R.drawable.brown_bear);
        animalPictures.put(AnimalEnum.GRIZZLY_BEAR, R.drawable.grizzly_bear);
        animalPictures.put(AnimalEnum.WOMBAT, R.drawable.wombat);
        animalPictures.put(AnimalEnum.GECKO, R.drawable.gecko);
        animalPictures.put(AnimalEnum.CORAL_SNAKE, R.drawable.coral_snake);
        animalPictures.put(AnimalEnum.TORTOISE, R.drawable.tortoise);

        animalDescriptions = new EnumMap<>(AnimalEnum.class);

        animalDescriptions.put(AnimalEnum.BROWN_BEAR, getResources().getString(R.string.brown_bear_description));
        animalDescriptions.put(AnimalEnum.GRIZZLY_BEAR, getResources().getString(R.string.grizzly_bear_description));
        animalDescriptions.put(AnimalEnum.WOMBAT, getResources().getString(R.string.wombat_description));
        animalDescriptions.put(AnimalEnum.GECKO, getResources().getString(R.string.gecko_description));
        animalDescriptions.put(AnimalEnum.CORAL_SNAKE, getResources().getString(R.string.coral_snake_description));
        animalDescriptions.put(AnimalEnum.TORTOISE, getResources().getString(R.string.tortoise_description));

        animalHabitats = new EnumMap<>(AnimalEnum.class);

        animalHabitats.put(AnimalEnum.BROWN_BEAR, getResources().getString(R.string.brown_bear_habitat));
        animalHabitats.put(AnimalEnum.GRIZZLY_BEAR, getResources().getString(R.string.grizzly_bear_habitat));
        animalHabitats.put(AnimalEnum.WOMBAT, getResources().getString(R.string.wombat_habitat));
        animalHabitats.put(AnimalEnum.GECKO, getResources().getString(R.string.gecko_habitat));
        animalHabitats.put(AnimalEnum.CORAL_SNAKE, getResources().getString(R.string.coral_snake_habitat));
        animalHabitats.put(AnimalEnum.TORTOISE, getResources().getString(R.string.tortoise_habitat));

        animalShowing = AnimalEnum.BROWN_BEAR;
    }

    public void showAnimalImage() {
        if (animalShowing != null) {
            mAnimalPictureFragment.setPicture(
                    getResources().getDrawable(animalPictures.get(animalShowing)));
            mAnimalPictureFragment.setName(animalNames.get(animalShowing));
        }
    }

    public void showAnimalDescription() {
        if (animalShowing != null) {
            mAnimalDescriptionFragment.setHabitat(animalHabitats.get(animalShowing));
            mAnimalDescriptionFragment.setDescription(animalDescriptions.get(animalShowing));
        }
    }

    public void showAnimal() {
        showAnimalImage();
        showAnimalDescription();
    }

    protected void nextAnimal() {
        int nextIndex = animalShowing.ordinal()+1;
        if (nextIndex >= AnimalEnum.values().length) {
            nextIndex = 0;
        }

        animalShowing = AnimalEnum.values()[nextIndex];

        showAnimal();
    }

    protected void initNextButton() {
        mNextButton = (Button)findViewById(R.id.button_next);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAnimal();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();

        initAnimals();

        initNextButton();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_SHOWING, animalShowing.name());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        animalShowing = AnimalEnum.valueOf(savedInstanceState.getString(KEY_SHOWING));
        showAnimal();
    }
}
