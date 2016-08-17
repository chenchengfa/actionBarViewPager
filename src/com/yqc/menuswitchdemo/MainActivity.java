package com.yqc.menuswitchdemo;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	private ViewPager viewPager;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		Fragment fragment1 = new FirstFragment();
		Fragment fragment2 = new SecondFragment();
		Fragment fragment3 = new ThirdFragment();
		Fragment[] fragmentArray = new Fragment[] { fragment1, fragment2,
				fragment3 };
		LFFragmentPagerAdapter adapter = new LFFragmentPagerAdapter(
				getSupportFragmentManager(), fragmentArray);

		viewPager.setAdapter(adapter);
		viewPager.setOffscreenPageLimit(3);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
			Log.e("", "onPageSelected=="+arg0);
				actionBar.setSelectedNavigationItem(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Log.e("", "onPageScrolled=="+arg0);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				Log.e("", "onPageScrollStateChanged=="+arg0);
			}
		});

		actionBar = getActionBar();
		// 设置ActionBar 的导航方式: Tab导航
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// actionBar.get

		Tab tab1 = actionBar.newTab().setText("页签1")
				.setIcon(android.R.drawable.ic_menu_agenda)
				.setTabListener(new ActionTabListener(fragment1));

		Tab tab2 = actionBar.newTab().setText("页签2")
				.setIcon(android.R.drawable.ic_menu_agenda)
				.setTabListener(new ActionTabListener(fragment2));

		Tab tab3 = actionBar.newTab().setText("页签3")
				.setIcon(android.R.drawable.ic_menu_agenda)
				.setTabListener(new ActionTabListener(fragment3));

		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.addTab(tab3);

	}

	class ActionTabListener implements ActionBar.TabListener {

		// 声明Fragment

		private Fragment fragment;

		// 通过构造引用对应的Fragment

		public ActionTabListener(Fragment fragment) {
			this.fragment = fragment;
			
		}

		@Override
		public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
			Log.e("", "onTabReselected=="+tab);
		}

		@Override
		public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			// ft.add(android.R.id.content, fragment, null);
			mType = tab.getPosition();
			Log.e("", "onTabSelected---tab.getPosition()--=" + tab.getPosition());
			viewPager.setCurrentItem(tab.getPosition());
			invalidateOptionsMenu();
		}

		@Override
		public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			Log.e("", "onTabUnselected=="+tab);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.style1, menu);
		return true;
	}

	private int mType;

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		Log.e("", "当前mType:" + mType);
		menu.clear();
		MenuInflater inflater = this.getMenuInflater();
		switch (mType) {
		case 0:
			inflater.inflate(R.menu.style1, menu);
			break;

		case 1:
			inflater.inflate(R.menu.style2, menu);
			break;

		case 2:
			inflater.inflate(R.menu.style3, menu);
			break;
		}
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_item1:
			Toast.makeText(this, "点击了第一个按钮", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_item2:
			Toast.makeText(this, "点击了第二个按钮", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_item3:
			Toast.makeText(this, "点击了第三个按钮", Toast.LENGTH_SHORT).show();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
