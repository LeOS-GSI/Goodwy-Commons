package com.goodwy.commons.dialogs

import android.app.Activity
import android.text.Html
import android.text.method.LinkMovementMethod
import com.goodwy.commons.R
import com.goodwy.commons.extensions.getAlertDialogBuilder
import com.goodwy.commons.extensions.launchViewIntent
import com.goodwy.commons.extensions.setupDialogStuff
import kotlinx.android.synthetic.main.dialog_new_apps_icons.view.*

class NewAppsIconsDialog(val activity: Activity) {
    init {
        val view = activity.layoutInflater.inflate(R.layout.dialog_new_apps_icons, null).apply {
            val dialerUrl = "https://play.google.com/store/apps/details?id=com.goodwy.dialer"
            val smsMessengerUrl = "https://play.google.com/store/apps/details?id=com.goodwy.smsmessenger"
            val voiceRecorderUrl = "https://play.google.com/store/apps/details?id=com.goodwy.voicerecorder"

            val text = String.format(
                activity.getString(R.string.new_app),
                dialerUrl, activity.getString(R.string.simple_dialer),
                smsMessengerUrl, activity.getString(R.string.simple_sms_messenger),
                voiceRecorderUrl, activity.getString(R.string.simple_voice_recorder)
            )

            new_apps_text.text = Html.fromHtml(text)
            new_apps_text.movementMethod = LinkMovementMethod.getInstance()

            new_apps_dialer.setOnClickListener { activity.launchViewIntent(dialerUrl) }
            new_apps_sms_messenger.setOnClickListener { activity.launchViewIntent(smsMessengerUrl) }
            new_apps_voice_recorder.setOnClickListener { activity.launchViewIntent(voiceRecorderUrl) }
        }

        activity.getAlertDialogBuilder()
            .setPositiveButton(R.string.ok, null)
            .apply {
                activity.setupDialogStuff(view, this, cancelOnTouchOutside = false)
            }
    }
}
