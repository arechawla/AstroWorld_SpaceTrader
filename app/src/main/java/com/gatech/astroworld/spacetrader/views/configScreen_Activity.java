package com.gatech.astroworld.spacetrader.views;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.entity.Difficulty;
import android.widget.EditText;
import com.gatech.astroworld.spacetrader.model.Player;
import com.gatech.astroworld.spacetrader.viewmodels.Configuration_viewmodel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class configScreen_Activity extends AppCompatActivity {

    //Reference to viewModel
    private Configuration_viewmodel viewmodel;

    private EditText nameField;
    private Spinner difficultySpinner;
    private Button confirmButton;
    private SeekBar pilotPoints;
    private SeekBar traderPoints;
    private SeekBar fighterPoints;
    private SeekBar engineerPoints;
    private TextView pilotCounter;
    private TextView traderCounter;
    private TextView fighterCounter;
    private TextView engineerCounter;
    private TextView remainingPointsCounter;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference difficultyRef = mRootRef.child("difficulty");
    DatabaseReference distOfPointsRef = mRootRef.child("player").child("distOfPoints");


    //Setting up new game variables.
    private int maxStartPoints = 16;
    private int totalPoints = 0;
    private Difficulty difficulty;
    //FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Init activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen_);
        //Init UI components
        nameField = findViewById(R.id.player_name_input);
        difficultySpinner = findViewById(R.id.difficulty_spinner);
        confirmButton = findViewById(R.id.confirm_button);
        pilotPoints = findViewById(R.id.pilotBar);
        traderPoints = findViewById(R.id.traderBar);
        fighterPoints = findViewById(R.id.fighterBar);
        engineerPoints = findViewById(R.id.engineerBar);
        pilotCounter = findViewById(R.id.pilotCounter);
        traderCounter = findViewById(R.id.traderCounter);
        fighterCounter = findViewById(R.id.fighterCounter);
        engineerCounter = findViewById(R.id.engineerCounter);
        remainingPointsCounter = findViewById(R.id.remainingPointsCounter);

        //Predetermined Vars
        remainingPointsCounter.setText("16");
        difficulty = Difficulty.BEGINNER;


        //Creates SeekBar listener
        SeekBar.OnSeekBarChangeListener barChangeListener = new SeekBar.OnSeekBarChangeListener() {

            //Method is called when the barChangeListener is changed
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                totalPoints = totalPoints();
                remainingPointsCounter.setText(""+ (maxStartPoints - totalPoints));
                if (totalPoints <= maxStartPoints) {
                    if (seekBar.equals(pilotPoints)) {
                        pilotCounter.setText("" + progress);
                    }
                    if (seekBar.equals(traderPoints)) {
                        traderCounter.setText("" + progress);
                    }
                    if (seekBar.equals(fighterPoints)) {
                        fighterCounter.setText("" + progress);
                    }
                    if (seekBar.equals(engineerPoints)) {
                        engineerCounter.setText("" + progress);
                    }
                }
                pilotPoints.setMax(maxStartPoints - (totalPoints - pilotPoints.getProgress()));
                traderPoints.setMax(maxStartPoints - (totalPoints - traderPoints.getProgress()));
                fighterPoints.setMax(maxStartPoints - (totalPoints - fighterPoints.getProgress()));
                engineerPoints.setMax(maxStartPoints - (totalPoints - engineerPoints.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        //Assign listeners to each SeekBar
        pilotPoints.setOnSeekBarChangeListener(barChangeListener);
        traderPoints.setOnSeekBarChangeListener(barChangeListener);
        fighterPoints.setOnSeekBarChangeListener(barChangeListener);
        engineerPoints.setOnSeekBarChangeListener(barChangeListener);

        //Create spinner adapter for the difficulty spinner
        ArrayAdapter<Difficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Difficulty.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);


        //Character Configuration screen toast errors.
        final Toast error1 = Toast.makeText(getApplicationContext(), "You need at least 16 points.", Toast.LENGTH_SHORT);
        final Toast error2 = Toast.makeText(getApplicationContext(), "Please name your character.", Toast.LENGTH_SHORT);
        final Toast error3 = Toast.makeText(getApplicationContext(), "Character name must be less than 16 characters.", Toast.LENGTH_SHORT);

        //Creates listener for confirm button
        confirmButton.setOnClickListener(new View.OnClickListener() {
            //Method runs when confirm button is clicked
            public void onClick(View v) {
                //Check for input errors
                if (totalPoints < maxStartPoints) {
                    error1.show();
                    return;
                }
                if (nameField.getText().toString().matches("")) {
                    error2.show();
                    return;
                }
                if (nameField.getText().toString().length() >= 16) {
                    error3.show();
                    return;
                }

                Player newPlayer = initPlayer();

                //Set chosen game difficulty
                difficulty = (Difficulty) difficultySpinner.getSelectedItem();
                //Update game Singleton with new difficulty
                viewmodel.updateGame(difficulty);
                //Update game Singleton with new Player
                viewmodel.updatePlayer(newPlayer);


                difficultyRef.setValue(difficulty.toString());

                distOfPointsRef.child("engineerPoints").setValue(engineerPoints.getProgress());
                distOfPointsRef.child("pilotPoints").setValue(pilotPoints.getProgress());
                distOfPointsRef.child("traderPoints").setValue(traderPoints.getProgress());
                distOfPointsRef.child("fighterPoints").setValue(fighterPoints.getProgress());


                Intent i = new Intent(getApplicationContext(), playerReviewScreen_Activity.class);
                startActivity(i);
            }
        });

        //Sets the viewmodel fragment up
        viewmodel = ViewModelProviders.of(this).get(Configuration_viewmodel.class);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        difficultyRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String dif = dataSnapshot.getValue(String.class);
//                difficulty = Difficulty.valueOf(dif);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//    }

    //Creates a new player object and populates with provided data
    private Player initPlayer () {
        Player newPlayer = new Player(nameField.getText().toString());
        newPlayer.setPilotPoints(pilotPoints.getProgress());
        newPlayer.setTraderPoints(traderPoints.getProgress());
        newPlayer.setFighterPoints(fighterPoints.getProgress());
        newPlayer.setEngineerPoints(engineerPoints.getProgress());

        return newPlayer;
    }

    //Sum of all player points
    private int totalPoints() {
        int sum = pilotPoints.getProgress()
                 + engineerPoints.getProgress()
                 + traderPoints.getProgress()
                 + fighterPoints.getProgress();
        return sum;
    }
}
