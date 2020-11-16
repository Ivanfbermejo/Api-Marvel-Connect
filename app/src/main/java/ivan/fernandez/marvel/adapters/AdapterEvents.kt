package ivan.fernandez.marvel.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ivan.fernandez.marvel.R
import ivan.fernandez.marvel.objetos.Event
import ivan.fernandez.marvel.objetos.Serie

class AdapterEvents(items: ArrayList<Event>, ctx: Context) :
        ArrayAdapter<Event>(ctx, R.layout.comic, items) {

    private class ViewHolder {
        var comic_foto: ImageView? = null
        var comic_titulo: TextView? = null
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view

        val viewHolder: ViewHolder

        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.comic, viewGroup, false)

            viewHolder = ViewHolder()
            viewHolder.comic_titulo = view!!.findViewById<View>(R.id.comic_titulo) as TextView
            viewHolder.comic_foto = view.findViewById<View>(R.id.comic_foto) as ImageView
        } else {
            viewHolder = view.tag as ViewHolder
        }

        val comic = getItem(i)
        viewHolder.comic_titulo!!.text = comic!!.title
        Glide.with(viewHolder.comic_foto!!.context)
                .load(comic.foto)
                .into(viewHolder.comic_foto!!)

        view.tag = viewHolder

        return view
    }
}