package com.example.bancoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.bancoapp.com.example.bancoapp.models.Contact

class ContactExpandableListAdapter(
    private val context: Context,
    private val contactos: List<Contact>
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int = contactos.size

    override fun getChildrenCount(groupPosition: Int): Int = 1 // Cada contacto tiene un "detalle"

    override fun getGroup(groupPosition: Int): Contact = contactos[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): Contact = contactos[groupPosition]

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun hasStableIds(): Boolean = true

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_contact_group, parent, false)
        val textNombreContacto = view.findViewById<TextView>(R.id.textNombreContacto)
        val contacto = getGroup(groupPosition)
        textNombreContacto.text = contacto.nombre_contacto
        return view
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_contact_child, parent, false)
        val textNumeroCuenta = view.findViewById<TextView>(R.id.textNumeroCuenta)
        val textAlias = view.findViewById<TextView>(R.id.textAlias)

        val contacto = getChild(groupPosition, childPosition)
        textNumeroCuenta.text = "Cuenta: ${contacto.numero_cuenta}"
        textAlias.text = "Alias: ${contacto.alias ?: "Sin alias"}"
        return view
    }
}
