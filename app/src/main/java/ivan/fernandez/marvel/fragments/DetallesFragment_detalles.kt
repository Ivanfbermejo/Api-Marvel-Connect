package ivan.fernandez.marvel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import ivan.fernandez.marvel.Api
import ivan.fernandez.marvel.R

class DetallesFragment_detalles : Fragment() {
    companion object {
        fun newInstance(id: String?): DetallesFragment_detalles {
            val fragment = DetallesFragment_detalles()
            val args = Bundle()
            args.putString("id", id)
            fragment.setArguments(args)
            return fragment
        }
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(
            R.layout.detalles_tabla, container,
            false)
        val activity = activity as AppCompatActivity
        val id_ = view.findViewById<TextView>(R.id.id)
        val description = view.findViewById<TextView>(R.id.description)
        val foto_texto = view.findViewById<TextView>(R.id.foto_text)
        val modified = view.findViewById<TextView>(R.id.modified)
        val resourceURI = view.findViewById<TextView>(R.id.resourceURI)
        Thread {
            val id = arguments?.getString("id")
            if(id!=null) {
                var personajes = Api().obtenerPersonaje(id)
                activity.runOnUiThread(java.lang.Runnable {
                    id_.text= personajes?.id.toString()
                    description.text=personajes?.description
                    modified.text=personajes?.modified
                    foto_texto.text=personajes?.foto
                    resourceURI.text=personajes?.resourceURI
                })
            }
        }.start()
        return view
    }
}