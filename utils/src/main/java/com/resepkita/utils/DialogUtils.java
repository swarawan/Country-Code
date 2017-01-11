package com.resepkita.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rioswarawan on 12/23/16.
 */

public class DialogUtils {

    private static final String TAG = "DialogUtils";
    private static AlertDialog dialog;

    /**
     * This method will return if dialog is shown.
     */
    public static boolean isShow() {
        return dialog != null && dialog.isShowing();
    }

    /**
     * This method will dismiss shown dialog
     */
    public static void dismiss() {
        if (isShow()) dialog.dismiss();
    }

    /**
     * This method is supposed to be called by other DialogUtils methods. <br />
     * Floating dialog. 1 OK button without result callback. Ignore max message limit.
     */
    public static void dialog(Context ctx, String title, String message, int iconResId) {
        if (message == null)
            message = "";

        if (isShow())
            Log.w(TAG, ctx.getString(R.string.duplicate_dialog));

        dialog = new AlertDialog.Builder(ctx).setTitle(title).setNeutralButton("OK", null).setIcon(iconResId)
                .setMessage(message).show();
    }

    /**
     * This method is supposed to be called by other DialogUtils methods. <br />
     * Floating dialog. 1 OK button without result callback.
     *
     * @param maxChar Limit maximum characaters to prevent dialog become too big, sometimes server
     *                send a very long response message in case of error.
     */

    public static void dialog(Context ctx, String title, String message, int maxChar, int iconResId) {
        if (message == null)
            message = "";

        if (isShow())
            Log.w(TAG, ctx.getString(R.string.duplicate_dialog));

        dialog = new AlertDialog.Builder(ctx).setTitle(title).setNeutralButton("OK", null).setIcon(iconResId)
                .setMessage(message.substring(0, Math.min(message.length(), maxChar))).show();
    }

    /**
     * This method is supposed to be called by other DialogUtils methods.
     * Floating dialog without title. 1 OK button without result callback.
     * Limit max char to prevent dialog become too big.
     */
    public static void dialog(Context ctx, String message, int maxChar) {
        if (message == null)
            message = "";

        if (isShow())
            Log.w(TAG, ctx.getString(R.string.duplicate_dialog));

        dialog = new AlertDialog.Builder(ctx).setNeutralButton("OK", null)
                .setMessage(message.substring(0, Math.min(message.length(), maxChar))).show();
    }

