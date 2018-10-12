package com.example.daiverandresdoria.lab_03.MyAdapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.daiverandresdoria.lab_03.Model.Fruit;
import com.example.daiverandresdoria.lab_03.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;
import android.view.ViewGroup;

import java.lang.reflect.Type;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private int layout;
    private List<Fruit> fruits;
    private onItemClickListener onItemClickListener;
    private Activity activity;

    public MyAdapter(List<Fruit> fruits, int layout, Activity activity, onItemClickListener onItemClickListener) {
        this.layout = layout;
        this.fruits = fruits;
        this.activity = activity;
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder vh =  new ViewHolder(LayoutInflater.from(activity).inflate(layout,parent,false));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(fruits.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener,MenuItem.OnMenuItemClickListener {
        public TextView name;
        public TextView description;
        public TextView cantidad;
        public ImageView iconBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.textViewName);
            this.description = (TextView) itemView.findViewById(R.id.textViewDescription);
            this.cantidad = (TextView) itemView.findViewById(R.id.textViewCantidad);
            this.iconBackground = (ImageView) itemView.findViewById(R.id.imageViewBackground);

            this.itemView.setOnCreateContextMenuListener(this);
        }
        public void bind(final Fruit fruit, final onItemClickListener listener){
            name.setText(fruit.getName());
            description.setText(fruit.getDescripcion());
            cantidad.setText(fruit.getCantidad()+"");
            Picasso.get().load(fruit.getIcon_background()).fit().into(iconBackground);
            //iconBackground=(ImageView) itemView.findViewById(R.id.imageViewBackground);

            if (fruit.getCantidad()==Fruit.Limite_cantidad){
                cantidad.setTextColor(ContextCompat.getColor(activity,R.color.colorPrimary));
                cantidad.setTypeface(null, Typeface.BOLD);
            }else
                cantidad.setTextColor(ContextCompat.getColor(activity,R.color.cardview_dark_background));
                cantidad.setTypeface(null, Typeface.NORMAL);

            iconBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   listener.onItemClick(fruit,getAdapterPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            Fruit fruitselecter = fruits.get(this.getAdapterPosition());
            menu.setHeaderTitle(fruitselecter.getName());
            menu.setHeaderIcon(fruitselecter.getIcon());

            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.menu_context,menu);
            for (int i = 0; i < menu.size(); i++)
                menu.getItem(i).setOnMenuItemClickListener(this);
        }
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.delet_fruit:
                    fruits.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                case R.id.reset_fruit_cantidad:
                    fruits.get(getAdapterPosition()).Reset();
                    notifyItemChanged(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }
    }
    public interface onItemClickListener{
        void onItemClick(Fruit fruit, int position);
    }
}
