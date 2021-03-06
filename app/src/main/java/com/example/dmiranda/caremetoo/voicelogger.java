package com.example.dmiranda.caremetoo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmiranda.caremetoo.config.CareMeTooConfig;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link voicelogger.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link voicelogger#newInstance} factory method to
 * create an instance of this fragment.
 */
public class voicelogger extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    //myvars
    private MediaRecorder mRecorder = null;
    private static String mFileName = null;
    private static String LOG_TAG = "VOICELOGGER";
    private boolean isRecording = false;
    private TextView RecordCaptionTextView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment voicelogger.
     */
    // TODO: Rename and change types and number of parameters
    public static voicelogger newInstance(String param1, String param2) {
        voicelogger fragment = new voicelogger();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public voicelogger() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_voicelogger, container, false);
        recordButton = (ImageButton) v.findViewById(R.id.recordbutton);
        RecordCaptionTextView = (TextView) v.findViewById(R.id.txt_record);
        recordButton.setOnClickListener(recordButtonOnClickListener);

        return v;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }
    View.OnClickListener recordButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!isRecording){
                startRecording();
                recordButton.setBackground( getResources().getDrawable(R.drawable.mic_4_red));
                RecordCaptionTextView.setText(R.string.record_voice_text_recording);
            }else{
                stopRecording();
                recordButton.setBackground( getResources().getDrawable(R.drawable.mic_4));
                RecordCaptionTextView.setText(R.string.record_voice_text_to_record);
            }
        }
    };
    //views
    ImageButton recordButton;
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }




    private String generateFileName(){
        String Filename = String.valueOf(System.currentTimeMillis());
        return CareMeTooConfig.externalVoiceLogsPath+"/voicelog-"+Filename+ ".m4a";
    }
    private void startRecording(){
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mFileName = generateFileName();
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setOutputFile(mFileName);
        try{
            mRecorder.prepare();
            mRecorder.start();


        }catch (IOException e){
            Log.e(LOG_TAG, "Error preparing recorder");

        }
        isRecording = true;
    }
    private void stopRecording(){
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        isRecording = false;
        mFileName = null;
        startActivity(new Intent(getActivity(), EventDetailsActivity.class));

        }

}
