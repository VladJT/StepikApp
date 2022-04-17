package jt.projects.stepikapp.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import jt.projects.stepikapp.R;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        private TextView textViewHolidayName;
        private TextView textViewHolidayDate;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            textViewHolidayName = itemView.findViewById(R.id.textViewHolidayName);
            textViewHolidayName.setOnClickListener(this);
            textViewHolidayDate = itemView.findViewById(R.id.textViewHolidayDate);
        }

        void bind(int position) {
            String holidayName = listOfHolidaysInfo.holidayInfo[position].getHolidayName();
            String holidayDate = listOfHolidaysInfo.holidayInfo[position].getHolidayDate();
            textViewHolidayName.setText(holidayName);
            textViewHolidayDate.setText(holidayDate);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onItemClickListener.onListItemClick(position);
        }
    }

    private ListOfHolidaysInfo listOfHolidaysInfo;
    private int length;

    final private ListItemClickListener onItemClickListener;

    interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public MyAdapter(ListOfHolidaysInfo listOfHolidaysInfo, int length, ListItemClickListener onItemClickListener) {
        this.listOfHolidaysInfo = listOfHolidaysInfo;
        this.length = length;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return length;
    }
}
