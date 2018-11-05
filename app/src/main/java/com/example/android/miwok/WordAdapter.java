package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Word> {
    private int backgroundColor;

    public WordAdapter(Context context, ArrayList<Word> words, int color) {
        super(context, 0, words);
        backgroundColor = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word word = getItem(position);

        setMiwokTranslation(listItemView, word);
        setDefaultTranslation(listItemView, word);
        setImage(listItemView, word);
        setBackgroundColor(listItemView);

        return listItemView;
    }

    private void setBackgroundColor(View listItemView) {
        View translations = listItemView.findViewById(R.id.translations);
        translations.setBackgroundColor(ContextCompat.getColor(getContext(), backgroundColor));
    }

    private void setImage(View listItemView, Word word) {
        ImageView wordImage = (ImageView) listItemView.findViewById(R.id.word_image);
        if (word.hasImage()) {
            wordImage.setImageResource(word.getImageResourceId());
            wordImage.setVisibility(View.VISIBLE);
        } else {
            wordImage.setVisibility(View.GONE);
        }
    }

    private void setDefaultTranslation(View listItemView, Word word) {
        TextView defaultTranslation = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTranslation.setText(word.getDefaultTranslation());
    }

    private void setMiwokTranslation(View listItemView, Word word) {
        TextView miwokTranslation = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTranslation.setText(word.getMiwokTranslation());
    }
}
