package ex.sadisst.bakaholdem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CombinationsAdapter mAdapter;
    private ArrayList<Combination> mCombinations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int gridColumnCount = 1;
        mRecyclerView = findViewById(R.id.recyclerViewCombinations);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        mCombinations = new ArrayList<>();

        mAdapter = new CombinationsAdapter(this, mCombinations);
        mRecyclerView.setAdapter(mAdapter);

        initializeData();

        int swipeDirs = 0;//ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int dragDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                dragDirs, swipeDirs) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView,
                                  @NonNull @NotNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull @NotNull RecyclerView.ViewHolder target) {
                int from    = viewHolder.   getAdapterPosition();
                int to      = target.       getAdapterPosition();
                Collections.swap(mCombinations, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                mCombinations.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        helper.attachToRecyclerView(mRecyclerView);
    }

    private void initializeData() {
        String[] combinationsList = getResources().getStringArray(R.array.combinations);
        String[] combinationsInfo = getResources().getStringArray(R.array.combinationsInfo);
        String[] combinationsDetailInfo = getResources().getStringArray(R.array.combinationsDetailInfo);
        String[] combinationsExample    = getResources().getStringArray(R.array.combinationsExamples);

        mCombinations.clear();

        for (int i = 0; i < combinationsList.length; i++) {
            mCombinations.add(new Combination(combinationsList[i],
                    combinationsInfo[i], combinationsDetailInfo[i], combinationsExample[i]));//,
        }

        mAdapter.notifyDataSetChanged();
    }

    public void pickADeck(View view) {
        Intent intent = new Intent(this, CombinationsPicker.class);
        this.startActivity(intent);
    }
}