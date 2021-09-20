package lk.aditi.ecom_seller.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.aditi.ecom_seller.R;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

import lk.aditi.ecom_seller.models.TimeLineModel;


public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineAdapter_View> {
    private List<TimeLineModel> timeLineList;


    public TimeLineAdapter(List<TimeLineModel> timeLineList) {
        this.timeLineList = timeLineList;

    }

    @Override
    public TimeLineAdapter_View onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.track_order_timeline, parent, false);
        return new TimeLineAdapter_View(view,viewType);
    }

    @Override
    public void onBindViewHolder( TimeLineAdapter_View holder, int position) {

        if(timeLineList.get(position).getStatus()==1){
            holder.timelineView.setStartLineColor(R.color.red,0);
            holder.timelineView.setMarker(holder.timelineView.getContext().getResources().getDrawable(R.drawable.ic_process_filled1));
            holder.textView.setTextColor(holder.detail.getContext().getResources().getColor(R.color.black));
        }
        else if(timeLineList.get(position).getStatus()==0){
            holder.timelineView.setMarker(holder.timelineView.getContext().getResources().getDrawable(R.drawable.ic_process_blank));
            holder.textView.setTextColor(holder.detail.getContext().getResources().getColor(R.color.gray));
        }


        //holder.detail.setTextColor(holder.detail.getContext().getResources().getColor(R.color.gray));
        holder.textView.setText(timeLineList.get(position).getTitle());
        holder.detail.setText(timeLineList.get(position).getDetail());
        holder.tv_timeline_date.setText(timeLineList.get(position).getDate());
        holder.tv_timeline_time.setText(timeLineList.get(position).getTime());


        Log.i("vdggd", "list: "+timeLineList.toString());
        Log.i("xbcbcbc", "onBindViewHolder: "+timeLineList.get(position).getDate());
        Log.i("xbcbcbc", "onBindViewHolder: "+timeLineList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return timeLineList.size();
    }

    public class TimeLineAdapter_View extends RecyclerView.ViewHolder {
        TimelineView timelineView;
        TextView textView,time,detail,tv_timeline_date,tv_timeline_time;
        public TimeLineAdapter_View( View itemView, int viewType) {
            super(itemView);

            timelineView = (TimelineView) itemView.findViewById(R.id.timeline);
            textView =  itemView.findViewById(R.id.textView);
            tv_timeline_time =  itemView.findViewById(R.id.tv_timeline_time);
            tv_timeline_date =  itemView.findViewById(R.id.tv_timeline_date);
            time =  itemView.findViewById(R.id.time);
            detail =  itemView.findViewById(R.id.detail);
            timelineView.initLine(viewType);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }
}
