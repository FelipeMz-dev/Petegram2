package com.mz_dev.petagram;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.mz_dev.petagram.view.AboutActivity;
import com.mz_dev.petagram.view.ContactActivity;

public class OptionsMenuHandler{

    public static boolean handleOptionsItemSelected(MenuItem item, Context context) {
        int id = item.getItemId();

        if (id == R.id.menuContact) {
            Intent intent = new Intent(context, ContactActivity.class);
            context.startActivity(intent);
            return true;
        } else if (id == R.id.menuAbout) {
            Intent intent = new Intent(context, AboutActivity.class);
            context.startActivity(intent);
            return true;
        }else {
            return false;
        }
    }
}
