package com.payfever.presentation.fragment.set_ringtones;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.payfever.R;
import com.payfever.data.model.ringtone.Ringtone;
import com.payfever.presentation.PayFeverApplication;

import java.util.List;

/**
 * Created by
 * mRogach on 03.11.2015.
 */

public final class RingTonesAdapter extends BaseAdapter implements View.OnClickListener {

    private List<Ringtone> mRingTones;
    private OnPayToneItemListener listener;

    public void setOnPayToneItemListener(final OnPayToneItemListener _listener) {
        this.listener = _listener;
    }

    public void setRingtones(final List<Ringtone> _ringTones) {
        this.mRingTones = _ringTones;
    }

    @Override
    public int getCount() {
        return mRingTones.size();
    }

    @Override
    public Ringtone getItem(int position) {
        return mRingTones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int _position, View _convertView, ViewGroup _parent) {
        ViewHolder viewHolder;
        if (_convertView == null) {
            _convertView = LayoutInflater.from(PayFeverApplication.getApplication()).inflate(R.layout.item_ringtones_list, _parent, false);
            viewHolder = new ViewHolder();
            findViews(viewHolder, _convertView);
            setListeners(viewHolder);
            _convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) _convertView.getTag();
        }
        updateView(viewHolder, _position);
        viewHolder.ivPlayRingtone.setTag(_position);
        viewHolder.tvSetPayTone.setTag(_position);
        return _convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivPlayRingtone_IRL:
                listener.playRingtone(mRingTones.get((Integer) v.getTag()));
                break;
            case R.id.tvSetPayTone_IRL:
                Ringtone ringtone = mRingTones.get((Integer) v.getTag());
                listener.setPayTone(ringtone);
                break;
        }
    }

    private class ViewHolder {
        public TextView tvRingtoneTitle;
        public ImageView ivPlayRingtone;
        public TextView tvRevenue;
        public TextView tvSetPayTone;
    }

    private void findViews(final ViewHolder _viewHolder, final View _convertView) {
        _viewHolder.tvRingtoneTitle         = (TextView) _convertView.findViewById(R.id.tvRingtoneTitle_IRL);
        _viewHolder.ivPlayRingtone          = (ImageView) _convertView.findViewById(R.id.ivPlayRingtone_IRL);
        _viewHolder.tvRevenue               = (TextView) _convertView.findViewById(R.id.tvRevenue_IRL);
        _viewHolder.tvSetPayTone            = (TextView) _convertView.findViewById(R.id.tvSetPayTone_IRL);
    }

    private void setListeners(final ViewHolder _viewHolder) {
        _viewHolder.ivPlayRingtone.setOnClickListener(this);
        _viewHolder.tvSetPayTone.setOnClickListener(this);
    }

    private void updateView(final ViewHolder _viewHolder, final int _position) {
        _viewHolder.tvRingtoneTitle.setText(mRingTones.get(_position).getName());
        _viewHolder.tvRevenue.setText(String.valueOf(mRingTones.get(_position).getRevenue()));
        if (mRingTones.get(_position).isPlaying()) {
            _viewHolder.ivPlayRingtone.setImageResource(R.drawable.pause_white_192x192);
        } else
            _viewHolder.ivPlayRingtone.setImageResource(R.drawable.play_arrow_black_192x192);
    }
}
