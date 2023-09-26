package com.mz_dev.petagram;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.mz_dev.petagram.view.AboutActivity;
import com.mz_dev.petagram.view.ContactActivity;

public class NavigationMenuOptions implements Toolbar.OnMenuItemClickListener {

    Context context;

    public NavigationMenuOptions(Context context) {
        this.context = context;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        final int itemId = item.getItemId();
        if (itemId == R.id.menuContact){
            Intent intent = new Intent(context, ContactActivity.class);
            context.startActivity(intent);
        } else if (itemId == R.id.menuAbout) {
            Intent intent = new Intent(context, AboutActivity.class);
            context.startActivity(intent);
        }
        return false;
    }
}
