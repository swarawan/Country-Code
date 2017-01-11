package com.resepkita.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by rioswarawan on 12/19/16.
 */

public class ViewUtils {

    /**
     * Get root view of Activity, from XML layout that set using setContentView()
     */
    public static ViewGroup getRootView(Activity activity) {
        ViewGroup viewGroup = Convert.as(ViewGroup.class, activity.findViewById(android.R.id.content));
        if (viewGroup == null)
            return null;
        return Convert.as(ViewGroup.class, viewGroup.getChildAt(0));
    }

    /**
     * Set view visibility. View.VISIBLE, View.INVISIBLE, or View.GONE
     */
    public static void setVisibility(@NonNull View parent, @IdRes int viewResId, int visibility) {
        View view = parent.findViewById(viewResId);
        if (view != null)
            view.setVisibility(visibility);
    }

    /**
     * Connect a resource id with click listener
     */
    public static void setOnClick(@NonNull View parent, @IdRes int viewResId,
                                  @Nullable View.OnClickListener listener) {
        View view = parent.findViewById(viewResId);
        if (view != null)
            view.setOnClickListener(listener);
    }

    /**
     * Connect an adapterview resource id with item click listener. Commonly
     * used by Grid or List.
     */
    public static void setOnItemClick(@NonNull View parent, @IdRes int viewResId,
                                      @Nullable AdapterView.OnItemClickListener listener) {
        AdapterView<?> view = (AdapterView<?>) parent.findViewById(viewResId);
        if (view != null)
            view.setOnItemClickListener(listener);
    }

    /**
     * Connect an adapterview resource id with item selected listener. Commonly
     * used by Spinner (dropdown list). <br />
     * <br />
     * Be aware that Spinner OnItemSelectedListener event will fire right after
     * being set, so you should ensure all initialization have completed when
     * handling this OnItemSelectedListener event. <br />
     * <br />
     * One possible way to set OnItemSelectedListener event is by using Runnable: <br />
     * <br />
     * <pre>
     * {@code
     *
     * spinner.post(new Runnable() {
     *    public void run() {
     *       ViewUtils.setOnItemSelected(parent, R.id.spinner, onDropDownChanged);
     *    }
     * });
     * }
     * </pre>
     */
    public static void setOnItemSelected(@NonNull View parent, @IdRes int viewResId,
                                         AdapterView.OnItemSelectedListener listener) {
        AdapterView<?> view = (AdapterView<?>) parent.findViewById(viewResId);
        if (view != null)
            view.setOnItemSelectedListener(listener);
    }

    /**
     * Get string from TextView resID.
     */
    public static String getTextView(@NonNull View parent, @IdRes int textViewResId) {
        TextView text = Convert.as(TextView.class, parent.findViewById(textViewResId));
        return (text != null) ? text.getText().toString() : null;
    }

    /**
     * Set text on TextView with specific resID.
     */
    public static void setTextView(@NonNull View parent, @IdRes int textViewResId, CharSequence str) {
        TextView text = Convert.as(TextView.class, parent.findViewById(textViewResId));
        if (text != null)
            text.setText(str);
    }

    /**
     * Set text resID on TextView with specific resID.
     */
    public static void setTextView(@NonNull View parent, @IdRes int textViewResId,
                                   @StringRes int textResID) {
        TextView text = Convert.as(TextView.class, parent.findViewById(textViewResId));
        if (text != null)
            text.setText(textResID);
    }


    /**
     * Set text on TextView with specific resID.
     */
    public static void setTextView(TextView text, CharSequence str) {
        if (text != null)
            text.setText(str);
    }

    /**
     * Set text resID on TextView with specific resID.
     */
    public static void setTextView(TextView text, @StringRes int textResID) {
        if (text != null)
            text.setText(textResID);
    }

