package ivan.fernandez.marvel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ivan.fernandez.marvel.CellClickListener
import ivan.fernandez.marvel.R
import ivan.fernandez.marvel.objetos.PersomajeMarvel
import java.util.*

class AdapterListaPersonajes(private val repoList: ArrayList<PersomajeMarvel>,
                             private val cellClickListener: CellClickListener) : RecyclerView.Adapter<AdapterListaPersonajes.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name = view.findViewById(R.id.name) as TextView
        val foto = view.findViewById(R.id.foto) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.personaje_lista,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = repoList.get(position)
        holder.name.text = data.name
        Glide.with(holder.foto.context)
                .load(data.foto)
                .into(holder.foto)
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data)
        }
        AnimationUtils.loadAnimation(holder.itemView.context, R.anim.animation).also { hyperspaceJumpAnimation ->
            holder.itemView.startAnimation(hyperspaceJumpAnimation)
        }
    }

    override fun getItemCount() = repoList.size
}