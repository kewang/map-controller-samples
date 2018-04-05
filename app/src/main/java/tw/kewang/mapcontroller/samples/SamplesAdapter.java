package tw.kewang.mapcontroller.samples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SamplesAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<SampleItem> samples = new ArrayList<>();

    public SamplesAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void addSample(String title, Class<?> clazz) {
        samples.add(new SampleItem(title, clazz));
    }

    @Override
    public int getCount() {
        return samples.size();
    }

    @Override
    public Object getItem(int position) {
        return samples.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row_item, null);

            holder = new Holder();

            holder.txtTitle = convertView.findViewById(R.id.text_title);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        decorate(holder, (SampleItem) getItem(position));

        return convertView;
    }

    private void decorate(Holder holder, SampleItem sample) {
        holder.txtTitle.setText(sample.title);
    }

    private class Holder {
        private TextView txtTitle;
    }

    public class SampleItem {
        String title;
        Class<?> clazz;

        public SampleItem(String title, Class<?> clazz) {
            this.title = title;
            this.clazz = clazz;
        }
    }
}