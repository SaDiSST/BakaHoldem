package ex.sadisst.bakaholdem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

public class FragmentCombinations extends Fragment {
    private RecyclerView mRecyclerView;
    private CombinationsAdapter mAdapter;
    private ArrayList<Combination> mCombinations;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCombinations = new ArrayList<>();
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

    public FragmentCombinations() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_combinations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int gridColumnCount = 1;

        mRecyclerView = getActivity().findViewById(R.id.recyclerViewCombinations);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), gridColumnCount));

        mAdapter = new CombinationsAdapter(getContext(), mCombinations);
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
}