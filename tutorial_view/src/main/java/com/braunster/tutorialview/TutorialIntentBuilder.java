package com.braunster.tutorialview;

import android.content.Context;
import android.content.Intent;

/**
 * Created by braunster on 04/12/14.
 */
public class TutorialIntentBuilder {

    // View location, width and height.
    private static final String
            VIEW_TO_SURROUND_X = "view_to_surround_x",
            VIEW_TO_SURROUND_Y = "view_to_surround_y",
            VIEW_TO_SURROUND_WIDTH = "view_to_surround_width",
            VIEW_TO_SURROUND_HEIGHT = "view_to_surround_height";

    private static final String TUTORIAL_INFO_LAYOUT_ID = "tut_info_layout_id";

    private static final String HAS_STATUS_BAR = "has_status_bar";

    private static final String TUTORIAL_BACKGROUND_COLOR = "tut_background_color";

    private static final String TUTORIAL_TEXT = "tutorial_default_text";
    private static final String TUTORIAL_TEXT_TYPEFACE = "tutorial_default_text_typeface";
    private static final String TUTORIAL_TEXT_COLOR = "tutorial_default_text_color";
    private static final String TUTORIAL_TEXT_SIZE = "tutorial_default_text_size";

    private static final String TUTORIAL_ANIMATION = "tutorial_animation";

    private Intent intent;

    public TutorialIntentBuilder(Context context){
        intent = new Intent(context, TutorialActivity.class );
    }

    public TutorialIntentBuilder setX(int x){
        intent.putExtra(VIEW_TO_SURROUND_X, x);
        return this;
    }

    public TutorialIntentBuilder setY(int y){
        intent.putExtra(VIEW_TO_SURROUND_Y, y );
        return this;
    }

    public TutorialIntentBuilder setWidth(int width){
        intent.putExtra(VIEW_TO_SURROUND_WIDTH, width);
        return this;
    }

    public TutorialIntentBuilder setHeight(int height){
        intent.putExtra(VIEW_TO_SURROUND_HEIGHT, height);
        return this;
    }

    public TutorialIntentBuilder hasStatusBar(boolean hasStatusBar){
        intent.putExtra(HAS_STATUS_BAR, hasStatusBar);
        return this;
    }

    public TutorialIntentBuilder setTutorialLayoutId(int tutorialLayoutId){
        intent.putExtra(TUTORIAL_INFO_LAYOUT_ID, tutorialLayoutId);
        return this;
    }

    public TutorialIntentBuilder setBackgroundColor(int color){
        intent.putExtra(TUTORIAL_BACKGROUND_COLOR, color);
        return this;
    }

    public TutorialIntentBuilder setTutorialText(String tutorialText){
        intent.putExtra(TUTORIAL_TEXT, tutorialText);
        return this;
    }

    public TutorialIntentBuilder setTutorialTextTypefaceName(String typefaceName){
        intent.putExtra(TUTORIAL_TEXT_TYPEFACE, typefaceName);
        return this;
    }

    public TutorialIntentBuilder setTutorialTextSize(int textSize){
        intent.putExtra(TUTORIAL_TEXT_SIZE, textSize);
        return this;
    }

    public TutorialIntentBuilder setTutorialTextColor(int color){
        intent.putExtra(TUTORIAL_TEXT_COLOR, color);
        return this;
    }

    public Intent getIntent(){
        return intent;
    }

    public static void showTutorial(TutorialInterface tutorial, Intent intent){
        final int x = intent.getIntExtra(VIEW_TO_SURROUND_X, -1);
        final int y = intent.getIntExtra(VIEW_TO_SURROUND_Y, -1);
        final int width = intent.getIntExtra(VIEW_TO_SURROUND_WIDTH, -1);
        final int height = intent.getIntExtra(VIEW_TO_SURROUND_HEIGHT, -1);

        tutorial.setPositionToSurround(x, y, width, height);
    }

    public static void updateTutorialViewFromIntent(final TutorialInterface tutorial, Intent intent){

        tutorial.setHasStatusBar(intent.getBooleanExtra(HAS_STATUS_BAR, true));

        // Setting the tutorial background color if was given.
        if (intent.getExtras().containsKey(TUTORIAL_BACKGROUND_COLOR))
            tutorial.setTutorialBackgroundColor(intent.getIntExtra(TUTORIAL_BACKGROUND_COLOR, -1));

        final int tutorialLayoutId = intent.getIntExtra(TUTORIAL_INFO_LAYOUT_ID, -1);

        // Setting the custom layout id if isn't -1.
        if (tutorialLayoutId == -1)
            tutorial.setTutorialText(intent.getStringExtra(TUTORIAL_TEXT));

        // Getting the text size, -1 will be ignored by the tutorial view.
        tutorial.setTutorialTextSize(intent.getExtras().getInt(TUTORIAL_TEXT_SIZE, -1));

        // Text color
        if (intent.getExtras().containsKey(TUTORIAL_TEXT_COLOR))
            tutorial.setTutorialTextColor(intent.getExtras().getInt(TUTORIAL_TEXT_COLOR));

        // Text Typeface
        if (intent.getExtras().containsKey(TUTORIAL_TEXT_TYPEFACE))
            tutorial.setTutorialTextTypeFace(intent.getExtras().getString(TUTORIAL_TEXT_TYPEFACE));

        if (intent.getExtras().containsKey(TUTORIAL_ANIMATION))
            tutorial.setAnimationType(AbstractTutorialView.AnimationType.values()[intent.getExtras().getInt(TUTORIAL_ANIMATION)]);

        tutorial.setTutorialInfoLayoutId(tutorialLayoutId);
    }

    public TutorialIntentBuilder setAnimationType(int animationType){
        intent.putExtra(TUTORIAL_ANIMATION, animationType);
        return this;
    }
}
