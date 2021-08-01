package ex.sadisst.bakaholdem;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.LinkedList;
import java.util.Objects;

public class CombinationsPicker extends AppCompatActivity {
    private static final String[] ranks =
            {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
    private LinkedList<String> combination = new LinkedList<>(), sortedHand = new LinkedList<>();

    private static SharedPreferences mPrefs;
    private static String sharedPrefsFile;

    private Spinner playerCard1;
    private Spinner playerCardSuit1;
    private Spinner playerCard2;
    private Spinner playerCardSuit2;
    private Spinner deckCard1;
    private Spinner deckCardSuit1;
    private Spinner deckCard2;
    private Spinner deckCardSuit2;
    private Spinner deckCard3;
    private Spinner deckCardSuit3;
    private Spinner deckCard4;
    private Spinner deckCardSuit4;
    private Spinner deckCard5;
    private Spinner deckCardSuit5;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combination_picker);

        sharedPrefsFile = getPackageName();
        initSpinners();
        mPrefs = getSharedPreferences(sharedPrefsFile, MODE_PRIVATE);

        playerCard1     .setSelection(mPrefs.getInt("pCard1", 0));
        playerCard2     .setSelection(mPrefs.getInt("pCard2", 0));
        deckCard1       .setSelection(mPrefs.getInt("dCard1", 0));
        deckCard2       .setSelection(mPrefs.getInt("dCard2", 0));
        deckCard3       .setSelection(mPrefs.getInt("dCard3", 0));
        deckCard4       .setSelection(mPrefs.getInt("dCard4", 0));
        deckCard5       .setSelection(mPrefs.getInt("dCard5", 0));

        playerCardSuit1 .setSelection(mPrefs.getInt("pCard1Suit", 0));
        playerCardSuit2 .setSelection(mPrefs.getInt("pCard2Suit", 0));
        deckCardSuit1   .setSelection(mPrefs.getInt("dCard1Suit", 0));
        deckCardSuit2   .setSelection(mPrefs.getInt("dCard2Suit", 0));
        deckCardSuit3   .setSelection(mPrefs.getInt("dCard3Suit", 0));
        deckCardSuit4   .setSelection(mPrefs.getInt("dCard4Suit", 0));
        deckCardSuit5   .setSelection(mPrefs.getInt("dCard5Suit", 0));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor prefEditor = mPrefs.edit();
        prefEditor.putInt("pCard1",      playerCard1.getSelectedItemPosition());
        prefEditor.putInt("pCard2",      playerCard2.getSelectedItemPosition());
        prefEditor.putInt("dCard1",      deckCard1  .getSelectedItemPosition());
        prefEditor.putInt("dCard2",      deckCard2  .getSelectedItemPosition());
        prefEditor.putInt("dCard3",      deckCard3  .getSelectedItemPosition());
        prefEditor.putInt("dCard4",      deckCard4  .getSelectedItemPosition());
        prefEditor.putInt("dCard5",      deckCard5  .getSelectedItemPosition());

        prefEditor.putInt("pCard1Suit",  playerCardSuit1.getSelectedItemPosition());
        prefEditor.putInt("pCard2Suit",  playerCardSuit2.getSelectedItemPosition());
        prefEditor.putInt("dCard1Suit",  deckCardSuit1  .getSelectedItemPosition());
        prefEditor.putInt("dCard2Suit",  deckCardSuit2  .getSelectedItemPosition());
        prefEditor.putInt("dCard3Suit",  deckCardSuit3  .getSelectedItemPosition());
        prefEditor.putInt("dCard4Suit",  deckCardSuit4  .getSelectedItemPosition());
        prefEditor.putInt("dCard5Suit",  deckCardSuit5  .getSelectedItemPosition());

