/**
 *
 */
package ivan.fernandez.marvel.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import ivan.fernandez.marvel.Api
import ivan.fernandez.marvel.R
import ivan.fernandez.marvel.adapters.PageAdapterDetalles
import ivan.fernandez.marvel.fragments.*

class DatallesActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.detalles)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<NestedScrollView>(R.id.nestedscrollview).isFillViewport=true;
        Thread {
            val id = intent.extras!!.getString("id")
            if(id!=null) {
                var personajes = Api().obtenerPersonaje(id)
                runOnUiThread(java.lang.Runnable {
                    findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = personajes?.name
                    Glide.with(this)
                        .load(personajes?.foto)
                        .into(findViewById(R.id.foto))
                })
            }
        }.start()
        var tabLayout = findViewById<TabLayout>(R.id.secciones)
        var mPager: ViewPager
        mPager = findViewById(R.id.pager)
        val pagerAdapter = PageAdapterDetalles(supportFragmentManager)
        var firstFragmet: DetallesFragment_detalles = DetallesFragment_detalles.newInstance(intent.extras!!.getString("id"))
        var comics: DetallesFragment_comics = DetallesFragment_comics.newInstance(intent.extras!!.getString("id"))
        var series: DetallesFragment_series = DetallesFragment_series.newInstance(intent.extras!!.getString("id"))
        var events: DetallesFragment_events = DetallesFragment_events.newInstance(intent.extras!!.getString("id"))
        pagerAdapter.addFragment(firstFragmet, "Detalles")
        pagerAdapter.addFragment(comics, "Comics")
        pagerAdapter.addFragment(series, "Series")
        pagerAdapter.addFragment(events, "Events")

        mPager!!.adapter = pagerAdapter
        tabLayout!!.setupWithViewPager(mPager)
    }
}