package com.techtist.srikasiviswanathar.pooja.activities.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import com.techtist.srikasiviswanathar.R;
import com.techtist.srikasiviswanathar.pooja.activities.models.PoojaResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PoojaAdapter extends ArrayAdapter<PoojaResponse.PoojaData> {

    private List<PoojaResponse.PoojaData> poojaList;
    private final SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());  // 24-hour format
    private final SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault()); // 12-hour format with AM/PM

    public PoojaAdapter(Context context, List<PoojaResponse.PoojaData> poojaList) {
        super(context, R.layout.item_pooja, poojaList);
        this.poojaList = poojaList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pooja, parent, false);
            holder = new ViewHolder();
            holder.tvTitle = convertView.findViewById(R.id.tvPoojaTitle);
            holder.tvDate = convertView.findViewById(R.id.tvPoojaDate);
            holder.tvStartTime = convertView.findViewById(R.id.tvPoojaStartTime);
            holder.tvEndTime = convertView.findViewById(R.id.tvPoojaEndTime);
            holder.tvSponsors = convertView.findViewById(R.id.tvPoojaSponsors);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PoojaResponse.PoojaData poojaData = poojaList.get(position);
        holder.tvTitle.setText(poojaData.getPoojaTitle());
        holder.tvDate.setText("Date: " + poojaData.getPoojaDate());
        holder.tvStartTime.setText("Start Time: " + formatTime(poojaData.getStartTime()));
        holder.tvEndTime.setText("End Time: " + formatTime(poojaData.getEndTime()));
        holder.tvSponsors.setText("Sponsors: " + poojaData.getSponsors());

        return convertView;
    }

    private static class ViewHolder {
        TextView tvTitle, tvDate, tvStartTime, tvEndTime, tvSponsors;
    }

    // Method to convert 24-hour format to 12-hour format with AM/PM
    private String formatTime(String time) {
        try {
            Date date = inputFormat.parse(time);
            return outputFormat.format(date);  // Convert to 12-hour format with AM/PM
        } catch (ParseException e) {
            e.printStackTrace();
            return time; // Return original if parsing fails
        }
    }
}
