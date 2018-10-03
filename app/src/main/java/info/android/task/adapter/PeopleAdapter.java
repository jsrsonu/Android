package info.android.task.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import info.android.task.R;
import info.android.task.model.PeopleList;
import info.android.task.model.Result;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private List<Result> peoples;
    private int rowLayout;
    private Context context;


    public class PeopleViewHolder extends RecyclerView.ViewHolder {
        LinearLayout peoplesLayout;
        TextView peopleName;

        public PeopleViewHolder(View v) {
            super(v);
            peoplesLayout = (LinearLayout) v.findViewById(R.id.peoples_layout);
            peopleName = (TextView) v.findViewById(R.id.name);
        }
    }

    public PeopleAdapter(List<Result> peoples, int rowLayout, Context context) {
        this.peoples = peoples;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public PeopleAdapter.PeopleViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PeopleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(PeopleViewHolder holder, final int position) {
        holder.peopleName.setText(peoples.get(position).getName());

        holder.peoplesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPeopleName(peoples.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return peoples.size();
    }

    public void onClickPeopleName(Result result){}
}
