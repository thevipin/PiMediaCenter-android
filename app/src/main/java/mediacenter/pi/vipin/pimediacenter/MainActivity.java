package mediacenter.pi.vipin.pimediacenter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;

import mediacenter.pi.vipin.pimediacenter.casting.Server;
import mediacenter.pi.vipin.pimediacenter.network.KeyStrokes;
import mediacenter.pi.vipin.pimediacenter.player.remoter.PlayerRemoteController;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * Vipin Desciptions
     */
    static MainActivity activity;
    public static MainActivity getThis()
    {
        return activity;
    }

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this; // Vipin's code


        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        /* Vipin's Doings
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private KeyStrokes keyStrokes;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            final RelativeLayout RTlayout=(RelativeLayout)rootView.findViewById(R.id.relativelayoutText);
            RTlayout.setVisibility(View.INVISIBLE);
            //Button btnEnter =(Button)findViewById(R.id.btnEnter);
            keyStrokes=new KeyStrokes();
            Button btnEnter=(Button)rootView.findViewById(R.id.btnEnter);
            btnEnter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    keyStrokes.KeyEnter();
                }
            });
            Button btnUp=(Button)rootView.findViewById(R.id.btnUp);
            btnUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    keyStrokes.KeyUp();
                }
            });
            Button btnLeft=(Button)rootView.findViewById(R.id.btnLeft);
            btnLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    keyStrokes.KeyLeft();
                }
            });
            Button btnRight=(Button)rootView.findViewById(R.id.btnRight);
            btnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    keyStrokes.KeyRight();
                }
            });
            Button btnDown=(Button)rootView.findViewById(R.id.btnDown);
            btnDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    keyStrokes.KeyDown();
                }
            });

            Button btnInfo=(Button)rootView.findViewById(R.id.btnInfo);
            btnInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    keyStrokes.MouseRight();
                }
            });
            Button btnBack=(Button)rootView.findViewById(R.id.btnBack);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    keyStrokes.KeyBack();
                }
            });
            final EditText txtText=(EditText)rootView.findViewById(R.id.editText);
            Button btnTextSent =(Button)rootView.findViewById(R.id.btnTextSent);
            btnTextSent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txt=txtText.getText().toString();
                    keyStrokes.Text(txt);
                    RTlayout.setVisibility(View.INVISIBLE);
                }
            });

            Button btnText=(Button)rootView.findViewById(R.id.btnText);

            btnText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RTlayout.setVisibility(View.VISIBLE);
                }
            });

            return rootView;
        }


    }

    public static class Player extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private PlayerRemoteController remoter;
        Boolean IsIncrease = false;
        Boolean Is2x = false;


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static MainActivity.Player newInstance(int sectionNumber) {
            MainActivity.Player fragment = new MainActivity.Player();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_player, container, false);
            try {
                remoter=new PlayerRemoteController();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Button btnPasuseNResume =(Button)rootView.findViewById(R.id.btnPlayerPauseNResume);
            btnPasuseNResume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        remoter.DirectWrite("p");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Button btnStop=(Button)rootView.findViewById(R.id.btnPlayerStop);
            btnStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        remoter.Stop();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Button btnSeek10Back=(Button)rootView.findViewById(R.id.btnPlayerSeekBack10);
            btnSeek10Back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        remoter.Seek10Back();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Button btnSeek10For=(Button)rootView.findViewById(R.id.btnPlayerSeekFor);
            btnSeek10For.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        remoter.Seek10For();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Button btnShowSub=(Button)rootView.findViewById(R.id.btnPlayerShowSub);
            btnShowSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        remoter.ShowSubtitles();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Button btnHideSub=(Button)rootView.findViewById(R.id.btnPlayerHideSub);
            btnHideSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        remoter.HideSubtitles();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Button btnForward=(Button)rootView.findViewById(R.id.btnPlayerForward);
            btnForward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (IsIncrease)
                        {
                            remoter.IncreaseSpeed();
                            IsIncrease = false;
                        }
                        else
                        {
                            remoter.FastForward();
                            Is2x = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Button btnRewind =(Button)rootView.findViewById(R.id.btnPlayerRewind);
            btnRewind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (Is2x)
                        {
                            remoter.Rewind();
                            Is2x = false;
                        }
                        else
                        {
                            remoter.DecreaseSpeed();
                            IsIncrease = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


            return rootView;
        }


    }

    public static class Cast extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        Boolean IsIncrease = false;
        Boolean Is2x = false;


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static MainActivity.Cast newInstance(int sectionNumber) {
            MainActivity.Cast fragment = new MainActivity.Cast();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_cast, container, false);
            final Server server=new Server(this.getContext());
            final EditText textView=(EditText)rootView.findViewById(R.id.castTempText);
            ((Button)rootView.findViewById(R.id.castTempButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PackageManager pm = rootView.getContext().getPackageManager();
                    Intent intent = pm.getLaunchIntentForPackage("at.huber.raspicast");
                    startActivity(intent);
                    //openApplication(rootView.getContext(), "at.huber.raspicast");
                }
            });
            return rootView;
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position==1)
                return  Player.newInstance(position+1);
            if(position==2)
                return Cast.newInstance(position+1);
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
