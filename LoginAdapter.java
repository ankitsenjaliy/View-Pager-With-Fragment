ppackage com.example.hotelmanagementsystem.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter {

    Context context;
    int totalTabs;

    public LoginAdapter(FragmentManager fm, Context context, int totalTabs){

        super(fm);

        this.context = context;
        this.totalTabs = totalTabs;

    }

    public int getCount(){

        return totalTabs;
    }

    public Fragment getItem(int position){

        switch (position){

            case 0:

                CustomerLoginFragment customerLoginFragment = new CustomerLoginFragment();
                return customerLoginFragment;

            case 1:

                AdminLoginFragment adminLoginFragment = new AdminLoginFragment();
                return adminLoginFragment;

            default:

                return null;
        }
    }

}
