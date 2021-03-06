package tw.edu.fcu.vegetablemaster.firebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Red on 2017/6/13.
 */

public class FirebaseFunction {

    private static final String TAG = "FirebaseFunction";

    public void getTodayPrice() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("vegetables/today");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<Vegetable> list = new ArrayList<Vegetable>();
                for(DataSnapshot vegSnapshot: dataSnapshot.getChildren()) {
                    list.add(vegSnapshot.getValue(Vegetable.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

}
