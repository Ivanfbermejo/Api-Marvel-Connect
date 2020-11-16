package ivan.fernandez.marvel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import ivan.fernandez.marvel.Api
import ivan.fernandez.marvel.R
import ivan.fernandez.marvel.adapters.PageAdapterDetalles

class DetallesFragment : Fragment() {
    companion object {
        fun newInstance(id: String?): DetallesFragment {
            val fragment = DetallesFragment()
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
            R.layout.detalles, container,
            false)
        val activity = activity as AppCompatActivity
        val tabLayout = view.findViewById<TabLayout>(R.id.secciones)
        val mPager = view.findViewById<ViewPager>(R.id.pager)
        view.findViewById<NestedScrollView>(R.id.nestedscrollview).isFillViewport=true;
        Thread {
            val id = arguments?.getString("id")
            if(id!=null) {
                var personajes = Api().obtenerPersonaje(id)
                activity.runOnUiThread(java.lang.Runnable {
                    view.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = personajes?.name
                    Glide.with(this)
                            .load(personajes?.foto)
                            .into(view.findViewById(R.id.foto))
                })
            }
        }.start()
        val pagerAdapter = PageAdapterDetalles(activity.supportFragmentManager)
        val firstFragmet: DetallesFragment_detalles = DetallesFragment_detalles.newInstance(arguments?.getString("id"))
        val comics: DetallesFragment_comics = DetallesFragment_comics.newInstance(arguments?.getString("id"))
        val series: DetallesFragment_series = DetallesFragment_series.newInstance(arguments?.getString("id"))
        val events: DetallesFragment_events = DetallesFragment_events.newInstance(arguments?.getString("id"))
        pagerAdapter.addFragment(firstFragmet, "Detalles")
        pagerAdapter.addFragment(comics, "Comics")
        pagerAdapter.addFragment(series, "Series")
        pagerAdapter.addFragment(events, "Events")
        mPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(mPager)
        return view
    }
}