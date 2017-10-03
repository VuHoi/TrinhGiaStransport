package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vukhachoi.testhorizontal.DayFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Vu Khac Hoi on 10/1/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<DayFragment> mFragmentList = new ArrayList<>();
    List<Date>dates=new ArrayList<>();
    Calendar ab;
int Size=0;
    private FragmentManager mFragmentManager;
    public ViewPagerAdapter(FragmentManager manager,int size,Calendar calendar) {
        super(manager);
this.ab=calendar;
Size=size;
        mFragmentManager=manager;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
    @Override
    public int getCount() {
        return Size;
    }

    public void AddFrag (DayFragment dayFragment,Date date) {
        mFragmentList.add(dayFragment);
        dates.add(date);
    }

    public void remove () {
        mFragmentList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        if (mFragmentList != null) {
            if (object instanceof UpdateableFragment) {
                    ((UpdateableFragment) object).update(ab.getTime());
                ab.add(Calendar.DATE,1);
            }
        }
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

public void a(Date date)
{
dates.add(date);
}
}


