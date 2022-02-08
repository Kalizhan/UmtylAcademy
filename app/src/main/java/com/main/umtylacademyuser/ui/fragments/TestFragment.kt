package com.main.umtylacademyuser.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.ui.activities.SelectedTestActivity
import com.main.umtylacademyuser.utils.adapters.RecyclerViewAdapter
import com.main.umtylacademyuser.utils.models.TestCard
import kotlinx.android.synthetic.main.fragment_test.view.*

class TestFragment : Fragment() {
    private lateinit var recyclerDataArrayList: ArrayList<TestCard>
    var text = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_test, container, false)

        view.kz_ru_switch.setOnCheckedChangeListener { _, checked ->
            text = if (checked) "kz" else "ru"
        }

        getinitView(view)
        return view
    }

    private fun getinitView(view: View) {

        recyclerDataArrayList = ArrayList()

        recyclerDataArrayList.add(TestCard(resources.getString(R.string.algebra), R.drawable.algebra))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.read_property), R.drawable.read_property))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.geography), R.drawable.geography))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.biology), R.drawable.biology))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.kz_history), R.drawable.kz_history))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.kz_lang), R.drawable.kz_lang))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.chemistry), R.drawable.chemistry))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.eng_lang), R.drawable.eng_lang))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.world_history), R.drawable.world_history))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.ru_lang), R.drawable.ru_lang))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.math), R.drawable.math))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.kz_literature), R.drawable.kz_literature))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.physics), R.drawable.physics))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.geography2), R.drawable.geography2))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.law), R.drawable.law))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.geometry), R.drawable.geometry))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.music), R.drawable.music))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.inform), R.drawable.inform))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.technology), R.drawable.technology))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.oz_oz), R.drawable.oz_oz))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.attestat), R.drawable.attestat))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.kval_test), R.drawable.kval_test))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.metodist_test), R.drawable.metodist_test))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.primary_class), R.drawable.primary_class))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.psychology), R.drawable.psychology))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.logic_questions), R.drawable.logic_questions))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.management), R.drawable.management))
        recyclerDataArrayList.add(TestCard(resources.getString(R.string.belbin_test), R.drawable.belbin_test))

        val layoutManager = GridLayoutManager(requireContext(), 3)
        val adapter = RecyclerViewAdapter(recyclerDataArrayList, requireContext())

        view.idTestCard.layoutManager = layoutManager
        view.idTestCard.adapter = adapter
        adapter.setOnItemClickListener(object : RecyclerViewAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(requireContext(), SelectedTestActivity::class.java)
                intent.putExtra("LessonName", recyclerDataArrayList[position].title)
                intent.putExtra("lang", text)
                startActivity(intent)
            }
        })
    }


}