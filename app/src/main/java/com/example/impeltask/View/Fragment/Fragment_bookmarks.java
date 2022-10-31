package com.example.impeltask.View.Fragment;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
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

import com.example.impeltask.Helpers.BookmarkColumns;
import com.example.impeltask.R;
import com.example.impeltask.databinding.FragmentBookmarksBinding;
import com.example.impeltask.databinding.FragmentNewsBinding;

import java.util.ArrayList;


public class Fragment_bookmarks extends Fragment {

    FragmentBookmarksBinding binding;

    ArrayList mTitles = new ArrayList();
    ArrayList mUrls = new ArrayList();

    //"content://com.android.chrome.browser/bookmarks"
    //public final Uri BOOKMARKS_URI = Uri.parse("chrome://bookmarks");
    public final Uri BOOKMARKS_URI = Uri.parse("content://com.android.chrome/bookmarks");
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

    private void go_to_default_browser() {
        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://"));
        ResolveInfo resolveInfo = getActivity().getPackageManager().resolveActivity(browserIntent, PackageManager.MATCH_DEFAULT_ONLY);

       try {
           // This is the default browser's packageName
           String packageName =resolveInfo.activityInfo.packageName;

           Toast.makeText(getContext(), packageName, Toast.LENGTH_SHORT).show();
           //startActivity(browserIntent);
           startActivity(getActivity().getPackageManager().getLaunchIntentForPackage(packageName));
       }catch (Exception e){}
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
        getBookmarks();
        //getBrowserHist();
        /*Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.android.chrome");
        if (launchIntent != null) {
            startActivity(launchIntent);//null pointer check in case package name was not found

        }else {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.android.chrome"); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }*/

        /*Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.android.chrome", "bookmarks"));
        startActivity(intent);*/

        //chrome://bookmarks/#1
        /*Uri uri = Uri.parse("https://chrome://bookmarks/#1"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);*/

    }


    @SuppressLint("Range")
    public void getBookmarks() {
        String[] proj = new String[]{BookmarkColumns.TITLE, BookmarkColumns.URL};
        Uri uriCustom = Uri.parse(String.valueOf(BOOKMARKS_URI));
        String sel = BookmarkColumns.BOOKMARK + " = 1"; // 0 = history, 1 = bookmark
        @SuppressLint("Recycle") Cursor mCur = getActivity().getContentResolver().query(uriCustom, proj, sel, null, null);
       // getActivity().startManagingCursor(mCur);
        mCur.moveToFirst();
        @SuppressWarnings("unused")
        String title = "";
        @SuppressWarnings("unused")
        String url = "";

        if (mCur.moveToFirst() && mCur.getCount() > 0) {
            boolean cont = true;
            while (mCur.isAfterLast() == false && cont) {
                title = mCur.getString(mCur.getColumnIndex(BookmarkColumns.TITLE));
                url = mCur.getString(mCur.getColumnIndex(BookmarkColumns.URL));
                Log.e("title", title);
                Log.e("url", url);
                // Do something with title and url
                mCur.moveToNext();
            }
        } else {
            Toast.makeText(getContext(), "no data", Toast.LENGTH_SHORT).show();
        }
    }


    @SuppressLint("Range")
    public void getBrowserHist() {


        String[] proj = new String[]{HISTORY_PROJECTION[5], HISTORY_PROJECTION[1]};
        Uri uriCustom = Uri.parse(String.valueOf(BOOKMARKS_URI));
        String sel = HISTORY_PROJECTION[4] + " = 0";  // 0 = history, 1 =bookmark
        Cursor mCur = getActivity().getContentResolver().query(uriCustom, proj, sel, null, null);
        getActivity().startManagingCursor(mCur);
        mCur.moveToFirst();

        String title = "";
        String url = "";

        if (mCur.moveToFirst() && mCur.getCount() > 0) {
            while (mCur.isAfterLast() == false && mCur.getCount() > 0) {

                //title = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.TITLE));
                //url = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.URL));
                title = mCur.getString(mCur.getColumnIndex(HISTORY_PROJECTION[5]));
                url = mCur.getString(mCur.getColumnIndex(HISTORY_PROJECTION[1]));
                // Do something with title and url

                mCur.moveToNext();
            }
        } else {
            Toast.makeText(getContext(), "no data", Toast.LENGTH_SHORT).show();
        }


    }
}