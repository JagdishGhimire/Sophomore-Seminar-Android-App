package com.example.android.miwok;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.content.Context.AUDIO_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener(){
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }
        }
    };

    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };


    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.word_list, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Where are you going?", "TImi kata gai rako", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "Timro naam k ho", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "Mero naam ..", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "Kasto Lagi ra cha?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "Khusi lagi ra cha", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "Timi aai rako ho?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "Ho Ma Aairako ho", R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "Ma aaudai chu", R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "La jaam", R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "Yeta aau na", R.raw.phrase_come_here));

        WordAdapter wordAdapater = new WordAdapter(getActivity(), words, R.color.category_phrases);
        ListView listView = (ListView) view.findViewById(R.id.word_list_view);
        listView.setAdapter(wordAdapater);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                releaseMediaPlayer();

                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = MediaPlayer.create(view.getContext(), words.get(position).getPronunciation());

                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}
