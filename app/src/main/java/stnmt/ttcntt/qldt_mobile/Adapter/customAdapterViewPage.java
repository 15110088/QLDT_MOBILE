package stnmt.ttcntt.qldt_mobile.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class customAdapterViewPage extends FragmentStatePagerAdapter {

    ArrayList<Fragment> listFrame= new ArrayList<>();
    ArrayList<String> listString= new ArrayList<>();

    public void addFragment(Fragment fm,String title){
        listString.add(title);
        listFrame.add(fm);
    }

    public void removeAllFragment(){
        if(listFrame.size()>0) {
            listString.clear();
            listFrame.clear();
        }
    }

    public customAdapterViewPage(FragmentManager fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return listFrame.get(position);
    }

    @Override
    public int getCount() {
        return listFrame.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  listString.get(position);
    }


}
