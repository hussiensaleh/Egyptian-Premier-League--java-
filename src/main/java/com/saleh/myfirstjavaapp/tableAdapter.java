package com.saleh.myfirstjavaapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class tableAdapter extends BaseAdapter {
        private ArrayList<tableTeam> teams;
        private Context context;
        public tableAdapter(Context context, ArrayList<tableTeam> teams){
            this.context=context;
            this.teams=teams;

        }
        @Override
        public int getCount() {
            return teams.size();
        }

        @Override
        public Object getItem(int position) {
            return teams.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final tableTeam team =teams.get(position);
            View myView=View.inflate(context,R.layout.table_ticket,null);
            TextView rank=myView.findViewById(R.id.rank_ticket);
            rank.setText(position+1+"");
            TextView name=myView.findViewById(R.id.name_ticket);
            name.setText(team.name);
            TextView played=myView.findViewById(R.id.played_ticket);
            played.setText(team.playedMatches+"");
            TextView win=myView.findViewById(R.id.win_ticket);
            win.setText(team.win+"");
            TextView draw=myView.findViewById(R.id.draw_ticket);
            draw.setText(team.draw+"");
            TextView lose=myView.findViewById(R.id.lose_ticket);
            lose.setText(team.lose+"");
            TextView plus=myView.findViewById(R.id.plus_ticket);
            plus.setText(team.plus+"");
            TextView mines=myView.findViewById(R.id.mines_ticket);
            mines.setText(team.minus+"");
            TextView farq=myView.findViewById(R.id.farq_ticket);
            farq.setText(team.farq+"");
            TextView points=myView.findViewById(R.id.points_ticket);
            points.setText(team.points+"");

            return myView;
        }
    }