    /**
     * Floating dialog, with option to auto close activity. 1 OK button without result callback.
     * Limit max char to prevent dialog become too big.
     */
    public static void dialog(final Activity activity, String title, String message, int maxChar,
                              int iconResId, boolean autoFinishActivity) {
        if (message == null)
            message = "";

        if (isShow())
            Log.w(TAG, activity.getString(R.string.duplicate_dialog));

        dialog = new AlertDialog.Builder(activity).setTitle(title).setNeutralButton("OK", null).setIcon(iconResId)
                .setMessage(message.substring(0, Math.min(message.length(), maxChar))).create();

        if (autoFinishActivity) {
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    activity.finish();
                }
            });
        }
        dialog.show();
    }

    /**
     * Floating dialog without button and title. Limit max char to prevent dialog become too big.
     */
    public static void dialogNoBtn(Context ctx, String message, int maxChar) {
        if (message == null)
            message = "";

        if (isShow())
            Log.w(TAG, ctx.getString(R.string.duplicate_dialog));

        dialog = new AlertDialog.Builder(ctx)
                .setMessage(message.substring(0, Math.min(message.length(), maxChar)))
                .setCancelable(false)
                .show();
    }

    /**
     * Floating dialog without button and title.
     */
    public static void dialogNoBtn(Context ctx, String message) {
        if (message == null)
            message = "";

        if (isShow())
            Log.w(TAG, ctx.getString(R.string.duplicate_dialog));

        dialog = new AlertDialog.Builder(ctx)
                .setMessage(message)
                .setCancelable(false)
                .show();
    }

    /**
     * Floating informational dialog. 1 OK button without result callback.
     */
    public static void msg(Context ctx, String title, String message, int maxChar) {
        dialog(ctx, title, message, maxChar, R.drawable.ic_check_circle_black_36dp);
    }

    /**
     * Floating informational dialog without title. 1 OK button without result callback.
     */
    public static void msg(Context ctx, String message, int maxChar) {
        dialog(ctx, message, maxChar);
    }

    /**
     * Floating alert dialog. 1 OK button without result callback.
     */
    public static void alert(Context ctx, String title, String message, int maxChar) {
        dialog(ctx, title, message, maxChar, android.R.drawable.ic_dialog_alert);
    }

    /**
     * Helper to create default floating fail dialog. 1 OK button without result callback. <br />
     * <br />
     * NOTE: Bad practice, but this is higher-ups decision.
     */
    public static void alertFail(Context context, String title, String message) {
        Log.d("FailAlert", title + " - " + message);
        dialog(context, "", context.getString(R.string.connection_fail), 255, android.R.drawable.ic_dialog_alert);
    }

    /**
     * Toast message that only display message up to n characters
     */
    public static void toast(Context ctx, String text, int maxChar) {
        Toast.makeText(ctx, text.substring(0, Math.min(text.length(), maxChar)), Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast message with default maxChar=200
     */
    public static void toast(Context ctx, String text) {
        toast(ctx, text, 200);
    }

    /**
     * Snackbar from top, or from bottom on older device. <br />
     * Long text is automatically truncated by snackbar itself. If both button text and action are
     * null, default action will be Dismiss. <br />
     *
     * @param root            Caller don't need to check for null root, we check here instead,
     *                        if root is null then we'll return immediately.
     * @param text            If message is null then we'll set as empty message.
     * @param buttonText      optional.
     * @param onButtonClicked optional.
     */
    public static void snack(@Nullable View root,
                             @ColorInt int bgColor,
                             @Nullable String text,
                             @ColorInt int textColor,
                             @Nullable String buttonText,
                             @Nullable View.OnClickListener onButtonClicked) {
        if (root == null)
            return;
        if (text == null)
            text = "";

        showStandardSnackbar(root, bgColor, text, textColor, buttonText, onButtonClicked);
    }

    private static void showStandardSnackbar(@NonNull View root,
                                             @ColorInt int bgColor,
                                             @NonNull String text,
                                             @ColorInt int textColor,
                                             @Nullable String buttonText,
                                             @Nullable View.OnClickListener onButtonClicked) {
        final Snackbar sb = Snackbar.make(root, text, Snackbar.LENGTH_LONG);
        sb.getView().setBackgroundColor(bgColor);
        sb.setActionTextColor(textColor);

        TextView tsbText = Convert.as(TextView.class,
                sb.getView().findViewById(android.support.design.R.id.snackbar_text));

        if (tsbText != null) {
            tsbText.setTextColor(Color.WHITE);
            tsbText.setMaxLines(5);
        }

        if (buttonText != null && onButtonClicked != null)
            sb.setAction(buttonText, onButtonClicked);
        else if (buttonText == null && onButtonClicked == null) {
            sb.setAction("Tutup", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sb.dismiss();
                }
            });
        }
        sb.show();
    }

    /**
     * Helper to create orange snackbar with white text, Dismiss action, and auto hide soft keyboard
     * so it won't overlap/hide the snackbar when appears at bottom.
     */
    public static void snack(@NonNull Context context, @Nullable View root, @Nullable String text) {
        SysUtils.hideSoftKeyboard(context, root);
        snack(root, ContextCompat.getColor(context, R.color.snackbar_error), text, Color.WHITE, null, null);
    }

    /**
     * Helper to create green snackbar with white text, Dismiss action, and auto hide soft keyboard
     * so it won't overlap/hide the snackbar when appears at bottom.
     */
    public static void snackOK(@NonNull Context context, @Nullable View root, @Nullable String text) {
        SysUtils.hideSoftKeyboard(context, root);
        snack(root, ContextCompat.getColor(context, R.color.snackbar_ok), text, Color.WHITE, null, null);
    }

    /**
     * Helper to create default failure message. Mostly used for main tab background http call.
     * Can also be used by progress http response. Auto hide soft keyboard. <br />
     * <br />
     * NOTE: Text message sent to this snackbar will be written to log, not displayed.
     * This is bad practice, but it's upper management request.
     */
    public static void snackFail(@NonNull Context context, @Nullable View root, @Nullable String text) {
        Log.d("snackFail", text);
        snack(context, root, context.getString(R.string.connection_fail));
    }

}
