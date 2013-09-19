package com.squareup.shipfaster.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.squareup.shipfaster.R;
import com.squareup.shipfaster.common.ViewHolder;
import java.util.List;

public class ItemAdapter extends BaseAdapter {

  private final Context context;
  private final List<Item> items;

  public ItemAdapter(Context context) {
    this.context = context;
    items = Item.newFakeItemList();
  }

  @Override public View getView(int position, View view, ViewGroup parent) {
    if (view == null) {
      view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
    }

    ImageView photoView = ViewHolder.get(view, R.id.photo);
    TextView descriptionView = ViewHolder.get(view, R.id.description);

    Item item = getItem(position);

    descriptionView.setText(item.description());

    // Trigger the download of the URL asynchronously into the image view.
    Picasso.with(context)
        .load(item.photoUrl)
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.error)
        .resizeDimen(R.dimen.list_detail_image_size, R.dimen.list_detail_image_size)
        .centerInside()
        .into(photoView);

    return view;
  }

  @Override public int getCount() {
    return items.size();
  }

  @Override public Item getItem(int position) {
    return items.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }
}