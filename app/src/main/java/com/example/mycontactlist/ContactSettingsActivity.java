package com.example.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.ToggleButton;

public class ContactSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_settings);

        initMapButton();
        initListButton();
        initSettingsButton();
        initSortByClick();
        initSortOrderClick();
        initSettings();
        initSortColor();


    }


    private void initListButton() {
        ImageButton ibList = (ImageButton)findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ContactSettingsActivity.this, ContactListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

        });
    }


    private void initMapButton() {
        ImageButton ibMap = (ImageButton)findViewById(R.id.imageButtonMap);
        ibMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ContactSettingsActivity.this, ContactMapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

        });
    }

    private void initSettingsButton() {
        ImageButton ibSettings = (ImageButton)findViewById(R.id.imageButtonSettings);
        ibSettings.setEnabled(false);
    }



    private void initSettings() {
        String sortBy = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortfield", "contactname");
        String sortOrder = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortorder", "ASC");
        String sortColor = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortcolor", "green");


        RadioButton rbName = (RadioButton) findViewById(R.id.radioName);
        RadioButton rbCity = (RadioButton) findViewById(R.id.radioCity);
        RadioButton rbBirthDay = (RadioButton) findViewById(R.id.radioBirthday);

        if (sortBy.equalsIgnoreCase("contactname")) {
            rbName.setChecked(true);
        } else if (sortBy.equalsIgnoreCase("city")) {
            rbCity.setChecked(true);
        } else {
            rbBirthDay.setChecked(true);
        }


        RadioButton rbAscending = (RadioButton) findViewById(R.id.radioAscending);
        RadioButton rbDescending = (RadioButton) findViewById(R.id.radioDescending);

        if (sortOrder.equalsIgnoreCase("ASC")) {
            rbAscending.setChecked(true);
        } else {
            rbDescending.setChecked(true);
        }



        final ScrollView ColorScrollView = (ScrollView) findViewById(R.id.ColorScrollView);

        RadioButton rbGreen = (RadioButton) findViewById(R.id.radioGreen);
        RadioButton rbBlue = (RadioButton) findViewById(R.id.radioBlue);
        RadioButton rbLilac = (RadioButton) findViewById(R.id.radioLilac);

        if (sortColor.equalsIgnoreCase("Green")) {
            rbGreen.setChecked(true);
            ColorScrollView.setBackgroundResource(R.color.background_color);

        } else if (sortColor.equalsIgnoreCase("Blue")){
            rbBlue.setChecked(true);
            ColorScrollView.setBackgroundResource(R.color.background_color1);
        } else {
            rbLilac.setChecked(true);
            ColorScrollView.setBackgroundResource(R.color.data_entry_color);
        }
    }


        private void initSortByClick() {
            RadioGroup rgSortBy = (RadioGroup) findViewById(R.id.radioGroupSortBy);
            rgSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup arg0, int arg1) {
                    RadioButton rbName = (RadioButton) findViewById(R.id.radioName);
                    RadioButton rbCity = (RadioButton) findViewById(R.id.radioCity);

                    if (rbName.isChecked()) {
                        getSharedPreferences("MyContactListPreferences",
                                Context.MODE_PRIVATE).edit()
                                .putString("sortfield", "contactname").commit();
                    }
                    else if (rbCity.isChecked()) {
                        getSharedPreferences("MyContactListPreferences",
                                Context.MODE_PRIVATE).edit()
                                .putString("sortfield", "city").commit();
                    } else {
                        getSharedPreferences("MyContactListPreferences",
                                Context.MODE_PRIVATE).edit()
                                .putString("sortfield", "birthday").commit();
                    }

                }
            });
        }

        private void initSortOrderClick() {
            RadioGroup rgSortOrder = (RadioGroup) findViewById(R.id.radioGroupSortOrder);
            rgSortOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


                @Override
                public void onCheckedChanged(RadioGroup arg0, int arg1) {
                    RadioButton rbAscending = (RadioButton) findViewById(R.id.radioAscending);
                    if (rbAscending.isChecked()) {
                        getSharedPreferences("MyContactListPreferences",
                                Context.MODE_PRIVATE).edit()
                                .putString("sortorder", "ASC").commit();
                    } else {
                        getSharedPreferences("MyContactListPreferences",
                                Context.MODE_PRIVATE).edit()
                                .putString("sortorder", "DESC").commit();
                    }
                }
            });
        }

    private void initSortColor() {
        RadioGroup rgSortColor = (RadioGroup) findViewById(R.id.radioGroupSortColor);
        rgSortColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            final ScrollView ColorScrollView = (ScrollView) findViewById(R.id.ColorScrollView);


            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rbGreen = (RadioButton) findViewById(R.id.radioGreen);
                RadioButton rbBlue = (RadioButton) findViewById(R.id.radioBlue);
                if (rbGreen.isChecked()) {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortcolor", "Green").commit();
                    ColorScrollView.setBackgroundResource(R.color.background_color);

                } else if (rbBlue.isChecked()) {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortcolor", "Blue").commit();
                    ColorScrollView.setBackgroundResource(R.color.background_color1);

                } else {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit()
                            .putString("sortcolor", "Lilac").commit();
                    ColorScrollView.setBackgroundResource(R.color.data_entry_color);
                }
            }
        });
    }



}
