package ${PACKAGE_NAME}

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ${NAME}: ListAdapter<${ITEM_MODEL_NAME}, ${VIEW_HOLDER_NAME}>(${DIFF_UTIL_OBJECT_NAME}) {

    var onItemClicked: ((${ITEM_MODEL_NAME}) -> Unit) ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ${VIEW_HOLDER_NAME} {
        return ${VIEW_HOLDER_NAME}.from(parent)
    }

    override fun onBindViewHolder(holder: ${VIEW_HOLDER_NAME}, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClicked)
    }
}

class ${VIEW_HOLDER_NAME} private constructor(private val binding: ${BINDING_FILE_NAME}): RecyclerView.ViewHolder(binding.root) {

    fun bind(${ITEM_MODEL_NAME}: ${ITEM_MODEL_NAME}, clickListener: ((${ITEM_MODEL_NAME}) -> Unit)?) {
        binding.${BINDING_VARIABLE_NAME} = ${ITEM_MODEL_NAME}
        binding.root.setOnClickListener {
            clickListener?.invoke(${ITEM_MODEL_NAME})
        }
    }

    companion object {
        fun from(parent: ViewGroup): ${VIEW_HOLDER_NAME} {
            val layoutInflater = LayoutInflater.from(parent.context)
            return ${VIEW_HOLDER_NAME}(
                ${BINDING_FILE_NAME}.inflate(layoutInflater, parent, false)
            )
        }
    }
}

object ${DIFF_UTIL_OBJECT_NAME}: DiffUtil.ItemCallback<${ITEM_MODEL_NAME}>() {
    override fun areItemsTheSame(oldItem: ${ITEM_MODEL_NAME}, newItem: ${ITEM_MODEL_NAME}): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: ${ITEM_MODEL_NAME}, newItem: ${ITEM_MODEL_NAME}): Boolean {
        return newItem == oldItem
    }
}