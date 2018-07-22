/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

// This activity is responsible for displaying the master list of all images
// Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private int headPos;
    private int bodyPos;
    private int legPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headPos = 0;
        bodyPos = 0;
        legPos = 0;
    }

    // Define the behavior for onImageSelected
    public void onImageSelected(int position) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        // COMPLETE (2) Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments
        switch (position / 12) {
            case 0:
                headPos = position % 12;
                break;
            case 1:
                bodyPos = position % 12;
                break;
            case 2:
                legPos = position % 12;
                break;
            default:
                Log.e(TAG, "Clicked image should not exist...");
                break;
        }

        // COMPLETED (3) Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
        Bundle bundle = new Bundle();
        bundle.putInt("headPos", headPos);
        bundle.putInt("bodyPos", bodyPos);
        bundle.putInt("legPos", legPos);

        // COMPLETED (4) Get a reference to the "Next" button and launch the intent when this button is clicked
        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(bundle);

        Button button = (Button) findViewById(R.id.button_view_android);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

}
