package com.mike.givemewingzz.postsandarchives.core.UI;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mike.givemewingzz.postsandarchives.R;
import com.mike.givemewingzz.postsandarchives.appmodel.Token;
import com.mike.givemewingzz.postsandarchives.core.BaseAuthActivity;
import com.mike.givemewingzz.postsandarchives.utils.EventBusSingleton;
import com.mike.givemewingzz.postsandarchives.utils.image_utils.ImageLoaderUtility;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LandingView extends BaseAuthActivity implements View.OnClickListener {

    public static final String TAG = LandingView.class.getSimpleName();

    @Bind(R.id.userFirstName)
    public TextView userFirstName;

    @Bind(R.id.userBio)
    public TextView userBio;

    @Bind(R.id.userHeadline)
    public TextView userHeadline;

    @Bind(R.id.profileBlur)
    public ImageView profileBlur;

    @Bind(R.id.profileImage)
    public ImageView profilePicture;

    @Bind(R.id.landingPage)
    public ViewGroup viewGroup;

    @Bind(R.id.next)
    public Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        ButterKnife.bind(this);

        viewGroup.setOnClickListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBusSingleton.unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBusSingleton.register(this);
    }

    @Override
    protected void onLoginSucceeded(Token token) {
    }

    @Override
    protected void onTokenLoaded(Token token) {

        ImageLoaderUtility.blurAndLoadImage(token.getUserProfileImage(this), profileBlur);

        Token tempToken = token;
        tempToken.load(this);

        Log.d(TAG, "Auth Token : " + token.getAccessToken());

        String welcomeKey = this.getResources().getString(R.string.welcome_key);
        String welcomedUserHead = welcomeKey.toUpperCase() + " " + tempToken.getUserFullName(this).toUpperCase() + "\u0023";

        userFirstName.setText(welcomedUserHead);

        userBio.setText(tempToken.getUserBio(this));

        String headLine = " " + tempToken.getUserName(this)
                + ",...\n" + "You got " + " \u0023" + "<font color='#0091EA'>"
                + tempToken.getUserMediaCount(this) + "</font>" + " posts and" + " \u0023"
                + "<font color='#0091EA'>" + tempToken.getFollowedBy(this) + "</font>" + " followers.";

        String inject = "<font color='#0091EA'>Hey</font>";

        String finalHeadLine = inject + headLine;

        userHeadline.setText(Html.fromHtml(finalHeadLine));

        ImageLoaderUtility.loadImage(token.getUserProfileImage(this), profilePicture);

    }

    private void showSnackbar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {

            case R.id.next:

//                ActivityOptionsCompat optionsCompat = null;
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(LandingView.this, null);
//                }
//
//                Intent intent = new Intent(LandingView.this, MediaList.class);
//                if (optionsCompat != null) {
//                    startActivity(intent, optionsCompat.toBundle());
//                }

                break;

            default:
                break;

        }

    }

    public void toggleHeight(View... views) {

        for (View current : views) {

            ViewGroup.LayoutParams params = current.getLayoutParams();
            params.height = 100;
            params.width = 50;
            current.setLayoutParams(params);
        }

    }

}
