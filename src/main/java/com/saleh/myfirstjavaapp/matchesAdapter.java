package com.saleh.myfirstjavaapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class matchesAdapter  extends BaseAdapter{
    private ArrayList<match> matches;
    private Context context;
    public matchesAdapter(Context context, ArrayList<match> matches){
        this.context=context;
        this.matches=matches;

    }
    @Override
    public int getCount() {
        return matches.size();
    }

    @Override
    public Object getItem(int position) {
        return matches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final match match =matches.get(position);
        View myView=View.inflate(context,R.layout.mattch_ticket,null);
        TextView team1TV=myView.findViewById(R.id.team1name);
        team1TV.setText(match.getTeam1());
        TextView team2TV=myView.findViewById(R.id.team2name);
        team2TV.setText(match.getTeam2());
        Button team1Logo =myView.findViewById(R.id.team1logo);
        team1Logo.setBackground(context.getResources().getDrawable(match.getTeam1Image()));
        Button team2Logo =myView.findViewById(R.id.team2logo);
        team2Logo.setBackground(context.getResources().getDrawable(match.getTeam2Image()));
        TextView score1 =myView.findViewById(R.id.team1score);
        score1.setText(match.getTeam1Score()+"");
        TextView score2 =myView.findViewById(R.id.team2score);
        score2.setText(match.getTeam2Score()+"");
        TextView time =myView.findViewById(R.id.time_ticket);
        time.setText(match.getTime());
        TextView date =myView.findViewById(R.id.date_ticket);
        date.setText(match.getDate());
        TextView status =myView.findViewById(R.id.status_ticket_tv);
        TextView goals=myView.findViewById(R.id.goals_tv);
        switch (match.getWinner()){
            case -1 :
                status.setText("لم تبدا بعد");
                goals.setVisibility(View.GONE);
                score1.setVisibility(View.GONE);
                score2.setVisibility(View.GONE);
                break;
            case 0  :
                status.setText("    الان    ");
                goals.setVisibility(View.VISIBLE);
                score1.setVisibility(View.VISIBLE);
                score2.setVisibility(View.VISIBLE);
                break;
            case 1  :
                status.setText("   انتهت   ");
                goals.setVisibility(View.VISIBLE);
                score1.setVisibility(View.VISIBLE);
                score2.setVisibility(View.VISIBLE);
                break;
            case 2  :
                status.setText("   انتهت   ");
                goals.setVisibility(View.VISIBLE);
                score1.setVisibility(View.VISIBLE);
                score2.setVisibility(View.VISIBLE);
                break;
            case 3  :
                status.setText("   انتهت   ");
                goals.setVisibility(View.VISIBLE);
                score1.setVisibility(View.VISIBLE);
                score2.setVisibility(View.VISIBLE);
                break;
        }
        goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (match.getTeam1Score()==0&&match.getTeam2Score()==0){
                    Toast.makeText(context," لا يوجد اهداف ",Toast.LENGTH_SHORT).show();
                }else {
                    Intent i = new Intent(context, MatchDetails.class);
                    i.putExtra("roundId", match.getRoundId());
                    i.putExtra("matchRoundId", match.getRoundMatchId());
                    context.startActivity(i);
                }
            }
        });









        return myView;
    }
}
