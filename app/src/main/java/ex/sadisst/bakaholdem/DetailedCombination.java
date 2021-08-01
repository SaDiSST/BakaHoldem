package ex.sadisst.bakaholdem;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class DetailedCombination extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        TextView combinationTitle = findViewById(R.id.combinationDetailedTitle);
        TextView combinationDescr = findViewById(R.id.detailedItemText);
        TextView combinationExample = findViewById(R.id.combinationExample);

        combinationTitle.setText(getIntent().getStringExtra("title"));
        combinationDescr.setText(getIntent().getStringExtra("descr"));
        combinationExample.setText(getIntent().getStringExtra("example"));
    }
}
