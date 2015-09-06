package com.example.squall.fragmentsapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.squall.fragmentsapp.fragments.DefTitleFragment;
import com.example.squall.fragmentsapp.fragments.DefinitionFragment;
import com.example.squall.fragmentsapp.fragments.OnListItemSelected;
import com.example.squall.fragmentsapp.fragments.WordFragment;
import com.example.squall.fragmentsapp.fragments.WordTitleFragment;

public class MainActivity extends Activity implements OnListItemSelected {

    public static final String BACK_STACK = "DefinitionApp_Backstack";
    private DefinitionFragment mDefinitionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        /*
            If fragment_container is inside the current view, then must be a Handset
            As the layout-large version does not contain this
         */
        if (findViewById(R.id.fragment_container) != null) {
            Log.d("HANDSET_MODE", "In Handset Mode");
            if (savedInstanceState != null) {
                Log.d("RESTORE_STATE", "Restoring State");
            } else {
                WordTitleFragment titleFragment = new WordTitleFragment();
                WordFragment wordFragment = new WordFragment();

                wordFragment.setArguments(getIntent().getExtras());

                getFragmentManager().beginTransaction().add(R.id.title_container, titleFragment).commit();
                getFragmentManager().beginTransaction().add(R.id.fragment_container, wordFragment).commit();
            }
        }
        mDefinitionFragment = (DefinitionFragment) getFragmentManager().findFragmentById(R.id.definition_fragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ON_DEST", "onDestroy() called");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ON_DEST", "onPause() called");
        if (mDefinitionFragment != null) {
            getFragmentManager().beginTransaction().remove(mDefinitionFragment).commit();
        }
    }

    @Override
    public void itemSelected(int pos) {

        Log.d("ITEM_SEL", "Item Selected");
            if (mDefinitionFragment == null) {
                Log.d("POS_NUM", pos + "");
                setupHandsetDefFragment(pos);
            } else {
                /*
                    If this is called we are in two-pane mode and using a tablet
                    device
                 */
                mDefinitionFragment.updateDefinitionView(pos);
            }
    }

    private void setupHandsetDefFragment(int pos) {
    /*
        TODO Uncomment the block after this and comment out the
        code before the closing brace of this
        block to use multi-activity rather than
        multi-fragment view

        Note no title text for this, but it would be simple to implement
    */

//                    Intent intent = new Intent(this, DisplayActivity.class);
//                    intent.putExtra(DefinitionFragment.LAST_POSITION, pos);
//                    startActivity(intent);

    //Comment From here to end of method for Multi-Activity mode
        DefinitionFragment newDefFragment = new DefinitionFragment();
        DefTitleFragment newTitleFragment = new DefTitleFragment();

          /*
            Create a Bundle and put the current position into it
            to be used by the onStart()/updateDefinitionView method in DefinitionFragment
         */
        Bundle bDef = new Bundle();
        bDef.putInt(DefinitionFragment.LAST_POSITION, pos);
        newDefFragment.setArguments(bDef);

        /*
            Replace the fragment_container with the DefinitionFragment
            Add this transaction to the backstack to allow navigation back to
            the WordFragment view.
         */
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, newDefFragment)
                .replace(R.id.title_container, newTitleFragment).addToBackStack(BACK_STACK).commit();
    }

    /*
        Unused
        Does not consider action bar
    */
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
}