        prefEditor.apply();
    }

    private void initSpinners() {
        AdapterView.OnItemSelectedListener listener =  new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                findHighestCombination();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };

        playerCardSuit1 = findViewById(R.id.playerCardSuit1);
        playerCardSuit2 = findViewById(R.id.playerCardSuit2);
        deckCardSuit1   = findViewById(R.id.deckCardSuit1);
        deckCardSuit2   = findViewById(R.id.deckCardSuit2);
        deckCardSuit3   = findViewById(R.id.deckCardSuit3);
        deckCardSuit4   = findViewById(R.id.deckCardSuit4);
        deckCardSuit5   = findViewById(R.id.deckCardSuit5);

        ArrayAdapter<CharSequence> suitsAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.card_suits,
                        android.R.layout.simple_spinner_item);
        suitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        if (playerCardSuit1 != null) {
            playerCardSuit1.setAdapter(suitsAdapter);
            playerCardSuit1.setOnItemSelectedListener(listener);
        }

        if(playerCardSuit2 != null) {
            playerCardSuit2.setAdapter(suitsAdapter);
            playerCardSuit2.setOnItemSelectedListener(listener);
        }

        if(deckCardSuit1 != null) {
            deckCardSuit1.setAdapter(suitsAdapter);
            deckCardSuit1.setOnItemSelectedListener(listener);
        }

        if(deckCardSuit2 != null) {
            deckCardSuit2.setAdapter(suitsAdapter);
            deckCardSuit2.setOnItemSelectedListener(listener);
        }

        if(deckCardSuit3 != null) {
            deckCardSuit3.setAdapter(suitsAdapter);
            deckCardSuit3.setOnItemSelectedListener(listener);
        }

        if(deckCardSuit4 != null) {
            deckCardSuit4.setAdapter(suitsAdapter);
            deckCardSuit4.setOnItemSelectedListener(listener);
        }

        if(deckCardSuit5 != null) {
            deckCardSuit5.setAdapter(suitsAdapter);
            deckCardSuit5.setOnItemSelectedListener(listener);
        }

        playerCard1 = findViewById(R.id.playerCard1);
        playerCard2 = findViewById(R.id.playerCard2);
        deckCard1   = findViewById(R.id.deckCard1);
        deckCard2   = findViewById(R.id.deckCard2);
        deckCard3   = findViewById(R.id.deckCard3);
        deckCard4   = findViewById(R.id.deckCard4);
        deckCard5   = findViewById(R.id.deckCard5);

        ArrayAdapter<CharSequence> kindsAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.card_values,
                        android.R.layout.simple_spinner_item);
        kindsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (playerCard1 != null) {
            playerCard1.setAdapter(kindsAdapter);
            playerCard1.setOnItemSelectedListener(listener);
        }
        if (playerCard2 != null) {
            playerCard2.setAdapter(kindsAdapter);
            playerCard2.setOnItemSelectedListener(listener);
        }
        if (deckCard1 != null) {
            deckCard1.setAdapter(kindsAdapter);
            deckCard1.setOnItemSelectedListener(listener);
        }
        if (deckCard2 != null) {
            deckCard2.setAdapter(kindsAdapter);
            deckCard2.setOnItemSelectedListener(listener);
        }
        if (deckCard3 != null) {
            deckCard3.setAdapter(kindsAdapter);
            deckCard3.setOnItemSelectedListener(listener);
        }
        if (deckCard4 != null) {
            deckCard4.setAdapter(kindsAdapter);
            deckCard4.setOnItemSelectedListener(listener);
        }
        if (deckCard5 != null) {
            deckCard5.setAdapter(kindsAdapter);
            deckCard5.setOnItemSelectedListener(listener);
        }
    }

    private void findHighestCombination() {
        combination.clear();

        //region gettingKindsAndSuits
        String[] kinds = new String[7], suits = new String[7];
        int i = 0;
        playerCard1     = (Spinner) findViewById(R.id.playerCard1);
        playerCardSuit1 = (Spinner) findViewById(R.id.playerCardSuit1);
        kinds[i] = playerCard1.getSelectedItem().toString();
        suits[i] = playerCardSuit1.getSelectedItem().toString();
        i++;

        playerCard2     = (Spinner) findViewById(R.id.playerCard2);
        playerCardSuit2 = (Spinner) findViewById(R.id.playerCardSuit2);
        kinds[i] = playerCard2.getSelectedItem().toString();
        suits[i] = playerCardSuit2.getSelectedItem().toString();
        i++;

        deckCard1       = (Spinner) findViewById(R.id.deckCard1);
        deckCardSuit1   = (Spinner) findViewById(R.id.deckCardSuit1);
        kinds[i] = deckCard1.getSelectedItem().toString();
        suits[i] = deckCardSuit1.getSelectedItem().toString();
        i++;

        deckCard2       = (Spinner) findViewById(R.id.deckCard2);
        deckCardSuit2   = (Spinner) findViewById(R.id.deckCardSuit2);
        kinds[i] = deckCard2.getSelectedItem().toString();
        suits[i] = deckCardSuit2.getSelectedItem().toString();
        i++;

        deckCard3       = (Spinner) findViewById(R.id.deckCard3);
        deckCardSuit3   = (Spinner) findViewById(R.id.deckCardSuit3);
        kinds[i] = deckCard3.getSelectedItem().toString();
        suits[i] = deckCardSuit3.getSelectedItem().toString();
        i++;

        deckCard4       = (Spinner) findViewById(R.id.deckCard4);
        deckCardSuit4   = (Spinner) findViewById(R.id.deckCardSuit4);
        kinds[i] = deckCard4.getSelectedItem().toString();
        suits[i] = deckCardSuit4.getSelectedItem().toString();
        i++;

        deckCard5       = (Spinner) findViewById(R.id.deckCard5);
        deckCardSuit5   = (Spinner) findViewById(R.id.deckCardSuit5);
        kinds[i] = deckCard5.getSelectedItem().toString();
        suits[i] = deckCardSuit5.getSelectedItem().toString();
        //endregion

        sortedHand = sortHand(kinds, suits);

        TextView pcTitle= findViewById(R.id.possibleCombinationsTitle);
        TextView pcName = findViewById(R.id.possibleCombinationsName);
        TextView pcValue= findViewById(R.id.possibleCombinationsValue);

        pcTitle.setVisibility(View.VISIBLE);
        pcName.setVisibility(View.VISIBLE);
        pcValue.setVisibility(View.VISIBLE);

        if (sortedHand.size() >= 5
                && isRoyalFlush(sortedHand)) {
            pcName.setText(R.string.cmb_name_royalFlush);
        } else if (sortedHand.size() >= 5
                && isStraightFlush(sortedHand)){
            pcName.setText(R.string.cmb_name_flush);
        } else if (sortedHand.size() >= 4
                && isFourOfAKind(sortedHand)) {
            pcName.setText(R.string.cmb_name_four_of_a_kind);
        } else if (sortedHand.size() >= 5
                && isFullHouse(sortedHand)) {
            pcName.setText(R.string.cmb_name_fullhouse);
        } else if (sortedHand.size() >= 5
                && isFlush(sortedHand)){
            pcName.setText(R.string.cmb_name_flush);
        } else if (sortedHand.size() >= 5
                && isStraight(sortedHand)) {
            pcName.setText(R.string.cmb_name_straight);
        } else if (sortedHand.size() >= 3
                && isThreeOfAKind(sortedHand)) {
            pcName.setText(R.string.cmb_name_three_of_a_kind);
        } else if (sortedHand.size() >= 4
                && isTwoPair(sortedHand)) {
            pcName.setText(R.string.cmb_name_two_pair);
        } else if (sortedHand.size() >= 2
                && isOnePair(sortedHand)) {
            pcName.setText(R.string.cmb_name_one_pair);
        } else {
                pcTitle.setVisibility(View.INVISIBLE);
                pcName.setVisibility(View.INVISIBLE);
                pcValue.setVisibility(View.INVISIBLE);

                pcName.setText("");
                pcValue.setText("");
        }
        pcValue.setText(combination.toString());
    }

    private boolean isRoyalFlush(LinkedList<String> sortedHand) {
        boolean itCouldBe = false, itContains;
        LinkedList<String> possibleVariants = new LinkedList<>(), finalVariant = new LinkedList<>();
        for (int i = 0; i < sortedHand.size(); i++) {
            if (ranks[0].equals(sortedHand.get(i).substring(0, 1))) {
                itCouldBe = true;

                if (!possibleVariants.contains(sortedHand.get(i))) {
                    possibleVariants.add(sortedHand.get(i));
                }
            }
        }

        if (possibleVariants.size() > 3) {
            return false;
        }

        if (itCouldBe) {
            itContains = false;
            //we're interested of A-K-Q-J-10 combo with the same suit
            //already got all the Aces
            for (int v = 0; v < possibleVariants.size(); v++) {
                for (int i = 1; i < 5; i++) {
                    for (String value : sortedHand) {
                        if (ranks[i].equals(value.substring(0, value.length() - 1))
                                && possibleVariants.get(v).substring(
                                        possibleVariants.get(v).length() - 1).equals(
                                                value.substring(value.length() - 1)
                                )
                        ) {
                            for (String variant : possibleVariants) {
                                if (variant.equals(value)) {
                                    itContains = true;
                                    break;
                                }
                            }

                            if (!itContains) {
                                possibleVariants.add(value);
                            }
                        }
                    }
                }
            }
        }

        for (String suit : getResources().getStringArray(R.array.card_suits)) {
            for (String variant : possibleVariants) {
                if (Objects.equals(variant.substring(variant.length() - 1), suit)) {
                    finalVariant.add(variant);
                }
            }

            if (finalVariant.size() != 5) {
                finalVariant.clear();
            } else {
                combination = finalVariant;
                break;
            }
        }

        return combination.size() == 5;
    }

    private boolean isStraightFlush(LinkedList<String> sortedHand) {
        LinkedList<String> possibleVariants = new LinkedList<>(), finalVariant = new LinkedList<>();


        for (int i = 0; i < sortedHand.size(); i++) {
            if (i > 2) break; //we need just 3 of biggest cards to understand if we can build a flush

            for (int r = 0; r < ranks.length - 5; r++) {
                if (ranks[r].equals(sortedHand.get(i).substring(0, sortedHand.get(i).length() - 1))) {

                    possibleVariants.add(sortedHand.get(i));
                    break;
                }
            }
        }

        for (String variant : possibleVariants) {
            int depth = 1;

            finalVariant.clear();
            finalVariant.add(variant);

            for (int i = 0; i < sortedHand.size(); i++) {
                if (finalVariant.size() == 5) {
                    combination = finalVariant;
                    break;
                } else if (variant.substring(variant.length() - 1).equals(sortedHand.get(i).substring(sortedHand.get(i).length() - 1))) {
                    int variantDiff = getDiffBetweenRanks(variant, sortedHand.get(i));

                    if (variantDiff == depth) {
                        if (!finalVariant.contains(sortedHand.get(i))) {
                            finalVariant.add(sortedHand.get(i));
                            depth++;
                        }
                    } else if (variantDiff > depth) {
                        break;
                    }
                } else {
                    if (!variant.equals(sortedHand.get(i))
                            && getDiffBetweenRanks(variant, sortedHand.get(i)) > depth) {
                        break;
                    }
                }
            }

            if (finalVariant.size() == 5
                    && combination.size() != 5) {
                combination = finalVariant;
            }

            if (combination.size() == 5) {
                break;
            }
        }

        return combination.size() == 5;
    }

    private boolean isFourOfAKind(LinkedList<String> sortedHand) {
        boolean itCouldBe = false;
        int amountOfKinds = 4;
        LinkedList<String> finalVariant = findSomeOfKinds(sortedHand, amountOfKinds);

        if (finalVariant.size() == amountOfKinds) {
            itCouldBe = true;
            combination = finalVariant;
        }

        return itCouldBe;
    }

    private boolean isFullHouse(LinkedList<String> sortedHand) {
        int amountOfKinds = 3, pair = 2;
        LinkedList<String> threeOfKind = findSomeOfKinds(sortedHand, amountOfKinds),
            cutHand, finalVariant = new LinkedList<>();

        if (threeOfKind.isEmpty()) return false;

        cutHand = new LinkedList<>(sortedHand);
        for (String card : threeOfKind) {
            cutHand.remove(card);
        }

        LinkedList<String> onePair = findSomeOfKinds(cutHand, pair);
        if (onePair.isEmpty()) return false;

        finalVariant.addAll(threeOfKind);
        finalVariant.addAll(onePair);
        combination = finalVariant;

        return combination.size() == 5;
    }

    private boolean isFlush(LinkedList<String> sortedHand) {
        LinkedList<String> finalVariant = new LinkedList<>();

        for (String s : getResources().getStringArray(R.array.card_suits)) {
            finalVariant.clear();

            for (String h : sortedHand) {
                if (h.substring(h.length() - 1).equals(s) && finalVariant.size() != 5) finalVariant.add(h);
            }

            if (finalVariant.size() == 5) {
                combination = finalVariant;
                break;
            }
        }

        return combination.size() == 5;
    }

    private boolean isStraight(LinkedList<String> sortedHand) {
        LinkedList<String> finalVariant = new LinkedList<>(), possibleVariants = new LinkedList<>();

        for (int i = 0; i < 3; i++) {
            for (int r = 0; r < ranks.length - 5; r++) {
                if (ranks[r].equals(sortedHand.get(i).substring(0, sortedHand.get(i).length() - 1))) {
                    possibleVariants.add(sortedHand.get(i));
                    break;
                }
            }
        }

        for (String variant : possibleVariants) {
            int depth = 1;

            finalVariant.clear();
            finalVariant.add(variant);

            for (int i = 0; i < sortedHand.size(); i++) {
                if (!variant.substring(0, variant.length() - 1).equals(sortedHand.get(i).substring(0, sortedHand.get(i).length() - 1))) {
                    int variantDiff = getDiffBetweenRanks(variant, sortedHand.get(i));

                    if (variantDiff == depth) {
                        if (!finalVariant.contains(sortedHand.get(i))) {
                            finalVariant.add(sortedHand.get(i));
                            depth++;
                        }
                    } else if (variantDiff > depth) {
                        break;
                    }
                } else {
                    if (!variant.equals(sortedHand.get(i))
                            && getDiffBetweenRanks(variant, sortedHand.get(i)) > depth) {
                        break;
                    }
                }

                if (finalVariant.size() == 5) {
                    combination = finalVariant;
                    break;
                }
            }

            if (combination.size() == 5) break;
        }
        return combination.size() == 5;
    }

    private boolean isThreeOfAKind(LinkedList<String> sortedHand) {
        int amountOfKinds = 3;
        LinkedList<String> finalVariant = findSomeOfKinds(sortedHand, amountOfKinds);

        if (finalVariant.size() == amountOfKinds) {
            combination = finalVariant;
        }

        return combination.size() == 3;
    }

    private boolean isTwoPair(LinkedList<String> sortedHand){
        LinkedList<String> first, second, finalVariant = new LinkedList<>();
        int pair = 2;

        first   = findSomeOfKinds(sortedHand, pair);
        finalVariant.addAll(first);
        LinkedList<String> cutHand = new LinkedList<>(sortedHand);

        for (String f : first) cutHand.remove(f);

        second  = findSomeOfKinds(cutHand, pair);
        finalVariant.addAll(second);

        if (finalVariant.size() == 4) combination = finalVariant;

        return combination.size() == 4;
    }

    private boolean isOnePair(LinkedList<String> sortedHand) {
        LinkedList<String> first, finalVariant = new LinkedList<>();
        int pair = 2;

        first = findSomeOfKinds(sortedHand, pair);
        finalVariant.addAll(first);

        if(finalVariant.size() == 2) combination = finalVariant;

        return combination.size() == 2;
    }

    private LinkedList<String> findSomeOfKinds(LinkedList<String> sortedHand, int amountOfKinds) {
        LinkedList<String> finalVariant = new LinkedList<>();
        for (int i = 0; i < sortedHand.size(); i++) {
            finalVariant.add(sortedHand.get(i));

            for (int j = 0; j < sortedHand.size(); j++) {
                if (sortedHand.get(i).substring(0, sortedHand.get(i).length() - 1)
                        .equals(sortedHand.get(j).substring(0, sortedHand.get(j).length() - 1))
                        && !sortedHand.get(i).substring(sortedHand.get(i).length() - 1)
                        .equals(sortedHand.get(j).substring(sortedHand.get(j).length() - 1))) {
                    finalVariant.add(sortedHand.get(j));
                }
            }

            if (finalVariant.size() == amountOfKinds) {
                break;
            } else {
                finalVariant.clear();
            }
        }

        return finalVariant;
    }

    private LinkedList<String> sortHand(String[] kinds, String[] suits) {
        LinkedList<String> sorted = new LinkedList<>();
        boolean itContains;

        for (String rank : ranks) {
            for (int i = 0; i < kinds.length; i++) {
                itContains = false;

                if (kinds[i].equals(rank)) {
                    for (String s : sorted) {
                        if (s != null
                                && s.equals(kinds[i] + suits[i])
                        ){
                            itContains = true;
                            break;
                        }
                    }

                    if (!itContains) {
                        sorted.add(kinds[i] + suits[i]);;
                    }
                }
            }
        }

        return sorted;
    }

    private int getDiffBetweenRanks(String main, String second) {
        int mainRank = getRank(main);
        int secondRank = getRank(second);

        return secondRank - mainRank;
    }

    private int getRank(String rank) {
        int rankOfKind = 0;
        for (int r = 0; r < ranks.length; r++) {
            if (rank.substring(0, rank.length() - 1).equals(ranks[r])) {
                rankOfKind = r;
                break;
            }
        }

        return rankOfKind;
    }

    private void makeToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void clearInputField(View view) {
        Spinner suitView = null, kindView = null;
        int viewID = view.getId();

        switch (viewID) {
            case R.id.clearDeckCard1:
                suitView    = findViewById(R.id.deckCardSuit1);
                kindView    = findViewById(R.id.deckCard1);
                break;
            case R.id.clearDeckCard2:
                suitView    = findViewById(R.id.deckCardSuit2);
                kindView    = findViewById(R.id.deckCard2);
                break;
            case R.id.clearDeckCard3:
                suitView    = findViewById(R.id.deckCardSuit3);
                kindView    = findViewById(R.id.deckCard3);
                break;
            case R.id.clearDeckCard4:
                suitView    = findViewById(R.id.deckCardSuit4);
                kindView    = findViewById(R.id.deckCard4);
                break;
            case R.id.clearDeckCard5:
                suitView    = findViewById(R.id.deckCardSuit5);
                kindView    = findViewById(R.id.deckCard5);
                break;
            case R.id.clearPlayerCard1:
                suitView    = findViewById(R.id.playerCardSuit1);
                kindView    = findViewById(R.id.playerCard1);
                break;
            case R.id.clearPlayerCard2:
                suitView    = findViewById(R.id.playerCardSuit2);
                kindView    = findViewById(R.id.playerCard2);
                break;
            default:
                break;
        }
        if (suitView != null) {
            suitView.setSelection(0);
        }

        if (kindView != null) {
            kindView.setSelection(0);
        }
    }
}