    /**
     * Set resID on TextView with specific resID. If resID = 0, TextView will be
     * invisible. Otherwise TextView will be visible.
     */
    public static void setTextViewWithVisibility(@NonNull View parent, @IdRes int textViewResId,
                                                 @StringRes int textResID) {
        TextView text = Convert.as(TextView.class, parent.findViewById(textViewResId));
        if (text != null) {
            if (textResID != 0) {
                text.setText(textResID);
                text.setVisibility(View.VISIBLE);
            } else {
                text.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Set text on TextView with specific resID. If text null, TextView will be
     * invisible. Otherwise TextView will be visible.
     */
    public static void setTextViewWithVisibility(@NonNull View parent, @IdRes int textViewResId,
                                                 CharSequence str) {
        TextView text = Convert.as(TextView.class, parent.findViewById(textViewResId));
        if (text != null) {
            if (str != null) {
                text.setText(str);
                text.setVisibility(View.VISIBLE);
            } else {
                text.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Set text on TextView with specific resID. If text null, TextView will be
     * invisible. Otherwise TextView will be visible.
     */
    public static void setTextViewWithVisibility(TextView text,
                                                 CharSequence str) {
        if (text != null) {
            if (str != null) {
                text.setText(str);
                text.setVisibility(View.VISIBLE);
            } else {
                text.setVisibility(View.GONE);
            }
        }
    }

    public static void setTextColor(@NonNull View parent, @IdRes int textViewResId,
                                    @ColorInt int color) {
        TextView text = Convert.as(TextView.class, parent.findViewById(textViewResId));
        if (text != null)
            text.setTextColor(color);
    }

    public static void setTextUnderline(@NonNull View parent, @IdRes int textViewResId) {
        TextView text = Convert.as(TextView.class, parent.findViewById(textViewResId));
        if (text != null)
            text.setPaintFlags(text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    /**
     * Get string from EditText resID.
     */
    public static String getEditText(@NonNull View parent, @IdRes int viewResId) {
        EditText edit = Convert.as(EditText.class, parent.findViewById(viewResId));
        return getEditText(edit);
    }

    /**
     * Get string from EditText resID.
     */
    public static String getEditText(EditText edit) {
        return (edit != null) ? edit.getText().toString() : null;
    }

    /**
     * Set string on EditText resID.
     */
    public static void setEditText(@NonNull Activity activity, @IdRes int viewResId, @Nullable String str) {
        View activityView = ViewUtils.getRootView(activity);
        if (activityView == null)
            return;
        setEditText(activityView, viewResId, str);
    }

    /**
     * Set string on EditText resID.
     */
    public static void setEditText(@NonNull View parent, @IdRes int viewResId, @Nullable String str) {
        EditText edit = Convert.as(EditText.class, parent.findViewById(viewResId));
        if (edit != null)
            edit.setText(str);
    }

    /**
     * Get selected item from dropdown resID.
     */
    public static Object getDropDown(@NonNull View parent, @IdRes int viewResId) {
        Spinner ddl = Convert.as(Spinner.class, parent.findViewById(viewResId));
        return getDropDown(ddl);
    }

    /**
     * Get selected item from dropdown resID.
     */
    public static Object getDropDown(Spinner ddl) {
        return (ddl != null) ? ddl.getSelectedItem() : null;
    }

    /**
     * Get selected item position from dropdown resID. Return -1 if failed.
     */
    public static int getDropDownIdx(@NonNull View parent, @IdRes int viewResId) {
        Spinner ddl = Convert.as(Spinner.class, parent.findViewById(viewResId));
        return (ddl != null) ? ddl.getSelectedItemPosition() : -1;
    }

    /**
     * Set selected item position on dropdown.
     */
    public static void setDropDown(@NonNull View parent, @IdRes int viewResId, final int position) {
        final Spinner ddl = Convert.as(Spinner.class, parent.findViewById(viewResId));
        if (ddl != null)
            ddl.post(new Runnable() {
                @Override
                public void run() {
                    ddl.setSelection(position);
                }
            });
    }

    /**
     * Get selected RadioButton ID from RadioGroup ID.
     */
    public static int getRadioGroup(@NonNull View parent, @IdRes int radioGroupResId) {
        RadioGroup rg = Convert.as(RadioGroup.class, parent.findViewById(radioGroupResId));
        return (rg != null) ? rg.getCheckedRadioButtonId() : -1;
    }

    /**
     * Get view position relative to other view (origin), in pixels. Result
     * stored in int[2] array, x & y respectively.
     */
    public static void getRelativePos(@NonNull View view, @NonNull View origin,
                                      int scrollYOffset, int[] outPos) {
        if (outPos.length < 2)
            return;

        int[] originLocation = new int[2];
        origin.getLocationInWindow(originLocation);

        int[] viewLocation = new int[2];
        view.getLocationInWindow(viewLocation);

        outPos[0] = viewLocation[0] - originLocation[0];
        outPos[1] = viewLocation[1] + scrollYOffset - originLocation[1];
    }

    /**
     * Animate view expansion.
     */
    public static void animateExpand(@NonNull final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        final Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1.0f ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));

        // seems more stable using this
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                v.startAnimation(a);
            }
        });
    }

    /**
     * Animate view collapse.
     */
    public static void animateCollapse(@NonNull final View v) {
        final int initialHeight = v.getMeasuredHeight();

        final Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1.0f) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = (int) (initialHeight * (1.0f - interpolatedTime));
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));

        // seems more stable using this
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                v.startAnimation(a);
            }
        });
    }

    /**
     * Return integer color code from a specific style attributes. <br />
     * <br />
     * Don't supply Context ctx with getBaseContext(), use Activity.this
     * instead.
     */
    public static int getColorFromAttr(@NonNull Context context, int attrID) {
        int[] attrs = new int[]{attrID};
        TypedArray ta = context.obtainStyledAttributes(attrs);
        int color = ta.getColor(0, 0);
        ta.recycle();
        return color;
    }

    /**
     * Return drawable from a specific style attributes.
     */
    public static int getResourceFromAttr(@NonNull Context context, int attrID) {
        int[] attrs = new int[]{attrID};
        TypedArray ta = context.obtainStyledAttributes(attrs);
        int resourceId = ta.getResourceId(0, 0);
        ta.recycle();
        return resourceId;
    }

    /**
     * Helper method for {@link #fixNumberPassword(EditText)}
     *
     * @param editTextResID resource ID of EditText.
     */
    public static void fixNumberPassword(@NonNull View parent, @IdRes int editTextResID) {
        fixNumberPassword(Convert.as(EditText.class, parent.findViewById(editTextResID)));
    }

    /**
     * Make EditText to accept input type as numbered password, such as PIN
     * input. This target pre-Honeycomb API.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void fixNumberPassword(EditText editText) {
        if (editText == null)
            return;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
            editText.setTypeface(Typeface.MONOSPACE);
        } else {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        }
    }

    /**
     * Helper for {@link #limitInputLength(EditText, int)}
     */
    public static void limitInputLength(@NonNull View parent, @IdRes int viewResId, int maxLength) {
        EditText edit = Convert.as(EditText.class, parent.findViewById(viewResId));
        if (edit != null)
            limitInputLength(edit, maxLength);
    }

    /**
     * Limit maximum number of character can be inputted on EditText.
     */
    public static void limitInputLength(@NonNull EditText editText, int maxLength) {
        InputFilter.LengthFilter lf = new InputFilter.LengthFilter(maxLength);
        InputFilter[] filters = new InputFilter[]{lf};
        editText.setFilters(filters);
    }

    /**
     * Get the view of one item in ListView using known position.
     * http://stackoverflow.com/questions/24811536/
     */
    public static View getListViewItemViewAt(@NonNull ListView listView, int position) {
        int firstPos = listView.getFirstVisiblePosition();
        if (position < 0 || firstPos < 0)
            return null;

        int numOfHeaders = listView.getHeaderViewsCount();
        int viewPosition = (position - firstPos) + numOfHeaders;
        if (viewPosition < 0 || viewPosition >= listView.getChildCount())
            return null;
        else
            return listView.getChildAt(viewPosition);
    }

    /**
     * Update the view of one item in ListView using known position. <br />
     * http://stackoverflow.com/questions/2123083/ <br />
     * NOTE: When header/footer is used, listview.getAdapter will not return original adapter,
     * but a wrapper adapter instead. So supplying original adapter is required.
     */
    public static void updateListViewItemViewAt(@NonNull ListView listView, int position,
                                                @NonNull ListAdapter adapter) {
        View view = getListViewItemViewAt(listView, position);
        if (view != null)
            adapter.getView(position, view, listView);
    }

    /**
     * Update the view of one group in ExpandableListView using known position. <br />
     * NOTE: When header/footer is used, listview.getAdapter will not return original adapter,
     * but a wrapper adapter instead. So supplying original adapter is required.
     */
    public static void updateExpandableListViewItemViewAt(
            @NonNull ExpandableListView xlv, int position, boolean isExpanded,
            @NonNull ExpandableListAdapter adapter) {
        int firstPos = xlv.getFirstVisiblePosition();
        if (position < 0 || firstPos < 0)
            return;

        int numOfHeaders = xlv.getHeaderViewsCount();
        int viewPosition = (position - firstPos) + numOfHeaders;
        if (viewPosition < 0 || viewPosition >= xlv.getChildCount())
            return;

        View view = xlv.getChildAt(viewPosition);
        if (view != null)
            adapter.getGroupView(position, isExpanded, view, xlv);
    }

    /**
     * Set image on ImageView, both using resource ID.
     */
    public static void setImageView(@NonNull View parent, @IdRes int imageViewResId,
                                    @DrawableRes int imageResId) {
        ImageView imageView = Convert.as(ImageView.class, parent.findViewById(imageViewResId));
        if (imageView != null)
            imageView.setImageResource(imageResId);
    }

    /**
     * Set drawable on specific ImageView resource ID.
     */
    public static void setImageView(@NonNull View parent, @IdRes int imageViewResId, Drawable d) {
        ImageView imageView = Convert.as(ImageView.class, parent.findViewById(imageViewResId));
        if (imageView != null)
            imageView.setImageDrawable(d);
    }

    /**
     * Set image on ImageView, both using resource ID.
     */
    public static void setImageView(ImageView imageView,
                                    @DrawableRes int imageResId) {
        if (imageView != null)
            imageView.setImageResource(imageResId);
    }

    /**
     * Set drawable on specific ImageView resource ID.
     */
    public static void setImageView(ImageView imageView, Drawable d) {
        if (imageView != null)
            imageView.setImageDrawable(d);
    }


    private static AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * This is from Yudis/Muyassar/Putri.
     */
    @SuppressLint("NewApi")
    public static int generateViewId() {
        if (Build.VERSION.SDK_INT < 17) {
            for (; ; ) {
                int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF)
                    newValue = 1; // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        } else {
            return View.generateViewId();
        }
    }
}
