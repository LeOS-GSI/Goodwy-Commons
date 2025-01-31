package com.goodwy.commons.dialogs

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.goodwy.commons.R
import com.goodwy.commons.adapters.setupSimpleListItem
import com.goodwy.commons.fragments.BaseBottomSheetDialogFragment
import com.goodwy.commons.models.SimpleListItem

open class BottomSheetChooserDialog(collection: Boolean = false) : BaseBottomSheetDialogFragment() {

    val collection = collection
    var onItemClick: ((SimpleListItem) -> Unit)? = null

    override fun setupContentView(parent: ViewGroup) {
        val listItems = arguments?.getParcelableArray(ITEMS) as Array<SimpleListItem>
        listItems.forEach { item ->
            val view = if (collection) layoutInflater.inflate(R.layout.item_collection_list, parent, false)
                        else layoutInflater.inflate(R.layout.item_simple_list, parent, false)
            setupSimpleListItem(view, item, collection) {
                onItemClick?.invoke(it)
            }
            parent.addView(view)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        onItemClick = null
    }

    companion object {
        private const val TAG = "BottomSheetChooserDialog"
        private const val ITEMS = "data"

        fun createChooser(
            fragmentManager: FragmentManager,
            title: Int?,
            items: Array<SimpleListItem>,
            collection: Boolean = false,
            callback: (SimpleListItem) -> Unit
        ): BottomSheetChooserDialog {
            val extras = Bundle().apply {
                if (title != null) {
                    putInt(BOTTOM_SHEET_TITLE, title)
                }
                putParcelableArray(ITEMS, items)
            }
            return BottomSheetChooserDialog(collection).apply {
                arguments = extras
                onItemClick = callback
                show(fragmentManager, TAG)
            }
        }
    }
}
