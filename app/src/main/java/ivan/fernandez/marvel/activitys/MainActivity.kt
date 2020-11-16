package ivan.fernandez.marvel.activitys

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ivan.fernandez.marvel.*
import ivan.fernandez.marvel.adapters.AdapterListaPersonajes
import ivan.fernandez.marvel.fragments.*
import ivan.fernandez.marvel.objetos.PersomajeMarvel

class MainActivity : AppCompatActivity(), CellClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragmet)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_list, ListaFragment())
        fragmentTransaction.commit()
        Thread {
            val salida = Api().obtenerPersonajes()
            runOnUiThread(java.lang.Runnable {
                viewManager = LinearLayoutManager(this)
                viewAdapter = AdapterListaPersonajes(salida, this)

                recyclerView = findViewById<RecyclerView>(R.id.recyclerview).apply {
                    setHasFixedSize(true)
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
                recyclerView.scheduleLayoutAnimation()
            })
        }.start()
    }

    override fun onCellClickListener(data: PersomajeMarvel) {
        val isTablet = resources.getBoolean(R.bool.isTablet)
        if (!isTablet) {
            val intent = Intent(this, DatallesActivity::class.java)
            intent.putExtra("id", data.id.toString())
            startActivity(intent)
        } else {
            supportFragmentManager.fragments.let {
                if (it.isNotEmpty()) {
                    supportFragmentManager.beginTransaction().apply {
                        for (fragment in it) {
                            if(fragment is DetallesFragment_detalles || fragment is DetallesFragment_comics || fragment is DetallesFragment_series || fragment is DetallesFragment_events)
                                remove(fragment)
                        }
                        commit()
                    }
                }
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.fragment_detalles,
                DetallesFragment.newInstance(data.id.toString())
            )
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}