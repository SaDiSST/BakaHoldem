package ex.sadisst.bakaholdem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

class CombinationsAdapter extends RecyclerView.Adapter<CombinationsAdapter.ViewHolder> {
    private ArrayList<Combination> mCombinationData;
    private Context mContext;

    CombinationsAdapter(Context context, ArrayList<Combination> combinationData){
        this.mCombinationData   = combinationData;
        this.mContext           = context;
    }

    @NonNull
    @Override
    public CombinationsAdapter.ViewHolder onCreateViewHolder(
            @NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(
            @NonNull @org.jetbrains.annotations.NotNull CombinationsAdapter.ViewHolder holder,
            int position) {
        Combination currCombination = mCombinationData.get(position);
        holder.bindTo(currCombination);
    }

    @Override
    public int getItemCount() {
        return mCombinationData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mExample;
//        private ImageView mCombinationImage;

        ViewHolder(View itemView){
            super(itemView);

            mTitleText  = itemView.findViewById(R.id.itemTitle);
            mInfoText   = itemView.findViewById(R.id.combinationDescription);
            mExample    = itemView.findViewById(R.id.combinationExample);
//            mCombinationImage   = itemView.findViewById(R.id.detailedItemImage);

            itemView.setOnClickListener(this);
        }

        void bindTo(Combination currentCombination) {
            mTitleText.setText(currentCombination.getTitle());
            mInfoText.setText(currentCombination.getInfo());
            mExample.setText(currentCombination.getCombinationExample());

//            Glide
        }

        @Override
        public void onClick(View view) {
            Combination currentCombination = mCombinationData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailedCombination.class);
            detailIntent.putExtra("title", currentCombination.getTitle());
            detailIntent.putExtra("descr", currentCombination.getDetailInfo());
            detailIntent.putExtra("example", currentCombination.getCombinationExample());

            mContext.startActivity(detailIntent);
        }
    }
}
