package com.example.impeltask.View.Fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Browser;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.impeltask.R;
import com.example.impeltask.databinding.FragmentBookmarksBinding;
import com.example.impeltask.databinding.FragmentNewsBinding;

import java.util.ArrayList;


public class Fragment_bookmarks extends Fragment {

    FragmentBookmarksBinding binding;

    ArrayList mTitles = new ArrayList();
    ArrayList mUrls = new ArrayList();

    public final Uri BOOKMARKS_URI = Uri.parse("content://com.android.chrome/bookmarks");
    //public final Uri BOOKMARKS_URI = Uri.parse("content://browser/bookmarks");
    //public final Uri BOOKMARKS_URI = Uri.parse("content://com.android.chrome.browser/bookmarks");
    //public final Uri BOOKMARKS_URI = Uri.parse("content://com.android.chrome.browser/bookmarks/Bookmarks bar");
    public final String[] HISTORY_PROJECTION = new String[]{
            "_id", // 0
            "url", // 1
            "visits", // 2
            "date", // 3
            "bookmark", // 4
            "title", // 5
            "favicon", // 6
            "thumbnail", // 7
            "touch_icon", // 8
            "user_entered", // 9
    };
    public final int HISTORY_PROJECTION_TITLE_INDEX = 5;
    public final int HISTORY_PROJECTION_URL_INDEX = 1;

    private Boolean isStarted = false;
    private Boolean isVisible = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isStarted && isVisible) {
            viewDidAppear();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBookmarksBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        isStarted = true;
        if (isVisible && isStarted) {
            viewDidAppear();
        }

    }

    private void viewDidAppear() {
        //getBookmarks();
        getBrowserHist();
    }


    @SuppressLint("Range")
    public void getBookmarks() {
        String[] proj = new String[]{HISTORY_PROJECTION[5], HISTORY_PROJECTION[1]};
        Uri uriCustom = Uri.parse(String.valueOf(BOOKMARKS_URI));
        String sel = HISTORY_PROJECTION[4] + " = 1"; // 0 = history, 1 = bookmark
        Cursor mCur = getActivity().getContentResolver().query(uriCustom, proj, sel, null, null);
        //getActivity().startManagingCursor(mCur);
        // mCur.moveToFirst();
        @SuppressWarnings("unused")
        String title = "";
        @SuppressWarnings("unused")
        String url = "";

        if (mCur.moveToFirst() && mCur.getCount() > 0) {
            boolean cont = true;
            while (mCur.isAfterLast() == false && cont) {
                title = mCur.getString(mCur.getColumnIndex(HISTORY_PROJECTION[5]));
                url = mCur.getString(mCur.getColumnIndex(HISTORY_PROJECTION[1]));
                // Do something with title and url
                Log.d("dataxx", mCur
                        .getString(HISTORY_PROJECTION_TITLE_INDEX));
                Log.d("dataxx", mCur
                        .getString(HISTORY_PROJECTION_URL_INDEX));
                mCur.moveToNext();
            }
        }else {
            Log.d("dataxx", "nothing");
        }
    }



    @SuppressLint("Range")
    public void getBrowserHist()  {


        String[] proj = new String[] { HISTORY_PROJECTION[5], HISTORY_PROJECTION[1]};
        Uri uriCustom = Uri.parse(String.valueOf(BOOKMARKS_URI));
        String sel = HISTORY_PROJECTION[4] + " = 1";  // 0 = history, 1 =bookmark
        Cursor mCur = getActivity().getContentResolver().query(uriCustom, proj, sel, null, null);
        getActivity().startManagingCursor(mCur);
       // mCur.moveToFirst();

        String title = "";
        String url = "";

        /*if (mCur.moveToFirst() && mCur.getCount() > 0) {
            while (mCur.isAfterLast() == false && mCur.getCount() > 0) {

                //title = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.TITLE));
                //url = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.URL));
                title = mCur.getString(mCur.getColumnIndex(HISTORY_PROJECTION[5]));
                url = mCur.getString(mCur.getColumnIndex(HISTORY_PROJECTION[1]));
                // Do something with title and url

                mCur.moveToNext();
            }
        }
        if (mCur.getCount()>0 && (mCur.moveToFirst())){
            Toast.makeText(getContext(), "hi", Toast.LENGTH_SHORT).show();
        }*/

        //Log.d("dataxx", String.valueOf(mCur.getCount()));

    }
}