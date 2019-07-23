package com.example.compaq.animation;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //0:Pool 1:naruto
    int []gameState={2,2,2,2,2,2,2,2,2};
    int activePlayer=0;
    int [][]winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;
    public void dropin(View view)
    {
        ImageView counter=(ImageView)view;

        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        int temp=0;

        if(gameState[tappedCounter]==2 && gameActive)
        {   counter.setTranslationY(-1500);
            gameState[tappedCounter]=activePlayer;
            if(activePlayer==0)
            {   temp+=1;
                counter.setImageResource(R.drawable.pool);
                activePlayer=1;
            }
            else
            {   temp+=1;
                counter.setImageResource(R.drawable.naruto);
                activePlayer=0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for(int[]x:winningPositions)
            {
                if(gameState[x[0]]==gameState[x[1]]&& gameState[x[1]]==gameState[x[2]]&&gameState[x[0]]!=2) //someone has won
                {   String winner;
                    gameActive=false;
                    if(activePlayer==1)
                    {
                        winner="Pool";
                    }
                    else
                    {
                        winner="Naruto";
                    }

                    Button button=(Button)findViewById(R.id.button);
                    TextView text=(TextView)findViewById(R.id.textView );
                    text.setText(winner+" wins!!!");
                    button.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                }
            }

        }
        for(int i=0;i<9;i++)
        {
            if(gameState[i]!=2)
            {
                if(i==8)
                {
                    Button button=(Button)findViewById(R.id.button);
                    TextView text=(TextView)findViewById(R.id.textView );
                    text.setText("Play Again");
                    button.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                    break;
                }

            }
            else
            {
                break;
            }

        }


    }
    public void playAgain(View view)
    {
        Button button=(Button)findViewById(R.id.button);
        TextView text=(TextView)findViewById(R.id.textView );
        button.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);
        GridLayout grid=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<grid.getChildCount();i++)
        {
            ImageView counters=(ImageView)grid.getChildAt(i);
            counters.setImageDrawable(null);
        }
       for(int i=0;i<gameState.length;i++)
       {
           gameState[i]=2;
       }
        activePlayer=0;
        gameActive=true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
