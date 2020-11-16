package ivan.fernandez.marvel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ivan.fernandez.marvel.Api
import ivan.fernandez.marvel.R
import ivan.fernandez.marvel.adapters.AdapterEvents
import ivan.fernandez.marvel.adapters.AdapterSeries
import ivan.fernandez.marvel.objetos.Comic
import ivan.fernandez.marvel.objetos.Event

class DetallesFragment_events : Fragment() {
    companion object {
        fun newInstance(id: String?): DetallesFragment_events {
            val fragment = DetallesFragment_events()
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
            R.layout.detalles_comics, container,
            false)
        val activity = activity as AppCompatActivity
        val listView = view.findViewById<ListView>(R.id.lista_comics)
        Thread {
            val id = arguments?.getString("id")
            if(id!=null) {
                var events = Api().obtenerEventsPersonaje(id)
                activity.runOnUiThread(java.lang.Runnable {
                    if(events.size>0) {
                        var adapter = AdapterEvents(events, activity)

                        listView.adapter = adapter
                        listView.onItemClickListener = object : AdapterView.OnItemClickListener {

                            override fun onItemClick(parent: AdapterView<*>, view: View,
                                                     position: Int, id: Long) {
                                val itemValue = listView.getItemAtPosition(position) as Event
                                Toast.makeText(activity,
                                        "${itemValue.title}", Toast.LENGTH_LONG)
                                        .show()
                            }
                        }
                    }else{
                        listView.visibility=View.GONE
                        view.findViewById<TextView>(R.id.sin_contenido).visibility=View.VISIBLE
                    }
                })
            }
        }.start()
        return view
    }
}