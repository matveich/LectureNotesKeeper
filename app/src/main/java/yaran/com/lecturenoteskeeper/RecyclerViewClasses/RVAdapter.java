package yaran.com.lecturenoteskeeper.RecyclerViewClasses;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import yaran.com.lecturenoteskeeper.MainActivity;
import yaran.com.lecturenoteskeeper.R;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVCardViewHolder> {


    List<RVCard> RVCards;

    public RVAdapter(List<RVCard> RVCards) {
        this.RVCards = RVCards;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RVCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.small_card, viewGroup, false);
        RVCardViewHolder rvh = new RVCardViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(RVCardViewHolder mRVCardViewHolder, int i) {
        mRVCardViewHolder.title.setText(RVCards.get(i).title);
        mRVCardViewHolder.subject.setText(RVCards.get(i).subject);
        mRVCardViewHolder.type = RVCards.get(i).type;
        mRVCardViewHolder.imagePath = RVCards.get(i).imagePath;
        Picasso.with(MainActivity.context)
                .load(new File(RVCards.get(i).imagePath))
                .error(MainActivity.context.getResources().getDrawable(R.drawable.header))
                //.resize(MainActivity.dpToPx(180), MainActivity.dpToPx(240))
                .into(mRVCardViewHolder.smallImage);
        switch (RVCards.get(i).type) {
            case 1:
                mRVCardViewHolder.typeImage.setImageDrawable(MainActivity.context.getResources().getDrawable(R.drawable.letter));
                break;
            case 2:
                mRVCardViewHolder.typeImage.setImageDrawable(MainActivity.context.getResources().getDrawable(R.drawable.homework));
                break;
            case 3:
                mRVCardViewHolder.typeImage.setImageDrawable(MainActivity.context.getResources().getDrawable(R.drawable.other));
                break;
        }
        mRVCardViewHolder.cv.setLayoutParams(new RelativeLayout.LayoutParams((int) (MainActivity.width / 2.5), (int) (MainActivity.width / 2.5 / 3 * 4)));
        RelativeLayout.LayoutParams ll = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (MainActivity.width / 2.5 / 2.25));
        ll.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        ll.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mRVCardViewHolder.someBackground.setLayoutParams(ll);
        mRVCardViewHolder.typeImage.setLayoutParams(new RelativeLayout.LayoutParams((int) (MainActivity.width / 2.5 / 2.8125), (int) (MainActivity.width / 2.5 / 2.8125)));
        mRVCardViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.context, "touch me dude", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return RVCards.size();
    }

    public static class RVCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        TextView title;
        TextView subject;
        ImageView smallImage;
        ImageView typeImage;
        int type;
        String imagePath;
        ImageView someBackground;

        RVCardViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cv = (CardView) itemView.findViewById(R.id.cv);
            title = (TextView) itemView.findViewById(R.id.small_title);
            subject = (TextView) itemView.findViewById(R.id.small_subject);
            smallImage = (ImageView) itemView.findViewById(R.id.small_image);
            typeImage = (ImageView) itemView.findViewById(R.id.type_icon);
            someBackground = (ImageView) itemView.findViewById(R.id.some_background);
            itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        ViewCompat.setElevation(itemView, 1);
                    } else {
                        ViewCompat.setElevation(itemView, 1);
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {

        }

    }

}