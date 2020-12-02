package com.example.worktest.ui.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worktest.adapter.Adapter
import com.example.worktest.databinding.FragmentImagesBinding
import com.example.worktest.model.Image
import com.example.worktest.mvp.BaseFragment
import com.example.worktest.util.gone
import com.example.worktest.util.onOverScroll
import com.example.worktest.util.visible
import org.koin.android.ext.android.get

class ImagesFragment : BaseFragment<ImagesView, ImagesPresenter>(), ImagesView {

    private val imageAdapter = Adapter()
    private lateinit var binding: FragmentImagesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImagesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = imageAdapter
            onOverScroll { presenter.loadNextPage() }
        }
        presenter.onViewCreated()
    }

    override fun setData(data: List<Image>) {
        imageAdapter.setData(data)
        imageAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        binding.progressBar.visible()
    }

    override fun hideProgress() {
        binding.progressBar.gone()
    }

    override fun getMvpView(): ImagesView = this

    override fun createPresenter(): ImagesPresenter = get()
}